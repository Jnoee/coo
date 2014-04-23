package coo.core.security.permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

import coo.base.exception.UncheckedException;
import coo.base.model.BitCode;
import coo.base.util.CollectionUtils;
import coo.core.util.SpringUtils;

/**
 * 权限配置管理组件。
 */
@Component
public class PermissionConfig {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String PERMISSION_DIR = "/META-INF/coo/";
	private static final String PERMISSION_PATH = "classpath*:"
			+ PERMISSION_DIR + "*permissions.xml";
	private Permissions permissions = new Permissions();
	private List<Permission> allPermissions = new ArrayList<Permission>();

	/**
	 * 初始化权限配置管理组件。
	 */
	@PostConstruct
	public void init() {
		try {
			initPermissions();
			initAllPermissions();
			log.info("加载权限配置文件成功。");
		} catch (Exception e) {
			throw new UncheckedException("加载权限配置文件时发生异常。", e);
		}
	}

	/**
	 * 初始化加载权限配置文件。
	 * 
	 * @throws IOException
	 *             加载权限配置文件失败时抛出该异常。
	 */
	private void initPermissions() throws IOException {
		List<Resource> permissionResources = SpringUtils
				.getResourcesByWildcard(PERMISSION_PATH);
		XStream xstream = new XStream();
		xstream.processAnnotations(new Class[] { Permissions.class,
				PermissionGroup.class, Permission.class });
		for (Resource permissionResource : permissionResources) {
			log.debug("加载权限配置文件[{}]...", permissionResource.getURI().toString());
			permissions.merge((Permissions) xstream.fromXML(permissionResource
					.getInputStream()));
		}
	}

	/**
	 * 初始化所有权限列表。
	 */
	private void initAllPermissions() {
		for (PermissionGroup permissionGroup : getPermissionGroups()) {
			for (Permission permission : permissionGroup.getPermissions()) {
				if (allPermissions.contains(permission)) {
					throw new UncheckedException("权限[" + permission + "]定义重复。");
				}
				allPermissions.add(permission);
			}
		}
	}

	/**
	 * 获取所有权限分组。
	 * 
	 * @return 返回所有权限分组。
	 */
	public List<PermissionGroup> getPermissionGroups() {
		return permissions.getPermissionGroups();
	}

	/**
	 * 根据权限编码生成权限分组列表，即指定的权限编码中不包含的权限将不被列入权限分组中。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回生成的权限分组列表。
	 */
	public List<PermissionGroup> generatePermissionGroups(BitCode permissionCode) {
		List<PermissionGroup> permissionGroups = newPermissionGroupList();
		for (Permission permission : getPermissions(permissionCode)) {
			addPermissionToGroup(permissionGroups, permission);
		}
		cleanEmptyPermissionGroup(permissionGroups);
		return permissionGroups;
	}

	/**
	 * 获取指定权限编码的权限。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回指定权限编码的权限。
	 */
	public Permission getPermission(String permissionCode) {
		for (Permission permission : allPermissions) {
			if (permission.getCode().equals(permissionCode)) {
				return permission;
			}
		}
		throw new UncheckedException("未找到权限编码为" + permissionCode + "的权限。");
	}

	/**
	 * 获取指定权限编码的权限集合。
	 * 
	 * @param permissionCodes
	 *            权限编码集合
	 * @return 返回指定权限编码的权限集合。
	 */
	public List<Permission> getPermissions(String[] permissionCodes) {
		List<Permission> permissions = new ArrayList<Permission>();
		if (!CollectionUtils.isEmpty(permissionCodes)) {
			for (String permissionCode : permissionCodes) {
				permissions.add(getPermission(permissionCode));
			}
		}
		return permissions;
	}

	/**
	 * 获取指定权限编码的权限ID集合。
	 * 
	 * @param permissionCodes
	 *            权限编码集合
	 * @return 返回指定权限编码的权限ID集合。
	 */
	public List<Integer> getPermissionIds(String[] permissionCodes) {
		List<Integer> permissionIds = new ArrayList<Integer>();
		for (Permission permission : getPermissions(permissionCodes)) {
			permissionIds.add(permission.getId());
		}
		return permissionIds;
	}

	/**
	 * 根据权限编码获取权限列表。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回权限编码对应的权限列表。
	 */
	public List<Permission> getPermissions(BitCode permissionCode) {
		List<Permission> resultPermissions = new ArrayList<Permission>();
		for (Permission permission : allPermissions) {
			if (permissionCode.isTrue(permission.getId())) {
				resultPermissions.add(permission);
			}
		}
		return resultPermissions;
	}

