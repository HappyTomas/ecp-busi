/**
 * 
 */
package com.zengshi.ecp.order.service.busi.impl;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dao.mapper.busi.OrdPayRelMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdPayRel;
import com.zengshi.ecp.order.dao.model.OrdPayRelCriteria;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOperChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.ImOrdBelongService;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月10日下午2:44:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
public class OrdOperChkSVImpl implements IOrdOperChkSV {
    
    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private OrdPayRelMapper ordPayRelMapper;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    private static final String MODULE = OrdOperChkSVImpl.class.getName();
    
    public final static HashMap<String,String> operMap = new HashMap<String,String>(); 
    
    static {  
        //01操作为付款，value为订单状态01
        operMap.put("01", "01");
        //02操作为发货，value为订单状态为02,04
        operMap.put("02", "02,04");
        //03操作为收货，value为订单状态为05
        operMap.put("03", "05");  
        //04操作为取消，value为订单状态为01
        operMap.put("04", "01");
        //05操作为线下审核，value为订单状态为01
        operMap.put("05", "01");       
    } 

    @Override
    public ROrdCartsChkResponse queryOrdOperChk(ROrderDetailsRequest info) {
        String msg="";
        ROrdCartsChkResponse res=new ROrdCartsChkResponse();
        res.setStatus(false);
        String oper=info.getOper();
        String statusSet=operMap.get(oper);
        if(StringUtil.isBlank(statusSet)){
            res.setMsg("订单状态已更新，请刷新页面");
            return res;
            //throw new BusinessException("该订单无法进行该操作，请重新刷新页面");
        }
        String orderId=info.getOrderId();
        OrdPayRelCriteria ordPayRelCriteriaQRY = new OrdPayRelCriteria();
        OrdPayRelCriteria.Criteria criteriaQRY = ordPayRelCriteriaQRY.createCriteria();
        criteriaQRY.andJoinOrderidEqualTo(orderId);
        List<OrdPayRel> list = ordPayRelMapper.selectByExample(ordPayRelCriteriaQRY);
        if(list !=null && list.size() >= 1){
            //线上支付审核
            for(OrdPayRel resp : list){
                OrdMain ordMain=ordMainSV.queryOrderByOrderId(resp.getOrderId());
                if(StringUtil.isEmpty(ordMain)){
                    res.setMsg("订单："+resp.getOrderId()+"不存在，请重新刷新页面");
                    return res;
                    //throw new BusinessException("该订单不存在，请重新刷新页面");
                }
                String status=ordMain.getStatus();
                boolean flag=indexOfString(status,statusSet);
                if(flag==false){
                    res.setMsg("订单："+resp.getOrderId()+"状态已更新，请刷新页面");
                    //throw new BusinessException("该订单无法进行该操作，请重新刷新页面");
                    return res;
                }
                res.setStatus(true);
            }
        }else{
            //线下支付审核
            OrdMain ordMain=ordMainSV.queryOrderByOrderId(orderId);
            if(StringUtil.isEmpty(ordMain)){
                res.setMsg("订单："+orderId+"不存在，请重新刷新页面");
                return res;
                //throw new BusinessException("该订单不存在，请重新刷新页面");
            }
            String status=ordMain.getStatus();
            boolean flag=indexOfString(status,statusSet);
            if(flag==false){
                res.setMsg("订单："+orderId+"状态已更新，请刷新页面");
                //throw new BusinessException("该订单无法进行该操作，请重新刷新页面");
                return res;
            }
            res.setStatus(true);
          
        }
        return res;
    }

