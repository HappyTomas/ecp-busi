package com.zengshi.ecp.im.service.util;

import com.zengshi.ecp.im.dao.model.ImStaffHotline;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by zhangys on 16/11/7.
 */
public class ImStaffHotlineCmp implements Comparator<ImStaffHotline>,Serializable {

    @Override public int compare(ImStaffHotline o1,
                                 ImStaffHotline o2) {
        int cmp = o1.getLineStatus().compareTo(o2.getLineStatus());
        if (cmp != 0) {
            return cmp;
        }
        cmp = o1.getServiceCount().compareTo(o2.getServiceCount());
        return cmp;
    }
}
