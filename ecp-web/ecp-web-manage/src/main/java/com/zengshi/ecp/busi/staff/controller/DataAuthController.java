package com.zengshi.ecp.busi.staff.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.DataAuthVO;
import com.zengshi.ecp.busi.staff.vo.DataFieldItemVO;
import com.zengshi.ecp.busi.staff.vo.DataFieldRuleRespVO;
import com.zengshi.ecp.busi.staff.vo.DataFieldRuleVO;
import com.zengshi.ecp.busi.staff.vo.DataFuncTreeNodeVO;
import com.zengshi.ecp.busi.staff.vo.DataFuncVO;
import com.zengshi.ecp.busi.staff.vo.DataRuleItemVO;
import com.zengshi.ecp.busi.staff.vo.DataRuleVO;
import com.zengshi.ecp.busi.staff.vo.FindDataAuthVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataAuthVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataFieldRuleBatchVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataFuncVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataRuleBatchVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataRuleItemVO;
import com.zengshi.ecp.busi.staff.vo.SaveDataRuleVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IDataAuthManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年10月10日上午11:40:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/dataauth")
public class DataAuthController extends EcpBaseController {

    private static final String MODULE = DataAuthController.class.getName();
    
    @Resource
    private IDataAuthManageRSV dataAuthManageRSV;  //数据权限dubbo
    
