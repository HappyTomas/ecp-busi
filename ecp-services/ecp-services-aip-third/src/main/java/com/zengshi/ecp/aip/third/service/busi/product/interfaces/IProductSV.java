package com.zengshi.ecp.aip.third.service.busi.product.interfaces;

import java.util.List;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IProductSV {

    /**
     * 
     *(获取匹配产品规则)<br/> 
     * @author huangjx
     * @param s 商品发布的目标类目，必须是叶子类目
     * @return 
     * @since JDK 1.7
     */
    public String getProductMatch(CatgReqDTO catgReqDTO)throws BusinessException;
    
    /**
     * 
     *根据tmall.product.match.schema.get获取到的规则，填充相应地的字段值以及类目，匹配符合条件的产品，返回匹配product结果，注意，有可能返回多个产品ID，以逗号分隔（尤其是图书类目）；
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public List<String> setProductMatch(CatgReqDTO catgReqDTO)throws BusinessException;
    
    /**
     * 
     * 获得产品基本信息 xml
     * @author huangjx
     * @param s 
     * @return 
     * @since JDK 1.7
     */
    public ProductStatusThirdRespDTO getProductStatus(ProductThirdReqDTO productReqDTO)throws BusinessException;
    
    /**
     * 
     * 产品添加规则 获得xml
     * @author huangjx
     * @param s 
     * @return 
     * @since JDK 1.7
     */
    public String getProductAddRule(CatgReqDTO catgReqDTO)throws BusinessException;
    
    /**
     * 
     *	//添加产品  返回产品id
     * @author huangjx
     * @param s 
     * @return 
     * @since JDK 1.7
     */
    public String productAddByRule(ProductThirdReqDTO productReqDTO)throws BusinessException;
    
}

