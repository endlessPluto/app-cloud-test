package com.nwp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * 各种格式的编码加码工具类.集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.
 */
@Log4j2
public class EncodeUtils {
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static MessageDigest msgDigest;// 数据加密

	/**
	 * @Description:Hex编码.
	 * @param input
	 * @return
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * @Description:Hex编码.
	 * @param input
	 * @return
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			log.error("Hex编码转化有误："+e);
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * @Description:Base64编码.
	 * @param input
	 * @return
	 */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * @Description:Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 * @param input
	 * @return
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * @Description:Base64解码.
	 * @param input
	 * @return
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * @Description:URL 编码, Encode默认为UTF-8.
	 * @param input
	 * @return
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * @Description:URL 编码.
	 * @param input
	 * @param encoding
	 * @return
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("URL编码转化有误："+e);
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * @Description:URL 解码, Encode默认为UTF-8.
	 * @param input
	 * @return
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * @Description:URL 解码.
	 * @param input
	 * @param encoding
	 * @return
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			log.error("URL编码转化有误："+e);
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * @Description:Html 转码.
	 * @param html
	 * @return
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * @Description:Html 解码.
	 * @param htmlEscaped
	 * @return
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * @Description:Xml 转码.
	 * @param xml
	 * @return
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * @Description:Xml 解码.
	 * @param xmlEscaped
	 * @return
	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * MD5进行数据加密
	 * @param msg
	 * @return
	 */
	public static String calcHexDigest(String msg) {
		try {
			if (msgDigest == null) {
				msgDigest = MessageDigest.getInstance("MD5");
			}
		} catch (NoSuchAlgorithmException ex) {
			log.error("MD5加密有误："+ex);
			ex.printStackTrace();
		}
		return bytes2hex(msgDigest.digest(msg.getBytes()));
	}

	/**
	 * 字节转16进制
	 * @param bytes
	 * @return
	 */
	public static String bytes2hex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int pos = 0; pos < bytes.length; pos++) {
			int n = bytes[pos] & 0xFF;
			buf.append(halfByte2hex((n >> 4) & 0xF));
			buf.append(halfByte2hex(n & 0xF));
		}
		return buf.toString();
	}

	/**
	 * @param n
	 * @return
	 */
	public static char halfByte2hex(int n) {
		return (char) (n > 9 ? 'A' + n - 10 : '0' + n);
	}
}
