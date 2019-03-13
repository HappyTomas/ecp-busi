package com.zengshi.ecp.busi.prom.createprom.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.prom.PromConst;
import com.zengshi.ecp.busi.prom.PromUtil;
import com.zengshi.ecp.busi.prom.auth.vo.QueryPromType4ShopVO;
import com.zengshi.ecp.busi.prom.auth.vo.QueryShopVO;
import com.zengshi.ecp.busi.prom.createprom.vo.PromInfoVO;
import com.zengshi.ecp.busi.prom.createprom.vo.PromRuleVO;
import com.zengshi.ecp.busi.prom.createprom.vo.PromShipAddress;
import com.zengshi.ecp.busi.prom.createprom.vo.PromTypeVO;
import com.zengshi.ecp.busi.prom.createprom.vo.PromVO;
import com.zengshi.ecp.prom.dubbo.dto.ConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-14下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/createprom")
public class CreatePromController extends EcpBaseController {
    /**
     * 平台创建促销功能
     */
    private static String MODULE = CreatePromController.class.getName();
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IPromAuthRSV promAuthRSV;
    
    @Resource
    private IPromQueryRSV promQueryRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private IShopManageRSV shopManageRSV;
    
    @Resource
    private IBaseSysCfgRSV baseSysCfgRSV;
    /**
     * 促销列表 页面
     * @return
     * @author huangjx
     */
    @RequestMapping()
    public String init(Model model){
        return "/prom/createprom/query-shop-grid";
    }
    /**
     * 促销类型页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/ct/{shopId}")
    public String showPromType(Model model,@PathVariable("shopId") String shopId,@Valid QueryPromType4ShopVO vo,BindingResult result){
        
        //返回页面vo定义
        List<PromTypeVO> promTypeVOList=new ArrayList<PromTypeVO>();
        
        model.addAttribute("promTypeVOList", promTypeVOList);
        //参数设置
        QueryPromType4ShopDTO queryDTO = vo.toBaseInfo(QueryPromType4ShopDTO.class);
        //参数传入店铺空 提醒
        if(StringUtil.isEmpty(shopId)){
            result.addError(new ObjectError("shopId",ResourceMsgUtil.getMessage("web.prom.400003", null)));
            return "/prom/createprom/prom-show-form";
        }
        
        //默认查询全部 设置size
        queryDTO.setPageSize(Integer.MAX_VALUE);
        queryDTO.setPageNo(1);
        queryDTO.setStatus(PromConst.PromType4Shop.STATUS_1);
        queryDTO.setShopId(new Long(shopId));
        //店铺id授权列表
        PageResponseDTO<PromType4ShopResponseDTO> page= promAuthRSV.queryPromType4ShopForPage(queryDTO);
        
     
        //获得所有的促销列表
        PromTypeDTO promTypeDTO=new PromTypeDTO();
        List<PromTypeResponseDTO> promTypeList=promRSV.queryPromTypeList(promTypeDTO);
        
        if(!CollectionUtils.isEmpty(promTypeList)){
            List<PromType4ShopResponseDTO> l=null;
            //店铺授权列表 
            if(page!=null){
                //店铺授权非空
                if(!CollectionUtils.isEmpty(page.getResult())){
                    l=page.getResult();
                }
            }
            
             for(PromTypeResponseDTO promTypeResponseDTO:promTypeList){
                 PromTypeVO promTypeVO=new PromTypeVO();
                 ObjectCopyUtil.copyObjValue(promTypeResponseDTO, promTypeVO, "", false);
                 //验证店铺授权
                 this.checkShopAuth(promTypeVO,l);
                 promTypeVOList.add(promTypeVO);
             }
        }else{
            //平台没有促销类型 提示信息
            result.addError(new ObjectError("promTypeCode",ResourceMsgUtil.getMessage("web.prom.400004", null)));
            return "/prom/createprom/prom-show-form";
        }
       
        return "/prom/createprom/prom-show-form";
       
    }
    /**
     * 店铺查询列表
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/shoplist")
    @ResponseBody
    public Model shopList(Model model, QueryShopVO queryShopVO) throws Exception {

        // 组织参数
        ShopSelectReqDTO  conds=new ShopSelectReqDTO();
        
        conds=queryShopVO.toBaseInfo(ShopSelectReqDTO.class);
        
        ObjectCopyUtil.copyObjValue(queryShopVO, conds, "", false);
        
        //conds.setShopID(queryShopVO.getShopId());
        
        if(!StringUtil.isEmpty(queryShopVO.getStatus())){
             //待实现
        }
        PageResponseDTO<ShopInfoResDTO> page=shopInfoRSV.listShopInfoByCond(conds);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }
    /**
     * 促销tab页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/{shopId}/{promTypeCode}")
    public String promTab(@PathVariable("shopId") String shopId,@PathVariable("promTypeCode") String promTypeCode,Model model){
        
    	//优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        //获得促销类型对象
        PromTypeDTO promTypeDTO=new PromTypeDTO();
        
        promTypeDTO.setPromTypeCode(promTypeCode);
        PromTypeResponseDTO p=promQueryRSV.queryPromType(promTypeDTO);
        model.addAttribute("promType", p);
        
        model.addAttribute("shopId", shopId);
        
        PromVO promVO=new PromVO();
        promVO.setDiscountMap(new HashMap());
        
        PromInfoVO vo=new PromInfoVO();
        vo.setPromTypeCode(promTypeCode);
        vo.setShopId(new Long(shopId));
        promVO.setPromInfoVO(vo);
        promVO.setPromRuleVO(new PromRuleVO());
        
        model.addAttribute("promVO",promVO);
        
        this.initPageData(model,promVO);
        
        return "/prom/createprom/prom-tab";
    }
    /**
     * 保存
     * 
     * @param promType4ShopVO
     * @param result
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public PromVO save(@ModelAttribute("promVO") @Valid PromVO promVO) {
        
        PromVO vo=new PromVO();
        
        try{
            
            PromDTO promDTO=this.promSave(promVO);
            //调用保存服务
            
            promRSV.saveProm(promDTO);
            vo.setResultMsg(ResourceMsgUtil.getMessage("prom.result.success.msg", null));
            
         }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        
        }
       
        return vo;
    }
    
    /**
     * 提交
     * 
     * @param promType4ShopVO
     * @param result
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public PromVO submit(@ModelAttribute("promVO") @Valid PromVO promVO) {
        
        PromVO vo=new PromVO();
        
        try{
                PromDTO promDTO=this.promSave(promVO);
                 //调用保存服务
                 promRSV.createProm(promDTO);
                 
                 vo.setResultMsg(ResourceMsgUtil.getMessage("prom.result.success.msg", null));
                 
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        
        }
       
        return vo;
    }

    /**
     * 促销tab页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/view/{promId}")
    public String viewPromTab(@PathVariable("promId") String promId, Model model) {
          this.queryProm(PromConst.DO_ACTION_VIEW, model, promId);
        return "/prom/createprom/prom-tab";
    }
    /**
     * 促销tab页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/edit/{promId}")
    public String editPromTab(@PathVariable("promId") String promId, Model model) {
        this.queryProm(PromConst.DO_ACTION_EDIT, model, promId);
        return "/prom/createprom/prom-tab";
    }
    /**
     * 促销tab页面
     * @return
     * @author huangjx
     */
    @RequestMapping("/copy/{promId}")
    public String copyPromTab(@PathVariable("promId") String promId, Model model) {
        this.queryProm(PromConst.DO_ACTION_COPY, model, promId);
        return "/prom/createprom/prom-tab";
    }
    /**
     * 匹配店铺促销类型 授权
     * @param vo
     * @param promTypeList
     * @author huangjx
     */
    private void checkShopAuth(PromTypeVO vo, List<PromType4ShopResponseDTO> promTypeList){
        
           //为空 表示没有授权 或者授权过期
            if(CollectionUtils.isEmpty(promTypeList)){
                return;
            }
            
            //有存在授权，验证是否为当前授权代码
            for(PromType4ShopResponseDTO dto:promTypeList){
                
                    if(vo.getPromTypeCode().equals(dto.getPromTypeCode())){
                        vo.setShopId(dto.getShopId());
                        break;
                    }
                    
            }
   }
 
 
    /**
     * 促销基本信息业务验证
     * @param promVO
     * @param result
     * @author huangjx
     */
    private void checkPromInfo(PromVO promVO){
        //1促销开始时间 要早于截止时间
        //2展示开始时间要早于展示截止时间
        //3促销时间和展示时间验证
        if (new Timestamp(promVO.getPromInfoVO().getEndTime().getTime()).compareTo(new Timestamp(promVO.getPromInfoVO()
                .getStartTime().getTime())) <= 0) { // throw new Exception("结束时间早于开始时间！");
            throw new BusinessException("web.prom.400010");
        }
        if(PromUtil.matchSysCfgValue(PromConst.PromSys.PROM_TYPE_HIDE_SHOW_TIME,promVO.getPromInfoVO().getPromTypeCode())!=true)
        {
            if (promVO.getPromInfoVO().getShowStartTime() == null) { // throw new Exception("展示开始时间不能为空！");
                throw new BusinessException("web.prom.400009");
            }
            if (promVO.getPromInfoVO().getShowEndTime() == null) { // throw new Exception("展示结束时间不能为空！");
                throw new BusinessException("web.prom.400008");
            }
            if (new Timestamp(promVO.getPromInfoVO().getShowEndTime().getTime()).compareTo(new Timestamp(promVO.getPromInfoVO()
                    .getShowStartTime().getTime())) <= 0) { // throw new Exception("展示结束时间早于展示开始时间！");
                throw new BusinessException("web.prom.400007");
            }
            if (new Timestamp(promVO.getPromInfoVO().getShowStartTime().getTime()).compareTo(new Timestamp(promVO.getPromInfoVO()
                    .getStartTime().getTime())) > 0) {
                // throw new Exception("展示开始时间晚于促销开始时间！");
                throw new BusinessException("web.prom.400006");
            }
            if (new Timestamp(promVO.getPromInfoVO().getShowEndTime().getTime()).compareTo(new Timestamp(promVO.getPromInfoVO()
                    .getEndTime().getTime())) < 0) {
                // throw new Exception("展示结束时间早于促销结束时间！");
                throw new BusinessException("web.prom.400005");
            }
        } 
   }
    /**
     * 促销规则业务验证
     * @param promVO
     * @param result
     * @author huangjx
     */
    private void checkPromRule(PromVO promVO){
              //提示说明
               /* web.prom.400011 = 客户组没有设置！
                web.prom.400012 = 渠道来源没有设置！
                web.prom.400013 = 客户等级没有设置！
                web.prom.400014 = 区域没有设置！
                web.prom.400015 = 次数没有设置！
                web.prom.400016 = 总量没有设置！
                web.prom.400017 = 最小购买数量大于最大购买量！*/
        if(promVO.getPromRuleVO()!=null){
            //如果选择 限制值 后续值域必须选择
            if(PromConst.PromConstraint.CUSTGROUP_1.equals(promVO.getPromRuleVO().getCustGroup())){
                  //客户组选择  需要对应的客户组值
                  if(StringUtil.isEmpty(promVO.getPromRuleVO().getCustGroupValue())){
                      throw new BusinessException("web.prom.400011");
                  }
            }
            if(PromConst.PromConstraint.CHANNEL_1.equals(promVO.getPromRuleVO().getChannel())){
                //渠道来源选择  需要对应的渠道值
                if(StringUtil.isEmpty(promVO.getPromRuleVO().getChannelValue())){
                    throw new BusinessException("web.prom.400012");
                }
             }
            if(PromConst.PromConstraint.CUSTLEVEL_1.equals(promVO.getPromRuleVO().getCustLevel())){
                //客户等级选择  需要对应的客户等级值
                if(StringUtil.isEmpty(promVO.getPromRuleVO().getCustLevelValue())){
                    throw new BusinessException("web.prom.400013");
                }
             }
            if(PromConst.PromConstraint.AREA_1.equals(promVO.getPromRuleVO().getArea())){
                //区域选择  需要对应的区域值
                if(StringUtil.isEmpty(promVO.getPromRuleVO().getAreaValue())){
                    throw new BusinessException("web.prom.400014");
                }
             }
            if(PromConst.PromConstraint.LIMITTIMESTYPE_1.equals(promVO.getPromRuleVO().getLimitTimesType())
                    ||PromConst.PromConstraint.LIMITTIMESTYPE_2.equals(promVO.getPromRuleVO().getLimitTimesType())
                    ||PromConst.PromConstraint.LIMITTIMESTYPE_3.equals(promVO.getPromRuleVO().getLimitTimesType())
                 ){
                    //购买次数限制
                    if(StringUtil.isEmpty(promVO.getPromRuleVO().getLimitTimesTypeValue())){
                        throw new BusinessException("web.prom.400015");
                    }
             }
            if(PromConst.PromConstraint.LIMITTOTALTYPE_1.equals(promVO.getPromRuleVO().getLimitTotalType())
                    ||PromConst.PromConstraint.LIMITTOTALTYPE_2.equals(promVO.getPromRuleVO().getLimitTotalType())
                    ||PromConst.PromConstraint.LIMITTOTALTYPE_3.equals(promVO.getPromRuleVO().getLimitTotalType())
                 ){
                    //购买总量限制
                    if(StringUtil.isEmpty(promVO.getPromRuleVO().getLimitTotalTypeValue())){
                        throw new BusinessException("web.prom.400016");
                    }
             }
            //每次购买最小量 小于等于 最大量
            if(!StringUtil.isEmpty(promVO.getPromRuleVO().getMinBuyCnt()) && !StringUtil.isEmpty(promVO.getPromRuleVO().getMaxBuyCnt())){
                        if(new Long(promVO.getPromRuleVO().getMinBuyCnt()).compareTo(new Long(promVO.getPromRuleVO().getMaxBuyCnt()))>0){
                            throw new BusinessException("web.prom.400017");
                        }
            }
          
        }
          
   }
    
