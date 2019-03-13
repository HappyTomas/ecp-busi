package com.zengshi.ecp.search.index.delta.inspector;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.dto.GdsParamDTO;
import com.zengshi.ecp.general.solr.message.GdsDeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.search.index.delta.AbstractObjectInspector;

public class GdsObjectInspector extends AbstractObjectInspector<GdsDeltaMessage> {

    public GdsObjectInspector(SecObjectRespDTO secObjectRespDTO, String message)
            throws IndexException {
        super(secObjectRespDTO, message, GdsDeltaMessage.class);
    }
    
    public GdsObjectInspector(SecObjectRespDTO secObjectRespDTO, GdsDeltaMessage gdsDeltaMessage)
            throws IndexException {
        super(secObjectRespDTO, gdsDeltaMessage);
    }

    @Override
    public boolean inspecte(SecObjectRespDTO secObjectRespDTO, GdsDeltaMessage gdsDeltaMessage) {

        boolean flag = false;
        if(EOperType.CATG.getType().equals(gdsDeltaMessage.getOperType())){
            IGdsCategoryRSV gdsCategoryRSV=EcpFrameContextHolder.getBean("gdsCategoryRSV", IGdsCategoryRSV.class);
            GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
            reqDTO.setCatgCode(gdsDeltaMessage.getCatgCodes());
            GdsCategoryRespDTO resp=gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
            if(resp==null || resp.getCatlogId()==null){
                return false;
            }
            gdsDeltaMessage.setCatlogIds(resp.getCatlogId().toString());
        }

        if (StringUtils.isNotBlank(gdsDeltaMessage.getCatlogIds())) {

            String cataLogIdArr[] = new String[] { gdsDeltaMessage.getCatlogIds() };

            if (gdsDeltaMessage.getCatlogIds().contains(SearchConstants.COMMA)) {
                cataLogIdArr = gdsDeltaMessage.getCatlogIds().split(SearchConstants.COMMA);
            }

            // 判断商品所属目录是否被包含在本搜索数据对象所在的商品目录
            if (StringUtils.isNotBlank(secObjectRespDTO.getObjectParams())) {

                // 参数解析
                List<GdsParamDTO> paramList = com.alibaba.fastjson.JSONObject.parseArray(
                        secObjectRespDTO.getObjectParams(), GdsParamDTO.class);
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(paramList)) {

                    flag = true;

                    for (String s : cataLogIdArr) {

                        boolean contains = false;
                        for (GdsParamDTO searchExtraParamDTO : paramList) {

                            if (StringUtils.equals(s, searchExtraParamDTO.getCatalogId() + "")) {
                                contains = true;
                                break;
                            }

                        }

                        // 不包含
                        if (!contains) {
                            flag = false;
                            break;
                        }
                    }

                }

            }

        } else {

            // 目录编码不传则不做目录匹配
            flag = true;
        }

        return flag;

    }

}
