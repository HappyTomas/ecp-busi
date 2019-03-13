package com.zengshi.aip.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ObtainValueByReflectUtils {
	//xstream对以上范围字符进行特殊字符转换，导致单下划线变为双下划线，初始化时重新定义NoNameCoder
    public static XStream xStream=new XStream(new DomDriver("UTF-8", new NoNameCoder()));
    //也可用下句
    //xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
    
	
	/**
	 * @Title: getFiledsInfo
	 * @Description:根据对象取得属性值及名称,返回map
	 * @author: luocan
	 * @Create: 2014年11月1日 下午5:50:37
	 * @Modify: 2014年11月1日 下午5:50:37
	 * @param:
	 * @return:
	 */
	public  static Map<String,Object> getFiledsInfo(Object o) throws Exception {
		Field[] fields = o.getClass().getDeclaredFields();
		Map<String,Object> infoMap = new HashMap<String,Object>();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			
			infoMap.put(fields[i].getName(), fields[i].get(o));
		}
		return infoMap;
	}

	/**
	 * @Title: getFiledsInfo2
	 * @Description:根据对象取得其属性和值，返回List
	 * @author: luocan
	 * @Create: 2014年11月1日 下午5:55:21
	 * @Modify: 2014年11月1日 下午5:55:21
	 * @param:
	 * @return:
	 */
	public  static List<Object> getFiledsInfo2(Object o) throws Exception {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			infoMap = new HashMap<String,Object>();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", fields[i].get(o));
			list.add(infoMap);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: objectToXML
	 * @Description:根据java bean生成xml（有待完善，暂时只支持全是String属性的对象）
	 * @author: luocan
	 * @Create: 2014年11月3日 上午11:23:03
	 * @Modify: 2014年11月3日 上午11:23:03
	 * @param:
	 * @return:
	 */
	/*public static String objectToXML(Object o) throws Exception {
		Document document = DocumentHelper.createDocument();
		String className=o.getClass().getSimpleName();
		document.addElement(className);
		Element root = document.getRootElement();
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			fields[i].setAccessible(true);
//			root.addElement(fields[i].getName()).setText((String) fields[i].get(o));
			root.addElement(fields[i].getName()).setData( fields[i].get(o));
		}
		return document.asXML();
	}*/
	
	/**
	 * @Title: beanToXML
	 * @Description:利用XStream将javabean转化成xml
	 * @author: luocan
	 * @Create: 2014年11月3日 上午11:25:19
	 * @Modify: 2014年11月3日 上午11:25:19
	 * @param:
	 * @return:
	 */
	public static String beanToXML(Object o) throws Exception {
     //也可用下句替代
     //xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
     //重定义根节点，否则将是包名全路径加类名
     xStream.aliasPackage("", o.getClass().getPackage().getName());
     return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ xStream.toXML(o);
	}
	
	 public static Object XML2bean(String inputXml ) throws Exception {

		  if (null == inputXml || "".equals(inputXml)) {
		   return null;
		  }
		  return xStream.fromXML(inputXml);

		 }


}
