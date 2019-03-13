package com.zengshi.ecp.aip.third.service.busi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.paas.utils.LogUtil;
import com.taobao.top.schema.exception.TopSchemaException;
import com.taobao.top.schema.factory.SchemaReader;
import com.taobao.top.schema.factory.SchemaWriter;
import com.taobao.top.schema.field.ComplexField;
import com.taobao.top.schema.field.Field;
import com.taobao.top.schema.field.InputField;
import com.taobao.top.schema.field.MultiCheckField;
import com.taobao.top.schema.field.MultiComplexField;
import com.taobao.top.schema.field.SingleCheckField;
import com.taobao.top.schema.value.ComplexValue;
import com.taobao.top.schema.value.Value;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-third-server <br>
 * Description: <br>
 * Date:2016年12月7日下午6:36:42  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PengJie
 * @version  
 * @since JDK 1.6
 * 
 * 天猫Schema商品发布体系工具类
 * 
 */
public class SchemaUtil {
    
    //禁止实例化
    private SchemaUtil(){}
    
    private static final String MODULE = SchemaUtil.class.getName();
    
    //根据ruleXML打包天猫Schema体系XML
    public static String packSchemaXml(String ruleXML, Map<String, Object> valueMap) throws TopSchemaException,Exception{
        
        List<Field> ruleList = SchemaReader.readXmlForList(ruleXML);
        //Map<String, Field> ruleMap = SchemaReader.readXmlForMap(ruleXML);

        if (CollectionUtils.isNotEmpty(ruleList)) {
            for(Field field : ruleList){
                setFieldValue(field, valueMap.get(field.getId()));
            }
        }
 
        return SchemaWriter.writeParamXmlString(ruleList);
    }
    private static void setFieldValue(Field field, Object value) throws Exception{   
        
        try {
                switch (field.getType()) {
                case INPUT:
                    InputField inputField = (InputField) field;
                    if (value == null) {
                        inputField.setValue(inputField.getDefaultValue());
                    }else {
                    	if(value instanceof String){
                    		inputField.setValue(value.toString());
                    	}else if(value instanceof HashMap){
                    		Map<String, Object> map=(HashMap<String, Object>)value;
                    		String v="";
                    		 for (Map.Entry<String, Object> entry : map.entrySet()) {
                    			 if(entry.getValue()!=null && !"".equals(entry.getValue())){
                    				 if(entry.getValue() instanceof String){
                    					 v=v+entry.getValue().toString();
                       			   }else if(entry.getValue() instanceof HashMap){
                       				Map<String, Object> map1=(HashMap<String, Object>)entry.getValue();
                    	   				for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
                    	   					if(entry1.getKey().contains("content")){
                    	   						if(entry1.getValue()!=null && !"".equals(entry1.getValue())){
                    	   						      v=v+entry1.getValue().toString();
                    		   					}
                    	   					}
                    	   				}
                       			 }else if(entry.getValue() instanceof List){
                       				 List<Map<String, Object>> list=(ArrayList<Map<String, Object>>)entry.getValue();
                       				 for(Map<String, Object> m:list){
                       					for (Map.Entry<String, Object> entry1 : m.entrySet()) {
                    	   					if(entry1.getKey().contains("content")){
                    	   						if(entry1.getValue()!=null && !"".equals(entry1.getValue())){
                    	   						      v=v+entry1.getValue().toString();
                    		   					}
                    	   					}
                       					}
                       				 }
                       			   }
                    			 }
                    		 }
                    		 inputField.setValue(v);
                    	}else{
                    		inputField.setValue(value.toString());
                    	}
                        
                    }
                    break;
                case SINGLECHECK:
                    SingleCheckField singleCheckField = (SingleCheckField) field;
                    if (value == null) {
                        singleCheckField.setValue(singleCheckField.getDefaultValue());
                    }else {
                        singleCheckField.setValue(value.toString());
                    }
                    break;
                case MULTICHECK:
                    MultiCheckField multiCheckField = (MultiCheckField) field;
                    if (value == null) {
                        multiCheckField.setDefaultValue(multiCheckField.getDefaultValues());
                    }else {
                    	   if(value instanceof String){
                    		   multiCheckField.addValue(value.toString());
                    	   }else{
		                        List<String> checkValues = (List<String>) value;
		                        if (CollectionUtils.isNotEmpty(checkValues)) {
		                            for(String v : checkValues){
		                                multiCheckField.addValue(v);
		                            }
		                        }     
                        }
                    }
                    break;
                case COMPLEX:
                    ComplexField complexField = (ComplexField) field; 
                    if (value == null) {
                        complexField.setDefaultValue(complexField.getDefaultComplexValue());
                    }else {
                        ComplexValue descriptionComplexValue = new ComplexValue();
                        Map<String, Object> complexValueMap = (Map<String, Object>) value;
                        for(Field cField : complexField.getFieldList()){
                            descriptionComplexValue = setComplexValue(descriptionComplexValue, cField, complexValueMap.get(cField.getId()));
                        }
                        complexField.setComplexValue(descriptionComplexValue);
                    }
                    break;
                case MULTICOMPLEX:
                    MultiComplexField multiComplexField = (MultiComplexField) field;
                    List<Map<String, Object>> multComplexValueMap = (List<Map<String, Object>>) value;
                    if (CollectionUtils.isNotEmpty(multComplexValueMap)) {
                        for(Map<String, Object> one :  multComplexValueMap){
                            ComplexValue oneSkuInfo = new ComplexValue();
                            for(Field mField : multiComplexField.getFieldList()){
                                oneSkuInfo = setComplexValue(oneSkuInfo, mField, one.get(mField.getId()));
                            }
                            multiComplexField.addComplexValue(oneSkuInfo);   
                        }     
                    }
                    break;
                case MULTIINPUT:
                    
                    break;
                default:
                    break;
            }
        } catch (ClassCastException e) {
            LogUtil.error(MODULE, "节点["+field.getId()+"]需要的数据类型不匹配,错误原因："+e.getMessage());
        }
        catch (Exception e) {
            LogUtil.error(MODULE, "无法处理的错误："+e.getMessage());
            throw e;
        }
    }
    private static ComplexValue setComplexValue(ComplexValue complexValue, Field field, Object value) throws Exception{

        if (value == null ) {
            return complexValue;
        }
        
        try {
            switch (field.getType()) {
            case INPUT:
                complexValue.setInputFieldValue(field.getId(), value.toString());
                break;
            case SINGLECHECK:
                complexValue.setSingleCheckFieldValue(field.getId(), new Value(value.toString())); 
                break;
            case MULTICHECK:
	                List<Value> mutiCheckValues = new ArrayList<Value>();
		                List<String> values = (List<String>) value;
		                if (CollectionUtils.isNotEmpty(values)) {
		                    for(String v : values){
		                        mutiCheckValues.add(new Value(v));
		                    }
		                    complexValue.setMultiCheckFieldValues(field.getId(), mutiCheckValues);
		                }
                break;
            case COMPLEX:
                ComplexField complexField = (ComplexField) field; 
                ComplexValue subcomplexValue = new ComplexValue();
                Map<String, Object> subComplexValues = (Map<String, Object>) value;
                for(Field cField : complexField.getFieldList()){
                    subcomplexValue = setComplexValue(subcomplexValue, cField, subComplexValues.get(cField.getId()));
                }
                complexValue.setComplexFieldValue(field.getId(), subcomplexValue);
                break;
            case MULTICOMPLEX:
                MultiComplexField subMultiComplexField = (MultiComplexField) field;
                List<Map<String, Object>> subMultComplexValues = (List<Map<String, Object>>) value;
                List<ComplexValue> subMultComplexValueList = new ArrayList<ComplexValue>();
                if (CollectionUtils.isNotEmpty(subMultComplexValues)) {
                    for(Map<String, Object> one : subMultComplexValues){
                        ComplexValue oneInfo = new ComplexValue();
                        for(Field subField : subMultiComplexField.getFieldList()){
                            oneInfo = setComplexValue(oneInfo, subField, one.get(subField.getId()));
                        }
                        subMultComplexValueList.add(oneInfo);
                    }
                }                
                complexValue.setMultiComplexFieldValues(field.getId(), subMultComplexValueList);
                break;

            default:
                break;
            }
        } catch (ClassCastException e) {
            LogUtil.error(MODULE, "子节点["+field.getId()+"]需要的数据类型不匹配,错误原因："+e.getMessage());
        }
        catch (Exception e) {
            LogUtil.error(MODULE, "无法处理的错误："+e.getMessage());
            throw e;
        }
       
        return complexValue;
    }
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}

