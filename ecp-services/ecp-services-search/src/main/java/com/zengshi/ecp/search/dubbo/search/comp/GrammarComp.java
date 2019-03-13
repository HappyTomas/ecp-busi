package com.zengshi.ecp.search.dubbo.search.comp;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.GrammarParam;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.comp.interpreter.Context;
import com.zengshi.ecp.search.dubbo.search.result.GrammarResult;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/10/8.
 */
public class GrammarComp extends BaseComp{

    private final static String MODULE = "【搜索引擎】GrammarComp";

    private final static String TEXT="Grammar异常";

    /**
     * 文法翻译
     * @param grammarParam
     * @return
     */
    public static GrammarResult grammar(GrammarParam grammarParam) {
        GrammarResult grammarResult=new GrammarResult();

        //表达式校验
        if(grammarParam.getExp()==null){
            grammarResult.setMessage("表达式对象为空");
        }

        // 集合名称是否为空
        if (!validateCollectionName(grammarParam, grammarResult)) {
            return grammarResult;
        }

        // 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
        SecConfigRespDTO currSecConfigRespDTO = getAndValidateSecConfig(grammarParam,
                grammarResult);
        if (currSecConfigRespDTO == null) {
            return grammarResult;
        }

        // 获取搜索数据对象列表
        List<SecObjectRespDTO> secObjectRespDTOList = currSecConfigRespDTO
                .getSecObjectRespDTOList();
        if (secObjectRespDTOList == null || secObjectRespDTOList.isEmpty()) {
            grammarResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECOBJECTREGISTERD, new String[]{grammarParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return grammarResult;
        }

        List<SecFieldRespDTO> secFieldRespDTOList = new ArrayList<SecFieldRespDTO>();

        // 获取搜索数据对象字段列表
        for (SecObjectRespDTO secObjectRespDTO : secObjectRespDTOList) {
            if(CollectionUtils.isNotEmpty(secObjectRespDTO.getSecFieldRespDTOList())){
                secFieldRespDTOList.addAll(secObjectRespDTO.getSecFieldRespDTOList());
            }
        }

        if (secFieldRespDTOList.isEmpty()) {
            grammarResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECFIELDREGISTERD, new String[]{grammarParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return grammarResult;
        }

        //多语言字段map
        Map<String,String> multiLangMap=new HashMap<String,String>();
        // 组装BeanFieldName-IndexName映射信息，同一个索引配置中的多个搜索数据对象字段相同配置部分自动覆盖
        Map<String, String> beanFieldName2IndexNameMap = new HashMap<String, String>();
        beanFieldName2IndexNameMap.put(SearchConstants.ID, SearchConstants.ID);
        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_PARENT, SearchConstants.ID_PARENT);
        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_CHILD, SearchConstants.ID_CHILD);

        for (SecFieldRespDTO secFieldRespDTO : secFieldRespDTOList) {
            beanFieldName2IndexNameMap.put(secFieldRespDTO.getFieldBeanFieldName(),
                    secFieldRespDTO.getFieldIndexName());
            //多语言字段
            if(StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())) {
                multiLangMap.put(secFieldRespDTO.getFieldBeanFieldName(), secFieldRespDTO.getFieldBeanFieldName());
            }
        }

        Context ctx = new Context(beanFieldName2IndexNameMap,multiLangMap);

        try {
            String grammar=grammarParam.getExp().grammar(ctx);
            grammarResult.setIfSuccess(true);
            grammarResult.setGrammar(grammar);
        } catch (SearchException e) {
            grammarResult.setMessage(e.getMessage());
        }

        return grammarResult;
    }
}
