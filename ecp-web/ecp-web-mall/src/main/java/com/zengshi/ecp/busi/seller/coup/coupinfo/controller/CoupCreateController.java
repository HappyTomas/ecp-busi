package com.zengshi.ecp.busi.seller.coup.coupinfo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.coup.coupinfo.vo.CoupInfoVO;
import com.zengshi.ecp.busi.seller.coup.coupinfo.vo.QueryCouponInfoVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupVarLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

 
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-21下午4:40:35  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/seller/coupinfo")
public class CoupCreateController extends EcpBaseController {
    
    private static String MODULE = CoupCreateController.class.getName();
    
    private static String URL = "/seller/coupon"; 

    @Resource
    private ICoupRSV coupRSV;
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;//分类信息

    @Resource
    private ICoupTypeRSV coupTypeRSV;
    
    /**
     * 
     * init:index. <br/> 
     * 
     * @author huanghe5
     * @param model
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model, QueryCouponInfoVO vo) {

        return URL + "/coupinfo-grid";
    }
    
    /**
     * 
     * init:页面选择优惠券
     * 
     * @author lisp
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/selectgrid")
    public String selectGrid(Model model, QueryCouponInfoVO vo) {
    	/*  // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = vo.toBaseInfo(CoupInfoReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);

        // 调用服务
        PageResponseDTO<CoupInfoRespDTO> pageInfo = coupRSV.queryCoupInfoPage(queryDTO);
        model.addAttribute("coupPage", pageInfo);*/
    	model.addAttribute("shopId", vo.getShopId());
        return "/seller/coupon/userule/coup-grid/coupinfo-select";
    }
    
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/selectlist")
    public String selectlist(Model model, QueryCouponInfoVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = vo.toBaseInfo(CoupInfoReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        // 调用服务
        PageResponseDTO<CoupInfoRespDTO> pageInfo = coupRSV.queryCoupInfoPage(queryDTO);
        model.addAttribute("coupPage", pageInfo);

        return "/seller/coupon/userule/coup-grid/coupinfo-list";
    }
    
    
    /**
     * 优惠券类型链接页面
     * 
     * @param model
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/type")
    public String coupType(Model model) {
        //查询优惠券类型列表
        //cache获得大类
      /*  Map<Long, CoupTypeRespDTO>  map= CoupCacheUtil.searchCoupTypeCache();
        
        model.addAttribute("coupTypeRespDTOMap", map);
        
        return "/coupon/coupinfo/coup-type-form";*/
        return "/seller/coupon/coup-type-grid";
    }
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/grid")
    public String grid(Model model, QueryCouponInfoVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = vo.toBaseInfo(CoupInfoReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        // 调用服务
        PageResponseDTO<CoupInfoRespDTO> pageInfo = coupRSV.queryCoupInfoPage(queryDTO);
        model.addAttribute("coupPage", pageInfo);

        return URL+"/list/coupinfo-grid-list";
    }
    
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/coupgrid")
    public String coupgrid(Model model, QueryCouponInfoVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = vo.toBaseInfo(CoupInfoReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        // 调用服务
        PageResponseDTO<CoupInfoRespDTO> pageInfo = coupRSV.queryCoupInfoPage(queryDTO);
        model.addAttribute("coupPage", pageInfo);

        return "/seller/prom/coupon/coupon-list";
    }
    
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/coupListgrid")
    public String coupListgrid(Model model, QueryCouponInfoVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = vo.toBaseInfo(CoupInfoReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        // 调用服务
        PageResponseDTO<CoupInfoRespDTO> pageInfo = coupRSV.queryCoupInfoPage(queryDTO);
        model.addAttribute("coupPage", pageInfo);

        return "/seller/coupon/send/coupselect/coup-select-list";
    }
    
    /**
     * 生效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/valid")
    @ResponseBody
    public EcpBaseResponseVO valid(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450019");
            }
            CoupReqDTO dto = new CoupReqDTO();
            CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
            coupInfoReqDTO.setId(new Long(id));
            
            List<CoupInfoReqDTO> coupInfoReqDTOs=new ArrayList<CoupInfoReqDTO>();
            coupInfoReqDTOs.add(coupInfoReqDTO);
            
            dto.setCoupInfoReqDTOs(coupInfoReqDTOs);
            
            coupRSV.validCoup(dto);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 失效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public EcpBaseResponseVO invalid(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450019");
            }
            CoupReqDTO dto = new CoupReqDTO();
            CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
            coupInfoReqDTO.setId(new Long(id));
            
            List<CoupInfoReqDTO> coupInfoReqDTOs=new ArrayList<CoupInfoReqDTO>();
            coupInfoReqDTOs.add(coupInfoReqDTO);
            
            dto.setCoupInfoReqDTOs(coupInfoReqDTOs);
            
            coupRSV.invalidCoup(dto);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 批量失效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/batchinvalid")
    @ResponseBody
    public EcpBaseResponseVO batchinvalid(Model model, @RequestParam("ids")
    String ids) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(ids)) {
                throw new BusinessException("web.coupon.450019");
            }
            
            CoupReqDTO dto = new CoupReqDTO();
            
            //解析前段传入字符串
            String[] idsArr=ids.split("#");
            
            int count=idsArr.length;
            
            if(count<=0){
                throw new BusinessException("web.coupon.450019");
            }
            List<CoupInfoReqDTO> coupInfoReqDTOs=new ArrayList<CoupInfoReqDTO>();
            
            for(int i=0;i<count;i++){
                CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
                coupInfoReqDTO.setId(new Long(idsArr[i]));
                coupInfoReqDTOs.add(coupInfoReqDTO);
            }
             dto.setCoupInfoReqDTOs(coupInfoReqDTOs);
             
             coupRSV.invalidCoup(dto);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 删除
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public EcpBaseResponseVO del(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450019");
            }
            CoupReqDTO dto = new CoupReqDTO();
            CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
            coupInfoReqDTO.setId(new Long(id));
            
            List<CoupInfoReqDTO> coupInfoReqDTOs=new ArrayList<CoupInfoReqDTO>();
            coupInfoReqDTOs.add(coupInfoReqDTO);
            
            dto.setCoupInfoReqDTOs(coupInfoReqDTOs);
            
            coupRSV.deleteBatchCoup(dto);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 已选择优惠券列表 
     * @param model
     * @param id
     * @param gdsList
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/coupList")
    public String coupList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("coupIds") String[] coupIds) throws Exception {
        
        List<CoupInfoVO> coupList=new ArrayList<CoupInfoVO>();
        model.addAttribute("coupList", coupList);
        
        //非空 需要查询列表(编辑 初始化进入非空，重新编辑单品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && coupIds.length==0){
            
            CoupVarLimitReqDTO coupVarLimitReqDTO=new CoupVarLimitReqDTO();
            coupVarLimitReqDTO.setCoupId(new Long(coupId));
            coupVarLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
            
            List<CoupVarLimitRespDTO> list=coupLimitRSV.queryCoupVAR(coupVarLimitReqDTO);
            
            if(!CollectionUtils.isEmpty(list)){
                
                for(CoupVarLimitRespDTO coupVarLimitRespDTO:list){
                    
                    CoupInfoVO  vo=new CoupInfoVO();
                    
                    CoupInfoReqDTO dto=new CoupInfoReqDTO();
                    dto.setId(new Long(coupVarLimitRespDTO.getCoupId2()));
                    
                    CoupInfoRespDTO respDTO= coupRSV.queryCoupInfo(dto);
                    
                    if(respDTO!=null){
                        ObjectCopyUtil.copyObjValue(respDTO, vo, "", false);
                        coupList.add(vo); 
                    }
                }
            
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(coupIds) && coupIds.length>0){
                
                //页面传入参数 组织
                List<String> _coupList = java.util.Arrays.asList(coupIds);
                //去重 过滤
                Set set = new HashSet(_coupList);
                _coupList=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(_coupList)){
                    //设置列表值
                    for(String id1:_coupList){
                        
                        if(!StringUtil.isEmpty(id1)){ 
                            
                            CoupInfoVO  vo=new CoupInfoVO();
                            
                            CoupInfoReqDTO dto=new CoupInfoReqDTO();
                            dto.setId(new Long(id1));
                            
                            CoupInfoRespDTO respDTO= coupRSV.queryCoupInfo(dto);
                            
                            if(respDTO!=null){
                                ObjectCopyUtil.copyObjValue(respDTO, vo, "", false);
                                coupList.add(vo); 
                            }
                        }
                    }
                  
                }
            }
        }
        
        return "/seller/coupon/userule/rule150/coup-info-table";
    }
}
