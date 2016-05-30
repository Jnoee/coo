package coo.core.security.service;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import coo.base.model.BitCode;
import coo.core.enums.EnabledStatus;
import coo.core.hibernate.dao.Dao;
import coo.core.security.entity.UserEntity;
import coo.core.security.permission.PermissionConfig;

/**
 * 登录组件。
 */
public class LoginRealm extends AuthorizingRealm {
  @Resource
  private Dao<? extends UserEntity<?, ?>> userDao;
  @Resource
  private PermissionConfig permissionConfig;
  private String hashAlgorithmName = Sha256Hash.ALGORITHM_NAME;
  private String salt = Sha256Hash.ALGORITHM_NAME;

  /**
   * 清理当前用户缓存。
   */
  public void clearCache() {
    clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
  }

  /**
   * 检查密码是否正确。
   * 
   * @param password 原密码
   * @param hashedPassword 加密后的密码
   * @return 如果密码正确返回true，否则返回false。
   */
  public Boolean checkPassword(String password, String hashedPassword) {
    return encryptPassword(password).equals(hashedPassword);
  }

  /**
   * 加密。
   * 
   * @param password 待加密的密码
   * @return 返回加密后的密码。
   */
  public String encryptPassword(String password) {
    return new SimpleHash(hashAlgorithmName, password, getSaltByteSource()).toBase64();
  }

  @Override
  protected void onInit() {
    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(hashAlgorithmName);
    credentialsMatcher.setStoredCredentialsHexEncoded(false);
    setCredentialsMatcher(credentialsMatcher);
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    String username = upToken.getUsername();

    UserEntity<?, ?> user = userDao.findUnique("username", username);
    if (user == null) {
      throw new UnknownAccountException();
    }
    if (user.getEnabled() == EnabledStatus.DISABLED
        || user.getDefaultActor().getOrgan().getEnabled() == EnabledStatus.DISABLED) {
      throw new DisabledAccountException();
    }

    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getId(), user.getPassword(),
        getSaltByteSource(), getName());
    return info;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String userId = (String) getAvailablePrincipal(principals);
    UserEntity<?, ?> user = userDao.get(userId);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    BitCode permissionCode = user.getDefaultActor().getRole().getPermissions();
    info.addRoles(permissionConfig.getPermissionCodes(permissionCode));
    return info;
  }

  private ByteSource getSaltByteSource() {
    return ByteSource.Util.bytes(salt);
  }

  public String getHashAlgorithmName() {
    return hashAlgorithmName;
  }

  public void setHashAlgorithmName(String hashAlgorithmName) {
    this.hashAlgorithmName = hashAlgorithmName;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
