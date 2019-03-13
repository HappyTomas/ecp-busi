package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdFileImportUalMapper;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdFileImportMapper;
import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dao.model.OrdFileImportCriteria;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日下午5:43:57  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class OrdFileImportSVImpl extends GeneralSQLSVImpl implements IOrdFileImportSV {

    @Resource
    private OrdFileImportMapper ordFileImportMapper;

    @Resource
    private OrdFileImportUalMapper ordFileImportUalMapper;

    @Resource(name = "seq_ord_file_import")
    private Sequence seqOrdFileImport;

    @Override
    public OrdFileImport saveOrdFileImport(final OrdFileImport ordFileImport) {
        ordFileImport.setId(this.seqOrdFileImport.nextValue());
        this.ordFileImportMapper.insert(ordFileImport);
        return ordFileImport;
    }

//    @Override
//    public List<OrdFileImport> findByShopId(final String shopId) {
//        final OrdFileImportCriteria ofic = new OrdFileImportCriteria();
//        ofic.createCriteria().andOrderIdEqualTo(shopId);
//        return this.ordFileImportMapper.selectByExample(ofic);
//    }

    @Override
    public PageResponseDTO<RShowImportResponse> queryChangeImportStatus(
            RShowImportRequest rShowImportRequest) {
        OrdFileImportCriteria ofic = new OrdFileImportCriteria();
        ofic.setLimitClauseCount(rShowImportRequest.getPageSize());
        ofic.setLimitClauseStart(rShowImportRequest.getStartRowIndex());
        ofic.setOrderByClause("order_id desc");
        List<String> status = new ArrayList<String>();
        status.add("05"); //已提交
        status.add("06"); //已删除
        ofic.createCriteria().andShopIdEqualTo(rShowImportRequest.getShopId())
                             .andFromIdEqualTo(DelyConstants.FromId.FROM_ID_CHANGE_IMPORT) //变更实体编号文件导入
                             .andStatusNotIn(status);   
        return super.queryByPagination(rShowImportRequest, ofic, true, new PaginationCallback<OrdFileImport, RShowImportResponse>() {

            @Override
            public List<OrdFileImport> queryDB(BaseCriteria criteria) {
                return ordFileImportMapper.selectByExample((OrdFileImportCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return ordFileImportMapper.countByExample((OrdFileImportCriteria)criteria);
            }

            @Override
            public List<Comparator<OrdFileImport>> defineComparators() {
                List<Comparator<OrdFileImport>> ls = new ArrayList<Comparator<OrdFileImport>>();
                ls.add(new Comparator<OrdFileImport>(){

                    @Override
                    public int compare(OrdFileImport o1, OrdFileImport o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                    
                });
                return ls;
            }

            @Override
            public RShowImportResponse warpReturnObject(OrdFileImport t) {
                RShowImportResponse rsir = new RShowImportResponse();
                rsir.setImportNo(t.getId());
                BeanUtils.copyProperties(t, rsir);
                return rsir;
            }
        });
    }

    @Override
    public PageResponseDTO<RShowImportResponse> queryAddImportStatus(
            RShowImportRequest rShowImportRequest) {
        OrdFileImportCriteria ofic = new OrdFileImportCriteria();
        ofic.setLimitClauseCount(rShowImportRequest.getPageSize());
        ofic.setLimitClauseStart(rShowImportRequest.getStartRowIndex());
        ofic.setOrderByClause("order_id desc");
        List<String> status = new ArrayList<String>();
        status.add("05"); //已提交
        status.add("06"); //已删除
        ofic.createCriteria().andShopIdEqualTo(rShowImportRequest.getShopId())
                             .andFromIdEqualTo(DelyConstants.FromId.FROM_ID_ADD_IMPORT)   //新增实体编号文件导入
                             .andStatusNotIn(status);  
        return super.queryByPagination(rShowImportRequest, ofic, true, new PaginationCallback<OrdFileImport, RShowImportResponse>() {

            @Override
            public List<OrdFileImport> queryDB(BaseCriteria criteria) {
                return ordFileImportMapper.selectByExample((OrdFileImportCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return ordFileImportMapper.countByExample((OrdFileImportCriteria)criteria);
            }

            @Override
            public RShowImportResponse warpReturnObject(OrdFileImport t) {
                RShowImportResponse rsir = new RShowImportResponse();
                rsir.setImportNo(t.getId());
                BeanUtils.copyProperties(t, rsir);
                return rsir;
            }
        });
    }

    @Override
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo) {
        
      final OrdFileImportCriteria ofic = new OrdFileImportCriteria();
      ofic.createCriteria().andShopIdEqualTo(sBaseAndImportInfo.getShopId())
                           .andIdEqualTo(sBaseAndImportInfo.getImportNo());
      OrdFileImport ofi = new OrdFileImport();
      ofi.setStatus(sBaseAndImportInfo.getStatus());
      this.ordFileImportMapper.updateByExampleSelective(ofi, ofic);
      
    }

    @Override
    public RExportFileResp queryById(RExportFileReq rExportFileReq) throws BusinessException {

        OrdFileImport resp = this.ordFileImportMapper.selectByPrimaryKey(rExportFileReq.getKey());
        if(resp == null){
            return null;
        }
        RExportFileResp rExportFileResp = new RExportFileResp();
        rExportFileResp.setKey(rExportFileReq.getKey());  //关联KEY
        rExportFileResp.setCompleteFlag(resp.getStatus()); //是否完成
        rExportFileResp.setFileId(resp.getFileName());  //文件mongodb的ID
        rExportFileResp.setProgress(resp.getShopId());  //进度
        return rExportFileResp;
    }

    @Override
    public void updateById(OrdFileImport ordFileImport) throws BusinessException {

        OrdFileImport ofi = new OrdFileImport();
        ofi.setId(ordFileImport.getId());
        if(ordFileImport.getStatus() != null){
            ofi.setStatus(ordFileImport.getStatus());
        }
        if(ordFileImport.getFileName() != null){
            ofi.setFileName(ordFileImport.getFileName());
        }
        if(ordFileImport.getShopId() != null){
            ofi.setShopId(ordFileImport.getShopId());
        }
        if(ordFileImport.getRemark() != null){
            ofi.setRemark(ordFileImport.getRemark());
        }
        this.ordFileImportUalMapper.updateById(ofi);
    }

    @Override
    public void updateByIdSelective(OrdFileImport ordFileImport) throws BusinessException {
        OrdFileImport ofi = new OrdFileImport();
        ofi.setId(ordFileImport.getId());
        if(ordFileImport.getStatus() != null){
            ofi.setStatus(ordFileImport.getStatus());
        }
        if(ordFileImport.getFileName() != null){
            ofi.setFileName(ordFileImport.getFileName());
        }
        if(ordFileImport.getShopId() != null){
            ofi.setShopId(ordFileImport.getShopId());
        }
        if(ordFileImport.getRemark() != null){
            ofi.setRemark(ordFileImport.getRemark());
        }
        this.ordFileImportMapper.updateByPrimaryKeySelective(ofi);
    }

	@Override
	public OrdFileImportDTO insertOrdFileImport(OrdFileImportDTO ordFileImportDTO) {
		 OrdFileImport o = new OrdFileImport();
		 BeanUtils.copyProperties(ordFileImportDTO, o);
		 OrdFileImport ofi = saveOrdFileImport(o);
		 ordFileImportDTO.setId(ofi.getId());
		return ordFileImportDTO;
	}

	@Override
	public void updateOrdFileImportById(OrdFileImportDTO ordFileImportDTO) {
		 OrdFileImport o = new OrdFileImport();
		 BeanUtils.copyProperties(ordFileImportDTO, o);
		 updateByIdSelective(o);
	}
}
