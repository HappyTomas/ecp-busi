package com.zengshi.ecp.aip.third.service.busi.gds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.taobao.top.schema.field.ComplexField;
import com.taobao.top.schema.field.Field;
import com.taobao.top.schema.field.InputField;
import com.taobao.top.schema.field.LabelField;
import com.taobao.top.schema.field.MultiCheckField;
import com.taobao.top.schema.field.MultiComplexField;
import com.taobao.top.schema.field.MultiInputField;
import com.taobao.top.schema.field.SingleCheckField;
import com.taobao.top.schema.label.Label;
import com.taobao.top.schema.label.LabelGroup;
import com.taobao.top.schema.value.ComplexValue;
import com.taobao.top.schema.value.Value;

public class ConvertFieldValue {

	public static void setFieldByMap(Field f, HashMap inputValue) throws Exception {
		
	    if(f==null || inputValue==null){
	    	return;
	    }
	    //映射key
	    String id=null;
	    try{
            id=BaseParamUtil.fetchParamValue("UNPF_KEY_RELA", f.getId());
	    }catch(Exception ex){
	    	
	    }
	    if(StringUtils.isBlank(id)){
	    	return ;
	    }
	    Object v=inputValue.get(id);
	    if(v==null){
	    	return ;
	    }
		switch (f.getType()) {
	    case INPUT:
	    	setInputValue(f, v);
	      break;
	    case MULTIINPUT:
	    	setMultiInputField(f, v);
	      break;
	    case SINGLECHECK:
	    	setSingleCheckField(f, v);
	      break;
	    case MULTICHECK:
	    	setMultiCheckField(f, v);
	      break;
	    case COMPLEX:
	    	setComplexField(f, v);
	      break;
	    case MULTICOMPLEX:
	    	setMultiComplexField(f, v);
	      break;
	    case LABEL:
	    	setLabelField(f, v);
	    	  break;
	    default:
			break;
	    }
	}
	public static void setFieldValue(Field f, Object v) throws Exception {
	    if(f==null || v==null){
	    	return;
	    }
	    
		switch (f.getType()) {
	    case INPUT:
	    	setInputValue(f, v);
	      break;
	    case MULTIINPUT:
	    	setMultiInputField(f, v);
	      break;
	    case SINGLECHECK:
	    	setSingleCheckField(f, v);
	      break;
	    case MULTICHECK:
	    	setMultiCheckField(f, v);
	      break;
	    case COMPLEX:
	    	setComplexField(f, v);
	      break;
	    case MULTICOMPLEX:
	    	setMultiComplexField(f, v);
	      break;
	    case LABEL:
	    	setLabelField(f, v);
	    	  break;
	    default:
			break;
	    }
	}
   //v String 类型
	private static void setInputValue(Field f, Object v) throws Exception {
		    InputField inputField =(InputField)f;
		    inputField.setValue(v.toString());
		    
		    //defalust_value待定
		    //inputField.setDefaultValue(v.toString());

	}
   //v String 类型
	private static void setSingleCheckField(Field f, Object v) throws Exception {
		SingleCheckField singleCheckField =(SingleCheckField)f;
		singleCheckField.setValue(v.toString());

	}
  //v hashMap 类型
	private static void setComplexField(Field f, Object v) throws Exception {
	        ComplexField complexField = (ComplexField)f;
	        ComplexValue complexValue = new ComplexValue();
	        HashMap<String,String> map=(HashMap<String,String>)v;
           for (Map.Entry<String, String> entry : map.entrySet()) {
        	    complexValue.setInputFieldValue(entry.getKey(), entry.getValue());
    	        //complexValue.setSingleCheckFieldValue("checkId", new Value(entry.getValue()));
	        }  
	    
	        complexField.setComplexValue(complexValue);
	}
  // v List 类型
	private static void setMultiCheckField(Field f, Object v) throws Exception {
		    MultiCheckField multiCheckField = (MultiCheckField)f;
	        List<Value> values2 = new ArrayList<Value>();
	        
	        List<String> list=(ArrayList<String>)v;
	        
	        for(String s:list){
	        	 values2.add(new Value(s));
	        }
	       
	        multiCheckField.setValues(values2);

	}
	private static void setMultiComplexField(Field f, Object v)
			throws Exception {
	        MultiComplexField multiComplexField = (MultiComplexField)f;
	        HashMap<String,String> map=(HashMap<String,String>)v;
	        List<ComplexValue> values3 = new ArrayList<ComplexValue>();
	        ComplexValue complexValue2 = new ComplexValue();
	        for (Map.Entry<String, String> entry : map.entrySet()) {
		        complexValue2.setInputFieldValue(entry.getKey(), entry.getValue());
		        //complexValue2.setSingleCheckFieldValue("checkId", new Value(entry.getValue()));
	        }  
	        values3.add(complexValue2);
	        multiComplexField.setComplexValues(values3);

	}

	private static void setMultiInputField(Field f, Object v) throws Exception {
		    MultiInputField multiInputField = (MultiInputField)f;
	        List<String> values1 = new ArrayList<String>();
	        values1=(ArrayList)v;
	        multiInputField.setValues(values1);

	}

	private static void setLabelField(Field f, Object v) throws Exception {
	        LabelField labelField = (LabelField)f;
	        LabelGroup labelGroup = new LabelGroup();
	        Label label = new Label();
	        label.setDesc(v.toString());
	        labelGroup.add(label);
	        labelField.setLabelGroup(labelGroup);   
	}
}
