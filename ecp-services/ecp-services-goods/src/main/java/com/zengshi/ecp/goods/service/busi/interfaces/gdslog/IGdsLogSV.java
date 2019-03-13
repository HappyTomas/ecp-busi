package com.zengshi.ecp.goods.service.busi.interfaces.gdslog;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsLogSV {
	
	public static final String GDS_MODEL = "GDS";
	
	
    /**
     * 
     * addGdsLog:添加日志. <br/> 
     * 
     * @author liyong7
     * @param logReqDTO
     * @throws Exception 
     * @since JDK 1.6
     */
    public void addGdsLog(GdsLogReqDTO logReqDTO) throws Exception;

    /**
     * 
     * updateGdsLogByPKSelective:根据主键更新日志. <br/> 
     * 
     * @author liyong7
     * @param logReqDTO
     * @throws Exception 
     * @since JDK 1.6
     */
    public void updateGdsLogByPKSelective(GdsLogReqDTO logReqDTO) throws Exception;


    PageResponseDTO<GdsLogRespDTO> queryGdsLogRespDTOPaging(GdsLogReqDTO dto)
            throws Exception;
    List<GdsLogRespDTO> queryGdsLogRespDTO(GdsLogReqDTO dto)
            throws Exception;	
}