    /*
     * 判断字符串是否包含一些字符 indexOf
     */
    public static boolean indexOfString(String src, String dest) {
        boolean flag = false;
        if (src.indexOf(dest)!=-1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public ROrdCartsChkResponse queryBackOperChk(ROrderDetailsRequest info) {
        ROrdCartsChkResponse res=new ROrdCartsChkResponse();
        res.setStatus(false);
        String oper=info.getOper();
        switch (oper) {
        //退货申请
        case "00":
          //判断是否可以申请操作
            SRefundInfo sRefundInfo = this.ordBackApplySV.queryBackGdsStatus(info.getOrderId());
            if(sRefundInfo != null && "1".equals(sRefundInfo.getRefundStatus())){
                LogUtil.info(MODULE, "订单："+info.getOrderId()+"该订单已在退货流程中");
                res.setMsg("订单已在退货流程中");
                return res; 
            }
            break;
          //退款申请
        case "01":
            SOrderDetailsMain  sOrderDetailsMain = this.ordMainSV.queryOrderDetailsMain(info.getOrderId());
            if(("04".equals(sOrderDetailsMain.getStatus()))){  //部分发货
                LogUtil.info(MODULE, "订单："+info.getOrderId()+"订单已部分发货不允许退款");
                res.setMsg("订单已部分发货不允许退款");
                return res;
            }
            if(!("02".equals(sOrderDetailsMain.getStatus()))){  //不是待发货
                LogUtil.info(MODULE, "订单："+info.getOrderId()+"订单状态已更新");
                res.setMsg("订单状态已更新，请刷新页面");
                return res;
            }
            SRefundInfo sRefundInfo1 = this.ordBackApplySV.queryRefundStatus(info.getOrderId());
            if(sRefundInfo1!= null && "1".equals(sRefundInfo1.getRefundStatus())){
                LogUtil.info(MODULE, "订单："+info.getOrderId()+"该订单已在退款流程中");
                res.setMsg("订单已在退款流程中");
                return res; 
            } else if(sRefundInfo1!= null && "2".equals(sRefundInfo1.getRefundStatus())){
                LogUtil.info(MODULE, "订单："+info.getOrderId()+"订单含有虚拟产品不允许退款");
                res.setMsg("订单含有虚拟产品不允许退款");
                return res;
            } 
            break;
          //退货买家确认发货
        case "02":
            //校验申请状态是否正确
            ROrderBackReq rOrderBackReq = new ROrderBackReq();
            rOrderBackReq.setOrderId(info.getOrderId());
            rOrderBackReq.setBackId(info.getBackId());
            RBackApplyResp  chkResp =this.ordBackApplySV.queryOrdBackApply(rOrderBackReq);
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_SEND.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+info.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_SEND);
                throw new BusinessException("申请单状态已更新，请刷新页面");
            }
            break;

        default:
            break;
        }
        res.setStatus(true);
        return res;
    }

    @Override
    public ROrdCartsChkResponse queryChkStatus(ROrderDetailsRequest rOrderDetailsRequest) {
        ROrdCartsChkResponse res=new ROrdCartsChkResponse();
        res.setStatus(false);
        String oper=rOrderDetailsRequest.getOper();
        switch (oper) {
        //开票
        case "00":
          //判断是否可以开票操作
            SRefundInfo sRefundInfo = this.ordBackApplySV.queryBackGdsStatus(rOrderDetailsRequest.getOrderId());
            if(sRefundInfo != null && "1".equals(sRefundInfo.getRefundStatus())){
                LogUtil.info(MODULE, "订单："+rOrderDetailsRequest.getOrderId()+"该订单已在退货流程中");
                res.setMsg("订单已在退货流程中");
                return res; 
            }
            break;
        //判断订单号是否属于登录用户
        case "01":
            SBaseSplitInfo sBaseSplitInfo = new SBaseSplitInfo();
            sBaseSplitInfo.setOrderId(rOrderDetailsRequest.getOrderId());
            sBaseSplitInfo.setStaffId(rOrderDetailsRequest.getStaff().getId());
            OrdMain ordMain =this.ordMainSV.queryOrderByIdAndStaff(sBaseSplitInfo);
            if(ordMain == null){
                LogUtil.info(MODULE, "订单："+rOrderDetailsRequest.getOrderId()+"该订单不属于"+rOrderDetailsRequest.getStaff().getId());
                res.setMsg("订单不属于该用户");
                return res; 
            }
            break;
        default:
            break;
        }
        res.setStatus(true);
        return res;
    }

    @Override
    public ROrdCartsChkResponse queryShopChkStatus(ROrderDetailsRequest rOrderDetailsRequest,
            List<ShopInfoResDTO> shopInfoResDTOs) {
        ROrdCartsChkResponse res=new ROrdCartsChkResponse();
        res.setStatus(false);
        String oper=rOrderDetailsRequest.getOper();
        switch (oper) {
        //判断订单号是否属于登录店铺
        case "00":
            for(ShopInfoResDTO sirdto :shopInfoResDTOs){
                SBaseSplitInfo sBaseSplitInfo = new SBaseSplitInfo();
                sBaseSplitInfo.setOrderId(rOrderDetailsRequest.getOrderId());
                sBaseSplitInfo.setShopId(sirdto.getId());
                OrdMain ordMain =this.ordMainSV.queryOrderByIdAndShop(sBaseSplitInfo);
                if(ordMain != null){
                    res.setStatus(true);
                    return res; 
                }
            }
            LogUtil.info(MODULE, "订单："+rOrderDetailsRequest.getOrderId()+"该订单不属于"+rOrderDetailsRequest.getStaff().getId());
            LogUtil.info(MODULE, JSON.toJSONString(shopInfoResDTOs));
            res.setMsg("订单不属于该用户");
            return res;
        default:
            break;
        }
        res.setStatus(true);
        return res;
    }


}
