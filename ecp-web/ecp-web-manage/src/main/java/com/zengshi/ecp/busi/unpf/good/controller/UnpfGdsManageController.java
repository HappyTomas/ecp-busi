package com.zengshi.ecp.busi.unpf.good.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.unpf.dubbo.dto.gdssend.*;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.unpf.good.vo.UnpfGdsSendVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoManageRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsSendRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGoodSendRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 商品推送管理<br>
 * Date:2016年11月14日上午9:21:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/unpf/gdsManage")
public class UnpfGdsManageController extends EcpBaseController {
    private static String MODULE = UnpfGdsManageController.class.getName();

    private static final String IF_GDS_SCORE = "0";// 不是积分商城的商品


    @Resource
    private IUnpfGoodSendRSV unpfGoodSendRSV;

    @Resource
    private IGdsInfoManageRSV iGdsInfoManageRSV;

    @Resource
    private IGdsTypeRSV iGdsTypeRSV;

    @Resource
    private IShopInfoRSV iShopInfoRSV;

    @Resource
    private IUnpfGdsSendRSV unpfGdsSendRSV;
    
    /**
     * 
     * init:(初始化跳转到商品推送管理列表页面). <br/>
     * 
     * @author lisp
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model, GdsShopVO gsShopVO) {
        List<GdsTypeRespDTO> gdsTypeList = iGdsTypeRSV.queryAllGdsTypesFromCache();
        try {
            model.addAttribute("GDS_VERIFY_SWITCH",SysCfgUtil.fetchSysCfg("GDS_VERIFY_SWITCH").getParaValue());
        } catch (Exception e) {
            model.addAttribute("GDS_VERIFY_SWITCH","0");
        }
     
        model.addAttribute("gdsTypeList", gdsTypeList);
        model.addAttribute("shopId", gsShopVO.getShopId());
        
        model.addAttribute("ifGdsScore", IF_GDS_SCORE);
        return "/unpf/goods/send/gdsManage";
    }
    
    
    /**
     * 
     * gridList:(获取商品管理页的商品列表). <br/>
     * 
     * @author gxq
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, GdsManageVO vo) throws Exception {
    	
    
        // /后场服务所需要的DTO；
        GdsInfoReqDTO dto = vo.toBaseInfo(GdsInfoReqDTO.class);
        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        dto.setGdsStatus(vo.getStatus());
        if (StringUtil.isNotEmpty(vo.getGdsId())) {
            dto.setId(vo.getGdsId());
        }
        if (StringUtil.isEmpty(vo.getStatus())) {
           // dto.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        }
        if (StringUtil.isEmpty(vo.getShopId())) {
            throw new BusinessException("web.gds.200008");
        }
        if (StringUtil.isNotEmpty(vo.getCatgCode())) {
            dto.setPlatCatgs(vo.getCatgCode());
        }
        if (StringUtil.isNotEmpty(vo.getStartTime())) {
            dto.setBegCreateTime(new Timestamp(vo.getStartTime().getTime()));
        }
        if (StringUtil.isNotEmpty(vo.getEndTime())) {
            dto.setEndCreateTime(new Timestamp(vo.getEndTime().getTime()));
        }
        if(StringUtil.isNotBlank(vo.getPriceSort())){
            
            dto.setPriceSort(vo.getPriceSort());
        }
        if (StringUtil.isNotEmpty(vo.getIsbn())) {
            dto.setIsbn(vo.getIsbn());
        }
        dto.setIfScoreGds(vo.getIfGdsScore());
        List<Long> propIds = new ArrayList<Long>();
        // 出版日期:1005作者:1001
        propIds.add(1005l);
        propIds.add(1001l);
        dto.setPropIds(propIds);
        dto.setGdsQueryOptions(new GdsQueryOption[] { GdsQueryOption.BASIC
        	/*	, GdsQueryOption.SHIPTEMPLATE, GdsQueryOption.PROP */});
        PageResponseDTO<UnpfGdsSendRespDTO> list = new PageResponseDTO<UnpfGdsSendRespDTO>();
        EcpBasePageRespVO<Map> respVO = null;
        try {
            list = unpfGoodSendRSV.queryGdsInfoListPage(dto);
            if (CollectionUtils.isNotEmpty(list.getResult())) {
                String verifySwitch = "";
                try {
                    verifySwitch = SysCfgUtil.fetchSysCfg("GDS_VERIFY_SWITCH").getParaValue();
                } catch (Exception e) {
                    LogUtil.error(MODULE, "获取商品审核开关参数失败！", e);
                }
                ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(vo.getShopId());
                for (UnpfGdsSendRespDTO gdsInfoRespDTO : list.getResult()) {
                    if (gdsInfoRespDTO.getAllPropMaps() != null) {
                        if (gdsInfoRespDTO.getAllPropMaps().get("1005") != null) {
                            String gdsPublishTime = gdsInfoRespDTO.getAllPropMaps().get("1005")
                                    .getValues().get(0).getPropValue();
                            gdsInfoRespDTO.setGdsPublishTime(gdsPublishTime);

                        }
                        if (gdsInfoRespDTO.getAllPropMaps().get("1001") != null) {
                            String author = gdsInfoRespDTO.getAllPropMaps().get("1001").getValues()
                                    .get(0).getPropValue();
                            gdsInfoRespDTO.setGdsAuthor(author);
                        }
                    }
                    if(shopInfoResDTO!=null){
                        gdsInfoRespDTO.setShopStatus(shopInfoResDTO.getShopStatus());
                    }
                    gdsInfoRespDTO.setVerifySwitch(verifySwitch);
                }
            }
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(list);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询列表失败", e);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(list);
            return super.addPageToModel(model, respVO);
        }
        if (GdsUtils.isEqualsValid(vo.getIfGdsScore())) {
            return super.addPageToModel(model, GdsParamsTool.batchGdsDetailUrl(respVO, "url", 2l));
        } else {
            return super.addPageToModel(model, GdsParamsTool.batchGdsDetailUrl(respVO, "url"));
        }
    }

    /**
     * 
     * gdssend:(单个商品推送). <br/>
     * 
     * @author lisp
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/gdssend")
    @ResponseBody
    public EcpBaseResponseVO gdssend(UnpfGdsSendVO unpfGdsSendVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        // 页面传入a,b,c格式
        String[] arrayGdsSendStr = new String[] {};
        String[] arrayStr = new String[] {};
        arrayGdsSendStr = unpfGdsSendVO.getSendGds().split(",");
        List<String> gdsSendlist = java.util.Arrays.asList(arrayGdsSendStr);
        List<String> list = new ArrayList<>();
        GdsSendReqDTO gdsSendReqDTO=new GdsSendReqDTO();
        if(gdsSendlist.size()>0){
        	String s = new String();
        	for (String string : gdsSendlist) {
        		arrayStr = string.split("@");
        		list= java.util.Arrays.asList(arrayStr);
        		gdsSendReqDTO.setGdsId(Long.valueOf(list.get(0)));
        		gdsSendReqDTO.setPlatType(list.get(1));
        		gdsSendReqDTO.setShopId(unpfGdsSendVO.getShopId());
        		gdsSendReqDTO.setIfThrow("0");
        		GdsSendRespDTO resp= unpfGdsSendRSV.send(gdsSendReqDTO);
        		if(!"1".equals(resp.getIfResult())){
        			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        			s+="商品编码:"+list.get(0)+",错误信息:"+resp.getMsg();
    			}
			}
        	vo.setResultMsg(s);
        }
        return vo;
    }

    /**
     *  对应关系修改
     * @param unpfGdsSendVO
     * @return
     */
    @RequestMapping(value = "/gdsUpdate")
    public String gdsUpdate(Model model,UnpfGdsSendVO unpfGdsSendVO) {
        LogUtil.info(MODULE, "===:"+JSON.toJSONString(unpfGdsSendVO));
        UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
        unpfGdsSendReqDTO.setShopId(unpfGdsSendVO.getShopId());
        unpfGdsSendReqDTO.setPlatType("taobao");
        unpfGdsSendReqDTO.setGdsId(unpfGdsSendVO.getGdsId());
        List<UnpfGdsSendLogRespDTO> unpfGdsSendRespDTOList = null;
        unpfGdsSendRespDTOList = this.unpfGoodSendRSV.querySends(unpfGdsSendReqDTO);        
        if(CollectionUtils.isEmpty(unpfGdsSendRespDTOList)||unpfGdsSendRespDTOList.get(0).getOutGdsId()==null||"".equals(unpfGdsSendRespDTOList.get(0).getOutGdsId())){
        	 unpfGdsSendReqDTO.setPlatType("youzan");
        	 unpfGdsSendRespDTOList = this.unpfGoodSendRSV.querySends(unpfGdsSendReqDTO); 
        }
        if(CollectionUtils.isEmpty(unpfGdsSendRespDTOList)||unpfGdsSendRespDTOList.get(0).getOutGdsId()==null||"".equals(unpfGdsSendRespDTOList.get(0).getOutGdsId())){
        	model.addAttribute("outGdsId","");
        	model.addAttribute("platType","taobao");
        }else{
        	model.addAttribute("platType",unpfGdsSendReqDTO.getPlatType());
        	model.addAttribute("outGdsId",unpfGdsSendRespDTOList.get(0).getOutGdsId());
        }
        model.addAttribute("vo",unpfGdsSendVO);
        return "/unpf/goods/send/gdsUpdate/gdsUpdate";
    }
    @RequestMapping(value = "/getOutGdsId")
    @ResponseBody
    public EcpBaseResponseVO getOutGdsId(Model model,UnpfGdsSendVO unpfGdsSendVO) {
        LogUtil.info(MODULE, "===:"+JSON.toJSONString(unpfGdsSendVO));
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        vo.setResultMsg("");
        try{
        	UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
        	unpfGdsSendReqDTO.setShopId(unpfGdsSendVO.getShopId());
        	unpfGdsSendReqDTO.setPlatType(unpfGdsSendVO.getPlatType());
        	unpfGdsSendReqDTO.setGdsId(unpfGdsSendVO.getGdsId());
        	List<UnpfGdsSendLogRespDTO> unpfGdsSendRespDTOList = null;
        	unpfGdsSendRespDTOList = this.unpfGoodSendRSV.querySends(unpfGdsSendReqDTO);        
        	if(!CollectionUtils.isEmpty(unpfGdsSendRespDTOList)){
        		vo.setResultMsg(unpfGdsSendRespDTOList.get(0).getOutGdsId());
        	}
        }catch (Exception e) {
        	LogUtil.error(MODULE, "获取信息异常" , e);
        	vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
        return vo;
    }
    
    /**
     * 修改对应关系提交
     * @param unpfGdsSendVO
     * @return
     */
    @RequestMapping(value = "/gdsUpdateSubmit")
    @ResponseBody
    public EcpBaseResponseVO gdsUpdateSubmit(UnpfGdsSendVO unpfGdsSendVO) {
        LogUtil.info(MODULE, "===:"+JSON.toJSONString(unpfGdsSendVO));
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
            unpfGdsSendReqDTO.setShopId(unpfGdsSendVO.getShopId());
            unpfGdsSendReqDTO.setPlatType(unpfGdsSendVO.getPlatType());
            unpfGdsSendReqDTO.setGdsId(unpfGdsSendVO.getGdsId());
            unpfGdsSendReqDTO.setOutGdsId(unpfGdsSendVO.getSendGds());
            this.unpfGoodSendRSV.updateGdsSendSubmit(unpfGdsSendReqDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            vo.setResultMsg("保存成功");
        } catch (BusinessException e){
            LogUtil.error(MODULE, "提交业务异常" , e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        } catch (Exception e) {
            LogUtil.error(MODULE, "提交异常" , e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg("保存异常");
        }

        return vo;
    }
}
