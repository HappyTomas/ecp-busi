package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.sku.impl.GdsCatgSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class FreeMatchDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = FreeMatchDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IPromMatchSV promMatchSV;
    
    @Resource
    private GdsCatgSVImpl  gdsCatgSV;
    
    @Resource
    private IGdsInfoQueryRSV  gdsInfoQueryRSV;
    
    /**
     * TODO是否满足优惠规则 (促销类型)
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#isFulFilPromByGds(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException {
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        
        return Boolean.TRUE;
    }

    /**
     * TODO计算优惠信息  自由搭配设置值为价格优惠  没有优惠 默认为0
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#calculatePromotion(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     * @author huangjx
     */
    @Override
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException {
        
        PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
        promMatchSkuDTO.setPromId(ordPromDetailDTO.getPromInfoDTO().getId());
        promMatchSkuDTO.setGdsId(ordPromDetailDTO.getGdsId());
        promMatchSkuDTO.setSkuId(ordPromDetailDTO.getSkuId());
        promMatchSkuDTO.setStatus(PromConstants.PromMatchSku.STATUS_1);
        promMatchSkuDTO.setPromStatus(PromConstants.PromInfo.STATUS_10);//生效
        
        List<PromMatchSkuDTO> list=promMatchSV.queryMatchSkuList(promMatchSkuDTO);
        
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
        BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
        BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
        BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
        BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
        BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
        
        //开始计算价格
        discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
        
        discountFinalPrice= BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
        
        discountPrice = discountCaclPrice.subtract(discountFinalPrice);
        
        if(CollectionUtils.isEmpty(list)){
            //优惠了价格
            //discountPrice = BigDecimal.ZERO;
        }else{
            //促销单价
        	discountFinalPrice = BigDecimal.valueOf(list.get(0).getPrice());
            //优惠了价格
        	discountPrice = discountCaclPrice.subtract(discountFinalPrice);
        }

        //优惠了金额
        discountMoney = BigDecimal.valueOf(ordPromDetailDTO.getOrderAmount()).multiply(discountPrice);

        promDiscountDTO.setDiscountPrice(discountPrice);
        promDiscountDTO.setDiscountMoney(discountMoney);
        promDiscountDTO.setDiscountCaclPrice(discountCaclPrice);
        promDiscountDTO.setDiscountFinalPrice(discountFinalPrice);
        
        return promDiscountDTO;
    }

    /**
     * TODO促销信息录入-优惠规则,是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#needToVerified(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        return Boolean.TRUE;
    }
    /**
     * TODO 产品自由搭配
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        // 产品自由搭配
        //没有录入 搭配商品信息哦
        if(CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
            throw new BusinessException("prom.400142");
        }else{
            //搭配录入数量 非空、非有效数字
            for(PromMatchSkuDTO promMatchSkuDTO:promDTO.getMatchSkuDTOList()){
                //非空验证
                if(StringUtil.isEmpty(promMatchSkuDTO.getPromCnt())){
                    throw new BusinessException("prom.400143");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(promMatchSkuDTO.getPromCnt().toString())){
                    throw new BusinessException("prom.400144");
                }
                //大于等于0
                if(promMatchSkuDTO.getPromCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400145");
                }
                
                //非空验证
                if(StringUtil.isEmpty(promMatchSkuDTO.getPrice())){
                    throw new BusinessException("prom.400146");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(promMatchSkuDTO.getPrice().toString())){
                    throw new BusinessException("prom.400147");
                }
                //大于等于0
                if(promMatchSkuDTO.getPrice().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400148");
                }
            }
            this.valid(promDTO);
        }
    }
    /**
     * 验证搭配商品 和选择商品 必需不同
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void valid(PromDTO promDTO) throws BusinessException {
        
       if(!CollectionUtils.isEmpty(promDTO.getSkuList())){
            //搭配商品
           if(!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
               
               for(PromMatchSkuDTO promMatchSkuDTO:promDTO.getMatchSkuDTOList()){
                   //促销商品
                   for(PromSkuDTO promSkuDTO:(List<PromSkuDTO>)promDTO.getSkuList()){
                             if (  promMatchSkuDTO.getSkuId().equals(promSkuDTO.getSkuId())){
                                 //单品一致
                                 String[] key=new String[1];
                                 key[0]=promMatchSkuDTO.getSkuId().toString();
                                 throw new BusinessException("prom.400150",key);
                             }
                     }
               }
           }
       }
       //搭配的商品不能属于选择的分类
       if(!CollectionUtils.isEmpty(promDTO.getCatgList())){
     	   
     	  if(!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
     		      //只有一个搭配商品才验证 否则详情详情页面主副商品都是一个而且价格为搭配价
                   if(promDTO.getMatchSkuDTOList().size()==1){
		                   for(PromMatchSkuDTO promMatchSkuDTO:promDTO.getMatchSkuDTOList()){
					          //调用接口 获得gds sku当前分类 
					          GdsInfoReqDTO dto=new GdsInfoReqDTO();
					          dto.setId(promMatchSkuDTO.getGdsId());
					          
					          GdsQueryOption[] gdsQuery=new GdsQueryOption[1];
					          gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
					          dto.setGdsQueryOptions(gdsQuery);
					          
					          GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);
					          
					          List<String> platformCatgList=new ArrayList<String>();
					         
					          if(gdsDTO!=null){
					          	//如果下架 删除等 gdsDTO为null
						            if(!CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
						                  for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
						                      platformCatgList.add(p.getCatgCode());
						                  }
						                  if(!CollectionUtils.isEmpty(platformCatgList)){
							                  // 验证 当前获得列表对应的分类 属于当前商品+单品对应分类或者子分类。 如果是加入列表，否则不加入
									          ArrayList arrList=promDTO.getCatgList();
									          for(int i=0;i<arrList.size();i++){
									        	   PromSkuDTO ps=new PromSkuDTO();
									        	   ps=(PromSkuDTO)arrList.get(i);
									        	   if(ps!=null && StringUtils.isNotBlank(ps.getCatgCode())){
									        		   //调用接口 获得 当前分类下子节点 
											              Integer result=  gdsCatgSV.compareCatg(platformCatgList,ps.getCatgCode());
											              if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
											                //返回结果关系
											            	  String[] key=new String[1];
								                              key[0]=ps.getCatgCode();
								                              throw new BusinessException("prom.400210",key);
											              }
									        	   }
									          }
						                  }
						            }
					          }
					         
				          }
		        	  }
               }
     	   }
    }
}
