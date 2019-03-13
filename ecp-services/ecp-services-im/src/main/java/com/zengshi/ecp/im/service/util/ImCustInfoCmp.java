package com.zengshi.ecp.im.service.util;

import com.zengshi.ecp.im.dao.model.ImCustInfo;
import com.zengshi.ecp.im.dubbo.util.ImConstants;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by zhangys on 16/11/7.
 */
public class ImCustInfoCmp implements Comparator<ImCustInfo>,Serializable{
    @Override
    public int compare(ImCustInfo o1, ImCustInfo o2) {
//        int diffSec = DateTimeUtil
//            .getSecondsBySub(o1.getReqTime(), o2.getReqTime());
//        if (diffSec >= ImConstants.QUEUE_INVALID_SECONDS) {
//            return -1;
//        }

        int custTypeComp =
            o2.getCustLevel().compareTo(o1.getCustLevel());
        if (custTypeComp != 0) {
            return custTypeComp;
        }
        return o1.getReqTime().compareTo(o2.getReqTime());
    }
}
