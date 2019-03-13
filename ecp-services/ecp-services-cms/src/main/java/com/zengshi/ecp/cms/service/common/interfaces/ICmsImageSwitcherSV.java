package com.zengshi.ecp.cms.service.common.interfaces;

import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface ICmsImageSwitcherSV extends IGeneralSQLSV{
    
 /**
  * 
  * queryCmsSiteInfoPage:(这里用一句话描述这个方法的作用). <br/> 
  * 
  * @author wangbh
  * @param dto
  * @return
  * @throws BusinessException 
  * @since JDK 1.7
  */
    public PageResponseDTO<CmsImageSwitcherRespDTO> queryCmsImageSwitcherPage(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
 
    
    /**
     * 
     * saveCmsImageSwitcher:(保存，未发布). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * updateCmsImageSwitcher:(修改). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * selectCmsImageSwitcher:(查询一条数据). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsImageSwitcherRespDTO selectCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
    
}
