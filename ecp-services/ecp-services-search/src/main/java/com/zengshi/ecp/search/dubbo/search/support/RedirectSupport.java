package com.zengshi.ecp.search.dubbo.search.support;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.search.dubbo.dto.SecRedirectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月19日下午5:57:53 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class RedirectSupport {

    public static RedirectResult redirect(String keyword) {

        RedirectResult redirectResult = new RedirectSupport.RedirectResult();
        redirectResult.setRedirectType(SearchConstants.Redirect.REDIRECT_TYPE_NONEMATCHES);
        redirectResult.setResult(keyword);
        
        if(StringUtils.isBlank(keyword)){
            redirectResult.setResult(SearchConstants.STAR);
            return redirectResult;
        }

        List<SecRedirectRespDTO> secRedirectRespDTOList = SearchCacheUtils.getSecRedirectList();

        if (secRedirectRespDTOList == null || secRedirectRespDTOList.isEmpty()) {
            return redirectResult;
        }

        // 随机匹配
        for (SecRedirectRespDTO secRedirectRespDTO : secRedirectRespDTOList) {

            boolean flag1 = StringUtils.equals(secRedirectRespDTO.getMatchType(),
                    SearchConstants.Redirect.MATCH_TYPE_ALL)
                    && StringUtils.equals(secRedirectRespDTO.getMatchCont(), keyword);

            boolean flag2 = StringUtils.equals(secRedirectRespDTO.getMatchType(),
                    SearchConstants.Redirect.MATCH_TYPE_CONTAIN)
                    && keyword.contains(secRedirectRespDTO.getMatchCont());

            if (flag1 || flag2) {

                redirectResult.setRedirectType(secRedirectRespDTO.getRedirectType());
                if (StringUtils.equals(secRedirectRespDTO.getRedirectType(),
                        SearchConstants.Redirect.REDIRECT_TYPE_NEWQUERY)) {
                    redirectResult.setResult(secRedirectRespDTO.getRedirectNewquery());
                } else if (StringUtils.equals(secRedirectRespDTO.getRedirectType(),
                        SearchConstants.Redirect.REDIRECT_TYPE_URL)) {
                    redirectResult.setResult(secRedirectRespDTO.getRedirectUrl());
                }

                return redirectResult;
            }
        }

        return redirectResult;

    }

    public static class RedirectResult {

        private String redirectType;

        private String result;

        public String getRedirectType() {
            return redirectType;
        }

        public void setRedirectType(String redirectType) {
            this.redirectType = redirectType;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

    }
}
