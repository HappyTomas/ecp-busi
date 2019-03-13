package com.zengshi.ecp.prom.service.busi.interfaces;

import java.util.Date;
import java.util.List;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromInfoSV extends IGeneralSQLSV{
    /**
     * 保存 提交
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfo savePromInfo(PromDTO promDTO) throws BusinessException;

    /**
     * 编辑状态 促销基本信息
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfo editPromInfoStatusByPromId(Long promId, Long updateStaff, String status,
            Date updateTime) throws BusinessException;

    /**
     * 保存 提交
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSku(PromDTO promDTO) throws BusinessException;

    /**
     * 黑名单
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuLimt(PromDTO promDTO) throws BusinessException;

    /**
     * 保存 提交 促销规则
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromConstraint(PromDTO promDTO) throws BusinessException;

    /**
     * 保存 提交 计算规则
     * 
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromCalRule(PromDTO promDTO) throws BusinessException;

    /**
     * 促销列表
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoResponseDTO> queryPromInfoList(PromDTO promDTO) throws BusinessException;

    /**
     * 促销列表 分页
     * 
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoForPage(PromInfoDTO promInfoDTO,String queryFlag)
            throws BusinessException;
    /**
     * 促销列表 分页
     * @param promInfoDTO
     * @param groupIdList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoForPage(PromInfoDTO promInfoDTO,List<Long> groupIds)
            throws BusinessException;
    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoResponseDTO queryPromInfoResponseDTOById(Long id) throws BusinessException;

    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoDTO queryPromInfoDTOById(Long id) throws BusinessException;
    
    /**
     * 促销列表  无分页
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoResponseDTO> listPromInfo(PromInfoDTO promInfoDTO)
            throws BusinessException;
 
    /**
     * 促销单品库存量 保存
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuStockLimit(PromDTO promDTO) throws BusinessException;
    /**
     * 促销搭配库存量 保存
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromMatchStockLimit(PromDTO promDTO) throws BusinessException;
    /**
     * 促销库存量 更新
     * @param promStockLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromStockLimitDeduce(PromStockLimitDTO promStockLimitDTO) throws BusinessException;
    /**
     * 促销库存量  补偿新增机制
     * @param promStockLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromStockLimitAdd(PromStockLimitDTO promStockLimitDTO) throws BusinessException;
    /**
     * 促销赠品 保存
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGift(PromDTO promDTO) throws BusinessException;
 
    /**
     * 促销赠品库存量 更新
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromGiftDeduce(PromGiftDTO promGiftDTO) throws BusinessException;
    /**
     * 促销赠品库存量  补偿新增机制
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromGiftAdd(PromGiftDTO promGiftDTO) throws BusinessException;
    
    /**
     * 平台促销待审核数据统计
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     */
    public Long queryTotalByProm(PromInfoDTO promInfoDTO) throws BusinessException;
    /**
     * 
     * savePromCatg:(保存促销分类数据). <br/> 
     * 
     * @author PJieWin 
     * @param promDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void savePromCatg(PromDTO promDTO)throws BusinessException;
    
    /**
     * 显示秒杀促销列表 分页
     * 
     * @param promInfoDTO
     * @param 
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public  PageResponseDTO<PromInfoResponseDTO> listSecondPromInfoForPage(PromInfoDTO promInfoDTO) throws BusinessException;


    /**
     * 促销信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public PromInfoResponseDTO selectPromInfoResponseDTOById(Long id) throws BusinessException;

}
