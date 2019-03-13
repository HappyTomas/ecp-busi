package com.zengshi.ecp.busi.coup.buyer.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.coup.buyer.controller.vo.MyCoupQueryVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015-12-29下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */
@Controller
@RequestMapping(value="/gaincoup")
public class gainCoupController extends EcpBaseController {
    
    private static String MODULE = gainCoupController.class.getName();
    
   @Resource 
   private ICoupDetailRSV coupDetailRSV;
   
   @Resource 
   private ICoupRSV coupRSV;
   
   @Resource
   private ICustInfoRSV custInfoRSV;
   
   //加载页面
    @RequestMapping(value="/index")
    public String init(Model model,MyCoupQueryVO myCoupQueryVO) throws BusinessException{
        //排序字段设置
        myCoupQueryVO.setPageSize(9);
        model.addAttribute("mycoupPage", this.queryPageData(model, myCoupQueryVO));
        return "/coup/gaincoup/gaincoup";
    }
    
    /**
     * 查询功能
     * @param model
     * @param myCoupQueryVO
     * @author huangjx
     * @throws Exception 
     */
    private Model query(Model model,MyCoupQueryVO myCoupQueryVO) throws Exception{
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(this.queryPageData(model, myCoupQueryVO));
        return super.addPageToModel(model, respVO);
    }
    /**
     * 列表数据 查询
     * @param model
     * @param myCoupQueryVO
     * @return
     * @author huangjx
     */
    private  PageResponseDTO<CoupInfoRespDTO> queryPageData(Model model,MyCoupQueryVO myCoupQueryVO){
        // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = myCoupQueryVO.toBaseInfo(CoupInfoReqDTO.class);
        // 组织参数
        ObjectCopyUtil.copyObjValue(myCoupQueryVO, queryDTO, "", false);
        queryDTO.setStatus(CouponConstants.CoupInfo.STATUS_1);
        
       /* PageResponseDTO<CoupMeRespDTO> page= coupDetailRSV.queryCoupDetailPage(queryDTO);*/
        PageResponseDTO<CoupInfoRespDTO> page= coupRSV.queryCoupInfoPage(queryDTO);
        
        return page;
    }
    /**
     * 
     * @param model
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @RequestMapping(value="/list")
    public String myCoupList(Model model,MyCoupQueryVO myCoupQueryVO) throws BusinessException{
       
        model.addAttribute("mycoupPage", this.queryPageData(model, myCoupQueryVO));
        return "/coup/gaincoup/div/gaincoup-list";
    }
    
    
    /**
     * 优惠券立即领取
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/gain")
    @ResponseBody
    public EcpBaseResponseVO gain(Model model, @RequestParam("id") String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450002");
            }
            CoupCallBackReqDTO  dto=new CoupCallBackReqDTO();
            dto.setCoupId(Long.valueOf(id));
            dto.setStaffId(dto.getStaff().getId());
            dto.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_10);//主动领取
            dto.setCoupSum(1);
            //获取当前登录用户
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setId(custReq.getStaff().getId());
            //获取用户更多信息
            CustInfoResDTO custRes = custInfoRSV.getCustInfoById(custReq);
            //获得用户等级
            dto.setCustLevel(custRes.getCustLevelCode());
            CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
            coupInfoReqDTO.setId(Long.valueOf(id));
            CoupInfoRespDTO respDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
            Long shopId=-1l;
            if(respDTO!=null){
                shopId=respDTO.getShopId();
            }
            //根据优惠券id获得平台0 店铺级别
            dto.setShopId(shopId);
            coupDetailRSV.saveCoupGain(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
 
}


