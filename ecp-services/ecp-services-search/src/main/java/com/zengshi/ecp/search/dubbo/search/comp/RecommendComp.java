package com.zengshi.ecp.search.dubbo.search.comp;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.*;
import com.zengshi.ecp.search.dubbo.search.result.GrammarResult;
import com.zengshi.ecp.search.dubbo.search.result.RecommendResult;
import com.zengshi.ecp.search.dubbo.search.result.binder.BaseResultBinder;
import com.zengshi.ecp.search.dubbo.search.result.binder.BeanResultBinder;
import com.zengshi.ecp.search.dubbo.search.result.binder.MapResultBinder;
import com.zengshi.ecp.search.dubbo.search.result.binder.ReusltBindingException;
import com.zengshi.ecp.search.dubbo.search.support.RangeQuerySupport;
import com.zengshi.ecp.search.dubbo.search.util.*;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;

import java.util.*;

/**
 * Created by HDF on 2016/9/22.
 */
public class RecommendComp extends BaseComp{

    private final static String MODULE = "【搜索引擎】RecommendComp";

    private final static String TEXT="Recommend异常";

    /**
     * 基于物品的CB推荐
     * @param recommendParam
     * @param <T>
     * @return
     */
    public static <T> RecommendResult<T> itemBasedCBRecommend(RecommendParam recommendParam){

        LogUtil.info(MODULE, "ResultBinder实例：【"+ recommendParam.getBinderType().getBinderType()+"】");

        RecommendResult<T> recommendResult = new RecommendResult<T>();

        if(MapUtils.isEmpty(recommendParam.getItemData())&&recommendParam.getExp()==null){
            recommendResult.setMessage("检索推荐参数设置不足：物品字段属性数据、推荐文法不能同时为空");
            return recommendResult;
        }

        // 集合名称是否为空
        if (!validateCollectionName(recommendParam, recommendResult)) {
            return recommendResult;
        }

        // 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
        SecConfigRespDTO currSecConfigRespDTO = getAndValidateSecConfig(recommendParam,
                recommendResult);
        if (currSecConfigRespDTO == null) {
            return recommendResult;
        }

        // 获取搜索数据对象列表
        List<SecObjectRespDTO> secObjectRespDTOList = currSecConfigRespDTO
                .getSecObjectRespDTOList();
        if (secObjectRespDTOList == null || secObjectRespDTOList.isEmpty()) {
            recommendResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECOBJECTREGISTERD, new String[]{recommendParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return recommendResult;
        }

        List<SecFieldRespDTO> secFieldRespDTOList = new ArrayList<SecFieldRespDTO>();
        boolean insidePager=false;

        // 获取搜索数据对象字段列表
        for (SecObjectRespDTO secObjectRespDTO : secObjectRespDTOList) {
            if(org.apache.commons.collections.CollectionUtils.isNotEmpty(secObjectRespDTO.getSecFieldRespDTOList())){
                secFieldRespDTOList.addAll(secObjectRespDTO.getSecFieldRespDTOList());
                if(StringUtils.equals(SearchConstants.STATUS_1, secObjectRespDTO.getInsidepager())){
                    insidePager=true;
                }
            }
        }

        if (secFieldRespDTOList.isEmpty()) {
            recommendResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECFIELDREGISTERD, new String[]{recommendParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return recommendResult;
        }

        // 组装BeanFieldName-IndexName映射信息，同一个索引配置中的多个搜索数据对象字段相同配置部分自动覆盖
        Map<String, String> beanFieldName2IndexNameMap = new HashMap<String, String>();
        beanFieldName2IndexNameMap.put(SearchConstants.ID, SearchConstants.ID);
        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_PARENT, SearchConstants.ID_PARENT);
        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_CHILD, SearchConstants.ID_CHILD);

        //字段属性列表：MapResultBinder默认返回所有字段
        List<String> fieldList=new ArrayList<String>();

        //基于物品的检索推荐字段属性列表
        //TODO 由于在详情页可能有多个推荐模块，推荐的条件各不相同，因此暂时不在模型字段加入“是否作为基于物品CB推荐依据”配置
        //List<SecFieldRespDTO> cbSecFieldRespDTOList=new ArrayList<SecFieldRespDTO>();
        for (SecFieldRespDTO secFieldRespDTO : secFieldRespDTOList) {
            beanFieldName2IndexNameMap.put(secFieldRespDTO.getFieldBeanFieldName(),
                    secFieldRespDTO.getFieldIndexName());
            fieldList.add(secFieldRespDTO.getFieldBeanFieldName());
            //TODO 如果字段配置为：基于当前物品的CB推荐依据（详情页）。则需要作为查询条件
        }

        //数据对象绑定器初始化
        BaseResultBinder resultBinder = null;
        try {
            if(StringUtils.equals(BaseResultBinder.EBinderType.MAP.getBinderType(), recommendParam.getBinderType().getBinderType())){
                if(CollectionUtils.isEmpty(recommendParam.getFieldList())){
                    if(insidePager){
                        fieldList.add(SearchConstants.FIELD_ID_PARENT);
                        fieldList.add(SearchConstants.FIELD_ID_CHILD);
                    }else{
                        fieldList.add(SearchConstants.ID);
                    }

                    recommendParam.setFieldList(fieldList);
                }
                resultBinder=new MapResultBinder(recommendParam.getFieldList());
            }else if(StringUtils.equals(BaseResultBinder.EBinderType.BEAN.getBinderType(), recommendParam.getBinderType().getBinderType())){
                resultBinder=new BeanResultBinder<T>(recommendParam.getClazz());
            }
        } catch (ReusltBindingException e) {
            recommendResult.setMessage(SearchUtils.getExceptionMessage(e));
            return recommendResult;
        }

        // 查询
        SolrServer solrServer = null;
        QueryResponse queryResp = null;
        QueryBuilderResp queryBuilderResp;

        try {
            //获取SOLR服务器抽象对象
            solrServer = SearchUtils.getSolrServer(recommendParam.getCollectionName(),true);
            //查询推荐请求对象封装
            queryBuilderResp=query(currSecConfigRespDTO, secFieldRespDTOList,beanFieldName2IndexNameMap,
                    resultBinder.getFields(),insidePager, recommendParam);
            //发起请求
            queryResp = solrServer.query(queryBuilderResp.getSolrQuery(), SolrRequest.METHOD.POST);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, TEXT, e);

            // 已国际化信息
            recommendResult.setMessage(SearchUtils.getExceptionMessage(e));
            return recommendResult;
        } catch (SolrServerException e1) {
            LogUtil.error(MODULE, TEXT, e1);
            recommendResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY, new String[]{SearchUtils.getExceptionMessage(e1)},
                    LocaleUtil.getLocale()));
            return recommendResult;
        } catch (SearchException e) {
            LogUtil.error(MODULE, TEXT, e);
            recommendResult.setMessage(e.getMessage());
            return recommendResult;
        } catch (ReusltBindingException e) {
            LogUtil.error(MODULE, TEXT, e);
            // 追加具体异常信息(不国际化部分)
            recommendResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Error.KEY_ERROR_RESULTBEAN_STRUCTURE_FIELD,
                    new String[]{SearchUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
            return recommendResult;
        }

        //检索返回结果解析
        SolrDocumentList solrDocList = queryResp.getResults();

        if (solrDocList != null && solrDocList.size() > 0) {

            try {
                // 对象和属性拷贝
                // 推荐数据不需要高亮
                recommendResult.setResultList(resultBinder.getResults(null,solrDocList,false,
                        queryResp.getHighlighting(), beanFieldName2IndexNameMap,currSecConfigRespDTO.getConfigIfMultilan(),queryBuilderResp.getLangs(),queryBuilderResp.getMultiLangMap(),queryBuilderResp.getHlMap()));
            } catch (ReusltBindingException e) {
                LogUtil.error(MODULE, TEXT, e);

                // 追加具体异常信息(不国际化部分)
                recommendResult.setMessage(ResourceMsgUtil.getMessage(
                        SearchMessageKeyContants.Error.KEY_ERROR_RESULT_BINDING,
                        new String[]{SearchUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
            } catch (Exception e) {
                LogUtil.error(MODULE, TEXT, e);

                // 追加具体异常信息(不国际化部分)
                recommendResult.setMessage(ResourceMsgUtil.getMessage(
                        SearchMessageKeyContants.Error.KEY_ERROR_RESULT_BINDING,
                        new String[]{SearchUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
            }
        }

        // 额外响应信息设置
        recommendResult = addBaseRespInfo(recommendResult, queryResp);

        // 分页信息设置
        recommendResult.setNumFound(solrDocList.getNumFound());
        recommendResult.setPageSize(recommendParam.getPageSize());
        recommendResult.setPageNo(recommendParam.getPageNo());
        recommendResult
                .setTotallyPage((recommendResult.getNumFound() % recommendResult.getPageSize() == 0) ? (recommendResult
                        .getNumFound() / recommendResult.getPageSize()) : (recommendResult.getNumFound()
                        / recommendResult.getPageSize() + 1));

        // 返回结果
        return recommendResult;

    }

    private static QueryBuilderResp query(SecConfigRespDTO currSecConfigRespDTO,List<SecFieldRespDTO> secFieldRespDTOList,Map<String, String> beanFieldName2IndexNameMap, List<String> beanFieldNames,boolean insidePager,
                                          RecommendParam recommendParam) throws SearchException {

        //查询参数校验
        if(recommendParam.getPageNo()<=0){
            throw new SearchException(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_PAGENO_INVALID,new String[]{""+recommendParam.getPageNo()}));
        }

        if(recommendParam.getPageSize()<=0){
            throw new SearchException(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_PAGESIZE_INVALID, new String[]{""+recommendParam.getPageSize()}));
        }

        QueryBuilderResp queryBuilderResp=new QueryBuilderResp();

        SolrQuery solrQuery = new SolrQuery();

        solrQuery.set(CommonParams.QT, ERequestType.QUERY.getHandler());

        //只从存活的shards获取数据,没有此参数，如果集群内有挂掉的shard，将显示：
        //no servers hosting shard
        //需要注意值是Boolean而不是字符串
        solrQuery.set("shards.tolerant",true);

        solrQuery.setStart((int) ((recommendParam.getPageNo() - 1) * recommendParam.getPageSize()))
                .setRows(recommendParam.getPageSize());

        solrQuery.set("q.op", currSecConfigRespDTO.getConfigQueryOp());

        //语言环境
        List<ELang> langFieldTypes=null;

        //TODO 若索引配置是多语言配置，则需要根据检索关键字自动分析语言环境，并设置语言参数（注意了，不应该由前店传入，应该是个自动分析过程）
        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)){
            //TODO 对于详情页推荐，语种应该是根据item语种进行判断（判断字段依据中有多语言字段）--相当于界面上强制指定语种且一般详情页推荐字段不会是多语言字段
            //TODO grammer推荐，语种如何指定？？？
        }

        //多语言支持。
        //1、记录多语言：若索引配置配置了多语言，如果搜索不传入多语言参数则直接抛出异常（对于记录多语言无法通过索引配置获取和设置默认语言参数），否则将会以默认字段类型去检索，对于问题排查比较困难。
        //2、字段多语言：若索引配置配置了多语言，如果搜索不传入多语言参数则也直接抛出异常（虽然对于字段多语言可以设置默认语言参数，默认语言参数取第一个），否则将会以默认字段类型去检索，对于问题排查比较困难。
        //3、若索引配置没配置多语言，但是搜索传入了多语言参数则也直接抛出异常（虽然可以直接取消多语言参数）
        /*if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&StringUtils.isNotBlank(currSecConfigRespDTO.getLanField())&&
                (recommendParam.getLang()==null)){//记录多语言

            throw new SearchException("索引配置是记录多语言，但是检索参数未设置检索语言");

        }
        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)&&StringUtils.isNotBlank(currSecConfigRespDTO.getLans())&&
                (recommendParam.getLang()==null)){//字段多语言

            throw new SearchException("索引配置是字段多语言，但是检索参数未设置检索语言");

        }
        if(!StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&!StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)
                &&(recommendParam.getLang()!=null)){
            throw new SearchException("索引配置未配置多语言，但是检索参数设置了检索语言");
        }*/

        //记录多语言的检索和字段多语言的检索过程其实是一样的，都是要根据检索语言替换到字段名中。
        //区别在于记录多语言还需要额外设置语言字段(lang)值。
        //非*情况下才需要做语言字段限制
        //TODO 该filter是可选的
        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)){
            //户端强制指定检索语言（如界面提供检索语言参数选择）
            if(CollectionUtils.isNotEmpty(recommendParam.getLangs())){
                StringBuffer sb=new StringBuffer();
                for(ELang lang:recommendParam.getLangs()){
                    if(StringUtils.isNotBlank(sb.toString())) sb.append(" "+EOperator.OR.getName()+" ");
                    sb.append(SearchConstants.FIELD_LANG +SearchConstants.COLON+lang.getLang());
                }
                solrQuery.addFilterQuery(sb.toString());
            }
        }

        String indexName="";

        /*=========================以下数据都是经过多语言处理过后的start============================*/

        // 排序字段//TODO 注意了，排序字段（一般是数值、日期）一般不需要设置为多语言字段（排序字段多语言会有问题），因此无需考虑多语言环境下的排序处理
        Map<String, String> sortMap = new HashMap<String, String>();
        TreeMap<Short,String> sortIndexMap=new TreeMap<Short,String>();

        // 返回结果字段//TODO 需要考虑多语言下返回字段设置问题
        Map<String, String> flMap = new HashMap<String, String>();

        //多语言字段map
        Map<String,String> multiLangMap=new HashMap<String,String>();

        //索引字段名2多语言索引字段名列表
        Map<String,List<String>> indexName2MulLanIndexNameMapList=new HashMap<String,List<String>>();

        /*=========================以下数据都是经过多语言处理过后的end============================*/

        for (SecFieldRespDTO secFieldRespDTO : secFieldRespDTOList) {

            //非多语言
            //若字段配置为多语言，但是数据对象没有配置为多语言，则也当成普通字段处理。
            indexName=secFieldRespDTO.getFieldIndexName();

            //记录多语言
            if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())){
                multiLangMap.put(secFieldRespDTO.getFieldBeanFieldName(), secFieldRespDTO.getFieldBeanFieldName());

                //TODO 记录多语言，无论是检索全部（*）或者是有指定具体检索关键字的时候都需要根据记录的lang字段，设置返回对应的多语言字段，这是在检索的时候所无法指定的
                //TODO 因此目前只能是设置服务端返回所有字段才能支持记录多语言数据返回了（记录多语言不设置返回字段列表，默认返回全部）。

                //使用检索语言设置索引字段名（高亮需求）
                if(CollectionUtils.isNotEmpty(langFieldTypes)){
                    List<String> mulLanIndexNameList=new ArrayList<>();
                    for(ELang lang:langFieldTypes){
                        mulLanIndexNameList.add(secFieldRespDTO.getFieldIndexName().replace(
                                SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                        + SearchConstants.UNDERLINE,
                                SearchConstants.UNDERLINE + lang.getLang()
                                        + SearchConstants.UNDERLINE));
                    }
                    indexName2MulLanIndexNameMapList.put(indexName,mulLanIndexNameList);
                }
            }else if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)&&StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())) {//字段多语言
                multiLangMap.put(secFieldRespDTO.getFieldBeanFieldName(), secFieldRespDTO.getFieldBeanFieldName());

                //TODO 字段多语言暂时只支持返回某种语言下的数据（根据检索语言），后期如果有返回多个语言数据需求的，需要变更返回字段规则（不设置返回字段列表，默认返回全部）
                if(CollectionUtils.isNotEmpty(langFieldTypes)){
                    List<String> mulLanIndexNameList=new ArrayList<>();
                    for(ELang lang:langFieldTypes){
                        mulLanIndexNameList.add(secFieldRespDTO.getFieldIndexName().replace(
                                SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                        + SearchConstants.UNDERLINE,
                                SearchConstants.UNDERLINE + lang.getLang()
                                        + SearchConstants.UNDERLINE));
                    }
                    indexName2MulLanIndexNameMapList.put(indexName,mulLanIndexNameList);
                }
            }

            //默认排序字段后期加入配置模型
            if (StringUtils.isNotBlank(secFieldRespDTO.getFieldSort())&&(secFieldRespDTO.getFieldSortNum()!=null)&&StringUtils.isNotBlank(String.valueOf(secFieldRespDTO.getFieldSortNum()))) {

                //排序字段（一般是数值、日期）一般不需要设置为多语言字段（排序字段多语言会有问题），因此无需考虑多语言环境下的排序处理
                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                    throw new SearchException("排序字段（一般是数值、日期）不允许设置为多语言字段");
                }

                if (StringUtils.equals(secFieldRespDTO.getFieldSort(), SearchConstants.STATUS_0)) {
                    sortMap.put(indexName, ESort.DESC.getSort());
                } else if (StringUtils.equals(secFieldRespDTO.getFieldSort(),
                        SearchConstants.STATUS_1)) {
                    sortMap.put(indexName, ESort.ASC.getSort());
                }
                sortIndexMap.put(secFieldRespDTO.getFieldSortNum(),indexName);
            }

            // 按格式化Bean需要返回结果，不返回全部，节省服务器压力和带宽
            if (beanFieldNames.contains(secFieldRespDTO.getFieldBeanFieldName())) {
                flMap.put(indexName,indexName);
            }
        }

        // 搜索串组装
        boolean isFirst = true;

        //TODO 由于在详情页可能有多个推荐模块，推荐的条件各不相同，因此暂时不在模型字段加入“是否作为基于物品CB推荐依据”配置
        /*for (SecFieldRespDTO secFieldRespDTO : cbSecFieldRespDTOList) {

            //多语言字段
            if(StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())){
                //多语言查询
                if(StringUtils.isNotBlank(recommendParam.getLang())){
                    indexName=secFieldRespDTO.getFieldIndexName().replace(
                            SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                    + SearchConstants.UNDERLINE,
                            SearchConstants.UNDERLINE + recommendParam.getLang()
                                    + SearchConstants.UNDERLINE);
                }

            }else{

                //若字段配置为多语言，但是数据对象没有配置为多语言，则当成普通字段处理。
                indexName=secFieldRespDTO.getFieldIndexName();
            }

            //多值字段查询条件处理
            if(StringUtils.equals(secFieldRespDTO.getFieldIfMutlivalue(),SearchConstants.STATUS_VALID)){
                List list= (List) recommendParam.getItemData().get(secFieldRespDTO.getFieldBeanFieldName());
                if(CollectionUtils.isNotEmpty(list)){
                    for(Object o:list){
                        if (isFirst) {
                            sb_q.append(indexName+ SearchConstants.COLON + o);
                            isFirst = false;
                        } else {

                            //TODO 取推荐系统参数配置
                            sb_q.append(" " + EOperator.OR.getName() + " " + indexName+ SearchConstants.COLON
                                    + o);
                        }
                    }
                }
            }else{
                if (isFirst) {
                    sb_q.append(indexName+ SearchConstants.COLON + recommendParam.getItemData().get(secFieldRespDTO.getFieldBeanFieldName()));
                    isFirst = false;
                } else {

                    //TODO 取推荐系统参数配置
                    sb_q.append(" " + EOperator.OR.getName() + " " + indexName+ SearchConstants.COLON
                            + recommendParam.getItemData().get(secFieldRespDTO.getFieldBeanFieldName()));
                }
            }
        }*/

        //查询转换模式默认为AND
        String recCbPropertiesOp=EOperator.AND.getName();
        HashMap<String,String> cacheSecArgsMap=SearchCacheUtils.getSecArgsMap();
        if(cacheSecArgsMap.containsKey("REC_CB_PROPERTIES_OP")){
            recCbPropertiesOp=cacheSecArgsMap.get("REC_CB_PROPERTIES_OP");
        }

        //检索推荐表达式，优先级最高
        if(recommendParam.getExp()!=null){
            GrammarParam grammarParam=new GrammarParam();
            grammarParam.setExp(recommendParam.getExp());
            grammarParam.setCollectionName(recommendParam.getCollectionName());
            GrammarResult grammarResult=GrammarComp.grammar(grammarParam);
            if(!grammarResult.isIfSuccess()){
                //文法生成失败
                throw new SearchException("检索推荐自定义文法生成失败，错误信息："+grammarResult.getMessage());
            }
            //表达式翻译为空，就默认检索推荐全部
            if(StringUtils.isBlank(grammarResult.getGrammar())){
                grammarResult.setGrammar(SearchConstants.STAR);
            }
            solrQuery.setQuery(grammarResult.getGrammar());
        }else{
            StringBuffer sb_q = new StringBuffer(SearchConstants.BRACKETS_LEFT);
            for(String s:recommendParam.getItemData().keySet()){
                checkSrcField(s, beanFieldName2IndexNameMap);

                indexName=getIndexField(s, beanFieldName2IndexNameMap);

                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                    throw new SearchException("CB推荐字段不支持多语言字段");
                }

                Object value= recommendParam.getItemData().get(s);
                if(value instanceof List){
                    List list=(List)value;
                    if(CollectionUtils.isNotEmpty(list)){

                        //多值字段，属性值应该是OR关系
                        StringBuffer sb_or=new StringBuffer();
                        for(Object o:list){
                            if(StringUtils.isBlank(sb_or.toString())){
                                sb_or.append(o);
                            }else{
                                sb_or.append(" "+EOperator.OR.getName()+" "+o);
                            }
                        }

                        if (isFirst) {
                            sb_q.append(indexName+ SearchConstants.COLON + SearchConstants.BRACKETS_LEFT+sb_or.toString()+SearchConstants.BRACKETS_RIGHT);
                            isFirst = false;
                        } else {
                            sb_q.append(" " + recCbPropertiesOp + " " + indexName+ SearchConstants.COLON
                                    + SearchConstants.BRACKETS_LEFT+sb_or.toString()+SearchConstants.BRACKETS_RIGHT);
                        }
                    }
                }else{
                    if (isFirst) {
                        sb_q.append(indexName+ SearchConstants.COLON + value);
                        isFirst = false;
                    } else {
                        sb_q.append(" " + recCbPropertiesOp + " " + indexName+ SearchConstants.COLON
                                + value);
                    }
                }
            }
            sb_q.append(SearchConstants.BRACKETS_RIGHT);
            solrQuery.setQuery(sb_q.toString());
        }

        //基于标签推荐的匹配度门槛
        if(StringUtils.isNotBlank(recommendParam.getMm())){
            solrQuery.set("mm",recommendParam.getMm());
            //defType设置必不可少
            solrQuery.set("defType",EQueryParser.DEFTYPE_EDISMAX.getName());
        }

        // 支持手动指定默认检索字段
        /*// TODO 没法直接设置默认搜索字段，经验证默认搜索字段只能设置一个
        if(StringUtils.isNotBlank(recommendParam.getDf())){
            //TODO 目前aiecp_df字段还是不能使用，原因是原先设想未验证成功：
            //那如果copyFiled的不同字段需要使用不同的分词策略怎么解决呢？如多语言的情况下？copyFiled是把源字段数据分词后copy过去目标字段，还是copy到目标字段后使用目标字段的分词器分词呢？
            //答：无法通过copyFiled实现。因为copyFiled是源内容原原本本拷贝到dest，dest字段再使用自己的分词器统一分词。
            //TODO 因此目前暂时还是需要由客户端传入一个默认搜索字段（如商品标题、店铺名）
            solrQuery.set(CommonParams.DF,recommendParam.getDf());
        }else{
            //多语言检索，根据语言自动执行默认检索字段
            if((StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)
            )&&(recommendParam.getLang()!=null)){
                solrQuery.set(CommonParams.DF,SearchConstants.FIELD_DF+SearchConstants.UNDERLINE+recommendParam.getLang().getLang());
            }
        }*/

        // 范围查询
        solrQuery = RangeQuerySupport.addRangeQuerySupport(solrQuery, recommendParam,
                beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);

        // 推荐默认字段排序
        StringBuffer sb_sort=new StringBuffer("");
        if (CollectionUtils.isNotEmpty(recommendParam.getSortFieldList())) {

            int i=0;
            for (SortField sortField : recommendParam.getSortFieldList()) {

                checkSrcField(sortField.getName(), beanFieldName2IndexNameMap);

                indexName=getIndexField(sortField.getName(), beanFieldName2IndexNameMap);

                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                    throw new SearchException("排序字段（一般是数值、日期）不允许设置为多语言字段");
                }

                if (sb_sort.length() > 0)
                    sb_sort.append(SearchConstants.COMMA);

                if(recommendParam.isIfSortByScore()&&recommendParam.getScorePosition()==i){
                    sb_sort.append(SearchConstants.FIELD_SCORE+" "+ESort.DESC.getSort()+SearchConstants.COMMA);
                }

                sb_sort.append(indexName);
                sb_sort.append(" ");
                sb_sort.append(sortField.getValue().getSort());

                i++;
            }
        }else{
            if (MapUtils.isNotEmpty(sortIndexMap)) {

                for (Short s: sortIndexMap.keySet()) {
                    if (sb_sort.length() > 0)
                        sb_sort.append(SearchConstants.COMMA);

                    sb_sort.append(sortIndexMap.get(s));
                    sb_sort.append(" ");
                    sb_sort.append(sortMap.get(sortIndexMap.get(s)));
                }
            }else{
                sb_sort.append(SearchConstants.FIELD_SCORE+" desc");
            }
        }
        solrQuery.set(CommonParams.SORT, sb_sort.toString());

        //记录多语言需要返回所有字段
        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)){
            //默认返回所有字段
        }else{
            // 返回结果字段
            // id字段必须返回，否则无法做高亮字段处理
            flMap.put(SearchConstants.ID, SearchConstants.ID);
            if(insidePager){
                flMap.put(SearchConstants.ID_PARENT, SearchConstants.ID_PARENT);
                flMap.put(SearchConstants.ID_CHILD, SearchConstants.ID_CHILD);
            }
            //结果需要返回语言字段，ResultBinder中记录多语言需要根据语言字段返回多语言结果
            flMap.put(SearchConstants.FIELD_LANG_FIELD_TYPE,SearchConstants.FIELD_LANG_FIELD_TYPE);
            solrQuery.setFields(flMap.values().toArray(new String[] {}));
        }

        LogUtil.info(MODULE,"===================基本请求参数===================");
        LogUtil.info(MODULE, "查询推荐请求参数：【"+recommendParam.toString()+"】");
        LogUtil.info(MODULE, "查询推荐转换模式：【"+currSecConfigRespDTO.getConfigQueryOp()+"】");
        LogUtil.info(MODULE, "基于物品的检索推荐转换模式REC_CB_PROPERTIES_OP：【"+recCbPropertiesOp+"】");
        LogUtil.info(MODULE, "语言参数：【");
        if(CollectionUtils.isNotEmpty(langFieldTypes)){
            for(ELang lang:langFieldTypes){
                LogUtil.info(MODULE,lang.getLang());
            }
        }
        LogUtil.info(MODULE, "】");

        LogUtil.info(MODULE,"===================基本检索参数===================");
        LogUtil.info(MODULE, "最终查询推荐串：【"+solrQuery.getQuery()+"】");
        LogUtil.info(MODULE, "最终推荐排序串：【"+solrQuery.get(CommonParams.SORT)+"】");
        LogUtil.info(MODULE, "默认检索字段（df）：【"+solrQuery.get(CommonParams.DF)+"】");
        LogUtil.info(MODULE, "最终Filter查询推荐串：【");
        if(solrQuery.getFilterQueries()!=null&&solrQuery.getFilterQueries().length>0){
            for(String s:solrQuery.getFilterQueries()){
                LogUtil.info(MODULE, s);
            }
        }

        LogUtil.info(MODULE, "】");

        LogUtil.info(MODULE,"===================defType===================");
        LogUtil.info(MODULE, "defType："+solrQuery.get("defType"));
        LogUtil.info(MODULE, "最小匹配数mm："+solrQuery.get("mm"));

        queryBuilderResp.setSolrQuery(solrQuery);
        queryBuilderResp.setFlMap(flMap);
        queryBuilderResp.setMultiLangMap(multiLangMap);
        queryBuilderResp.setLangs(langFieldTypes);
        queryBuilderResp.setIndexName2MulLanIndexNameMapList(indexName2MulLanIndexNameMapList);

        return queryBuilderResp;

    }

}
