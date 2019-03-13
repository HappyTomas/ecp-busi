package com.zengshi.ecp.busi.coup.buyer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zengshi.ecp.busi.coup.CoupConst;
import com.zengshi.ecp.busi.coup.buyer.controller.vo.MyCoupQueryVO;
import com.zengshi.ecp.busi.coup.buyer.controller.vo.ParamOrderVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015-11-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */
@Controller
@RequestMapping(value="/mycoup")
public class myCoupController extends EcpBaseController {
    
    private static String MODULE = myCoupController.class.getName();
    
   @Resource 
   private ICoupDetailRSV coupDetailRSV;
    
   //加载页面
    @RequestMapping(value="/index")
    public String init(Model model,MyCoupQueryVO myCoupQueryVO) throws BusinessException{
      
        myCoupQueryVO.setQtype("1");
        
        List<ParamOrderVO> orderList= this.searchParamOrder();
        
        if(!CollectionUtils.isEmpty(orderList)){
            //排序字段设置
            Map<String,String> mapSort=new HashMap<String,String>();
            //mapSort.put("USE_TIME", "1");
            for(ParamOrderVO v:orderList){
                   if("0".equals(v.getSpValue())){
                       mapSort.put(v.getSpCode(), v.getSpValue());
                   }
            }
            myCoupQueryVO.setMapSort(mapSort);
        }
        //myCoupQueryVO.setPageSize(9);
        model.addAttribute("mycoupPage", this.queryPageData(model, myCoupQueryVO));
        model.addAttribute("orderList", orderList);
        return "/coup/mycoup/mycoup";
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
    private  PageResponseDTO<CoupMeRespDTO> queryPageData(Model model,MyCoupQueryVO myCoupQueryVO){
        
        //为了解决页面传入pagesize=9 初始化时就为9的问题 暂时先这么处理吧   下拉框选择显示条数 不变
        if(myCoupQueryVO.getPageSize()<=10){
            myCoupQueryVO.setPageSize(9);
        }
        // 后场服务所需要的DTO；
        CoupMeReqDTO queryDTO = myCoupQueryVO.toBaseInfo(CoupMeReqDTO.class);


        if(StringUtil.isEmpty(myCoupQueryVO.getQueryType())){
            //默认未使用
            myCoupQueryVO.setQueryType("1");
        }
        // 组织参数
        ObjectCopyUtil.copyObjValue(myCoupQueryVO, queryDTO, "", false);
        
        queryDTO.setOpeType(myCoupQueryVO.getQueryType());
        queryDTO.setStaffId(queryDTO.getStaff().getId());
        PageResponseDTO<CoupMeRespDTO> page= coupDetailRSV.queryCoupDetailPage(queryDTO);
        if(page!=null && !CollectionUtils.isEmpty(page.getResult())){
        	  for (CoupMeRespDTO coupMeRespDTO : page.getResult()) {
      			if (coupMeRespDTO.getConditions()==""||coupMeRespDTO.getConditions()==null) {
      				coupMeRespDTO.setConditions("全品类");
      			}
      			//折扣优惠券
      			if(StringUtil.isNotBlank(coupMeRespDTO.getUseRuleCode())&&coupMeRespDTO.getUseRuleCode().contains("240")){
      				coupMeRespDTO.setCoupValueShow(coupMeRespDTO.getCoupValue()/1000.0+"");
      			}else{
      				coupMeRespDTO.setCoupValueShow(coupMeRespDTO.getCoupValue()/100.00+"");
      			}
      		}
        }
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
        return "/coup/mycoup/div/mycoup-list";
    }
    
    
    /**
     * 数量计算
     * @param model
     * @param queryType
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/queryCount")
    @ResponseBody
    public EcpBaseResponseVO queryCount(Model model, @RequestParam("queryType") String queryType) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(queryType)) {
                 throw new BusinessException("web.coupon.450001");
            }
            //1:可使用 2:已使用 0:已过期
            CoupMeReqDTO dto= new CoupMeReqDTO();
            dto.setOpeType(queryType);
            dto.setStaffId(dto.getStaff().getId());
            
            Long num=coupDetailRSV.queryCoupDetailCount(dto);
            
            if(StringUtil.isEmpty(num)){
                num=new Long(0);
            }
            vo.setResultMsg(String.valueOf(num));
             
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 优惠券删除
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public EcpBaseResponseVO del(Model model, @RequestParam("id") String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450002");
            }
            CoupMeReqDTO  dto=new CoupMeReqDTO();
            dto.setId(Long.valueOf(id));
            dto.setStaffId(dto.getStaff().getId());
            coupDetailRSV.deleteCoupDetail(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 获得排序参数 列表
     * @return
     * @author huangjx
     */
    private List<ParamOrderVO> searchParamOrder(){
      //排序字段设置 处理
        List<ParamOrderVO> orderList=new ArrayList<ParamOrderVO>();
       
        List<BaseParamDTO> list= BaseParamUtil.fetchParamList(CoupConst.CoupKey.MYCOUP_ORDER_COL_ORDER);
        
        if(!CollectionUtils.isEmpty(list)){
            
            List<BaseParamDTO> listCol= BaseParamUtil.fetchParamList(CoupConst.CoupKey.MYCOUP_ORDER_COL);
            
              for(BaseParamDTO dto:list){
                  
                  ParamOrderVO paramVo=new ParamOrderVO();
                  orderList.add(paramVo);
                  paramVo.setSpCode(dto.getSpCode());
                  paramVo.setSpValue(dto.getSpValue());
                  
                  if(!CollectionUtils.isEmpty(listCol)){
                      for(BaseParamDTO dtoCol:listCol){
                          if(dto.getSpCode().equals(dtoCol.getSpValue())){
                              paramVo.setParamName( dtoCol.getSpCode());
                              break;
                          }
                      }
                      
                  }
                  
              }
        }
        return orderList;
    }
}


