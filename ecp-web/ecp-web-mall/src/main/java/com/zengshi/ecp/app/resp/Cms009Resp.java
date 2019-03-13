package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.cms.InfoRespVO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP信息服务-出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms009Resp extends IBody {
    
    List<InfoRespVO> infoList ;

    public List<InfoRespVO> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoRespVO> infoList) {
        this.infoList = infoList;
    }
    
}