    /**
     * 
     * dataRuleView:(跳转到规则配置). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datarule/view")
    public String dataRuleView(Model model){
        return "/staff/dataauth/data-rule";
    }
    
    /**
     * 
     * dataItemView:(跳转到数据项定义). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataitem/view")
    public String dataItemView(){
        return "/staff/dataauth/data-rule-item";
    }
    
    /**
     * 
     * gridList:(查询数据项列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataitem/gridlist")
    @ResponseBody
    public Model dataItemGridList(Model model, DataRuleItemVO vo) throws Exception{
        
        DataRuleItemReqDTO info = vo.toBaseInfo(DataRuleItemReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        if(vo.getListAll()!=null){
            info.setPageNo(0);//不分页查询
        }
        PageResponseDTO<DataRuleItemResDTO> t = dataAuthManageRSV.listDataRuleItem(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * dataItemAdd:(添加规则项目). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataitem/add")
    @ResponseBody
    public EcpBaseResponseVO dataItemAdd(@Valid SaveDataRuleItemVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataRuleItemReqDTO reqDto = new DataRuleItemReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.saveDataRuleItem(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加规则项目失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataItemUpdate:(修改规则项目). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataitem/update")
    @ResponseBody
    public EcpBaseResponseVO dataItemUpdate(@Valid SaveDataRuleItemVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataRuleItemReqDTO reqDto = new DataRuleItemReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.updateDataRuleItem(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "修改规则项目失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataItemDelete:(删除规则项目). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataitem/delete")
    @ResponseBody
    public EcpBaseResponseVO dataItemDelete(@Valid DataRuleItemVO vo) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataRuleItemReqDTO reqDto = new DataRuleItemReqDTO();
        reqDto.setId(vo.getId());
        try {
            dataAuthManageRSV.deleteDataRuleItemById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除规则项目失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataRuleAdd:(添加规则明细). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datarule/add")
    @ResponseBody
    public EcpBaseResponseVO dataRuleAdd(@Valid SaveDataRuleVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataRuleReqDTO reqDto = new DataRuleReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.saveDataRule(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataRuleSave:(保存规则明细[list]). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datarule/save")
    @ResponseBody
    public EcpBaseResponseVO dataRuleSave(@Valid SaveDataRuleBatchVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        reqDto.setId(vo.getAuthId());
        List<DataRuleReqDTO> listDR = new ArrayList<DataRuleReqDTO>();
        if(vo!=null && CollectionUtils.isNotEmpty(vo.getRuleArr()))
        for (SaveDataRuleVO saveDataRuleVO : vo.getRuleArr()) {//处理入参
            DataRuleReqDTO drReqDto = new DataRuleReqDTO();
            ObjectCopyUtil.copyObjValue(saveDataRuleVO, drReqDto, null, false);
            listDR.add(drReqDto);
        }
        reqDto.setRuleArr(listDR);
        try {
            dataAuthManageRSV.saveDataRuleBatch(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataRuleDelete:(删除规则明细). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datarule/delete")
    @ResponseBody
    public EcpBaseResponseVO dataRuleDelete(@Valid DataRuleVO vo) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataRuleReqDTO reqDto = new DataRuleReqDTO();
        reqDto.setId(vo.getId());
        try {
            dataAuthManageRSV.deleteDataRuleById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataAuthAdd:(添加数据规则). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataauth/add")
    @ResponseBody
    public EcpBaseResponseVO dataAuthAdd(@Valid SaveDataAuthVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.saveDataAuth(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加规则权限失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataAuthOpen:(打开数据功能open窗口). <br/> 
     * 
     * @author linby
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value = "/dataauth/{opType}/open")
	public String dataAuthOpen(@PathVariable("opType") String opType) {
    	StringBuffer sb = new StringBuffer("/staff/dataauth/open/data-auth-");
		return sb.append(opType).toString();
	}
    
    /**
     * 
     * dataAuthUpdate:(修改数据规则). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataauth/update")
    @ResponseBody
    public EcpBaseResponseVO dataAuthUpdate(@Valid SaveDataAuthVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.updateDataAuthById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "修改规则权限失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * gridList:(查询数据项列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/dataauth/gridlist")
    @ResponseBody
    public Model dataAuthGridList(Model model, DataAuthVO vo) throws Exception{
    	DataAuthReqDTO info = vo.toBaseInfo(DataAuthReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        if(vo.getListAll()!=null){
            info.setPageNo(0);//不分页查询
        }
        PageResponseDTO<DataAuthResDTO> t = dataAuthManageRSV.listDataAuth(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * dataAuthDelete:(删除数据规则). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataauth/delete")
    @ResponseBody
    public EcpBaseResponseVO dataAuthDelete(@Valid DataAuthVO vo) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        reqDto.setId(vo.getId());
        try {
            dataAuthManageRSV.deleteDataAuthById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * dataAuthFind:(查询数据规则). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/dataauth/find")
    @ResponseBody
    public FindDataAuthVO dataAuthFind(@Valid DataAuthVO vo) throws Exception{
        
        FindDataAuthVO res = new FindDataAuthVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        reqDto.setFuncId(vo.getFuncId());
        try {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * list:(查询规则明细列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datarule/list")
    @ResponseBody
    public List<DataRuleResDTO> dataRuleList(Model model, DataRuleVO vo) throws Exception{
    	List<DataRuleResDTO> list = new ArrayList<DataRuleResDTO>();
        DataRuleReqDTO info = new DataRuleReqDTO();
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        info.setPageNo(0);//不分页查询
        PageResponseDTO<DataRuleResDTO> t = dataAuthManageRSV.listDataRule(info);
        
//        String res = JSON.toJSONString(t.getResult());
        if(t.getResult()==null){
        	return list;
        }
        return t.getResult();
    }
    
    /**
     * 
     * dataFuncView:(跳转到数据功能). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/view")
    public String dataFuncView(){
        return "/staff/dataauth/data-func";
    }
    
    /**
     * 
     * dataFuncChsta:(数据功能状态变更). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/chsta")
    @ResponseBody
    public EcpBaseResponseVO dataFuncChsta(@Valid DataFuncVO vo) throws Exception{
    	EcpBaseResponseVO res = new EcpBaseResponseVO();
    	DataFuncReqDTO reqDto = new DataFuncReqDTO();
    	ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
    	try {
            dataAuthManageRSV.chstaDataFuncById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "数据功能状态修改失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
    	return res;
    }
    
    /**
     * 
     * dataFuncUpdate:(修改数据权限). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/update")
    @ResponseBody
    public EcpBaseResponseVO dataFuncUpdate(@Valid SaveDataFuncVO vo) throws Exception{
    	EcpBaseResponseVO res = new EcpBaseResponseVO();
    	DataFuncReqDTO reqDto = new DataFuncReqDTO();
    	ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
    	try {
            dataAuthManageRSV.updateDataFuncById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "修改数据功能失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
    	return res;
    }
    
    /**
     * 
     * dataFuncAdd:(添加数据功能). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/add")
    @ResponseBody
    public EcpBaseResponseVO dataFuncAdd(@Valid SaveDataFuncVO vo) throws Exception{
    	EcpBaseResponseVO res = new EcpBaseResponseVO();
    	DataFuncReqDTO reqDto = new DataFuncReqDTO();
    	ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
    	try {
            dataAuthManageRSV.saveDataFunc(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加数据功能失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
    	return res;
    }
    
    /**
     * 
     * gridList:(查询数据项列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/gridlist")
    @ResponseBody
    public Model dataFuncGridList(Model model, DataFuncVO vo) throws Exception{
    	DataFuncReqDTO info = vo.toBaseInfo(DataFuncReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        PageResponseDTO<DataFuncResDTO> t = dataAuthManageRSV.listDataFunc(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * list:(查询数据项列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/datafunc/list")
    @ResponseBody
    public String dataFuncList(DataFuncVO vo) throws Exception{
    	DataFuncReqDTO info = vo.toBaseInfo(DataFuncReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        info.setPageNo(0);//不分页查询
        PageResponseDTO<DataFuncResDTO> t = dataAuthManageRSV.listDataFunc(info);
        List<DataFuncResDTO> list = t.getResult();
        
        List<DataFuncTreeNodeVO> listNode = new ArrayList<DataFuncTreeNodeVO>();
        if(CollectionUtils.isNotEmpty(list)){
        	for (DataFuncResDTO dataFuncResDTO : list) {
        		DataFuncTreeNodeVO treeNodeVO = new DataFuncTreeNodeVO();
				ObjectCopyUtil.copyObjValue(dataFuncResDTO, treeNodeVO, null, true);
				if(StaffConstants.PublicParam.STATUS_INVALID.equals(treeNodeVO.getStatus())){
					Map<String, String> font = new HashMap<String, String>();
					font.put("color", "red");
					treeNodeVO.setFont(font);
				}else{
					Map<String, String> font = new HashMap<String, String>();
					treeNodeVO.setFont(font);
				}
				listNode.add(treeNodeVO);
			}
        }
        
        return JSON.toJSONString(listNode);
    }
    
    /**
     * 
     * fieldItemView:(跳转到结果集属性定义). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fielditem/view")
    public String fieldItemView(){
        return "/staff/dataauth/data-field-item";
    }
    
    /**
     * 
     * fieldItemAdd:(添加结果集属性). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fielditem/add")
    @ResponseBody
    public EcpBaseResponseVO fieldItemAdd(DataFieldItemVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataFieldItemReqDTO reqDto = new DataFieldItemReqDTO(); 
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.saveDataFieldItem(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加结果集属性失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * fieldItemUpdate:(修改结果集属性). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fielditem/update")
    @ResponseBody
    public EcpBaseResponseVO fieldItemUpdate(DataFieldItemVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataFieldItemReqDTO reqDto = new DataFieldItemReqDTO(); 
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        try {
            dataAuthManageRSV.updateDataFieldItemById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "修改结果集属性失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * fieldItemDelete:(删除结果集属性). <br/> 
     * 
     * @author linby 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fielditem/delete")
    @ResponseBody
    public EcpBaseResponseVO fieldItemDelete(DataFieldItemVO vo) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataFieldItemReqDTO reqDto = new DataFieldItemReqDTO();
        reqDto.setId(vo.getId());
        try {
            dataAuthManageRSV.deleteDataFieldItemById(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除结果集属性", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * gridList:(查询数据项列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fielditem/gridlist")
    @ResponseBody
    public Model fieldItemGridList(Model model, DataFieldItemVO vo) throws Exception{
        
        DataFieldItemReqDTO info = vo.toBaseInfo(DataFieldItemReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, info, null, false);
        if(vo.getListAll()!=null){
            info.setPageNo(0);//不分页查询
        }
        PageResponseDTO<DataFieldItemResDTO> t = dataAuthManageRSV.listDataFieldItem(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * list:(查询过滤规则明细列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fieldrule/list")
    @ResponseBody
    public Map<String,DataFieldRuleRespVO> fieldRuleList(Model model, DataFieldRuleVO vo) throws Exception{
    	//Map<itemId, DataFieldRuleRespVO>
    	Map<String,DataFieldRuleRespVO> map = new HashMap<String,DataFieldRuleRespVO>();
        
    	//查询结果集属性
        DataFieldItemReqDTO reqDto = new DataFieldItemReqDTO();
        reqDto.setFuncId(vo.getFuncId());//得到一个功能点下的属性集
        reqDto.setPageNo(0);//查全集
        PageResponseDTO<DataFieldItemResDTO> p = dataAuthManageRSV.listDataFieldItem(reqDto);
        if(p.getCount()<=0){//如果属性集为空直接返回
        	return map;
        }
        //封装返回
        for (DataFieldItemResDTO item : p.getResult()) {
        	DataFieldRuleRespVO respVO = new DataFieldRuleRespVO();
        	respVO.setItemId(item.getId());
        	respVO.setItemName(item.getName());
        	respVO.setItemAttrName(item.getAttrName());
        	respVO.setItemValueType(item.getValueType());
        	respVO.setItemValueFormate(item.getValueFormate());
        	respVO.setItemDefaultValue(item.getDefaultValue());
        	respVO.setFuncId(item.getFuncId());
        	
        	map.put(item.getId().toString(), respVO);
		}
    	
    	//查询明细
    	DataFieldRuleReqDTO info = new DataFieldRuleReqDTO();
        info.setAuthId(vo.getAuthId());
        info.setPageNo(0);//不分页查询
        PageResponseDTO<DataFieldRuleResDTO> t = dataAuthManageRSV.listDataFieldRule(info);
        if(t.getCount()<=0){
        	return map;
        }
        for (DataFieldRuleResDTO rule : t.getResult()) {
        	DataFieldRuleRespVO respVO = map.get(rule.getItemId().toString());
        	respVO.setId(rule.getId());
        	respVO.setItemValueFormate(rule.getValueFormate());
        	respVO.setItemDefaultValue(rule.getInputValue());
        	respVO.setAuthId(rule.getAuthId());
        	
        	map.put(rule.getItemId().toString(), respVO);//根据明细数据覆盖item
		}
        
        return map;
    }
    
    /**
     * 
     * fieldRuleSave:(保存规则明细[list]). <br/> 
     * 
     * @author linby 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/fieldrule/save")
    @ResponseBody
    public EcpBaseResponseVO fieldRuleSave(SaveDataFieldRuleBatchVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        DataAuthReqDTO reqDto = new DataAuthReqDTO();
        reqDto.setId(vo.getAuthId());
        List<DataFieldRuleReqDTO> fieldRuleArr = new ArrayList<DataFieldRuleReqDTO>();
        if(vo!=null && CollectionUtils.isNotEmpty(vo.getRuleArr()))
        for (DataFieldRuleVO ruleVO : vo.getRuleArr()) {//处理入参
        	DataFieldRuleReqDTO t = new DataFieldRuleReqDTO();
        	if(ruleVO.getItemId()==null){//itemId不存在；则未被勾选
        		continue;
        	}
        	ObjectCopyUtil.copyObjValue(ruleVO, t, null, false);
        	fieldRuleArr.add(t);
        }
        reqDto.setFieldRuleArr(fieldRuleArr);
        try {
            dataAuthManageRSV.saveDataFieldRuleBatch(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加规则配置失败", e);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
}

