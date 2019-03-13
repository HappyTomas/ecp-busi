package com.zengshi.ecp.system.filter;

import javax.servlet.http.HttpServletRequest;

import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;

public class SellerLocaleUtil {
  private static ThreadLocal<SellerResDTO> threadLocal = new ThreadLocal<SellerResDTO>();
    
    public static SellerResDTO getSeller(){
        SellerResDTO sellerResDTO = threadLocal.get();
        if(sellerResDTO == null){
            return new SellerResDTO();
        } else {
            return sellerResDTO;
        }
    }
    
    public static void setSeller(HttpServletRequest request){
        SellerResDTO sellerinfo =  (SellerResDTO) request.getSession().getAttribute("sellerInfo");
        threadLocal.set(sellerinfo);
    }
}

