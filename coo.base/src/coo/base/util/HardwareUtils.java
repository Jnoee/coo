package coo.base.util;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import coo.base.exception.UncheckedException;

/**
 * 硬件相关工具类。
 */
public class HardwareUtils {
	/**
	 * 获取当前机器所有MAC地址列表。
	 * 
	 * @return 返回当前机器所有MAC地址列表。
	 */
	public static List<String> getAllMac() {
		List<String> macs = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces
						.nextElement();
				byte[] macBytes = networkInterface.getHardwareAddress();
				if (macBytes != null) {
					String mac = byteToMac(macBytes);
					macs.add(mac);
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("获取MAC地址时发生异常", e);
		}
		return macs;
	}

	/**
	 * 将MAC地址字节数组转成字符串。
	 * 
	 * @param macBytes
	 *            MAC地址字节数组
	 * @return 返回MAC地址字符串。
	 */
	private static String byteToMac(byte[] macBytes) {
		StringBuilder builder = new StringBuilder();
		for (byte macByte : macBytes) {
			String macStr = "00" + Integer.toHexString(macByte);
			macStr = macStr.substring(macStr.length() - 2).toUpperCase();
			builder.append("-");
			builder.append(macStr);
		}
		String mac = builder.toString();
		mac = mac.substring(1);
		return mac;
	}
}
