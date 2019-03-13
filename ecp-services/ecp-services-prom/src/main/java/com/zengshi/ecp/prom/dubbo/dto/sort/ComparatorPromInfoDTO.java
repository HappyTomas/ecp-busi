package com.zengshi.ecp.prom.dubbo.dto.sort;

import java.util.Comparator;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class ComparatorPromInfoDTO implements Comparator {

    public int compare(Object arg0, Object arg1) {/*
        PromInfoDTO promVo0 = (PromInfoDTO) arg0;
        PromInfoDTO promVo1 = (PromInfoDTO) arg1;

        // 首先比较优先级 如果优先级一致 比较创建时间

        int flag = promVo1.getPriority().compareTo(promVo0.getPriority());
        if (flag == 0) {
            return promVo1.getCreateTime().compareTo(promVo0.getCreateTime());
        } else {
            return flag;
        }
    */

        PromInfoDTO promVo0 = (PromInfoDTO) arg0;
        PromInfoDTO promVo1 = (PromInfoDTO) arg1;

        // 首先比较优先级 如果优先级一致 比较促销开始时间
        //优先级 小的  排到前面
        int flag = promVo0.getPriority().compareTo(promVo1.getPriority());
        if (flag == 0) {
            //创建时间最新的排到前面
            return promVo1.getCreateTime().compareTo(promVo0.getCreateTime());
      
        } else {
            return flag;
        }
     
    }

}