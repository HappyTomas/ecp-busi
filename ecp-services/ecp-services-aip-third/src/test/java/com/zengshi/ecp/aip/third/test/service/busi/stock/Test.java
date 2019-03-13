package com.zengshi.ecp.aip.third.test.service.busi.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Element;

import com.zengshi.ecp.aip.third.service.busi.utils.SchemaUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.taobao.top.schema.Util.XmlUtils;
import com.taobao.top.schema.enums.RuleTypeEnum;
import com.taobao.top.schema.exception.TopSchemaException;
import com.taobao.top.schema.factory.SchemaReader;
import com.taobao.top.schema.factory.SchemaWriter;
import com.taobao.top.schema.field.ComplexField;
import com.taobao.top.schema.field.Field;
import com.taobao.top.schema.field.InputField;
import com.taobao.top.schema.field.MultiCheckField;
import com.taobao.top.schema.field.MultiComplexField;
import com.taobao.top.schema.field.SingleCheckField;
import com.taobao.top.schema.option.Option;
import com.taobao.top.schema.rule.Rule;
import com.taobao.top.schema.rule.ValueTypeRule;
import com.taobao.top.schema.value.ComplexValue;
import com.taobao.top.schema.value.Value;

import scala.Array;
import scala.util.parsing.combinator.testing.Str;


public class Test {

    public static void main(String[] args) throws Exception {

        ////////////-------组合商品发布规则XML---------///////////////
        
        //----单品规则----//
        MultiComplexField skus = new MultiComplexField();
        skus.setId("sku");
        skus.setName("SKU");
        SingleCheckField singleCheck = new SingleCheckField();
        singleCheck.setId("prop_1627207");
        singleCheck.setName("颜色分类");
        singleCheck.addOption("军绿色", "12345");
        singleCheck.addOption("天蓝色", "45678"); 
        skus.add(singleCheck);
        
        InputField sku_price = new InputField();
        sku_price.setId("sku_price");
        sku_price.setName("价格");
        sku_price.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "decimal");
        sku_price.addRule(RuleTypeEnum.MIN_VALUE_RULE, "0.00", "not include");
        sku_price.addRule(RuleTypeEnum.MAX_VALUE_RULE, "100000000.00", "not include");
        
