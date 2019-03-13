package com.zengshi.aip;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtil {

	// 日志信息处理。
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	// 记录文件的Properties属性信息。
	private static final Properties PROP;

	// 静态加载
	static {
		PROP = loadDefaultFile();
	}

	// 保持同步处理。
	public static final synchronized Properties loadDefaultFile() {
		if (PROP == null) {
			return loadFileProperty("systemConfig.properties", "AlipayMessage.properties");
		}
		return PROP;
	}

	// 默认进行数据加载
	public static Properties loadFileProperty(String... fileNames) {
		Properties prop = new Properties();
		InputStream is = null;
		for (String fileName : fileNames) {
			try {
				is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
				prop.load(is);
			} catch (Exception e) {
				logger.error("文件加载错误，请检查！");
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return prop;
	}

	// 获取值信息
	public static String getProperty(String name) {
		return PROP.getProperty(name);
	}

	public static String getParam(String fileName, String field) {
		Properties prop = new Properties();
		try {
			// String pathString=FileUtil.class.getClassLoader().getResource("").toURI().getPath()+fileName;
			InputStream // is = Object.class.getClass().getResourceAsStream(fileName);//new BufferedInputStream (new FileInputStream(fileName));;
			is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
			// System.out.println("---------"+pathString);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println(e + "file " + fileName + " not found");
		}
		return prop.getProperty(field);

	}

	@SuppressWarnings("rawtypes")
	public static void readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				System.out.println(key + Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 文件"systemConfig.properties"中的内容
		logger.debug(getProperty("UNICOM_OFFICIAL_ACCT_APP_ID"));
		// 文件 "AlipayMessage.properties"中的内容
		logger.debug(getProperty("13004"));
	}

}
