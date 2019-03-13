/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月13日下午5:09:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午5:09:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCategorySVImplTest extends EcpServicesTest{
    @Resource
    private IGdsCategorySV gdsCategorySV;
    @Resource
    private IGdsPropSV gdsPropSV;

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#saveGdsCategory(com.zengshi.ecp.goods.dao.model.GdsCategory)}.
     */
    //@Test
    public void testSaveGdsCategory() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        GdsCategory gdsCatg = new GdsCategory();
        gdsCatg.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
        gdsCatg.setCatgName("平板电脑");
        gdsCatg.setCatgParent(null);
        gdsCatg.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
        gdsCatg.setCatgTypeUnit(1L);
        gdsCatg.setCatgUrl("http://home.izengshi.com");
        gdsCatg.setCreateTime(now);
        gdsCatg.setIfEntityCode(GdsConstants.GdsCategory.IF_ENTITY_CODE_1);
        gdsCatg.setIfShow(GdsConstants.GdsCategory.IF_SHOW_0);
        gdsCatg.setMediaUuid("acbdsadsadsadsadsa");
        gdsCatg.setCreateTime(now);
        gdsCatg.setUpdateTime(now);
        gdsCategorySV.saveGdsCategory(gdsCatg);
        
        boolean isExists = gdsCategorySV.queryExist(gdsCatg.getCatgName(),gdsCatg.getCatgType(), gdsCatg.getCatgLevel(),null,null);
        Assert.assertTrue(isExists);
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryGdsCategoryById(java.lang.String)}.
     */
    @Test
    public void testQueryGdsCategoryById() {
        String id = new String("1000");
        GdsCategory catg = gdsCategorySV.queryGdsCategoryById(id);
        Assert.assertNotNull(catg);
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryGdsCategoryPage(com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO)}.
     */
    @Test
    public void testQueryGdsCategoryPage() {
        GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(1);
//        List<GdsCategory> catgs = gdsCategorySV.queryGdsCategoryPage(dto);
//        Assert.assertEquals(1, catgs.size());
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#editGdsCategory(com.zengshi.ecp.goods.dao.model.GdsCategory)}.
     */
    @Test
    public void testEditGdsCategory() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryExist(java.lang.String, java.lang.Short, java.lang.String[])}.
     */
    @Test
    public void testQueryExist() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#executeDisableGdsCategory(java.lang.String, java.lang.Long)}.
     */
    @Test
    public void testExecuteDisableGdsCategory() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryGdsCategory(java.lang.Short, java.lang.String[])}.
     */
    @Test
    public void testQueryGdsCategory() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryRootGdsCategory(java.lang.String[])}.
     */
    @Test
    public void testQueryRootGdsCategory() {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("11891", "11047");
    	map.put("11890", "11047");
    	map.put("11935", "11152");
    	map.put("11879", "11047");
    	map.put("11886", "11151");
    	map.put("11931", "11152");
    	map.put("11870", "11047");
    	map.put("11930", "11152");
    	map.put("11943", "11152");
    	map.put("11955", "11047");
    	map.put("11926", "11047");
    	map.put("11895", "11152");
    	map.put("11915", "11152");
    	map.put("11859", "11047");
    	map.put("11862", "11152");
    	map.put("11974", "11197");
    	
    	Set<Map.Entry<String, String>> es = map.entrySet();
    	for(Map.Entry<String, String> e : es){
    		List<GdsCategoryRespDTO> categoryReqDTOs=gdsCategorySV.queryRootGdsCategory(e.getValue(),null, "1");
        	for (GdsCategoryRespDTO gdsCategoryRespDTO : categoryReqDTOs) {
        		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    			System.out.print(e.getKey()+"=="+gdsCategoryRespDTO.getCatgName());
    			System.out.print(":");
    		}
        	System.out.println("");
    	}
    	
    	/*List<GdsCategoryRespDTO> categoryReqDTOs=gdsCategorySV.queryRootGdsCategory("1",null, new String[]{"1"});
    	for (GdsCategoryRespDTO gdsCategoryRespDTO : categoryReqDTOs) {
			System.out.println(gdsCategoryRespDTO.getCatgName());
		}
    	
    	List<GdsCategoryRespDTO> categoryReqDTOs1=gdsCategorySV.queryRootGdsCategory("2",null, new String[]{"1"});
    	for (GdsCategoryRespDTO gdsCategoryRespDTO : categoryReqDTOs1) {
			System.out.println(gdsCategoryRespDTO.getCatgName());
		}*/
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#querySubGdsCategory(java.lang.String, java.lang.String[])}.
     */
    @Test
    public void testQuerySubGdsCategory() {
    	List<GdsCategoryRespDTO> categoryReqDTOs=gdsCategorySV.querySubGdsCategory("1", new String[]{"1"});
    	for (GdsCategoryRespDTO gdsCategoryRespDTO : categoryReqDTOs) {
			System.out.println(gdsCategoryRespDTO.getCatgName());
		}
    	
    }
    
    
    
    @Resource
    private IGdsCatg2PropSV gdsCatg2PropSV;
    @Test
    public void testQueryCategoryProps() {
    	
    	GdsCatg2PropReqDTO reqDTO=new GdsCatg2PropReqDTO();
    	reqDTO.setCatgCode("111");
    	GdsCatg2PropRelationRespDTO categoryReqDTOs=gdsCatg2PropSV.queryCategoryProps(reqDTO);
    	System.out.println("");
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsCategorySVImpl#queryExistSubValidCatg(java.lang.String)}.
     */
    @Test
    public void testQueryExistSubValidCatg() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testQueryRootCategoryByPK(){
    	
    	Map<String, String> map = new HashMap<String, String>();
    	/*map.put("11891", "11047");
    	map.put("11890", "11047");
    	map.put("11935", "11152");
    	map.put("11879", "11047");
    	map.put("11886", "11151");
    	map.put("11931", "11152");
    	map.put("11870", "11047");
    	map.put("11930", "11152");
    	map.put("11943", "11152");
    	map.put("11955", "11047");
    	map.put("11926", "11047");
    	map.put("11895", "11152");
    	map.put("11915", "11152");
    	map.put("11859", "11047");
    	map.put("11862", "11152");
    	map.put("11974", "11197");*/
    	

    	map.put("11884","11047");
    	map.put("11944","11152");
    	map.put("11945","11152");
    	map.put("11880","11047");
    	map.put("11857","11047");
    	map.put("11928","11149");
    	map.put("11933","11152");
    	map.put("11932","11152");
    	map.put("11956","11047");
    	
    	
    	
    	Set<Map.Entry<String, String>> es = map.entrySet();
    	for(Map.Entry<String, String> e : es){
    		List<GdsCategoryRespDTO> categoryReqDTOs=gdsCategorySV.queryCategoryTraceUpon(e.getValue());
    		System.out.println("@@@");
    		for(GdsCategoryRespDTO ds : categoryReqDTOs){
    			System.out.print(e.getKey()+"=="+ds.getCatgName());
    			System.out.print(":");
    		}
        	System.out.println("");
        	
    	}
    	
    	System.out.println(gdsCategorySV.queryRootCategoryByPK("11047"));
    	System.out.println("!!!!");
//    	String catgCode = "111";
//    	GdsCategoryRespDTO respDTO = gdsCategorySV.queryRootCategoryByPK(catgCode);
//    	System.out.println(ToStringBuilder.reflectionToString(respDTO,ToStringStyle.MULTI_LINE_STYLE));
    }
    
    @Test
    public void testQueryCategoryTraceUpon(){
    	String catgCode = "111";
    	List<GdsCategoryRespDTO> lst = gdsCategorySV.queryCategoryTraceUpon(catgCode);
    	System.out.println(lst);
    }
    @Test
    public void testExecuteCompare(){
    	GdsCategoryCompareReqDTO crd = new GdsCategoryCompareReqDTO();
    	crd.setSourceCode("1115");
    	crd.setTargetCode("10655");
    	GdsCategoryCompareRespDTO respDTO = gdsCategorySV.executeCompare(crd);
    	System.out.println("@@@@@@@@@");
    	System.out.println(ToStringBuilder.reflectionToString(respDTO));
    	System.out.println("@@@@@@@@");
    }
    @Test
    public void testQueryCategoryLimitByCatgCodeAndTargetLevel(){
    	GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
    	reqDTO.setCatgCode("58219");
    	reqDTO.setTargetLevel((short)2);
    	GdsCategoryRespDTO respDTO = gdsCategorySV.queryCategoryLimitByCatgCodeAndTargetLevel(reqDTO);
    	System.out.println(ToStringBuilder.reflectionToString(respDTO));
    }
    
    
  @Resource
  private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
  @Test
  public  void test(){
      
      Long gdsId=new Long(12808);
      String catg2="1115";
      //调用接口 获得gds sku当前分类 
      GdsInfoReqDTO dto=new GdsInfoReqDTO();
      dto.setId(gdsId);
      
      GdsQueryOption[] gdsQuery=new GdsQueryOption[1];
      gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
      dto.setGdsQueryOptions(gdsQuery);
      
      GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);
      //分类代码
      String catg=null;
       if(gdsDTO!=null){
           if(gdsDTO.getMainCategory()!=null){
               catg=gdsDTO.getMainCategory().getCatgCode();
           }
       }
      // 验证 当前获得列表对应的分类 属于当前商品+单品对应分类或者子分类。 如果是加入列表，否则不加入
       GdsCategoryCompareReqDTO  compareReqDTO= new GdsCategoryCompareReqDTO();
         compareReqDTO.setSourceCode(catg);
         compareReqDTO.setTargetCode(catg2);
         GdsCategoryCompareRespDTO dto1=gdsCategorySV.executeCompare(compareReqDTO);
     
         
       if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(dto1.getResult()) || GdsCategoryCompareRespDTO.RESULT_LESS_THAN.equals(dto1.getResult()) ){
         //返回结果关系
           System.out.println("ok");
       }else{
           System.out.println("erroe");
       }   
  }
      
    
    
    
    public static void main(String[] args) {
	    Person p = new Person();
	    p.setId("a");
	    p.setAge("name");
	    
	    Person p1 = new Person();
	    p1.setId("a");
	    p1.setAge("name");
	    
	    List<Person> pLst = new ArrayList<>();
	    pLst.add(p1);
	    pLst.add(p);
	    System.out.println(JSONObject.toJSONString(pLst));
	    
	    
	    // 当前传入关联。
	    List<String> l1 = new ArrayList<>();
	    l1.add("a");
	    l1.add("b");
	    l1.add("c");
	    
	    // 现有关联。
	    List<String> l2 = new ArrayList<>();
	   // l2.add("a");
	   // l2.add("b");
	    l2.add("c");
	    
	    
	    l1.removeAll(l2);
	    System.out.println(l2);
	    
	    // 接口传： 123
	    // 现有：2 123 remove 2 = 13 交集是2
	    // 删除： 删除 1，3
	    
	    // 
	    
	    
	    
	    
	}
    
}


class Person{
	
	String id;
	String age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
    
	
	
	
}
