package com.zengshi.ecp.busi.coupon.coupinfo.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.CoupInfoVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.CoupVO;
import com.zengshi.ecp.busi.coupon.couptype.vo.CouponTypeVO;
import com.zengshi.ecp.busi.coupon.util.CoupConst;
import com.zengshi.ecp.busi.prom.PromUtil;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-10-20下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/coup")
public class CoupController extends EcpBaseController {

    /**
     * 优惠券小类功能
     */
    private static String MODULE = CoupController.class.getName();

    @Resource
    private ICoupRSV coupRSV;
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;
    
    @Resource
    private ICoupTypeRSV coupTypeRSV;

    @Resource
    private IBaseSysCfgRSV baseSysCfgRSV;
    
    
    /**
     * 优惠券小类 保存
     * 
     * @param coupVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/coupCode")
    @ResponseBody
    public EcpBaseResponseVO coupCode() {
    	EcpBaseResponseVO vo=new EcpBaseResponseVO();
        try {
           //优惠码
           String coupCode=coupRSV.coupCode();
           vo.setResultMsg(coupCode);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return vo;
    }
    
    
    /**
     * 
     * coupCheck:(验证优惠券小类). <br/> 
     * 
     * @author panjs 
     * @param coupVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/coupCheck")
    @ResponseBody
    public CoupVO coupCheck(@ModelAttribute("coupVO") @Valid CoupVO coupVO) {
        try { 
           CoupReqDTO  dto= this.check(coupVO);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            coupVO.setResultMsg(e.getErrorMessage());
            coupVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }
        return coupVO;
    }
    
    
    /**
     * 优惠券小类 保存
     * 
     * @param coupVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public CoupVO save(@ModelAttribute("coupVO") @Valid CoupVO coupVO) {
        try {
           CoupReqDTO  dto= this.check(coupVO);
           dto.getCoupInfoReqDTO().setStatus(CouponConstants.CoupInfo.STATUS_2);
           
           //优惠码
           String coupCode="";
           if(StringUtil.isEmpty(dto.getCoupInfoReqDTO().getId())){
               //新增
               coupCode=coupRSV.saveCoup(dto);
           }
           if(!StringUtil.isEmpty(dto.getCoupInfoReqDTO().getId())){
               //修改
               coupCode=coupRSV.saveEditCoup(dto);
           }
           coupVO.getCoupInfoVO().setCoupCode(coupCode);
           coupVO.setResultMsg(ResourceMsgUtil.getMessage("coup.result.success.msg", null));

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            coupVO.setResultMsg(e.getErrorMessage());
            coupVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return coupVO;
    }

    /**
     * 优惠券小类 提交
     * 
     * @param coupVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public CoupVO submit(@ModelAttribute("coupVO")
    @Valid
    CoupVO coupVO) {

        try {
            CoupReqDTO  dto= this.check(coupVO);
            dto.getCoupInfoReqDTO().setStatus(CouponConstants.CoupInfo.STATUS_1);
            
            //优惠码
            String coupCode="";
            if(StringUtil.isEmpty(dto.getCoupInfoReqDTO().getId())){
                //新增
                coupCode=coupRSV.saveCoup(dto);
            }
            else if(!StringUtil.isEmpty(dto.getCoupInfoReqDTO().getId())){
                //修改
                coupCode=coupRSV.saveEditCoup(dto);
            }
            coupVO.getCoupInfoVO().setCoupCode(coupCode);
            coupVO.setResultMsg(ResourceMsgUtil.getMessage("coup.result.success.msg", null));

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            coupVO.setResultMsg(e.getErrorMessage());
            coupVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return coupVO;
    }

    /**
     * 优惠券tab页面  编辑
     * 
     * @return
     * @author huangjx
     */
    @RequestMapping("/edit/{coupId}")
    public String editCoupTab(@PathVariable("coupId")
    String coupId, Model model) {
    	//优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        this.queryCoup(CoupConst.DO_ACTION_EDIT, model, coupId);
        return "/coupon/coupinfo/coup-tab";
    }

