package com.zengshi.ecp.aip.third.test.service.busi.product;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.product.interfaces.IProductSV;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.taobao.top.schema.exception.TopSchemaException;
import com.taobao.top.schema.factory.SchemaReader;
import com.taobao.top.schema.field.Field;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class ProductSVImplTest extends EcpServicesTest {

    @Resource
    private IProductSV defaultProductSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试  查询规则
    @Test
    public void testTaobaoQueryProductRule() throws BusinessException {
/*    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	catgReqDTO.setOutCatgCode("50019780");
    	
    	String value=defaultProductSV.getProductMatch(catgReqDTO);
    	*/
    	File file=new File("C:/Users/huangjx/Desktop/ruleXml拉取过来的原始数据 2.xml");
    	
    	try {
			//Map<String,Field> map=SchemaReader.readXmlForMap(file);
			
			List<Field> l=SchemaReader.readXmlForList(file);
			
			System.out.println(l);
		} catch (TopSchemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	//System.out.println(value);
    }
    //淘宝调用测试  匹配
    @Test
    public void testTaobaoSetProductRule() throws BusinessException {
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	catgReqDTO.setOutCatgCode("50019780");
    	catgReqDTO.setMatchXml(defaultProductSV.getProductMatch(catgReqDTO));
    	
 
    	//catgReqDTO.setMatchXml("<itemRule>     <field id=\"prop_13021751\" name=\"货号\" type=\"input\"><value>123</value>     </field>  <field id=\"prop_20000\" name=\"品牌\" type=\"singleCheck\"><value>30111</value>    </field> </itemRule>");
    	List<String> value=defaultProductSV.setProductMatch(catgReqDTO);
    	System.out.println(value);
    }
    
    //淘宝调用测试  产品基本信息
    @Test
    public void testTaobaoGetProductStatus() throws BusinessException {
    	ProductThirdReqDTO catgReqDTO=new ProductThirdReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	/*catgReqDTO.setProductId(123L);*/
    	catgReqDTO.setProductId(456L);
    	ProductStatusThirdRespDTO value=defaultProductSV.getProductStatus(catgReqDTO);
    	System.out.println(value);
    }
    
    //淘宝调用测试  获得产品添加规则
    @Test
    public void testTaobaoProductAddRule() throws BusinessException {
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	catgReqDTO.setOutCatgCode("50011992");
    	//catgReqDTO.setBrandId("1");
    	String value=defaultProductSV.getProductAddRule(catgReqDTO);
    	System.out.println(value);
    }
    
    //淘宝调用测试  获得产品添加
    @Test
    public void testTaobaoProductAdd() throws BusinessException {
    	ProductThirdReqDTO catgReqDTO=new ProductThirdReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("1023521935");
    	catgReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	catgReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	catgReqDTO.setAccessToken("620083070eddd78d1d9d7473ccb18e7e3082dcegd46a5122054631247");
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	catgReqDTO.setProductId(123L);
    	String value=defaultProductSV.productAddByRule(catgReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
