package com.zengshi.ecp.busi.staff.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.AcctInfoVO;
import com.zengshi.ecp.busi.staff.vo.AcctTypeVO;
import com.zengshi.ecp.busi.staff.vo.FileImportRespVO;
import com.zengshi.ecp.busi.staff.vo.FileImportVO;
import com.zengshi.ecp.busi.staff.vo.SaveAcctTypeVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/** 
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 资金账户管理<br>
 * Date:2015年8月22日下午2:40:43  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * <pre>
 * Urls:
 * acctview     page   : GET  /acct/grid/
 * acctquery    ajax   : POST /acct/gridlist/
 * </pre>
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/acct")
public class AcctManagerController extends EcpBaseController {
    
    private static String MODULE = AcctManagerController.class.getName();
    
    @Resource
    private IAcctManageRSV acctManageRSV;
    
    /**
     * 
     * grid:(跳转到资金账户管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/grid")
    public String grid(){
        return "/staff/acct/acct-grid";
    }
    
    /**
     * 
     * importpage:(跳转到资金账户导入页面). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/importpage")
    public String importPage(){
        return "/staff/acct/importdata/acct-importdata";
    }
    
    /**
     * 
     * gridList:(查询资金账户列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, AcctInfoVO vo) throws Exception{
        
        ShopRelatedAcctsReqDTO info = vo.toBaseInfo(ShopRelatedAcctsReqDTO.class);
        info.setShopId(vo.getShopId());
        info.setAcctType(vo.getAcctType());
        info.setStaffName(vo.getStaffName());
        PageResponseDTO<AcctInfoResDTO> t = acctManageRSV.listAccountByShop(info); 
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * downloadTemplate:(下载资金账户导入所需要EXCEL模板文件). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/downloadtemplate")
    public String downloadTemplate(){
        return "js-busi/staff/download/accttemplate.xls";
    }
    
    /**
     * 
     * importData:(导入资金账户). <br/> 
     * 
     * @author linby 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/importdata")
    @ResponseBody
    public EcpBaseResponseVO importData(Model model)  throws Exception{
        FileImportReqDTO aiReqDto = new FileImportReqDTO();
        EcpBaseResponseVO ebResVO = new EcpBaseResponseVO();
        try {
            acctManageRSV.importAcctInfoCapitalIncrementAboutShop(aiReqDto);
            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ebResVO.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        }
        return ebResVO;
    }
    
    /**
     * 
     * uploadfile:(上传资金账户文件). <br/> 
     * 
     * @author linby 
     * @param excel
     * @param model
     * @param request
     * @param response
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/uploadfile", method=RequestMethod.POST)
    @ResponseBody
    @NativeJson(true)
    public String uploadFile(@RequestParam(value = "excelFile", required = false) MultipartFile excel,
            Model model,HttpServletRequest request, HttpServletResponse response)  throws Exception{
    	JsonResultThreadLocal.set(false);
        if(excel==null||excel.isEmpty()){
            LogUtil.info(MODULE, "资金账户导入文件不存在");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "文件不存在" });
        }
        String fileId = "";
        String oriFileName = excel.getOriginalFilename();
        String[] fileNamea = oriFileName.split("\\.");
        String fileName =    fileNamea[0]+"_"+UUID.randomUUID();
        String fileExtName = fileNamea[1];
        try {
            fileId = FileUtil.saveFile(excel.getBytes(), fileName, fileExtName);
        } catch (IOException e) {
            LogUtil.error(MODULE, "文件保存失败",e);
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "文件保存失败" });
        }
        FileImportRespVO respVO = new FileImportRespVO();
        respVO.setFileId(fileId);
        respVO.setFileName(fileName);
        respVO.setFileExtName(fileExtName);
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        
        return JSON.toJSONString(respVO);
    }
    
    /**
     * 
     * importDataTemp:(导入资金账户临时数据). <br/> 
     * 
     * @author linby
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/importdatatemp")
    @ResponseBody
    public EcpBaseResponseVO importDataTemp(Model model, FileImportVO vo) throws Exception{
    	EcpBaseResponseVO ebResVO = new EcpBaseResponseVO();
    	if(StringUtil.isBlank(vo.getFileId())){
    		
    	}
    	FileImportReqDTO aiReqDto = new FileImportReqDTO();
    	ObjectCopyUtil.copyObjValue(vo, aiReqDto, null, true);
    	try {
			acctManageRSV.importAcctInfoTempsAboutShop(aiReqDto);
			ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ebResVO.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
		}
    	
    	return ebResVO;
    }
    
    /**
     * 
     * typeGrid:(跳转到资金账户类型管理列表). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/grid")
    public String typeGrid(){
        return "/staff/acct/type-grid";
    }
    
    /**
     * 
     * tempGridList:(查询资金账户临时数据对象列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/temp/gridlist")
    @ResponseBody
    public Model tempGridList(Model model, AcctInfoVO vo) throws Exception{
        
    	AcctInfoTempReqDTO info = vo.toBaseInfo(AcctInfoTempReqDTO.class);
    	info.setCreateStaff(info.getStaff().getId());
        PageResponseDTO<AcctInfoTempResDTO> t = acctManageRSV.listAcctInfoTemp(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * typeGridList:(查询资金账户列表数据). <br/> 
     * 
     * @author linby 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/type/gridlist")
    @ResponseBody
    public Model typeGridList(Model model, AcctTypeVO vo) throws Exception{
        
        AcctTypeReqDTO info = vo.toBaseInfo(AcctTypeReqDTO.class);
        info.setShopId(vo.getShopId());
        info.setAcctType(vo.getAcctType());
        info.setAdaptType(vo.getAdaptType());
        PageResponseDTO<AcctTypeResDTO> t = acctManageRSV.queryAcctType(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        @SuppressWarnings("rawtypes")
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * typeAdd:(增加资金账户类型). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/add")
    @ResponseBody
    public EcpBaseResponseVO typeAdd(@Valid SaveAcctTypeVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AcctTypeReqDTO reqDto = new AcctTypeReqDTO();
        reqDto.setAcctType(vo.getAcctType());
        reqDto.setAdaptType(vo.getAdaptType());
        reqDto.setShopId(vo.getShopId());
        reqDto.setDeductOrderRatio(vo.getDeductOrderRatio());
        try { 
            acctManageRSV.saveAcctType(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * typeUpdate:(修改资金账户类型). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/update")
    @ResponseBody
    public EcpBaseResponseVO typeUpdate(@Valid SaveAcctTypeVO vo)  throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AcctTypeReqDTO reqDto = new AcctTypeReqDTO();
        reqDto.setAcctType(vo.getAcctType());
        reqDto.setAdaptType(vo.getAdaptType());
        reqDto.setShopId(vo.getShopId());
        reqDto.setDeductOrderRatio(vo.getDeductOrderRatio());
        try {
            acctManageRSV.updateAcctType(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }
    
    /**
     * 
     * typeDelete:(删除资金账户类型). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/delete")
    @ResponseBody
    public EcpBaseResponseVO typeDelete(@Valid AcctTypeVO vo)  throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        AcctTypeReqDTO reqDto = new AcctTypeReqDTO();
        reqDto.setAcctType(vo.getAcctType());
        reqDto.setAdaptType(vo.getAdaptType());
        reqDto.setShopId(vo.getShopId());
        try {
            acctManageRSV.deleteAcctType(reqDto);
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            LogUtil.error(MODULE, "删除资金账户类型异常", e);
        }
        
        return res;
    }
    
    /**
     * 
     * typeAddPage:(跳转到资金账户类型[新增]). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/addpage")
    public String typeAddPage(Model model){
        return "/staff/acct/type-add";
    }
    
    /**
     * 
     * typeUpadtePage:(跳转到资金账户类型[修改]). <br/> 
     * 
     * @author linby 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/type/updatepage")
    public String typeUpdatePage(Model model, @Valid AcctTypeVO vo)  throws Exception{
        AcctTypeReqDTO  reqDto = new AcctTypeReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        AcctTypeResDTO resDto = acctManageRSV.findAcctType(reqDto);
        model.addAttribute("acctType", resDto);
        return "/staff/acct/type-update";
    }
}

