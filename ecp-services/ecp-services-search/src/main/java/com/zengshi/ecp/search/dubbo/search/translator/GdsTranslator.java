package com.zengshi.ecp.search.dubbo.search.translator;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.solr.dto.GdsParamDTO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.ITranslator;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.alibaba.fastjson.JSONObject;

public class GdsTranslator implements ITranslator {
    
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV ;
    
    public GdsTranslator(IGdsCatalog2SiteRSV gdsCatalog2SiteRSV){
        this.gdsCatalog2SiteRSV = gdsCatalog2SiteRSV;
    }

    @Override
    public SecConfigRespDTO translate(Long currentSiteId,
            Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap) throws SearchException {

        // 查询站点所映射的商品目录
        GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
        reqDTO.setSiteId(currentSiteId);
        reqDTO.setCatalogStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsCatalogRespDTO> gdsCatalogRespDTOList = gdsCatalog2SiteRSV
                .queryRelationBySiteId(reqDTO);

        if (CollectionUtils.isEmpty(gdsCatalogRespDTOList)) {
            throw new SearchException(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_ILLEGALSITEID, new Long[] { currentSiteId },
                    LocaleUtil.getLocale()));
        }

        // 查询商品目录所映射的唯一集合名称
        for (SecConfigRespDTO secConfigRespDTO : cacheSecConfigRespDTOMap.values()) {

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(secConfigRespDTO
                    .getSecObjectRespDTOList())) {
                for (SecObjectRespDTO secObjectRespDTO : secConfigRespDTO.getSecObjectRespDTOList()) {
                    if (StringUtils.isNotBlank(secObjectRespDTO.getObjectParams())) {
                        
                        List<GdsParamDTO> paramList = null;
                        try{
                            // 参数解析
                            paramList = JSONObject.parseArray(
                                    secObjectRespDTO.getObjectParams(), GdsParamDTO.class);
                        }catch(Exception e){
                            continue;
                        }

                        // 若不指定商品目录则不允许搜索
                        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(paramList)) {
                            if (gdsCatalogRespDTOList.size() == paramList.size()) {
                                boolean flag = true;
                                for (int i = 0; i < gdsCatalogRespDTOList.size(); i++) {
                                    if (!StringUtils.equals(""
                                            + gdsCatalogRespDTOList.get(i).getId(), paramList
                                            .get(i).getCatalogId() + "")) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag == true) {

                                    // 取第一个匹配到的索引配置
                                    return secConfigRespDTO;
                                }
                            }
                        }

                    }
                }
            }

        }

        String catalogIdStr = "";
        for (int i = 0; i < gdsCatalogRespDTOList.size(); i++) {
            if (i != 0)
                catalogIdStr += ",";
            catalogIdStr += gdsCatalogRespDTOList.get(i).getId();
        }

        throw new SearchException(ResourceMsgUtil.getMessage(
                SearchMessageKeyContants.Info.KEY_INFO_ILLEGALCATELOGIDS, new String[] {
                        currentSiteId + "", catalogIdStr }, LocaleUtil.getLocale()));

    }

}
