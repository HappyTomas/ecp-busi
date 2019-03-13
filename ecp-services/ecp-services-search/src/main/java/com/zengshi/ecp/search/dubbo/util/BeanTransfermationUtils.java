package com.zengshi.ecp.search.dubbo.util;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午2:21:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class BeanTransfermationUtils {
    
    public static void dto2bo(Object dest, Object orig) throws BusinessException{
        
        try {
//            PropertyUtils.copyProperties(dest,orig);
            BeanUtils.copyProperties(orig, dest);
        } catch(Exception e){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_DTO2BO,new String[]{SearchUtils.getExceptionMessage(e)});
        }
        
    }
    
    public static void bo2dto(Object dest, Object orig) throws BusinessException{
        
        try {
//            PropertyUtils.copyProperties(dest,orig);
            BeanUtils.copyProperties(orig, dest);
        } catch(Exception e){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_BO2DTO,new String[]{SearchUtils.getExceptionMessage(e)});
        }
        
    }

}

