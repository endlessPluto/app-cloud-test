package com.nwp.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.SystemUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Log4j2
public class IpconfigUtils {

	public final static String WIN_OS = "WINDOWS";
	public final static String MAC_OS = "MAC";
	public final static String LINUX_OS = "LINUX";
	public final static String OTHER_OS = "OTHER";

	/**
	 * 获取MAC地址的方法
	 * @return
	 */
	public static String getMacAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			byte[] mac = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
					continue;
				} else {
					mac = netInterface.getHardwareAddress();
					if (mac != null) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
						}
						if (sb.length() > 0) {
							return sb.toString();
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("MAC地址获取失败", e);
		}
		return "";
	}

	/**
	 * 获取ip地址
	 * @return
	 */
	public static String getIpAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
					continue;
				} else {
					Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("IP地址获取失败", e);
		}
		return "";
	}

	/**
	 * 获取操作系统
	 * @return
	 */
	public static String getOS() {
		if (SystemUtils.IS_OS_WINDOWS){
			return WIN_OS;
		}
		if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX){
			return MAC_OS;
		}
		if (SystemUtils.IS_OS_UNIX){
			return LINUX_OS;
		}
		return OTHER_OS;
	}

	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getIp(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				int index = ip.indexOf(",");
				if (index != -1) {
					return ip.substring(0, index);
				} else {
					return ip;
				}
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getRemoteAddr();
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}



	public static void main(String[] args) {
		System.out.println(getIpAddress());
	}
}