        InputField sku_id = new InputField();
        sku_id.setId("sku_id");
        sku_id.setName("商品编码");
        sku_id.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "decimal");
        sku_id.addRule(RuleTypeEnum.MIN_VALUE_RULE, "0.00", "not include");
        sku_id.addRule(RuleTypeEnum.MAX_VALUE_RULE, "100000000.00", "not include");
        
        skus.add(sku_price);
        skus.add(sku_id);
        
        //---标题---//
        InputField title = new InputField();
        title.setId("title");
        title.setName("商品标题");
        title.addRule(RuleTypeEnum.REQUIRED_RULE, "true");
        title.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "text");
        
        //----商品图片---//
        ComplexField itemImagesField = new ComplexField();
        itemImagesField.setId("item_images");
        itemImagesField.setName("商品图片");   
        InputField item_image_0 = new InputField();
        item_image_0.setId("item_image_0");
        InputField item_image_1 = new InputField();
        item_image_1.setId("item_image_1");
        List<Rule> imageRules = new ArrayList<Rule>();
        Rule rule = new ValueTypeRule("url");
        imageRules.add(rule);
        item_image_0.setRules(imageRules);
        item_image_1.setRules(imageRules);
        itemImagesField.add(item_image_0);
        itemImagesField.add(item_image_1);
        
        //----商品描述----//
        ComplexField descriptionField = new ComplexField();
        descriptionField.setId("description");
        descriptionField.setName("商品描述");
        
        ComplexField desc_module_116_cat_mod = new ComplexField();
        desc_module_116_cat_mod.setId("desc_module_116_cat_mod");
        desc_module_116_cat_mod.setName("产品图");
        InputField desc_module_116_cat_mod_content = new InputField();
        desc_module_116_cat_mod_content.setId("desc_module_116_cat_mod_content");
        desc_module_116_cat_mod_content.setName("产品图内容");
        desc_module_116_cat_mod_content.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "html");
        desc_module_116_cat_mod_content.addRule(RuleTypeEnum.MIN_LENGTH_RULE, "5", "include");
        InputField desc_module_116_cat_mod_order = new InputField();
        desc_module_116_cat_mod_order.setId("desc_module_116_cat_mod_order");
        desc_module_116_cat_mod_order.setName("产品图排序值");
        desc_module_116_cat_mod_order.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "integer");
        desc_module_116_cat_mod.add(desc_module_116_cat_mod_content);
        desc_module_116_cat_mod.add(desc_module_116_cat_mod_order);
        
        MultiComplexField desc_module_user_mods = new MultiComplexField();
        desc_module_user_mods.setId("desc_module_user_mods");
        desc_module_user_mods.setName("自定义模块列表");
        InputField desc_module_user_mod_name = new InputField();
        desc_module_user_mod_name.setId("desc_module_user_mod_name");
        desc_module_user_mod_name.setName("自定义模块名称");
        desc_module_user_mod_name.addRule(RuleTypeEnum.TIP_RULE, "最多支持添加3个自定义描述模块");
        InputField desc_module_user_mod_content = new InputField();
        desc_module_user_mod_content.setId("desc_module_user_mod_content");
        desc_module_user_mod_content.setName("自定义模块内容");
        desc_module_user_mod_content.addRule(RuleTypeEnum.TIP_RULE, "最多支持添加3个自定义描述模块");
        InputField desc_module_user_mod_order = new InputField();
        desc_module_user_mod_order.setId("desc_module_user_mod_order");
        desc_module_user_mod_order.setName("自定义模块排序值");
        desc_module_user_mod_order.addRule(RuleTypeEnum.TIP_RULE, "最多支持添加3个自定义描述模块");
        desc_module_user_mods.add(desc_module_user_mod_name);
        desc_module_user_mods.add(desc_module_user_mod_content);
        desc_module_user_mods.add(desc_module_user_mod_order);

        
        ComplexField desc_module_115_cat_mod = new ComplexField();
        desc_module_115_cat_mod.setId("desc_module_115_cat_mod");
        desc_module_115_cat_mod.setName("细节图");
        InputField desc_module_115_cat_mod_content = new InputField();
        desc_module_115_cat_mod_content.setId("desc_module_115_cat_mod_content");
        desc_module_115_cat_mod_content.setName("细节图内容");
        desc_module_115_cat_mod_content.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "html");
        desc_module_115_cat_mod_content.addRule(RuleTypeEnum.MIN_LENGTH_RULE, "5", "include");
        InputField desc_module_115_cat_mod_order = new InputField();
        desc_module_115_cat_mod_order.setId("desc_module_115_cat_mod_order");
        desc_module_115_cat_mod_order.setName("细节图排序值");
        desc_module_115_cat_mod_order.addRule(RuleTypeEnum.VALUE_TYPE_RULE, "integer");
        desc_module_115_cat_mod.add(desc_module_115_cat_mod_content);
        desc_module_115_cat_mod.add(desc_module_115_cat_mod_order);
        descriptionField.addRule(RuleTypeEnum.REQUIRED_RULE, "true");
        descriptionField.addRule(RuleTypeEnum.TIP_RULE, "最多支持发布20个描述模块");
        descriptionField.add(desc_module_115_cat_mod);
        descriptionField.add(desc_module_116_cat_mod);
        descriptionField.add(desc_module_user_mods);

        
        //---商品状态----//
        SingleCheckField itemStatusField = new SingleCheckField();
        itemStatusField.setId("item_status");
        itemStatusField.setName("商品状态");
        itemStatusField.setFieldRequired();
        List<Option> statusOptions = new ArrayList<Option>();
        Option option = new Option();
        option.setDisplayName("出售中");
        option.setValue("0");
        Option option1 = new Option();
        option1.setDisplayName("定时上架");
        option1.setValue("1");
        statusOptions.add(option);
        statusOptions.add(option1);
        itemStatusField.setOptions(statusOptions);
        
        //----提取方式----//
        MultiCheckField deliveryWayFiled = new MultiCheckField();
        deliveryWayFiled.setId("delivery_way");
        deliveryWayFiled.setName("提取方式");
        deliveryWayFiled.addOption("电子交易凭证", "1");
        deliveryWayFiled.addOption("邮寄", "2");
        
        //----创建root节点----//
        Element root = XmlUtils.createRootElement("itemRule");
        //----添加子节点----//
        XmlUtils.appendElement(root, skus.toElement());
        XmlUtils.appendElement(root, title.toElement());
        XmlUtils.appendElement(root, itemImagesField.toElement());
        XmlUtils.appendElement(root, descriptionField.toElement());
        XmlUtils.appendElement(root, itemStatusField.toElement());
        XmlUtils.appendElement(root, deliveryWayFiled.toElement());

       String xml =  root.asXML();
       
       Element newroot = XmlUtils.getRootElementFromString(xml);
       //System.out.println(newroot.asXML());

       ///------------解析TMALL返回的发布商品XML规则，生成可发布的商品XML------------/////////
       Map<String, Field> gdsRuleMap = SchemaReader.readXmlForMap(xml);
       List<Field> gdsRuleList = SchemaReader.readXmlForList(xml);
       Element uploadGdsXML = XmlUtils.createRootElement("itemRule");
       List<Field> xmlFields = new ArrayList<Field>();
       
       //商品标题
       InputField titleField = (InputField) gdsRuleMap.get("title");
       titleField.setDefaultValue("iPhone7s 大甩卖");
       titleField.setValue("测试测试商品标题");
       
       XmlUtils.appendElement(uploadGdsXML, titleField.toElement());
       
       xmlFields.add(titleField);

       //商品描述
       ComplexField description = (ComplexField) gdsRuleMap.get("description"); 
       ComplexValue descriptionComplexValue = new ComplexValue();
       Map<String, Field> descriptionMap = description.getFieldMap();
       //产品图
       ComplexField desc_module_116 = (ComplexField) descriptionMap.get("desc_module_116_cat_mod");
       ComplexValue desc_module_116_Value = new ComplexValue();
       desc_module_116_Value.setInputFieldValue(desc_module_116.getId()+"_content", "http://img01.taobaocdn.com/imgextra/i1/1750239373/T221JtXEFXXXXXXXXX_");
       desc_module_116_Value.setInputFieldValue(desc_module_116.getId()+"_order", "2");
       descriptionComplexValue.setComplexFieldValue(desc_module_116.getId(), desc_module_116_Value);
       //细节图
       
       
       description.setDefaultValue(descriptionComplexValue);
       description.setComplexValue(descriptionComplexValue);
       XmlUtils.appendElement(uploadGdsXML, description.toElement());
       xmlFields.add(description);
       
       //商品图片
       ComplexField item_images = (ComplexField) gdsRuleMap.get("item_images");
       ComplexValue imagesComplexValue = new ComplexValue();
       for(Field image : item_images.getFieldList())
       {
           imagesComplexValue.setInputFieldValue(image.getId(), "i4/T17oDgFiXaXXXXXXXX_!!0-item_pic.jpg");
       }
       item_images.setDefaultValue(imagesComplexValue);
       item_images.setComplexValue(imagesComplexValue);
       XmlUtils.appendElement(uploadGdsXML, item_images.toElement());
       xmlFields.add(item_images);

       //商品状态
       SingleCheckField item_status = (SingleCheckField) gdsRuleMap.get("item_status");
       item_status.setDefaultValue("0");
       item_status.setValue("0");
       XmlUtils.appendElement(uploadGdsXML, item_status.toElement());
       xmlFields.add(item_status);

       //提取方式
       MultiCheckField delivery_way = (MultiCheckField) gdsRuleMap.get("delivery_way");
       delivery_way.addDefaultValue("1");
       delivery_way.addDefaultValue("2");
       XmlUtils.appendElement(uploadGdsXML, delivery_way.toElement());
  
       //单品列表
       MultiComplexField skusinfo = (MultiComplexField) gdsRuleMap.get("sku");
       //在这里循环每一个单品信息
       ComplexValue oneSkuInfo = new ComplexValue();
       for(Field field : skusinfo.getFieldList()){
           switch (field.getId()) {
                case "sku_price":
                    oneSkuInfo.setInputFieldValue("sku_price", "19.88");
                    break;
                case "sku_id":
                    oneSkuInfo.setInputFieldValue("sku_id", "82231331233");
                    break;
                    
                case "prop_1627207":
                    //问题2：
                    //这种格式的Id需要解析处理，判断该Id所需要取的值
                    //首先要解析出1627207
                    //然后通过1627207找到对应的映射值
                    
                    break;
                default:
                    break;
          }
       }
       skusinfo.addDefaultComplexValue(oneSkuInfo);
       XmlUtils.appendElement(uploadGdsXML, skusinfo.toElement());

       ///------------解析TMALL返回的发布商品XML规则，生成可发布的商品XML------------/////////
       System.out.println(uploadGdsXML.asXML());
       System.out.println(SchemaWriter.writeParamXmlString(xmlFields));

       
       //测试packGdsXml
       System.out.println("=======================================TEST================================================");
       Element testRoot = XmlUtils.createRootElement("itemRule");
       //----添加子节点----//
       //XmlUtils.appendElement(testRoot, skus.toElement());
       //XmlUtils.appendElement(testRoot, title.toElement());
       //XmlUtils.appendElement(testRoot, itemImagesField.toElement());
       XmlUtils.appendElement(testRoot, descriptionField.toElement());
       //XmlUtils.appendElement(testRoot, itemStatusField.toElement());
       //XmlUtils.appendElement(testRoot, deliveryWayFiled.toElement());
       
       String testXML = testRoot.asXML();
       
       Map<String, Object> gdsInfoMap = new HashMap<String, Object>();
       //INPUT
       gdsInfoMap.put("title", "Iphone6s大甩卖");
       //SINGLECHECK
       gdsInfoMap.put("item_status", "0");
       //MULTICHECK
       List<String> delivery_ways= new ArrayList<String>();
       delivery_ways.add("0");
       delivery_ways.add("2");
       gdsInfoMap.put("delivery_way", delivery_ways);
       //COMPLEX 图片
       Map<String, String> imagesMap = new HashMap<String, String>();
       imagesMap.put("item_image_0", "madd0107/abcd1234");
       imagesMap.put("item_image_1", "madd0107/abcd1234");
       imagesMap.put("item_image_2", "madd0107/abcd1234");
       imagesMap.put("item_image_3", "madd0107/abcd1234");
       gdsInfoMap.put("item_images", imagesMap);
       //COMPLEX 商品描述 desc_module_116_cat_mod
       Map<String, Object> descModMap = new HashMap<String, Object>();
       Map<String, String> module_116 = new HashMap<String, String>();
       module_116.put("desc_module_116_cat_mod_content", "这是商品的116内容描述");
       module_116.put("desc_module_116_cat_mod_order", "0");
       Map<String, String> module_115 = new HashMap<String, String>();
       module_115.put("desc_module_115_cat_mod_content", "这是商品的115内容描述");
       module_115.put("desc_module_115_cat_mod_order", "1");
       List<Map<String, String>> userMods = new ArrayList<Map<String, String>>();
       Map<String, String> userMod_X = new HashMap<String, String>();
       userMod_X.put("desc_module_user_mod_name", "产品介绍");
       userMod_X.put("desc_module_user_mod_content", "这是自定义的产品介绍内容");
       userMod_X.put("desc_module_user_mod_order", "3");
       Map<String, String> userMod_Y = new HashMap<String, String>();
       userMod_Y.put("desc_module_user_mod_name", "细节图");
       userMod_Y.put("desc_module_user_mod_content", "这是自定义的细节图");
       userMod_Y.put("desc_module_user_mod_order", "4");
       userMods.add(userMod_Y);
       userMods.add(userMod_X);
       
       descModMap.put("desc_module_115_cat_mod", module_115);
       descModMap.put("desc_module_116_cat_mod", module_116);
       descModMap.put("desc_module_user_mods", userMods);
       gdsInfoMap.put("description", descModMap);
       //gdsInfoMap.put("description", "111111");

       //MULTICOMPLEX  单品列表
       List<Map<String, String>> testSkus = new ArrayList<Map<String, String>>();
       Map<String, String> skuinfo1 = new HashMap<String,String>();
       skuinfo1.put("sku_price", "19.99");
       skuinfo1.put("sku_quantity", "200");
       skuinfo1.put("prop_1627207", "28320");
       skuinfo1.put("prop_1172444", "22342");
       Map<String, String> skuinfo2 = new HashMap<String,String>();
       skuinfo2.put("sku_price", "99.00");
       skuinfo2.put("sku_quantity", "100");
       skuinfo2.put("prop_1627207", "5320");
       skuinfo2.put("prop_1117207", "55520");
       testSkus.add(skuinfo1);
       testSkus.add(skuinfo2);
       
       gdsInfoMap.put("sku", testSkus);

       
       String gdsRuleXml = SchemaUtil.packSchemaXml(testXML, gdsInfoMap);
       
       System.out.println(gdsRuleXml);

    }
}