    /**
     * 保存 验证业务
     * @param promVO
     * @author huangjx
     */
    private PromDTO promSave(PromVO promVO){
     
     if(PromConst.DO_ACTION_COPY.equals(promVO.getDoAction())){
         //copy  为和create区别
         promVO.getPromInfoVO().setId(null);
         
     }   
     //后场服务dto
     PromDTO promDTO=new PromDTO();
     
   //如果是搭配单品促销 即有搭配单品是 才设置1
     promVO.getPromInfoVO().setIfMatch(PromConst.PromInfo.IF_MATCH_0);
     
     //黑名单设置（促销类型为订单 才有黑名单）
     if(StringUtil.isEmpty(promVO.getPromInfoVO().getIfBlacklist())){
             promVO.getPromInfoVO().setIfBlacklist(PromConst.PromInfo.IF_BLACKLIST_0);
     }
    
     
     //@Valid 验证促销基本信息
     //基本信息业务验证
     this.checkPromInfo(promVO);
     
     //设置单品和促销数量
     this.initSkuList(promVO,promDTO);
     
     //如果开启黑名单设置 那么必需要有黑名单列表
     this.initBlackSkuList(promVO,promDTO);
     //促销规则 前店验证
      this.checkPromRule(promVO);
      
      //copy 促销基础信息
      ObjectCopyUtil.copyObjValue(promVO.getPromInfoVO(), promDTO, "", false);
      
      //copy 促销规则
      if(promVO.getPromRuleVO()!=null){
          if( promDTO.getPromConstraintDTO()==null){
              promDTO.setPromConstaintDTO(new PromConstraintDTO());
          }
          ObjectCopyUtil.copyObjValue(promVO.getPromRuleVO(), promDTO.getPromConstraintDTO(), "", false);
      }
      
      //优惠规则 后场验证  control不需要验证
      //优惠规则 可以直接json序列化
      if(promVO.getDiscountMap()!=null){
          promDTO.setDiscountRule(JSON.toJSONString(promVO.getDiscountMap()));
      }
      //赠品验证 并设置
      this.initGiftList(promVO,promDTO);
      
      //搭配商品列表
      this.initMatchSkuList(promVO,promDTO);
      
      //分类列表
      this.initCatgList(promVO, promDTO);
      
      //分类黑名单列表
      this.initCatgLimitList(promVO, promDTO);
      
      return promDTO;
   }
    
    
    /**
     * 初始化单品列表
     * @param promVO
     * @author huangjx
     */
    private void initSkuList(PromVO promVO,PromDTO promDTO){

        if (!StringUtil.isEmpty(promVO.getPromInfoVO().getJoinRange())) {
            // 1000000013 作为自由搭配 可不选择商品信息
           /* if (PromConst.PromInfo.JOIN_RANGE_0.equals(promVO.getPromInfoVO().getJoinRange())
                    && !PromConst.PROM_TYPE_CODE_1000000013.equals(promVO.getPromInfoVO()
                            .getPromTypeCode())
                    && !PromConst.PROM_TYPE_CODE_1000000014.equals(promVO.getPromInfoVO()
                            .getPromTypeCode())) {
           */     
              if(PromConst.PromInfo.JOIN_RANGE_0.equals(promVO.getPromInfoVO().getJoinRange()) && !PromUtil.matchSysCfgValue(PromConst.PromSys.PROM_TYPE_SYS_CFG_KEY,promVO.getPromInfoVO().getPromTypeCode())){

               /* if (CollectionUtils.isEmpty(promVO.getGdsMap())
                        && CollectionUtils.isEmpty(promVO.getCatgMap())) {
                    // 如果部分选择 那么商品非空 或者分类
                    throw new BusinessException("web.prom.400026");
                }*/
                if (!CollectionUtils.isEmpty(promVO.getGdsMap())) {

                	  ConstantTool tool=new ConstantTool();
                    // 如果选择商品 需要验证库存设置值
                    // 全场 不需要验证库存设置
                    ArrayList<PromSkuDTO> skuList = new ArrayList<PromSkuDTO>();
                    Iterator i = promVO.getGdsMap().entrySet().iterator();
                    while (i.hasNext()) {// 只遍历一次,速度快
                        Map.Entry e = (Map.Entry) i.next();

                        PromSkuDTO promSkuDTO = new PromSkuDTO();
                        promSkuDTO.setGdsId(new Long(e.getKey().toString().split("-")[0]));
                        promSkuDTO.setSkuId(new Long(e.getKey().toString().split("-")[1]));
                        promSkuDTO.setMatchType(PromConst.PromInfo.IF_MATCH_1);

                        try {
                            String skuValue = e.getValue().toString();
                            promSkuDTO.setPromCnt(new Long(skuValue.split("-")[0]));
                            if ("1000000019".equals(promVO.getPromInfoVO().getPromTypeCode())) {
                                //设置秒杀价、秒杀价类型
                                promSkuDTO.setPriceType(skuValue.split("-")[1]);
                                promSkuDTO.setPriceValue(MoneyUtil.convertYuanToCent(skuValue.split("-")[2], 2));
                            }
                            //如果是虚拟商品  默认固定值
                            //if("2".equals(e.getKey().toString().split("-")[2])){
                            if(!tool.isNeedStock(e.getKey().toString().split("-")[2])){
                                promSkuDTO.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
                            }
                            if(promSkuDTO.getPromCnt()<=0){
                                String[] keys = new String[1];
                                keys[0] = promSkuDTO.getSkuId().toString();
                                throw new BusinessException("web.prom.400028", keys);
                            }
                            // 是否验证 超出库存（系统配置） 待实现
                            if ("1".equals(PromConst.IS_CHECK_STOCK)) {
                                // 验证单品数量 是否超过库存数量 系统设置开关 库存提供接口
                                // 待实现
                            }
                        } catch (BusinessException ex) {
                            LogUtil.error(MODULE, "传入的促销数量不为数字", ex);
                            
                            if(!StringUtil.isEmpty(ex.getErrorCode())){
                                throw new BusinessException(ex.getErrorMessage());
                            }
                            String[] keys = new String[1];
                            keys[0] = promSkuDTO.getSkuId().toString();
                            throw new BusinessException("web.prom.400019", keys);
                        }catch (Exception ex) {
                            LogUtil.error(MODULE, "传入的促销数量不为数字", ex);
                            String[] keys = new String[1];
                            keys[0] = promSkuDTO.getSkuId().toString();
                            throw new BusinessException("web.prom.400019", keys);
                        }

                        skuList.add(promSkuDTO);
                    }
                    promDTO.setSkuList(skuList);
                }
            }
        }
    }

