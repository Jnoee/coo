package coo.base.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import coo.base.exception.UncheckedException;

/**
 * 加解密工具类。
 */
public class CryptoUtils {
	/**
	 * Base64编码。
	 * 
	 * @param content
	 *            待编码内容
	 * @return 返回编码后的内容。
	 */
	public static String encodeBase64(String content) {
		return new String(encodeBase64(content.getBytes()));
	}

	/**
	 * Base64解码。
	 * 
	 * @param content
	 *            待解码内容
	 * @return 返回解码后的内容。
	 */
	public static String decodeBase64(String content) {
		return new String(decodeBase64(content.getBytes()));
	}

	/**
	 * Base64编码。
	 * 
	 * @param contentBytes
	 *            待编码内容字节数组
	 * @return 返回编码后的内容字节数组。
	 */
	public static byte[] encodeBase64(byte[] contentBytes) {
		return Base64.encodeBase64(contentBytes);
	}

	/**
	 * Base64解码。
	 * 
	 * @param contentBytes
	 *            待解码内容字节数组
	 * @return 返回解码后的内容字节数组。
	 */
	public static byte[] decodeBase64(byte[] contentBytes) {
		return Base64.decodeBase64(contentBytes);
	}

	/**
	 * 符合RFC 1321标准的MD5编码。
	 * 
	 * @param content
	 *            待编码的内容
	 * @return 返回编码后的内容。
	 */
	public static String md5(String content) {
		return DigestUtils.md5Hex(content);
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
	 * @param keyPair
	 *            密钥对
	 * @return 返回Base64编码后的公钥字符串。
	 */
	public static String getPublicKey(KeyPair keyPair) {
		return new String(encodeBase64(keyPair.getPublic().getEncoded()));
	}

	/**
	 * 从密钥对中获取Base64编码的私钥字符串。
	 * 
	 * @param keyPair
	 *            密钥对
	 * @return 返回Base64编码的私钥字符串。
	 */
	public static String getPrivateKey(KeyPair keyPair) {
		return new String(encodeBase64(keyPair.getPrivate().getEncoded()));
	}

	/**
	 * 从Base64编码的公钥字符串中获取公钥。
	 * 
	 * @param publicKey
	 *            Base64编码的公钥字符串
	 * @return 返回公钥。
	 */
	public static PublicKey getPublicKey(String publicKey) {
		try {
			KeyFactory factory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec spec = new X509EncodedKeySpec(
					decodeBase64(publicKey.getBytes()));
			return factory.generatePublic(spec);
		} catch (Exception e) {
			throw new UncheckedException("将字符串转换为公钥时发生异常", e);
		}
	}

	/**
	 * 从Base64编码的私钥字符串中获取私钥。
	 * 
	 * @param privateKey
	 *            Base64编码的私钥字符串
	 * @return 返回私钥。
	 */
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(
					decodeBase64(privateKey.getBytes()));
			return factory.generatePrivate(spec);
		} catch (Exception e) {
			throw new UncheckedException("将字符串转换为私钥时发生异常", e);
		}
	}

	/**
	 * 对字符串进行签名。
	 * 
	 * @param srcString
	 *            待签名的字符串
	 * @param privateKey
	 *            私钥
	 * @return 返回Base64编码格式的签名。
	 */
	public static String sign(String srcString, PrivateKey privateKey) {
		try {
			Signature rsa = Signature.getInstance("MD5withRSA");
			rsa.initSign(privateKey);
			rsa.update(srcString.getBytes());
			byte[] sig = rsa.sign();
			return new String(encodeBase64(sig));
		} catch (Exception e) {
			throw new UncheckedException("对字符串进行签名时发生异常", e);
		}
	}

	/**
	 * 验证签名。
	 * 
	 * @param srcString
	 *            原文字符串
	 * @param publicKey
	 *            公钥
	 * @param signature
	 *            签名
	 * @return 验证签名成功返回true，否则返回false。
	 */
	public static Boolean verify(String srcString, PublicKey publicKey,
			String signature) {
		try {
			Signature rsa = Signature.getInstance("MD5withRSA");
			rsa.initVerify(publicKey);
			rsa.update(srcString.getBytes());
			return rsa.verify(decodeBase64(signature.getBytes()));
		} catch (Exception e) {
			throw new UncheckedException("验证签名时发生异常", e);
		}
	}
}