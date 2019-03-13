package com.zengshi.ecp.goods.dubbo.interfaces;

import java.math.BigDecimal;

import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 会员分类折扣服务 Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015年10月21日上午10:37:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public interface IGdsCatgCustDiscRSV {
    /**
     * 
     * addGdsCatgCustDisc:(新增会员分类折扣). <br/>
     * 
     * 
     * @author zjh
     * @param catgCustDiscReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void addGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO)
            throws BusinessException;

    /**
     * 
     * editGdsCatgCustDisc:(编辑会员分类折扣). <br/>
     * 
     * @author zjh
     * @param catgCustDiscReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void editGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO)
            throws BusinessException;

    /**
     * 
     * deleteGdsCatgCustDisc:(删除会员分类折扣). <br/>
     * 
     * @author zjh
     * @param catgCustDiscReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteGdsCatgCustDisc(GdsCatgCustDiscReqDTO catgCustDiscReqDTO)
            throws BusinessException;

    /**
     * 
     * queryGdsCatgCustDiscByPage:(分页查询会员分类折扣). <br/>
     * 
     * @author zjh
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatgCustDiscRespDTO> queryGdsCatgCustDiscByPage(GdsCatgCustDiscReqDTO catgCustDiscReqDTO)
            throws BusinessException;

    /**
     * 
     * calCatgCustDisc:(计算会员分类折扣). <br/>
     * 
     * @author zjh
     * @param calCatgCustDiscReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public BigDecimal calCatgCustDisc(CalCatgCustDiscReqDTO calCatgCustDiscReqDTO)
            throws BusinessException;
    /**
     * 
     * deleteGdsCatgCustDiscByGroup:(批量删除). <br/> 
     * 
     * @author zjh 
     * @param batchDelReqDTO
     * @throws Exception 
     * @since JDK 1.6
     */
    public void deleteGdsCatgCustDiscByGroup(GdsCatgCustDiscBatchDelReqDTO batchDelReqDTO  )throws Exception;

}
