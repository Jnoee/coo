package coo.core.pay.ali;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import coo.base.exception.UncheckedException;

/**
 * 支付宝配置。
 */
public class AliPayConfig {
  private String apiUrl = "https://mapi.alipay.com/gateway.do";
  private String privateKeyFilePath;
  private String publicKeyFilePath;
  private String privateKey;
  private String publicKey;
  private String partner;
  private String notifyUrl;

  /**
   * 初始化。
   */
  @PostConstruct
  public void init() {
    privateKey = readFile(privateKeyFilePath);
    publicKey = readFile(publicKeyFilePath);
  }

  /**
   * 读取密钥文件。
   * 
   * @param filePath 文件路径
   * @return 返回密钥文件内容。
   */
  private String readFile(String filePath) {
    try {
      return new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (Exception e) {
      throw new UncheckedException("读取证书文件时发生异常。", e);
    }
  }

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public String getPrivateKeyFilePath() {
    return privateKeyFilePath;
  }

  public void setPrivateKeyFilePath(String privateKeyFilePath) {
    this.privateKeyFilePath = privateKeyFilePath;
  }

  public String getPublicKeyFilePath() {
    return publicKeyFilePath;
  }

  public void setPublicKeyFilePath(String publicKeyFilePath) {
    this.publicKeyFilePath = publicKeyFilePath;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}