    /**
     * 优惠券tab页面  复制
     * 
     * @return
     * @author huangjx
     */
    @RequestMapping("/copy/{coupId}")
    public String copyCoupTab(@PathVariable("coupId")
    String coupId, Model model) {
    	//优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        this.queryCoup(CoupConst.DO_ACTION_COPY, model, coupId);
        return "/coupon/coupinfo/coup-tab";
    }
    /**
     * 优惠券tab页面  详情
     * 
     * @return
     * @author huangjx
     */
    @RequestMapping("/view/{coupId}")
    public String viewCoupTab(@PathVariable("coupId")
    String coupId, Model model) {
    	//优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        this.queryCoup(CoupConst.DO_ACTION_VIEW, model, coupId);
        return "/coupon/coupinfo/coup-tab";
    }
    /**
     * 优惠券tab页面  新增调整
     * @return
     * @author huangjx
     */
    @RequestMapping("/add/{coupTypeId}")
    public String coupTab(@PathVariable("coupTypeId") String coupTypeId,Model model){
    	//优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        // 优惠券大类id
        model.addAttribute("coupTypeId", coupTypeId);
        CouponTypeVO couponTypeVO = new CouponTypeVO();

        model.addAttribute("couponTypeVO", couponTypeVO);

        CoupTypeReqDTO queryDTO = new CoupTypeReqDTO();
        queryDTO.setId(new Long(coupTypeId));

        CoupTypeRespDTO dto = new CoupTypeRespDTO();

        dto = coupTypeRSV.queryCoupType(queryDTO);

        ObjectCopyUtil.copyObjValue(dto, couponTypeVO, "", false);
        if (!StringUtil.isEmpty(couponTypeVO.getUseRuleCode())) {
            Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getUseRuleCode());
            couponTypeVO.setUseMap(new HashMap(map1));
        }
        if (!StringUtil.isEmpty(couponTypeVO.getGetRuleCode())) {
            Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getGetRuleCode());
            couponTypeVO.setReceiveMap(new HashMap(map1));
        }
        CoupVO  coupVO=new CoupVO();
        CoupInfoVO vo=new CoupInfoVO();
        //有效日期类型
        vo.setEffType(CouponConstants.CoupInfo.EFF_TYPE_0);
        coupVO.setCoupInfoVO(vo);
        
        model.addAttribute("coupVO", coupVO);
        
        //面额字段 false 不展示  true展示
        model.addAttribute("ifShowCoupValue",this.setIfShowCoupValue(couponTypeVO.getUseMap()));
        
       
        return "/coupon/coupinfo/coup-tab";
    }

    /**
     * 编辑 详情查看
     * 
     * @param doAction
     * @param model
     * @param coupId
     * @author huangjx
     */
    private void queryCoup(String doAction, Model model, String coupId) {
        
        // 返回页面vo
        CoupVO coupVO = new CoupVO();
        // 查询
        model.addAttribute("doAction", doAction);
        coupVO.setDoAction(doAction);
        
        CoupInfoReqDTO dto=new CoupInfoReqDTO();
        dto.setId(new Long(coupId));
        CoupInfoRespDTO respDTO= coupRSV.queryCoupInfo(dto);
        //优惠券基础信息
        CoupInfoVO vo=new CoupInfoVO();
        ObjectCopyUtil.copyObjValue(respDTO, vo, "", false);
        
        if(respDTO.getCoupValue()!=null){
            vo.setCoupValue(respDTO.getCoupValue().toString());
        }
        
        //有效日期类型
        if(StringUtil.isEmpty(doAction)){
            vo.setEffType(CouponConstants.CoupInfo.EFF_TYPE_0);
        }
        //COPY  不复制名称
        if(CoupConst.DO_ACTION_COPY.equals(doAction)){
            vo.setCoupName("");
            vo.setCoupCode("");
        }
        
        //优惠券共同使用标记 
 	   if(StringUtils.isNotBlank(respDTO.getUseRuleCode())){
 		   Map map1 = (Map<String, Object>) JSON.parse(respDTO.getUseRuleCode());
 		   if (map1.get("150") != null) {
 	           vo.setCoupExclude(map1.get("150").toString());
 		   }
 	   }
 	   
        coupVO.setCoupInfoVO(vo);
        
        //优惠券领取信息
        CoupGetLimitReqDTO limitDTO=new CoupGetLimitReqDTO();
        limitDTO.setCoupId(new Long(coupId));
        List<CoupGetLimitRespDTO>  limitList=coupLimitRSV.queryCoupGet(limitDTO);
        //转成map 显示
        if(!CollectionUtils.isEmpty(limitList)){
             for(CoupGetLimitRespDTO coupGetLimitRespDTO:limitList){
                 if(!StringUtil.isEmpty(coupGetLimitRespDTO.getGainRuleValue())){
                     Map map1=new HashMap(); 
                     JSONArray ja= JSON.parseArray(coupGetLimitRespDTO.getGainRuleValue());
                     for (int i = 0; i < ja.size(); i++) {
                         CoupGetReqDTO getdto = new CoupGetReqDTO();
                         getdto = JSON.parseObject(ja.getString(i), CoupGetReqDTO.class);
                         map1.put("level-"+getdto.getCustLevel(), getdto.getCustLevel());
                         map1.put("num-"+getdto.getCustLevel(), getdto.getGainNum());
                         map1.put("start-"+getdto.getCustLevel(), getdto.getStartTime());
                         map1.put("end-"+getdto.getCustLevel(), getdto.getEndTime());
                     }
                     coupVO.setReceiveMap(map1);
                 }
             }
        }
        
        // 优惠券大类id
        model.addAttribute("coupTypeId", respDTO.getCoupTypeId());
        CouponTypeVO couponTypeVO = new CouponTypeVO();

        model.addAttribute("couponTypeVO", couponTypeVO);

        CoupTypeReqDTO queryDTO = new CoupTypeReqDTO();
        queryDTO.setId(new Long(respDTO.getCoupTypeId()));

        CoupTypeRespDTO typedto = new CoupTypeRespDTO();

        typedto = coupTypeRSV.queryCoupType(queryDTO);

        ObjectCopyUtil.copyObjValue(typedto, couponTypeVO, "", false);
        if (!StringUtil.isEmpty(couponTypeVO.getUseRuleCode())) {
            Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getUseRuleCode());
            couponTypeVO.setUseMap(new HashMap(map1));
        }
        if (!StringUtil.isEmpty(couponTypeVO.getGetRuleCode())) {
            Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getGetRuleCode());
            couponTypeVO.setReceiveMap(new HashMap(map1));
        }
    
        //面额字段 false 不展示  true展示
        model.addAttribute("ifShowCoupValue",this.setIfShowCoupValue(couponTypeVO.getUseMap()));
        
        model.addAttribute("coupVO", coupVO);
    }

    /**
     * vo 转为dto 优惠券基础信息
     * 
     * @param vo
     * @param dto
     * @author huangjx
     */
    private void tranlateCoupInfo(CoupVO vo, CoupReqDTO coupReqDTO) {

        CoupInfoReqDTO dto = new CoupInfoReqDTO();
        if (vo == null) {
            throw new BusinessException("web.coupon.450002");
        }
        if (vo.getCoupInfoVO() == null) {
            throw new BusinessException("web.coupon.450003");
        }
        if(CoupConst.DO_ACTION_COPY.equals(vo.getDoAction())){
            //copy  为和create区别
           vo.getCoupInfoVO().setId(null);
        } 
        // 是否发行量限制 1 无
        if (CoupConst.CHECK_PUBLISH_1.equals(vo.getCoupInfoVO().getCheckPublish())) {
            vo.getCoupInfoVO().setCoupNum(-1l);
        }
        // 是否发行量限制 0有
        if (CoupConst.CHECK_PUBLISH_0.equals(vo.getCoupInfoVO().getCheckPublish())) {
            // 发行量为空
            if (StringUtil.isEmpty(vo.getCoupInfoVO().getCoupNum())) {
                throw new BusinessException("web.coupon.450004");
            }
            // 发行量小于等于0
            if (vo.getCoupInfoVO().getCoupNum().longValue() <= 0) {
                throw new BusinessException("web.coupon.450005");
            }
        }
        //如果浮动类型  日期字段清空  
        if(CouponConstants.CoupInfo.EFF_TYPE_1.equals(vo.getCoupInfoVO().getEffType())){
            //固定类型
            //验证生效开始 截止时间非空  并且截止时间大于等于生效时间
            if(StringUtil.isEmpty(vo.getCoupInfoVO().getActiveTime())){
                throw new BusinessException("web.coupon.450015");
            }
            if(StringUtil.isEmpty(vo.getCoupInfoVO().getInactiveTime())){
                throw new BusinessException("web.coupon.450016");
            }
            if(vo.getCoupInfoVO().getActiveTime().compareTo(vo.getCoupInfoVO().getInactiveTime())>0){
                throw new BusinessException("web.coupon.450017");
            }
            vo.getCoupInfoVO().setFixedTime(null);
        }
        if(CouponConstants.CoupInfo.EFF_TYPE_0.equals(vo.getCoupInfoVO().getEffType())){
            //浮动类型
            if(StringUtil.isEmpty(vo.getCoupInfoVO().getFixedTime())){
                throw new BusinessException("web.coupon.450022");
            }
            //验证日期大于等于0
            if(vo.getCoupInfoVO().getFixedTime()<=0){
                throw new BusinessException("web.coupon.450018");
            }
            vo.getCoupInfoVO().setActiveTime(null);
            vo.getCoupInfoVO().setInactiveTime(null);
        }
        // 基础信息转化
        ObjectCopyUtil.copyObjValue(vo.getCoupInfoVO(), dto, "", false);
         //优惠码
        if(StringUtil.isEmpty(vo.getCoupInfoVO().getIfCode())){
            dto.setIfCode(CouponConstants.CoupInfo.IF_CODE_0);
        }
        //优惠券面额*100
        if(StringUtil.isNotBlank(vo.getCoupInfoVO().getCoupValue()) && new BigDecimal(vo.getCoupInfoVO().getCoupValue()).compareTo(BigDecimal.ZERO)>0){
            dto.setCoupValue((new BigDecimal(vo.getCoupInfoVO().getCoupValue()).multiply(new BigDecimal(100))).longValue());
        }
        
        List<CoupInfoReqDTO> coupInfoReqDTOs = new ArrayList<>();
        coupInfoReqDTOs.add(dto);
        coupReqDTO.setCoupInfoReqDTOs(coupInfoReqDTOs);
        coupReqDTO.setCoupInfoReqDTO(dto);
    }

    /**
     * vo 转为dto 优惠券满减限制
     * 
     * @param vo
     * @param dto
     * @author huangjx
     */
    private void tranlateFullLimit(CoupVO vo, CoupReqDTO coupReqDTO) {

        if (vo.getFullLimitVO() != null) {
            CoupFullLimitReqDTO dto = new CoupFullLimitReqDTO();
            // 满金额 需要金额非空
            // 满数量 需要数量非空
            if (CouponConstants.CoupFullLimit.TYPE_1.equals(vo.getFullLimitVO().getType())) {
                // 满金额
             /*   if (StringUtil.isEmpty(vo.getFullLimitVO().getSumLimit())) {
                    throw new BusinessException("web.coupon.450011");
                }*/
                if (!StringUtil.isEmpty(vo.getFullLimitVO().getSumLimit())) {
                    if (new BigDecimal(vo.getFullLimitVO().getSumLimit()).compareTo(BigDecimal.ZERO) < 0) {
                        throw new BusinessException("web.coupon.450012");
                    }
                }
                vo.getFullLimitVO().setAmount(null);
            }
            if (CouponConstants.CoupFullLimit.TYPE_2.equals(vo.getFullLimitVO().getType())) {
                // 满数量
               /* if (StringUtil.isEmpty(vo.getFullLimitVO().getAmount())) {
                    throw new BusinessException("web.coupon.450013");
                }*/
                if (!StringUtil.isEmpty(vo.getFullLimitVO().getAmount())) {
                    if (vo.getFullLimitVO().getAmount().compareTo(Long.valueOf(0)) < 0) {
                        throw new BusinessException("web.coupon.450014");
                    }
                }
                vo.getFullLimitVO().setSumLimit(null);
            }
            if( !StringUtil.isEmpty(vo.getFullLimitVO().getAmount()) ||  !StringUtil.isEmpty(vo.getFullLimitVO().getSumLimit())){
                ObjectCopyUtil.copyObjValue(vo.getFullLimitVO(), dto, "", false);
    
                //面额*100
                if(!StringUtil.isEmpty(vo.getFullLimitVO().getSumLimit()) && new BigDecimal(vo.getFullLimitVO().getSumLimit()).compareTo(BigDecimal.ZERO)>0){
                    dto.setSumLimit((new BigDecimal(vo.getFullLimitVO().getSumLimit()).multiply(new BigDecimal(100))).longValue());
                }
                
                List<CoupFullLimitReqDTO> coupFullLimitReqDTOs = new ArrayList<CoupFullLimitReqDTO>();
                coupFullLimitReqDTOs.add(dto);
                coupReqDTO.setCoupFullLimitReqDTOs(coupFullLimitReqDTOs);
                
                if(!CollectionUtils.isEmpty(coupFullLimitReqDTOs)){
                    vo.getRuleCodeMap().put("140", "1");
                }
            }
        }
    }

    /**
     * vo 转为dto 优惠券领取规则
     * 
     * @param vo
     * @param dto
     * @author huangjx
     */
    private void tranlateReceive(CoupVO vo, CoupReqDTO coupReqDTO) {

        // 领取规则非空
        if (!CollectionUtils.isEmpty(vo.getReceiveMap())) {

            List<CoupGetReqDTO> coupGetReqDTOs = new ArrayList<CoupGetReqDTO>();
            // 领取规则转化
            Iterator i = vo.getReceiveMap().entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                String key = e.getKey().toString();
                if (key.contains(CoupConst.RECEIVE_KEY_NUM)) {
                    String custLevel = key.split(CoupConst.SPILT_HX)[1];
                    String gainNum = vo.getReceiveMap().get("num-" + custLevel);
                    String startTime = vo.getReceiveMap().get("start-" + custLevel);
                    String endTime = vo.getReceiveMap().get("end-" + custLevel);
                    if (StringUtil.isEmpty(gainNum) && StringUtil.isEmpty(startTime) && StringUtil.isEmpty(endTime) ) {
                        //全部为空 跳过
                        continue;
                    }
                    //领取数量
                    if (StringUtil.isEmpty(gainNum)) {
                        throw new BusinessException("web.coupon.450006");
                    }
                    //领取数量 为数字
                    if (!StringUtils.isNumeric(gainNum)) {
                        throw new BusinessException("web.coupon.450023");
                    }
                    //领取数量不能小于等于0
                    if (Long.valueOf(gainNum).compareTo(Long.valueOf(0)) <= 0) {
                        throw new BusinessException("web.coupon.450007");
                    }
                    //领取开始时间
                    if (StringUtil.isEmpty(startTime)) {
                        throw new BusinessException("web.coupon.450008");
                    }
                    //领取截止时间
                    if (StringUtil.isEmpty(endTime)) {
                        throw new BusinessException("web.coupon.450009");
                    }
                    Date s1 =  Timestamp.valueOf(startTime);
                    Date e1 =  Timestamp.valueOf(endTime);
                    //领取截止时间大于等于开始时间
                    if (s1.compareTo(e1) >= 0) {
                        throw new BusinessException("web.coupon.450010");
                    }
                    CoupGetReqDTO dto = new CoupGetReqDTO();
                    // gainRuleValue 客户等级 ：01 数量:1 startTime:开始时间 endTime:截止时间
                    dto.setCustLevel(custLevel);
                    dto.setGainNum(gainNum);
                    dto.setEndTime(e1);
                    dto.setStartTime(s1);
                    coupGetReqDTOs.add(dto);
                }
            }
            // 领取规则设置为json格式
            Map  map=new HashMap();
            map.put("210", "1");
            map.put("220", "1");
            map.put("230", "1");
           
            coupReqDTO.getCoupInfoReqDTO().setGainRuleCode(JSON.toJSONString(map));
            
            String jsonStr = JSON.toJSONString(coupGetReqDTOs);
            CoupGetLimitReqDTO dto = new CoupGetLimitReqDTO();
            dto.setGainRuleValue(jsonStr);

            // 设置领取规则
            List<CoupGetLimitReqDTO> coupGetLimitReqDTOs = new ArrayList<CoupGetLimitReqDTO>();

            coupGetLimitReqDTOs.add(dto);
            coupReqDTO.setCoupGetLimitReqDTOs(coupGetLimitReqDTOs);

        }
    }

    /**
     * vo 转为dto 优惠券使用规则
     * 
     * @param vo
     * @param dto
     * @author huangjx
     */
    private void tranlateUse(CoupVO vo, CoupReqDTO coupReqDTO) {
        
        // 使用规则非空
        if (!CollectionUtils.isEmpty(vo.getUseMap())) {
            // 使用规则转化
            // 110 品类限制
            List<CoupCatgLimitReqDTO> coupCatgLimitReqDTOs = new ArrayList<CoupCatgLimitReqDTO>();
            coupReqDTO.setCoupCatgLimitReqDTOs(coupCatgLimitReqDTOs);

            // 120 店铺限制
            List<CoupShopLimitReqDTO> coupShopLimitReqDTOs = new ArrayList<CoupShopLimitReqDTO>();
            coupReqDTO.setCoupShopLimitReqDTOs(coupShopLimitReqDTOs);

            // 150 券共同使用限制
            List<CoupVarLimitReqDTO> coupVarLimitReqDTOs = new ArrayList<CoupVarLimitReqDTO>();
            coupReqDTO.setCoupVarLimitReqDTOs(coupVarLimitReqDTOs);

            // 160 同个订单使用张数限制
            // 170 渠道使用限制
            List<CoupUseLimitReqDTO> coupUseLimitReqDTOs = new ArrayList<CoupUseLimitReqDTO>();
            coupReqDTO.setCoupUseLimitReqDTOs(coupUseLimitReqDTOs);

            Iterator i = vo.getUseMap().entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                String key = e.getKey().toString();
                //为空跳过
                if(e.getValue()==null){
                   continue;   
                }
                //为空跳过
                if(StringUtil.isEmpty(e.getValue())){
                    continue;
                }
                String value = e.getValue().toString();

                String[] keys = key.split(CoupConst.SPILT_HX);
                String switchCode = keys[0];

                switch (switchCode) {
                case "110":
                    // 110 品类限制 (包括品类 商品 单品)
                    CoupCatgLimitReqDTO coupCatgLimitReqDTO = new CoupCatgLimitReqDTO();

                    String[] key1 = key.split(CoupConst.SPILT_HX);
                    // 表示分类
                    if (keys.length == 2) {
                        coupCatgLimitReqDTO
                                .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_2);
                        coupCatgLimitReqDTO.setCatgCode(value);
                        coupCatgLimitReqDTOs.add(coupCatgLimitReqDTO);
                        vo.getRuleCodeMap().put(switchCode, "1");
                    }
                    // 表示商品
                    if (keys.length == 3) {
                        coupCatgLimitReqDTO
                                .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_1);
                        coupCatgLimitReqDTO.setCatgCode(key1[1]);
                        coupCatgLimitReqDTO.setGdsId(new Long(value));
                        coupCatgLimitReqDTOs.add(coupCatgLimitReqDTO);
                        vo.getRuleCodeMap().put(switchCode, "1");
                    }
                    // 表示单品
                    if (keys.length == 4) {
                        coupCatgLimitReqDTO
                                .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_0);
                        coupCatgLimitReqDTO.setCatgCode(key1[1]);
                        coupCatgLimitReqDTO.setGdsId(new Long(key1[2]));
                        coupCatgLimitReqDTO.setSkuId(new Long(value));
                        coupCatgLimitReqDTOs.add(coupCatgLimitReqDTO);
                        vo.getRuleCodeMap().put(switchCode, "1");
                    }
                    break;
                case "120":
                    // 120 店铺限制
                    CoupShopLimitReqDTO coupShopLimitReqDTO = new CoupShopLimitReqDTO();
                    coupShopLimitReqDTO.setShopId(new Long(value));
                    coupShopLimitReqDTOs.add(coupShopLimitReqDTO);
                    vo.getRuleCodeMap().put(switchCode, "1");
                    break;
                case "150":
                    // 150 券共同使用限制
                    CoupVarLimitReqDTO coupVarLimitReqDTO = new CoupVarLimitReqDTO();
                    coupVarLimitReqDTO.setCoupId2(new Long(value));
                    coupVarLimitReqDTOs.add(coupVarLimitReqDTO);
                    
                    //vo.getRuleCodeMap().put(switchCode, "1");
                    vo.getRuleCodeMap().put(switchCode, vo.getCoupInfoVO().getCoupExclude());
                    
                    break;
                case "160":
                    // 160 同个订单使用张数限制
                    CoupUseLimitReqDTO coupUseLimitReqDTO = new CoupUseLimitReqDTO();
                   // coupUseLimitReqDTO.setUseRuleKey(CouponConstants.CoupUseLimit.USE_RULE_KEY_10);
                    coupUseLimitReqDTO.setUseRuleKey(switchCode);
                    coupUseLimitReqDTO.setUseRuleValue(value);
                    coupUseLimitReqDTOs.add(coupUseLimitReqDTO);
                    vo.getRuleCodeMap().put(switchCode, "1");
                    break;
                case "170":
                    // 170 渠道使用限制
                    CoupUseLimitReqDTO coupUseLimitReqDTO1 = new CoupUseLimitReqDTO();
                    //coupUseLimitReqDTO1.setUseRuleKey(CouponConstants.CoupUseLimit.USE_RULE_KEY_20);
                    coupUseLimitReqDTO1.setUseRuleValue(value);
                    coupUseLimitReqDTO1.setUseRuleKey(switchCode);
                    coupUseLimitReqDTOs.add(coupUseLimitReqDTO1);
                    vo.getRuleCodeMap().put(switchCode, "1");
                    break;
                case "180":
                    // 180 免邮限制
                    vo.getRuleCodeMap().put(switchCode, "1");
                    break;
                case "240":
                    // 180 免邮限制
                    vo.getRuleCodeMap().put(switchCode, "1");
                    break;
                default:

                }
            }

        }
        // 黑名单使用规则非空
        if (!CollectionUtils.isEmpty(vo.getUseBlackMap())) {

            List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs = new ArrayList<CoupBlackLimitReqDTO>();

            // 黑名单使用规则转化
            // 分类黑名单 130-catacode
            // 商品黑名单 130-catgcode-gdsid
            // 单品黑名单130-catgcode-gdsid-skuid

            Iterator i = vo.getUseBlackMap().entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                String key = e.getKey().toString();
                String value = e.getValue().toString();
                String[] keys = key.split(CoupConst.SPILT_HX);
                CoupBlackLimitReqDTO coupBlackLimitReqDTO = new CoupBlackLimitReqDTO();
                // 表示分类黑名单
                if (keys.length == 2) {
                    // 需要设置分类 相关数据 0单品 1商品 2分类
                    coupBlackLimitReqDTO
                            .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_2);
                    coupBlackLimitReqDTO.setCatgCode(value);
                }
                // 表示商品黑名单
                if (keys.length == 3) {
                    // 需要设置商品 相关数据
                    coupBlackLimitReqDTO
                            .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_1);
                    coupBlackLimitReqDTO.setGdsId(new Long(value));
                    coupBlackLimitReqDTO.setCatgCode(keys[1]);
                }
                // 表示单品黑名单
                if (keys.length == 4) {
                    // 需要设置单品 相关数据
                    coupBlackLimitReqDTO
                            .setCategoryType(CouponConstants.CoupBlackLimit.CATEGORY_TYPE_0);
                    coupBlackLimitReqDTO.setSkuId(new Long(value));
                    coupBlackLimitReqDTO.setGdsId(new Long(keys[2]));
                    coupBlackLimitReqDTO.setCatgCode(keys[1]);
                }
                coupBlackLimitReqDTOs.add(coupBlackLimitReqDTO);
            }
            coupReqDTO.setCoupBlackLimitReqDTOs(coupBlackLimitReqDTOs);
            
            if(!CollectionUtils.isEmpty(coupBlackLimitReqDTOs)){
                vo.getRuleCodeMap().put("130", "1");
            }
        }
    }

    /**
     * vo 转为dto
     * 
     * @param vo
     * @return
     * @author huangjx
     */
    private CoupReqDTO check(CoupVO vo) {

        CoupReqDTO coupReqDTO = new CoupReqDTO();
        // 优惠券基础信息
        this.tranlateCoupInfo(vo, coupReqDTO);
        // 使用规则
        this.tranlateUse(vo, coupReqDTO);

        this.tranlateFullLimit(vo, coupReqDTO);
        // 领取规则
        this.tranlateReceive(vo, coupReqDTO);
        
        //设置coupInfoDTO.gainRuleCode  useRuleCode
        this.setRuleCode(vo,coupReqDTO);
        
        return coupReqDTO;
    }
    
    /**
     * vo 转为dto
     * 
     * @param vo
     * @return
     * @author huangjx
     */
    private void setRuleCode(CoupVO vo,CoupReqDTO dto) { 
        
        if(!CollectionUtils.isEmpty(vo.getRuleCodeMap())){
            dto.getCoupInfoReqDTO().setUseRuleCode(JSON.toJSONString(vo.getRuleCodeMap()));
        }
    }
    
    /**
     * 设置是否展示 优惠券面额
     * @param map
     * @return
     * @author huangjx
     */
    private boolean setIfShowCoupValue(Map<String,String> map){
        //默认展示
        boolean ifShowCoupValue=true;
      //验证是否需要填写面额字段
        if(map!=null){
            Iterator i = map.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                //返回true表示 需要隐藏
                ifShowCoupValue=PromUtil.matchSysCfgValue(CoupConst.COUP_PARAM_NO_SHOW_VALUE,e.getKey().toString());
                if (ifShowCoupValue){
                    if("1".equals(e.getValue().toString())){
                        break;
                    }
                }
                
            }    
        }
        return !ifShowCoupValue;
    }
    /**
     * 优惠码跳转页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/code/{coupCode}")
    public String goToCode(@PathVariable("coupCode") String coupCode,  Model model) {
        CoupVO coupVO=new CoupVO();
        CoupInfoVO  coupInfoVO=new CoupInfoVO();
        coupInfoVO.setCoupCode(coupCode);
        coupVO.setCoupInfoVO(coupInfoVO);
        model.addAttribute("coupVO", coupVO);
        return "/coupon/coupinfo/coup-code";
    }
}
