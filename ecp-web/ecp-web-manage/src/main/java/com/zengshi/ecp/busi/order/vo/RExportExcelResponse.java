package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Created by wang on 15/12/1.
 */
public class RExportExcelResponse extends EcpBaseResponseVO {

    private static final long serialVersionUID = -8079966370431391488L;
    private String fileId;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
