package com.zengshi.ecp.prom.dubbo.dto.sort;

import java.util.Comparator;

import com.zengshi.ecp.prom.dubbo.dto.PromDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class ComparatorPromDTO implements Comparator {

    public int compare(Object arg0, Object arg1) {
        PromDTO promVo0 = (PromDTO) arg0;
        PromDTO promVo1 = (PromDTO) arg1;

        // 首先比较优先级 如果优先级一致 比较创建时间

        int flag = promVo0.getPriority().compareTo(promVo1.getPriority());
        if (flag == 0) {
            return promVo0.getCreateTime().compareTo(promVo1.getCreateTime());
        } else {
            return flag;
        }
    }

}