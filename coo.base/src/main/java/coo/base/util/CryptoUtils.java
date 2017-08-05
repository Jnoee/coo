package coo.base.util;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import coo.base.constants.Algorithm;
import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;

/**
 * 加解密工具类。
 */
public class CryptoUtils {
  public static final String NUMBER = "0123456789";
  public static final String CHAR = "abcdefghijklmnopqrstuvwxyz";
  public static final String ALL = "0123456789abcdefghijklmnopqrstuvwxyz";

  /**
   * 生成随机字符串。
   * 
   * @param src 源字符串（随机字符从源字符串中取）
   * @param length 生成字符串长度
   * @return 返回生成的随机字符串。
   */
  public static String genRandomCode(String src, Integer length) {
    char[] chars = src.toCharArray();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      double randomValue = Math.random();
      int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
      char characterToShow = chars[randomIndex];
      builder.append(characterToShow);
    }
    return builder.toString();
  }

  /**
   * Base64编码。
   * 
   * @param content 待编码内容
   * @return 返回编码后的内容。
   */
  public static String encodeBase64(String content) {
    try {
      return new String(encodeBase64(content.getBytes(Encoding.UTF_8)));
    } catch (UnsupportedEncodingException e) {
      throw new UncheckedException("Base64编码时发生异常。", e);
    }
  }

  /**
   * Base64解码。
   * 
   * @param content 待解码内容
   * @return 返回解码后的内容。
   */
  public static String decodeBase64(String content) {
    try {
      return new String(decodeBase64(content.getBytes(Encoding.UTF_8)));
    } catch (UnsupportedEncodingException e) {
      throw new UncheckedException("Base64解码时发生异常。", e);
    }
  }

  /**
   * Base64编码。
   * 
   * @param contentBytes 待编码内容字节数组
   * @return 返回编码后的内容字节数组。
   */
  public static byte[] encodeBase64(byte[] contentBytes) {
    return Base64.encodeBase64(contentBytes);
  }

  /**
   * Base64解码。
   * 
   * @param contentBytes 待解码内容字节数组
   * @return 返回解码后的内容字节数组。
   */
  public static byte[] decodeBase64(byte[] contentBytes) {
    return Base64.decodeBase64(contentBytes);
  }

  /**
   * 符合RFC 1321标准的MD5编码。
   * 
   * @param content 待编码的内容
   * @return 返回编码后的内容。
   */
  public static String md5(String content) {
    return DigestUtils.md5Hex(content);
  }

  /**
   * AES加密。
   * 
   * @param content 待加密的内容
   * @param password 密码
   * @return 返回加密后的内容。
   */
  public static String aesEncrypt(String content, String password) {
    try {
      byte[] raw = password.getBytes(Encoding.UTF_8);
      SecretKeySpec key = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encrypted = cipher.doFinal(content.getBytes(Encoding.UTF_8));
      Base64 encoder = new Base64();
      return encoder.encodeToString(encrypted);
    } catch (Exception e) {
      throw new UncheckedException("AES加密时发生异常。", e);
    }
  }

  /**
   * AES解密。
   * 
   * @param content 待解密的内容
   * @param password 密码
   * @return 返回解密后的内容。
   */
  public static String aesDecrypt(String content, String password) {
    try {
      byte[] raw = password.getBytes(Encoding.UTF_8);
      SecretKeySpec key = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] encrypted = new Base64().decode(content);
      byte[] original = cipher.doFinal(encrypted);
      return new String(original, Encoding.UTF_8);
    } catch (Exception e) {
      throw new UncheckedException("AES解密时发生异常。", e);
    }
  }

  /**
   * 生成密钥对。
   * 
   * @return 返回生成的密钥对。
   */
  public static KeyPair genKeyPair() {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
      keyGen.initialize(1024);
      return keyGen.genKeyPair();
    } catch (Exception e) {
      throw new UncheckedException("生成密钥对时发生异常", e);
    }
  }

  /**
   * 从密钥对中获取Base64编码的公钥字符串。
   * 
   * @param keyPair 密钥对
   * @return 返回Base64编码后的公钥字符串。
   */
  public static String getPublicKey(KeyPair keyPair) {
    return new String(encodeBase64(keyPair.getPublic().getEncoded()));
  }

  /**
   * 从密钥对中获取Base64编码的私钥字符串。
   * 
   * @param keyPair 密钥对
   * @return 返回Base64编码的私钥字符串。
   */
  public static String getPrivateKey(KeyPair keyPair) {
    return new String(encodeBase64(keyPair.getPrivate().getEncoded()));
  }

  /**
   * 从Base64编码的公钥字符串中获取公钥。
   * 
   * @param publicKey Base64编码的公钥字符串
   * @return 返回公钥。
   */
  public static PublicKey getPublicKey(String publicKey) {
    try {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      X509EncodedKeySpec spec = new X509EncodedKeySpec(decodeBase64(publicKey.getBytes()));
      return factory.generatePublic(spec);
    } catch (Exception e) {
      throw new UncheckedException("将字符串转换为公钥时发生异常", e);
    }
  }

  /**
   * 从Base64编码的私钥字符串中获取私钥。
   * 
   * @param privateKey Base64编码的私钥字符串
   * @return 返回私钥。
   */
  public static PrivateKey getPrivateKey(String privateKey) {
    try {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodeBase64(privateKey.getBytes()));
      return factory.generatePrivate(spec);
    } catch (Exception e) {
      throw new UncheckedException("将字符串转换为私钥时发生异常", e);
    }
  }

  /**
   * 对字符串进行签名。
   * 
   * @param srcString 待签名的字符串
   * @param privateKey 私钥
   * @return 返回Base64编码格式的签名。
   */
  public static String sign(String srcString, PrivateKey privateKey) {
    return sign(srcString, Algorithm.MD5RSA, privateKey);
  }

  /**
   * 对字符串进行签名。
   * 
   * @param srcString 待签名的字符串
   * @param algorithm 签名算法
   * @param privateKey 私钥
   * @return 返回Base64编码格式的签名。
   */
  public static String sign(String srcString, String algorithm, PrivateKey privateKey) {
    try {
      Signature rsa = Signature.getInstance(algorithm);
      rsa.initSign(privateKey);
      rsa.update(srcString.getBytes(Encoding.UTF_8));
      byte[] sig = rsa.sign();
      return new String(encodeBase64(sig));
    } catch (Exception e) {
      throw new UncheckedException("对字符串进行签名时发生异常", e);
    }
  }

  /**
   * 验证签名。
   * 
   * @param srcString 原文字符串
   * @param publicKey 公钥
   * @param signature 签名
   * @return 验证签名成功返回true，否则返回false。
   */
  public static Boolean verify(String srcString, PublicKey publicKey, String signature) {
    return verify(srcString, Algorithm.MD5RSA, publicKey, signature);
  }

  /**
   * 验证签名。
   * 
   * @param srcString 原文字符串
   * @param algorithm 签名算法
   * @param publicKey 公钥
   * @param signature 签名
   * @return 验证签名成功返回true，否则返回false。
   */
  public static Boolean verify(String srcString, String algorithm, PublicKey publicKey,
      String signature) {
    try {
      Signature rsa = Signature.getInstance(algorithm);
      rsa.initVerify(publicKey);
      rsa.update(srcString.getBytes(Encoding.UTF_8));
      return rsa.verify(decodeBase64(signature.getBytes()));
    } catch (Exception e) {
      throw new UncheckedException("验证签名时发生异常", e);
    }
  }

  /**
   * 私有构造方法。
   */
  private CryptoUtils() {}
}
