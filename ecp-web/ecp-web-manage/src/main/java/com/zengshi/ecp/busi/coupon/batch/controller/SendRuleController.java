package com.zengshi.ecp.busi.coupon.batch.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.coupon.batch.vo.CustVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.CoupInfoVO;
import com.zengshi.ecp.busi.coupon.couptype.vo.QueryCouponTypeVO;
import com.zengshi.ecp.busi.coupon.util.CoupConst;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-12-28下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/sendrule")
public class SendRuleController extends EcpBaseController {

    @Resource
    private ICoupTypeRSV coupTypeRSV;
    

    @Resource
    private ICoupRSV coupRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource 
    private ICoupDetailRSV coupDetailRSV;
    
    @Resource
    private IBaseSysCfgRSV baseSysCfgRSV;
    
    /**
     * 批量发送优惠券功能
     */
    private static String MODULE = SendRuleController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model) {
        
        List<BaseParamDTO> custLevelList= BaseParamUtil.fetchParamList(CoupConst.STAFF_CUST_LEVEL);
        model.addAttribute("custLevelList", custLevelList);
        return "/coupon/batch/send/batch-send-rule";
    }
    /**
     * 
     * select:页面列表
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("/select")
    public String select(Model model) {
        return "/coupon/batch/send/coupselect/coup-select-grid";
    }
    
    @RequestMapping(value = "/custgrid")
    public String custGrid(Model model,@RequestParam(value="companyId",required = false) String companyId) {
        return "/coupon/batch/send/cust/cust-grid";
    }
    
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Model grid(Model model, QueryCouponTypeVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupTypeReqDTO queryDTO = vo.toBaseInfo(CoupTypeReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);

        // 调用服务
        PageResponseDTO<CoupTypeRespDTO> page = coupTypeRSV.queryCoupTypePage(queryDTO);
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }
  
    /**
     * 保存
     * @param couponTypeVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO submit(Model model, @RequestParam("custIds") String[] custIds, @RequestParam("coupIds") String[] coupIds) {

        EcpBaseResponseVO vo=new EcpBaseResponseVO();
        try {
            
            int clen=custIds.length;
            int plen=coupIds.length;
            for(int i=0;i<clen;i++){
                for(int j=0;j<plen;j++){
                    try{
                        CoupCallBackReqDTO  dto=new CoupCallBackReqDTO();
                        dto.setCoupId(Long.valueOf(coupIds[j]));
                        dto.setStaffId(Long.valueOf(custIds[i]));
                        dto.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_20);//被动领取
                        dto.setCoupSum(1);
                        
                        CustInfoResDTO custDTO= custManageRSV.findCustInfoById(Long.valueOf(custIds[i]));
                        
                        //获得用户等级
                        dto.setCustLevel(custDTO.getCustLevelCode());
                        CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
                        coupInfoReqDTO.setId(Long.valueOf(coupIds[j]));
                        CoupInfoRespDTO respDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
                        Long shopId=-1l;
                        if(respDTO!=null){
                            shopId=respDTO.getShopId();
                        }
                        //根据优惠券id获得平台0 店铺级别
                        dto.setShopId(shopId);
                        coupDetailRSV.saveCoupGain(dto);
                    } catch (BusinessException e) {
                        LogUtil.error(MODULE, "报错了啦", e);
                    }
                }
            }

            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }

    /**
     * 选择优惠券列表 
     * @param model
     * @param ids
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/coupInfoList")
    public String coupInfoList(Model model, @RequestParam("ids") String[] ids) throws Exception {
        
        List<CoupInfoVO> coupInfoList=new ArrayList<CoupInfoVO>();
        model.addAttribute("coupInfoList", coupInfoList);
        
        //非空 需要查询列表(编辑 初始化进入非空，重新编辑列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(ids) && ids.length==0){ 
            
        }else{
            //新增 
            if(!StringUtil.isEmpty(ids) && ids.length>0){
                
                //页面传入参数 组织
                List<String> coupInfos = java.util.Arrays.asList(ids);
                //去重 过滤
                Set set = new HashSet(coupInfos);
                coupInfos=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(coupInfos)){
                    //设置列表值
                	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
                    for(String id:coupInfos){
                        if(!StringUtil.isEmpty(id)){ 
                            CoupInfoVO  vo=new CoupInfoVO();
                            CoupInfoReqDTO dto=new CoupInfoReqDTO();
                            dto.setId(new Long(id));
                            
                            CoupInfoRespDTO respDTO= coupRSV.queryCoupById(dto);
                            
                            if(respDTO!=null){
                            	//优惠码标识
                            	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
                            		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")&&StringUtil.isNotBlank(respDTO.getCoupCode())){
                            			continue;
                        	    	}
                            	}
                            	ObjectCopyUtil.copyObjValue(respDTO, vo, "", false);
                            	if(null!=vo.getUseRuleCode()&&vo.getUseRuleCode().contains("240")){
                            		vo.setCoupValue(respDTO.getCoupValue()/1000.0+"折");
                            	}else{
                            		vo.setCoupValue(respDTO.getCoupValue()/100.00+"元");
                            	}
                            	coupInfoList.add(vo); 
                            }
                        }
                    }
                  
                }
            }
        }
        return "/coupon/batch/send/couptable/coup-table";
    }

    /**
     * 选择客户列表 
     * @param model
     * @param ids
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/custList")
    public String custList(Model model, @RequestParam("ids") String[] ids) throws Exception {
        
        List<CustVO> custList=new ArrayList<CustVO>();
        model.addAttribute("custList", custList);
        
        //非空 需要查询列表(编辑 初始化进入非空，重新编辑列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(ids) && ids.length==0){ 
            
        }else{
            //新增 
            if(!StringUtil.isEmpty(ids) && ids.length>0){
                
                //页面传入参数 组织
                List<String> custs = java.util.Arrays.asList(ids);
                //去重 过滤
                Set set = new HashSet(custs);
                custs=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(custs)){
                    //设置列表值
                    
                    for(String id:custs){
                        if(!StringUtil.isEmpty(id)){
                            CustVO  vo=new CustVO();
                            CustInfoResDTO respDTO=custManageRSV.findCustInfoById(Long.valueOf(id));
                            if(respDTO!=null){
                                ObjectCopyUtil.copyObjValue(respDTO, vo, "", false);
                                custList.add(vo); 
                            }
                        }
                    }
                  
                }
            }
        }
        return "/coupon/batch/send/custtable/cust-table";
    }
}
