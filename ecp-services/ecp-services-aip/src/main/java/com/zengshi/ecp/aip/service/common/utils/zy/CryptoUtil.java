package com.zengshi.ecp.aip.service.common.utils.zy;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	private final static String HEX = "0123456789ABCDEF";

	public static final String DEFAULT_KEY = "27jrWz3sxrVbR+pnyg6j";


	/**
	 * 解密
	 * 
	 * @param seed
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String seed, String encrypted) {
		byte[] enc = toByte(encrypted);
		byte[] result = decrypt(seed.getBytes(), enc);
		return new String(result);
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}
	public static byte[] decrypt(byte[] seed, byte[] encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(seed, "DESede");
			Z3DESCipher cipher = new Z3DESCipher();
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String encrypt(String seed, String data) {
		byte[] da = null;
		try {
			da = data.getBytes();
		} catch (Exception e) {
			return "";
		}
		byte[] result = encrypt(seed.getBytes(), da);
		return toHex(result);
	}
	
	private static byte[] encrypt(byte[] seed, byte[] data) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(seed, "DESede");
			Z3DESCipher cipher = new Z3DESCipher();
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(data);
			return encrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}
}
