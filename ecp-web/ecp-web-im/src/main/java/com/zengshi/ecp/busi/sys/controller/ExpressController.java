/** 
 * Project Name:ecp-web-manage 
 * File Name:ExpressController.java 
 * Package Name:com.zengshi.ecp.busi.sys.controller 
 * Date:2015-9-2下午8:24:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.sys.vo.ExpressPageVO;
import com.zengshi.ecp.busi.sys.vo.ExpressVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.paas.utils.ResourceMsgUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-9-2下午8:24:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@RequestMapping(value="/express")
@Controller
public class ExpressController extends EcpBaseController {
    
    @Resource
    private IBaseExpressRSV baseExpressRSV;
    
    @RequestMapping(value="/init")
    public String init(){
        return "express/express-init";
    }
    
    @RequestMapping(value="/grid")
    @ResponseBody
    public Model queryList(Model model ,ExpressPageVO reqVo) throws Exception{
        
        BaseExpressReqDTO dto = reqVo.toBaseInfo(BaseExpressReqDTO.class);
        dto.setStatus(reqVo.getStatus());
        
        PageResponseDTO<BaseExpressRespDTO> dtos = this.baseExpressRSV.fetchExpressInfoByPage(dto);
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(dtos);
        
        return super.addPageToModel(model, respVO);
    }
    
    @RequestMapping(value="/new")
    public String newinit(Model model){
        BaseExpressRespDTO respDto = new BaseExpressRespDTO();
        respDto.setId(0L);
        model.addAttribute("express", respDto);
        model.addAttribute("operate", "new");
        return "express/edit/express-edit";
    }
    
    @RequestMapping(value="/edit/{id}")
    public String edit(Model model, @PathVariable("id")long id){
        BaseExpressReqDTO dto = new BaseExpressReqDTO();
        dto.setId(id);
        BaseExpressRespDTO respDto = this.baseExpressRSV.fetchExpressInfo(dto);
        
        model.addAttribute("express", respDto);
        model.addAttribute("operate", "edit");
        return "express/edit/express-edit";
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid @ModelAttribute("express") ExpressVO reqVo){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        
        if("new".equalsIgnoreCase(reqVo.getOperate())){
            this.saveNew(reqVo);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } else if("edit".equalsIgnoreCase(reqVo.getOperate())){
            this.saveEdit(reqVo);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } else {
            vo.setResultMsg(ResourceMsgUtil.getMessage("web.sys.express.001", null));
        }
        return vo;
    }
    
    /**
     * 
     * saveNew: 创建新的物流信息的方法<br/> 
     * 
     * @author yugn 
     * @param reqVo
     * @return 
     * @since JDK 1.6
     */
    private long saveNew(ExpressVO reqVo){
        
        BaseExpressReqDTO dto = new BaseExpressReqDTO();
        //dto.setId(reqVo.getId());
        dto.setExpressFullName(reqVo.getExpressFullName());
        dto.setExpressName(reqVo.getExpressName());
        dto.setExpressWebsite(reqVo.getExpressWebsite());
        
        long id = this.baseExpressRSV.createExpress(dto);
        ///新建保存；
        return id;
    }
    
    /**
     * 
     * saveEdit: 修改保存物流信息的方法<br/> 
     * 
     * @author yugn 
     * @param reqVo
     * @return 
     * @since JDK 1.6
     */
    private long saveEdit(ExpressVO reqVo){
        BaseExpressReqDTO dto = new BaseExpressReqDTO();
        dto.setId(reqVo.getId());
        dto.setExpressFullName(reqVo.getExpressFullName());
        dto.setExpressName(reqVo.getExpressName());
        dto.setExpressWebsite(reqVo.getExpressWebsite());
        
        long cnt = this.baseExpressRSV.updateExpress(dto);
        
        return cnt;
    }
    
    /**
     * 
     * invalid:注销 <br/> 
     * 
     * @author yugn 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/invalid/{id}")
    @ResponseBody
    public EcpBaseResponseVO invalid(@PathVariable("id")long id){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        BaseExpressReqDTO dto = new BaseExpressReqDTO();
        dto.setId(id);
        long cnt = this.baseExpressRSV.removeExpress(dto);
        
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    /**
     * 
     * valid:生效<br/> 
     * 
     * @author yugn 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/valid/{id}")
    @ResponseBody
    public EcpBaseResponseVO valid(@PathVariable("id")long id){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        BaseExpressReqDTO dto = new BaseExpressReqDTO();
        dto.setId(id);
        long cnt = this.baseExpressRSV.activeExpress(dto);
        
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
}

