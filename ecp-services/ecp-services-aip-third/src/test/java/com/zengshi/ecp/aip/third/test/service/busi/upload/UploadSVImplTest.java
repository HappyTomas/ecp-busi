package com.zengshi.ecp.aip.third.test.service.busi.upload;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.upload.interfaces.IUploadSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class UploadSVImplTest extends EcpServicesTest {

    @Resource
    private IUploadSV defaultUploadSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试 
    @Test
    public void testTaobaoUploadPrd() throws BusinessException {
    	UploadThirdReqDTO uploadThirdReqDTO=new UploadThirdReqDTO();
    	uploadThirdReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	
    	uploadThirdReqDTO.setAppkey("23521935");
    	uploadThirdReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	uploadThirdReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	uploadThirdReqDTO.setAuthId(8L);
    	uploadThirdReqDTO.setShopId(100L);
    	uploadThirdReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	uploadThirdReqDTO.setImageInputTitle("11.jpg");
    	uploadThirdReqDTO.setImgUrl("C:\\huangjx\\11.jpg");
    	uploadThirdReqDTO.setFileId(null);//该字段为空 取imgUrl路径处理图片
    	uploadThirdReqDTO.setPictureCategoryId("0");
    	uploadThirdReqDTO.setTitle("11");
    	
    	UploadThirdRespDTO value=defaultUploadSV.upload(uploadThirdReqDTO);
    	//返回结果
    /*	149180334472004940
    	http://img.alicdn.com/imgextra/i4/1125014918/TB2oZ_nXCFjpuFjSspbXXXagVXa_!!1125014918.jpg
*/    	System.out.println(value);
    }
    //淘宝调用测试 
    @Test
    public void testTaobaoUploadPrd1() throws BusinessException {
    	UploadThirdReqDTO uploadThirdReqDTO=new UploadThirdReqDTO();
    	uploadThirdReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	
    	uploadThirdReqDTO.setAppkey("23521935");
    	uploadThirdReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	uploadThirdReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	uploadThirdReqDTO.setAuthId(8L);
    	uploadThirdReqDTO.setShopId(100L);
    	uploadThirdReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	uploadThirdReqDTO.setImageInputTitle("5628ccf8e4b077c55b4c652f.jpg");
    	uploadThirdReqDTO.setFileId("5628ccf8e4b077c55b4c652f");
    	uploadThirdReqDTO.setPictureCategoryId("0");
    	uploadThirdReqDTO.setTitle("中国胎儿产前超声检查规范");
    	
    	UploadThirdRespDTO value=defaultUploadSV.upload(uploadThirdReqDTO);
    	//返回结果
    	/*{"picture_upload_response":{"picture":{"client_type":"client:computer","created":"2016-12-06 15:36:58","deleted":"0","modified":"2016-12-06 15:36:58","picture_category_id":0,"picture_id":149180334653332324,"picture_path":"http:\/\/img.alicdn.com\/imgextra\/i4\/1125014918\/TB2hU73XEdnpuFjSZPhXXbChpXa_!!1125014918.jpg","pixel":"350x350","sizes":111799,"status":"0","title":"中国胎儿产前超声检查规范"},"request_id":"43fko4ea9sdd"}}*/
      	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
