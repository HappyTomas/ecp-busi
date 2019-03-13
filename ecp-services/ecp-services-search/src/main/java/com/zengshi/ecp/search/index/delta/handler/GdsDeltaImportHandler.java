package com.zengshi.ecp.search.index.delta.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.message.GdsDeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.search.index.IndexManager;
import com.zengshi.ecp.search.index.delta.AbstractDeltaImportHandler;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsDeltaImportHandler extends AbstractDeltaImportHandler<GdsDeltaMessage> {

    public GdsDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, String message) throws IndexException {
        super(indexServer, suggestServer,secConfigRespDTO, secObjectRespDTO, message, GdsDeltaMessage.class);
    }
    
    public GdsDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, GdsDeltaMessage gdsDeltaMessage) throws IndexException {
        super(indexServer, suggestServer,secConfigRespDTO, secObjectRespDTO, gdsDeltaMessage);
    }

    /**
     * 
     * reFullUpdate:(查询刷新全部索引). <br/>
     * 
     * @author linwb3
     * @param deltaMessage
     * @since JDK 1.6
     */
    private void reFullUpdate(GdsDeltaMessage deltaMessage) {
        if (StringUtils.isNotBlank(deltaMessage.getSiteId())) {
            Long id = Long.parseLong(deltaMessage.getSiteId());
            SecConfigReqDTO req = new SecConfigReqDTO();
            req.setId(id);
            IndexManager.reFullImportIndex(req, false);
        }
    }

    /**
     * 
     * reFullCatgs:(重建分类索引). <br/>
     * 
     * @author linwb3
     * @param deltaMessage
     * @throws SearchException
     * @since JDK 1.6
     */
    private void reFullCatgs(GdsDeltaMessage deltaMessage) {
        // 如果是分类更新
        IGdsCategoryRSV gdsCategoryRSV = EcpFrameContextHolder.getBean("gdsCategoryRSV",
                IGdsCategoryRSV.class);
        IGdsInfoQueryRSV gdsInfoQueryRSV = EcpFrameContextHolder.getBean("gdsInfoQueryRSV",
                IGdsInfoQueryRSV.class);

        // 查询所有子分类
        GdsCategoryReqDTO req = new GdsCategoryReqDTO();
        req.setCatgCode(deltaMessage.getCatgCodes());
        req.setIsContainSub(true);
        List<GdsCategoryRespDTO> catgs = new ArrayList<GdsCategoryRespDTO>();
        GdsCategoryRespDTO category = gdsCategoryRSV.queryGdsCategoryByPK(req);
        catgs.add(category);
        if (CollectionUtils.isNotEmpty(category.getChildren())) {
            catgs.addAll(category.getChildren());
        }

        if (CollectionUtils.isNotEmpty(catgs)) {
            // 查询所有分类下的商品
            for (GdsCategoryRespDTO gdsCategoryRespDTO : catgs) {
                boolean falg = true;
                int pageNo = 1;
                while (falg) {
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    gdsInfoReqDTO.setFullInfo(false);
                    gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
                    gdsInfoReqDTO.setMainCatgs(gdsCategoryRespDTO.getCatgCode());
                    gdsInfoReqDTO.setPageNo(pageNo);
                    gdsInfoReqDTO.setPageSize(30);
                    PageResponseDTO<GdsInfoRespDTO> page = gdsInfoQueryRSV
                            .queryGdsInfoListByCatgCode(gdsInfoReqDTO);
                    if (page == null || CollectionUtils.isEmpty(page.getResult())) {
                        falg = false;
                    } else {
                        List<GdsInfoRespDTO> gdsInfos = page.getResult();
                        for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfos) {
                            if (gdsInfoRespDTO.getId() != null) {
                                try {
                                    DeltaMessage message = new DeltaMessage();
                                    message.setDeltaType(deltaMessage.getDeltaType());
                                    //增量商品编码
                                    List<String> ids=new ArrayList<String>();
                                    ids.add(gdsInfoRespDTO.getId() + "");
                                    message.setIds(ids);
                                    message.setObjectNameEn(deltaMessage.getObjectNameEn());
                                    //操作类型需要变更否则会导致死循环
                                    message.setOperType(EOperType.UPDATE.getType());
                                    this.deltaImportDbIndex(message);
                                } catch (Exception e) {
                                    LogUtil.error(MODULE, "分类索单条刷新失败，id为" + gdsInfoRespDTO.getId(),
                                            e);
                                }
                            }
                        }
                    }
                    pageNo++;
                }
            }
        }
    }

    @Override
    public void deltaDbImport(GdsDeltaMessage deltaMessage) throws BusinessException,
            IndexException {
        boolean match = this.deltaImportDbIndex(deltaMessage);
        if (!match) {
            if (StringUtils
                    .equals(this.getDeltaMessage().getOperType(), EOperType.REFULL.getType())) {
                reFullUpdate(deltaMessage);
            } else if (StringUtils.equals(this.getDeltaMessage().getOperType(),
                    EOperType.CATG.getType())) {
                reFullCatgs(deltaMessage);
            }
        }
    }

    @Override
    public void deltaCustomImport(GdsDeltaMessage deltaMessage) throws BusinessException,
            IndexException {
        this.deltaImportCustomIndex(deltaMessage);
    }

}