	/**
	 * 根据权限编码获取权限ID列表。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回权限编码对应的权限ID列表。
	 */
	public List<Integer> getPermissionIds(BitCode permissionCode) {
		List<Integer> permissionIds = new ArrayList<Integer>();
		for (Permission permission : getPermissions(permissionCode)) {
			permissionIds.add(permission.getId());
		}
		return permissionIds;
	}

	/**
	 * 根据权限编码获取权限代码列表。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回权限编码对应的权限代码列表。
	 */
	public List<String> getPermissionCodes(BitCode permissionCode) {
		List<String> permissionCodes = new ArrayList<String>();
		for (Permission permission : getPermissions(permissionCode)) {
			permissionCodes.add(permission.getCode());
		}
		return permissionCodes;
	}

	/**
	 * 根据权限ID列表获取权限编码。
	 * 
	 * @param permissionIds
	 *            权限ID列表
	 * @return 返回权限ID列表对应的权限编码。
	 */
	public BitCode getPermissionCode(List<Integer> permissionIds) {
		BitCode permissionCode = new BitCode();
		for (Integer permissionId : permissionIds) {
			permissionCode.setValue(permissionId, true);
		}
		return permissionCode;
	}

	/**
	 * 根据权限ID列表获取指定长度的权限编码。
	 * 
	 * @param permissionIds
	 *            权限ID列表
	 * @param bitCodeLength
	 *            权限编码长度
	 * @return 返回权限ID列表对应的权限编码。
	 */
	public BitCode getPermissionCode(List<Integer> permissionIds,
			Integer bitCodeLength) {
		BitCode permissionCode = new BitCode(bitCodeLength);
		for (Integer permissionId : permissionIds) {
			permissionCode.setValue(permissionId, true);
		}
		return permissionCode;
	}

	/**
	 * 从权限分组列表中清除权限集合为空的权限分组。
	 * 
	 * @param permissionGroups
	 *            权限分组列表
	 */
	private void cleanEmptyPermissionGroup(
			List<PermissionGroup> permissionGroups) {
		Iterator<PermissionGroup> permissionGroupIterator = permissionGroups
				.iterator();
		while (permissionGroupIterator.hasNext()) {
			if (permissionGroupIterator.next().getPermissions().isEmpty()) {
				permissionGroupIterator.remove();
			}
		}
	}

	/**
	 * 根据现有权限分组重新构建一个权限分组列表。
	 * 
	 * @return 返回权限分组列表。
	 */
	private List<PermissionGroup> newPermissionGroupList() {
		List<PermissionGroup> permissionGroups = new ArrayList<PermissionGroup>();
		for (PermissionGroup permissionGroup : getPermissionGroups()) {
			PermissionGroup newPermissionGroup = new PermissionGroup();
			newPermissionGroup.setName(permissionGroup.getName());
			permissionGroups.add(newPermissionGroup);
		}
		return permissionGroups;
	}

	/**
	 * 将一个现有的权限加入到新构建的权限分组列表对应的权限分组中。
	 * 
	 * @param newPermissionGroups
	 *            新构建的权限分组列表
	 * @param permission
	 *            权限
	 */
	private void addPermissionToGroup(
			List<PermissionGroup> newPermissionGroups, Permission permission) {
		String permissionGroupName = findGroupByPermission(permission)
				.getName();
		for (PermissionGroup newPermissionGroup : newPermissionGroups) {
			if (newPermissionGroup.getName().equals(permissionGroupName)) {
				newPermissionGroup.getPermissions().add(permission);
			}
		}
	}

	/**
	 * 根据权限查找其所在的权限分组。
	 * 
	 * @param permissionToFind
	 *            权限
	 * @return 返回权限所在的权限分组。
	 */
	private PermissionGroup findGroupByPermission(Permission permissionToFind) {
		for (PermissionGroup permissionGroup : getPermissionGroups()) {
			if (permissionGroup.getPermissions().contains(permissionToFind)) {
				return permissionGroup;
			}
		}
		throw new UncheckedException("在所有权限分组中未找到权限编码为"
				+ permissionToFind.getCode() + "的权限。");
	}

	/**
	 * 获取PermissionConfig组件实例。
	 * 
	 * @return 返回PermissionConfig组件实例。
	 */
	public static PermissionConfig instance() {
		return (PermissionConfig) SpringUtils.getBean("permissionConfig");
	}
}
