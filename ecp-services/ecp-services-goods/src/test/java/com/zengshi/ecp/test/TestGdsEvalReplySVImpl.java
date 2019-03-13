/** 
 * Project Name:ecp-services-goods 
 * File Name:TestGdsEvalReplySVImpl.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月26日上午10:58:37 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalReplySV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.ObjectCopyUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月26日上午10:58:37  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class TestGdsEvalReplySVImpl extends EcpServicesTest{

    @Resource
    private IGdsEvalSV gdsEvalSV;
    @Resource
    private IGdsEvalReplySV gdsEvalReplySV;
    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#addEvalRely(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO)}.
     */
    @Test
    public final void testAddEvalReply() {
       GdsEvalRespDTO eval = gdsEvalSV.queryGdsEvalByPK(1003L);
       GdsEvalReplyReqDTO dto = new GdsEvalReplyReqDTO();
       ObjectCopyUtil.copyObjValue(eval, dto, "createTime,createStaff", false);
       //dto.setReplyId(8L);
       dto.setEvalId(eval.getId());
       dto.setReplyId(1001L);
       dto.setContent("测试回复内容会被存放在mongodb!");
      // Assert.assertTrue(gdsEvalReplySV.addEvalReply(dto) > 0);
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#executeCountReplyByEvalPK(java.lang.Long, java.lang.String[])}.
     */
    @Test
    public final void testExecuteCountReplyByEvalPK() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#isExistReply(java.lang.Long, java.lang.String[])}.
     */
    @Test
    public final void testIsExistReply() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#isExistSubReply(java.lang.Long, java.lang.String[])}.
     */
    @Test
    public final void testIsExistSubReply() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#deleteReplyByEvalId(java.lang.Long, java.lang.Long)}.
     */
    @Test
    public final void testDeleteReplyByEvalId() {
        fail("Not yet implemented"); // TODO
    }
    @Test
    public final void testDeleteEvalReplyByPK(){
    	GdsEvalReplyReqDTO reqDTO = new GdsEvalReplyReqDTO();
    	reqDTO.setRecurisve(true);
    	reqDTO.setId(1000L);
    	gdsEvalReplySV.deleteEvalReplyByPK(reqDTO);
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#queryGdsEvalReplyRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO)}.
     */
    @Test
    public final void testQueryGdsEvalReplyRespDTOPaging() {
        GdsEvalReplyReqDTO dto = new GdsEvalReplyReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(99);
        dto.setReplyId(GdsConstants.GdsEvalReply.REPLY_ID_NULL);
        dto.setEvalId(18L);
        dto.setRecurisve(true);
        dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        
        PageResponseDTO<GdsEvalReplyRespDTO> page = gdsEvalReplySV.queryGdsEvalReplyRespDTOPaging(dto);
        
        if(page.getCount() > 0){
           for(GdsEvalReplyRespDTO reply : page.getResult()){
               printReply(reply, 1);
           }
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalReplySVImpl#querySubReplyByReplyId(java.lang.Long, java.lang.String)}.
     */
    @Test
    public final void testQuerySubReplyByReplyId() {
        fail("Not yet implemented"); // TODO
    }
    
    
    private void printReply(GdsEvalReplyRespDTO dto, int level){
          if(dto != null){
              printContent(level,dto.getId(),dto.getReplyId(),dto.getContent());
          }
          if(dto != null && null != dto.getSubReplys() && !dto.getSubReplys().isEmpty()){
              for(GdsEvalReplyRespDTO reply : dto.getSubReplys()){
                  printReply(reply, ++level);
              }
          }
    }
    
    
    
    private static final void printContent(int num,Long id,Long replyId,String content){
        if(num > 0){
            if(replyId == -1){
                System.out.println(String.format("回复ID: %d  回复内容:%s", id,content));
            }else{
                StringBuffer s = new StringBuffer();
                int i = 0;
                while(++i <= num){
                    s.append("-");
                }
                System.out.println(String.format("%s 回复ID: %d 回复%s  回复内容为:%s",s.toString(), id, replyId ,content));
            }
        }
    }
    
}