    /**
     * 初始化分类列表
     * @param promVO
     * @author huangjx
     */
    private void initCatgList(PromVO promVO,PromDTO promDTO){

        if(!CollectionUtils.isEmpty(promVO.getCatgMap())){
            ArrayList<PromSkuDTO> catgList = new ArrayList<PromSkuDTO>();
            Iterator i = promVO.getCatgMap().entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                PromSkuDTO promSkuDTO = new PromSkuDTO();
                promSkuDTO.setCatgCode(e.getKey().toString());//分类代码
                promSkuDTO.setPromCnt(new Long(Integer.MAX_VALUE));//促销数量
                catgList.add(promSkuDTO);
            }
            promDTO.setCatgList(catgList);
        }
    }
    /**
     * 初始化分类黑名单列表
     * @param promVO
     * @author huangjx
     */
    private void initCatgLimitList(PromVO promVO,PromDTO promDTO){

        if(PromConst.PromInfo.IF_BLACKLIST_1.equals(promVO.getPromInfoVO().getIfBlacklist())){
            if(CollectionUtils.isEmpty(promVO.getGdsBlackMap()) && CollectionUtils.isEmpty(promVO.getCatgLimitMap())){
                      //如果黑名单选择 那么黑名单商品、分类非空
                      throw new BusinessException("web.prom.400020");
            }
            if(!CollectionUtils.isEmpty(promVO.getCatgLimitMap())){
                ArrayList<PromSkuLimitDTO> catgLimitList = new ArrayList<PromSkuLimitDTO>();
                Iterator i = promVO.getCatgLimitMap().entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry e = (Map.Entry) i.next();
                    PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
                    promSkuLimitDTO.setCatgCode(e.getKey().toString());//分类代码
                    catgLimitList.add(promSkuLimitDTO);
                }
                promDTO.setCatgLimitList(catgLimitList);
            }
        }
    }
    
    /**
     * 初始化黑名单列表
     * @param promVO
     * @author huangjx
     */
    private void initBlackSkuList(PromVO promVO,PromDTO promDTO){
        
        if(PromConst.PromInfo.IF_BLACKLIST_1.equals(promVO.getPromInfoVO().getIfBlacklist())){
                
                    if(CollectionUtils.isEmpty(promVO.getGdsBlackMap()) && CollectionUtils.isEmpty(promVO.getCatgLimitMap())){
                              //如果黑名单选择 那么黑名单商品、分类非空
                              throw new BusinessException("web.prom.400020");
                    }
                    if(!CollectionUtils.isEmpty(promVO.getGdsBlackMap())){
                        
                        ArrayList<PromSkuLimitDTO> skuBlackList=new ArrayList<PromSkuLimitDTO>();
                        Iterator i=promVO.getGdsBlackMap().entrySet().iterator();
                            while(i.hasNext()){ 
                              Map.Entry e=(Map.Entry)i.next();
                            
                              PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
                              promSkuLimitDTO.setGdsId(new Long(e.getKey().toString().split("-")[0]));
                              promSkuLimitDTO.setSkuId(new Long(e.getKey().toString().split("-")[1]));
                         
                              skuBlackList.add(promSkuLimitDTO);  
                           }
                        promDTO.setBlackSkuList(skuBlackList);
                  }
                    
        }
    }
    
    /**
     * 初始化赠品列表
     * @param promVO
     * @param promDTO
     * @author huangjx
     */
    private void initGiftList(PromVO promVO,PromDTO promDTO){
              //赠品列表为空
            if(CollectionUtils.isEmpty(promVO.getGiftMap())){
                  return ;
              }
              //赠品处理
              ArrayList<PromGiftDTO> giftList=new ArrayList<PromGiftDTO>();
              
              Iterator i=promVO.getGiftMap().entrySet().iterator();
              
              while(i.hasNext()){//只遍历一次,速度快
                    Map.Entry e=(Map.Entry)i.next();
                  
                  PromGiftDTO promGiftDTO = new PromGiftDTO();
                  String[] key=new String[3];
                  try{
                      
                       key=e.getKey().toString().split("-");
                       
                       promGiftDTO.setGdsId(new Long(key[0]));
                       promGiftDTO.setSkuId(new Long(key[1]));
                       promGiftDTO.setGiftId(new Long(key[2]));
                       promGiftDTO.setShopId(promVO.getPromInfoVO().getShopId());
                       String[] value=new String[2];
                       value=e.getValue().toString().split("-");
                       
                       //赠送总数量
                       promGiftDTO.setPresentAllCnt(new Long(value[0]));
                       //每次赠送量
                       promGiftDTO.setEveryTimeCnt(new Long(value[1]));
                      
                  }catch(BusinessException ex){
                      LogUtil.error(MODULE, "传入的赠品数量不为数字", ex);
                      String[] keys=new String[1];
                      keys[0]=key[2];
                      throw new BusinessException("web.prom.400022",keys);
                  }catch(Exception ex){
                      LogUtil.error(MODULE, "传入的赠品数量不为数字", ex);
                      String[] keys=new String[1];
                      keys[0]=key[2];
                      throw new BusinessException("web.prom.400022",keys);
                  }
               
                  giftList.add(promGiftDTO);
          }
           //设置dto
          promDTO.setGiftList(giftList);
    }
    /**
     * 初始化搭配商品列表
     * @param promVO
     * @param promDTO
     * @author huangjx
     */
    private void initMatchSkuList(PromVO promVO,PromDTO promDTO){
              //搭配商品列表为空
            if(CollectionUtils.isEmpty(promVO.getMatchMap())){
                  return ;
              }
              //搭配商品处理
              ArrayList<PromMatchSkuDTO> matchSkuList=new ArrayList<PromMatchSkuDTO>();
              
              Iterator i=promVO.getMatchMap().entrySet().iterator();
              Iterator m=promVO.getMatchMap().entrySet().iterator();

              BigDecimal mathGdsAllPrice = new BigDecimal(0);
              if(PromConst.PROM_TYPE_CODE_1000000012.equals(promVO.getPromInfoVO().getPromTypeCode())){
                  while (m.hasNext()) {
                      //MachMap ：[ key: , value:_promCnt+"-"+_bindPrice+"-"+_guidePrice]
                      Map.Entry e=(Map.Entry)m.next();
                      String[] value=new String[3];
                      value=e.getValue().toString().split("-");
                      BigDecimal guidePrice = new BigDecimal(value[2]);
                      mathGdsAllPrice = mathGdsAllPrice.add(guidePrice);
                }
              }

              ConstantTool tool=new ConstantTool();
              int cnt=promVO.getMatchMap().size();
              long curentPrice = 0;
              int j=0;
              while(i.hasNext()){//只遍历一次,速度快
                    ++j;
                    Map.Entry e=(Map.Entry)i.next();
                  
                    PromMatchSkuDTO promMatchSkuDTO = new PromMatchSkuDTO();
                  
                    String[] key=new String[3];
                  try{
                      
                       key=e.getKey().toString().split("-");
                       
                       promMatchSkuDTO.setGdsId(new Long(key[0]));
                       promMatchSkuDTO.setSkuId(new Long(key[1]));
                       
                       String[] value=new String[3];
                       value=e.getValue().toString().split("-");
                       
                       //总数量
                       promMatchSkuDTO.setPromCnt(new Long(value[0]));
                       
                       //如果是虚拟商品  默认固定值
                       //if("2".equals(key[2])){
                    	if(!tool.isNeedStock(key[2])){
                           promMatchSkuDTO.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
                       }
                       //单价,促销配置的价格
                       Long promMathPrice = NumberUtil.tranlateNum(new BigDecimal(value[1]).multiply(new BigDecimal(100))).longValue();
                       promMatchSkuDTO.setPrice(promMathPrice);

                       //默认 可选
                       promMatchSkuDTO.setMatchType(PromConst.PromMatchSku.MATCH_TYPE_1);
                       
                       //1000000012 固定搭配（捆绑套餐）
                       if(PromConst.PROM_TYPE_CODE_1000000012.equals(promVO.getPromInfoVO().getPromTypeCode())){
                           //商品参考价
                           BigDecimal guidePrice = new BigDecimal(value[2]);
                           promMatchSkuDTO.setMatchType(PromConst.PromMatchSku.MATCH_TYPE_2);
                           //按照商品参考价/所捆绑的商品的总结的比例乘以所配置的搭配价格得出该商品目前的价格
                           //商品 A（100） B（200） C（300） 捆绑总价格550元。
                           //A最终分摊的价格为 100/100+200+300 乘以 550 四舍五入取整。
                           //B 最终分摊的价格为 200/100+200+300 乘以 550 四舍五入取整。 
                           //C最终的价格为555-A最终价格-B最终价格
                           if(mathGdsAllPrice.longValue() > 0 && j<cnt){
                               promMatchSkuDTO.setPrice(NumberUtil.tranlateNum(guidePrice.divide(mathGdsAllPrice,2,BigDecimal.
                                       ROUND_HALF_UP).multiply(new BigDecimal(promMathPrice))).longValue());
                               curentPrice += promMatchSkuDTO.getPrice();
                           }
                           else if (mathGdsAllPrice.longValue() > 0 && j == cnt) {
                               promMatchSkuDTO.setPrice(promMathPrice - curentPrice);
                        }
                       }
                       //1000000014 固定搭配
                       if(PromConst.PROM_TYPE_CODE_1000000014.equals(promVO.getPromInfoVO().getPromTypeCode())){
                           promMatchSkuDTO.setMatchType(PromConst.PromMatchSku.MATCH_TYPE_2);
                       }
                       //设置排序字段
                       promMatchSkuDTO.setSortNum(BigDecimal.ONE);
                       //自由搭配A 自由搭配B  固定搭配
						if (PromConst.PROM_TYPE_CODE_1000000014.equals(promVO
								.getPromInfoVO().getPromTypeCode())
								|| PromConst.PROM_TYPE_CODE_1000000013.equals(promVO
										.getPromInfoVO().getPromTypeCode())
								|| PromConst.PROM_TYPE_CODE_1000000011.equals(promVO
										.getPromInfoVO().getPromTypeCode())) {
							try {
								promMatchSkuDTO.setSortNum(new BigDecimal(value[2]));
							} catch (Exception ex) {
								LogUtil.error(MODULE, "排序为空或者没有值", ex);
								promMatchSkuDTO.setSortNum(BigDecimal.ONE);
							}
						}
                  }catch(BusinessException ex){
                      LogUtil.error(MODULE, "传入的单品商品数量或者单价不为数字", ex);
                      String[] keys=new String[1];
                      keys[0]=key[1];
                      throw new BusinessException("web.prom.400024",keys);
                  }catch(Exception ex){
                      LogUtil.error(MODULE, "传入的单品商品数量或者单价不为数字", ex);
                      String[] keys=new String[1];
                      keys[0]=key[1];
                      throw new BusinessException("web.prom.400024",keys);
                  }
               
                  matchSkuList.add(promMatchSkuDTO);
          }
    
           //设置dto
          promDTO.setMatchSkuDTOList(matchSkuList);
    }
    /**
     * 促销编辑 详情查看
     * @param doAction
     * @param model
     * @param promId
     * @author huangjx
     */
    private void queryProm(String doAction,Model model,String promId){

        // 查询
        // model.addAttribute("doAction", "view");
        model.addAttribute("doAction", doAction);
        // 返回页面vo
        PromVO promVO = new PromVO();

        // 查询促销基本信息 begin
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        promInfoDTO.setId(new Long(promId));
        PromInfoDTO rePromInfoDTO = promQueryRSV.queryPromInfo(promInfoDTO);
        if (rePromInfoDTO == null) {
            rePromInfoDTO = new PromInfoDTO();
        }
        // 设置基本信息
        PromInfoVO promInfoVO = new PromInfoVO();
        ObjectCopyUtil.copyObjValue(rePromInfoDTO, promInfoVO, "", false);
        //copy时  把促销名称 至为空
        if(PromConst.DO_ACTION_COPY.equals(doAction)){
            promInfoVO.setPromTheme("");
        }
        promVO.setPromInfoVO(promInfoVO);
        // 查询促销基本信息 end

        // 获得促销类型对象
        PromTypeDTO promTypeDTO = new PromTypeDTO();
        promTypeDTO.setPromTypeCode(rePromInfoDTO.getPromTypeCode());
        PromTypeResponseDTO p = promQueryRSV.queryPromType(promTypeDTO);
        model.addAttribute("promType", p);

        // 优惠规则begin
        PromCalRuleDTO promCalRuleDTO = new PromCalRuleDTO();
        promCalRuleDTO.setPromId(new Long(promId));
        PromCalRuleDTO repromCalRuleDTO = promQueryRSV.queryPromCalRule(promCalRuleDTO);
        // json转map
        Map<String, Object> map1 = new HashMap<String, Object>();
        try {
            if (repromCalRuleDTO != null) {
                map1 = (Map<String, Object>) JSON.parse(repromCalRuleDTO.getCalStr());
                promVO.setDiscountMap(new HashMap(map1));// 强类型转换 报错误
            }
        } catch (Exception ex) {
            LogUtil.error(MODULE, "报错了啦", ex);
        }
        // 优惠规则end

        // 促销规则begin
        ConstraintDTO constraintDTO = new ConstraintDTO();
        constraintDTO.setPromId(new Long(promId));
        PromConstraintDTO rePromConstraintDTO = promQueryRSV.queryPromConstraint(constraintDTO);

        if (rePromConstraintDTO != null) {
            PromRuleVO promRuleVO = new PromRuleVO();
            ObjectCopyUtil.copyObjValue(rePromConstraintDTO, promRuleVO, "", false);
            promVO.setPromRuleVO(promRuleVO);
        }

        //初始化页面数据
        this.initPageData(model,promVO);
        // 促销规则end
        model.addAttribute("shopId", promVO.getPromInfoVO().getShopId());
        model.addAttribute("promVO", promVO);
    }
    
    /**
     * 初始化页面数据
     * @param model
     * @author huangjx
     */
    private void initPageData(Model model,PromVO promVO){
        
        List<BaseParamDTO> channelList= BaseParamUtil.fetchParamList(PromConst.PromKey.CHANNEL_SOURCE);
        model.addAttribute("channelList", channelList);
        
        List<BaseParamDTO> timesList= BaseParamUtil.fetchParamList(PromConst.PromKey.LIMIT_TIMES_TYPE);
        model.addAttribute("timesList", timesList);
        
        List<BaseParamDTO> totalList= BaseParamUtil.fetchParamList(PromConst.PromKey.LIMIT_TOTAL_TYPE);
        model.addAttribute("totalList", totalList);
        
        List<BaseParamDTO> custLevelList= BaseParamUtil.fetchParamList(PromConst.StaffModel.STAFF_CUST_LEVEL);
        model.addAttribute("custLevelList", custLevelList);
        
        //客户组
        //List<BaseParamDTO> custGrouplList= BaseParamUtil.fetchParamList(PromConst.StaffModel.STAFF_CUST_GROUP);
        model.addAttribute("custGrouplList", queryCustGroupData(promVO.getPromInfoVO().getShopId(),StaffConstants.ShopStaffGroup.STATUS_ACTIVE));
        
        List<BaseAreaAdminRespDTO> provinceList=BaseAreaAdminUtil.fetchChildAreaInfos(PromConst.SysModel.COUNTRY_DEFAULT);
        model.addAttribute("provinceList", provinceList);
        
        
        List<BaseParamDTO> sendTypeList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_SEND_POINTS_TYPE);
        model.addAttribute("sendTypeList", sendTypeList);
        
        //是否显示 展示开始时间  展示截止时间
        model.addAttribute("ifShowShowTime", PromUtil.matchSysCfgValue(PromConst.PromSys.PROM_TYPE_HIDE_SHOW_TIME,promVO.getPromInfoVO().getPromTypeCode()));
        
        //是否可操作 分类、商品、单品选择操作
        model.addAttribute("ifShowSelect", PromUtil.matchSysCfgValue(PromConst.PromSys.PROM_TYPE_SYS_CFG_KEY,promVO.getPromInfoVO().getPromTypeCode()));
        //是否可操作 促销规则tab页面
        model.addAttribute("ifShowPromRuleTab", PromUtil.matchSysCfgValue(PromConst.PROM_RULE_IF_HIDE,promVO.getPromInfoVO().getPromTypeCode()));
        //加载邮费 区域信息
        List<PromShipAddress> result = new ArrayList<PromShipAddress>();
        if (PromConst.PROM_TYPE_CODE_1000000016.equals(promVO.getPromInfoVO().getPromTypeCode())){
            List<BaseAreaAdminRespDTO> provinceListAll = BaseAreaAdminUtil.fetchChildAreaInfos(PromConst.SysModel.COUNTRY_DEFAULT);
            PromShipAddress promShipAddress = null;
            if(StringUtil.isNotEmpty(provinceList)){
                for(BaseAreaAdminRespDTO dto : provinceListAll){
                    promShipAddress = new PromShipAddress();
                    promShipAddress.setAreaCode(dto.getAreaCode());
                    promShipAddress.setAreaName(dto.getAreaName());
                    List<BaseAreaAdminRespDTO> a =  BaseAreaAdminUtil.fetchChildAreaInfos(dto.getAreaCode());
                    promShipAddress.setList(a);
                    result.add(promShipAddress);
                }
            }
        }
            model.addAttribute("provinceCityList", result);
    
        
        
    }
    /**
     * 初始化页面 店铺客户分组
     * @param model
     * @author huangjx
     */
    private List<ShopStaffGroupResDTO> queryCustGroupData(Long shopId,String status){
      ShopStaffGroupReqDTO reqDto=new ShopStaffGroupReqDTO();
        reqDto.setShopId(shopId);
        reqDto.setStatus(status);
        reqDto.setPageNo(1);
        reqDto.setPageSize(Integer.MAX_VALUE);
        PageResponseDTO<ShopStaffGroupResDTO> page=shopManageRSV.listShopStaffGroup( reqDto);
        
        List<ShopStaffGroupResDTO> reList=new ArrayList<ShopStaffGroupResDTO>();
        if(CheckPageNull.checkPageNull(page)){
            reList=page.getResult();
        }
        return reList;
    }
}


