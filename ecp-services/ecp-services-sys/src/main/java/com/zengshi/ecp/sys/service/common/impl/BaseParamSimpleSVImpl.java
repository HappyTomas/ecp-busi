/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamSimpleSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-8-19����8:41:44 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.sys.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.common.BaseParamSimpleMapper;
import com.zengshi.ecp.sys.dao.model.BaseParamSimple;
import com.zengshi.ecp.sys.dao.model.BaseParamSimpleCriteria;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamSimpleSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-19 18:41:44 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
@Service("baseParamSimpleSV")
public class BaseParamSimpleSVImpl extends GeneralSQLSVImpl implements IBaseParamSimpleSV {

    @Resource
    private BaseParamSimpleMapper baseParamSimpleMapper;

    /**
     * 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamSimpleSV#fetchParamListByKey(java.lang.String)
     */
    @Override
    public List<BaseParamSimple> fetchParamListByKey(String paramKey) throws BusinessException {
        BaseParamSimpleCriteria criteria = new BaseParamSimpleCriteria();
        criteria.createCriteria().andParamLinkKeyEqualTo(paramKey);
        criteria.setOrderByClause(" sp_order asc");
        return this.baseParamSimpleMapper.selectByExample(criteria);
    }
    
    @Override
    public List<BaseParamSimple> fetchParamListByKeyLang(String paramKey, String spLang) throws BusinessException {
        BaseParamSimpleCriteria criteria = new BaseParamSimpleCriteria();
        //设置列表和语言的查询条件
        criteria.createCriteria().andParamLinkKeyEqualTo(paramKey).andSpLangEqualTo(spLang);
        criteria.setOrderByClause(" sp_order asc");
        return this.baseParamSimpleMapper.selectByExample(criteria);
    }

    /**
     * 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamSimpleSV#fetchParamListByKeyCode(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public List<BaseParamSimple> fetchParamListByKeyCode(String paramKey, String code)
            throws BusinessException {
        BaseParamSimpleCriteria criteria = new BaseParamSimpleCriteria();
        criteria.createCriteria().andParamLinkKeyEqualTo(paramKey).andSpCodeEqualTo(code);
        criteria.setOrderByClause(" sp_order asc");
        return this.baseParamSimpleMapper.selectByExample(criteria);
    }

    /**
     * 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamSimpleSV#fetchParamListByKeyCode(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public BaseParamSimple fetchParamListByKeyCode(String paramKey, String code, String lang)
            throws BusinessException {
        BaseParamSimpleCriteria criteria = new BaseParamSimpleCriteria();
        criteria.createCriteria().andParamLinkKeyEqualTo(paramKey).andSpCodeEqualTo(code).andSpLangEqualTo(lang);

        List<BaseParamSimple> lst = this.baseParamSimpleMapper.selectByExample(criteria);
        if(lst == null || lst.size() == 0){
            return null;
        } else {
            return lst.get(0);
        }
    }

}
