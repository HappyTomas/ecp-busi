package com.zengshi.ecp.busi.order;

public class HtmlEscape {
	private static String htmlEncode(char c) {
	    switch(c) {
//	       case '&':
//	           return"&amp;";
	       case '<':
	           return"&lt;";
	       case '>':
	           return"&gt;";
//	       case '"':
//	           return"&quot;";
//	       case ' ':
//	           return"&nbsp;";
	       default:
	           return c +"";
	    }
	}
	 
	/** 对传入的字符串str进行Html encode转换 */
	public static String htmlEncode(String str) {
	    if(str ==null || str.trim().equals(""))   return str;
	    StringBuilder encodeStrBuilder = new StringBuilder();
	    for (int i = 0, len = str.length(); i < len; i++) {
	       encodeStrBuilder.append(htmlEncode(str.charAt(i)));
	    }
	    return encodeStrBuilder.toString();
	}
	
	/** 对传入的字符串数组进行Html encode转换，返回String数组*/
	public static String[] htmlEncode(String[] strs) {
		if(strs ==null || strs.length < 1)   return strs;
		String[] encodeStrs = new String[strs.length];
		for (int i=0; i<strs.length; i++) {
			String str = strs[i];
			StringBuilder encodeStrBuilder = new StringBuilder();
		    for (int j = 0, len = str.length(); j < len; j++) {
		       encodeStrBuilder.append(htmlEncode(str.charAt(j)));
		    }
		    encodeStrs[i] = encodeStrBuilder.toString();
		}
		return encodeStrs;
	}
	
	public static void main(String[] args) {
		String str = "<script>alter('hello world!');</script>";
		System.out.println(htmlEncode(str));
		
		String[] strs = {" ","<script>alter('hello world!');</script>","&nbsp;<!-- 这里是注释 -->"};
		String[] encodeStrs = htmlEncode(strs);
		for (int i=0; i<encodeStrs.length; i++) {
			System.out.println(encodeStrs[i]);
		}
	}
}
