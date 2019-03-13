package com.zengshi.ecp.coupon.dubbo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom <br>
 * Description: <br>
 * Date:2015年10月8日上午9:36:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */
@Service("coupCacheUtil")
public class CoupCacheUtil {

    private static final String MODULE = CoupCacheUtil.class.getName();
    
    private static ICoupTypeRSV coupTypeRSV;
    
    public ICoupTypeRSV getCoupTypeRSV() {
        return coupTypeRSV;
    }
    
    @Resource(name="coupTypeRSV")
    public void setCoupTypeRSV(ICoupTypeRSV coupTypeRSV) {
        CoupCacheUtil.coupTypeRSV = coupTypeRSV;
    }
    
    
    
    /**
     * 
     * getPromType:(获取优惠券大类缓存). <br/>
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    public static CoupTypeRespDTO getCoupTypeCache(Long id) {
        CoupTypeRespDTO  resp = new CoupTypeRespDTO();
        LogUtil.info(MODULE, "========开始获取优惠券类型缓存：" + resp);
        try {
            Map map = (Map) CacheUtil
                    .getItem(CouponConstants.CoupKey.COUP_TYPE);
            if(!CollectionUtils.isEmpty(map)){
                resp=(CoupTypeRespDTO)map.get(id);
            }
            LogUtil.info(MODULE, "========结束获取优惠券类型缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取优惠券类型缓存报错：" + resp);
        }

        return resp;
    }
    /**
     * 优惠券类型 新增到cache
     * @return
     * @author huangjx
     */
    public  static void addCoupType(Long id) {
        
        //缓存查询
        Map<Long ,CoupTypeRespDTO> map = (HashMap<Long ,CoupTypeRespDTO> )CacheUtil.getItem(CouponConstants.CoupKey.COUP_TYPE);
        
        CoupTypeReqDTO coupTypeReqDTO=new CoupTypeReqDTO();
        coupTypeReqDTO.setId(id);
        CoupTypeRespDTO coupTypeRespDTO= coupTypeRSV.queryCoupType(coupTypeReqDTO);
        //无此数据 
        if(CollectionUtils.isEmpty(map)){
            if(coupTypeRespDTO!=null){
                //加入缓存
                searchCoupTypeCache();
            }
        }else{
            CoupTypeRespDTO dto= map.get(coupTypeRespDTO.getId());
            if(dto==null){
                //不存在数据 直接添加
                if(coupTypeRespDTO!=null){
                    map.put(coupTypeRespDTO.getId(), coupTypeRespDTO);
                    CacheUtil.addItem(CouponConstants.CoupKey.COUP_TYPE, map);
                }
            }else{
                //存在key 删除并添加新数据
                if(coupTypeRespDTO!=null){
                    map.remove(coupTypeRespDTO.getId());
                    if(CouponConstants.CoupType.STATUS_1.equals(coupTypeRespDTO.getStatus())){
                        map.put(coupTypeRespDTO.getId(), coupTypeRespDTO);
                    }
                    CacheUtil.addItem(CouponConstants.CoupKey.COUP_TYPE, map);
                }
            }
        }
    }
    /**
     * 查询优惠券类型列表
     * @return
     * @author huangjx
     */
    public  static Map<Long ,CoupTypeRespDTO>  searchCoupTypeCache() {
        //缓存查询
        Map<Long ,CoupTypeRespDTO> map = (HashMap<Long ,CoupTypeRespDTO> )CacheUtil.getItem(CouponConstants.CoupKey.COUP_TYPE);
        //无此数据 查表
        if(CollectionUtils.isEmpty(map)){
            //查表
            CoupTypeReqDTO coupTypeReqDTO =new CoupTypeReqDTO();
            coupTypeReqDTO.setStatus(CouponConstants.CoupType.STATUS_1);
            
            List<CoupTypeRespDTO> lst= coupTypeRSV.queryCoupTypeList(coupTypeReqDTO);
            //加入缓存
            map=new HashMap<Long ,CoupTypeRespDTO>();
            if(!CollectionUtils.isEmpty(lst)){
                 for(CoupTypeRespDTO dto:lst){
                     map.put(dto.getId(), dto);
                 }
                 CacheUtil.addItem(CouponConstants.CoupKey.COUP_TYPE, map);
            }
        }
        return map;
    }
    /**
     * 清空优惠券类型
     * @return
     * @author huangjx
     */
    public static long clearCoupTypeCache() {
        Map<Long,Object> map = (HashMap<Long,Object>) CacheUtil.getItem(CouponConstants.CoupKey.COUP_TYPE );
        if (CollectionUtils.isEmpty(map)) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + map.size());
            CacheUtil.delItem(CouponConstants.CoupKey.COUP_TYPE);
            LogUtil.info(MODULE, "========清空结束");
            return map.size();
        }
    }
    
}
