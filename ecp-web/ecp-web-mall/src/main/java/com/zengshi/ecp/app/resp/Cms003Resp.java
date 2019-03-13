package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.cms.AppFloorRespVO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP楼层服务-出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms003Resp extends IBody {
    
   List<AppFloorRespVO> appFloorList;

public List<AppFloorRespVO> getAppFloorList() {
    return appFloorList;
}

public void setAppFloorList(List<AppFloorRespVO> appFloorList) {
    this.appFloorList = appFloorList;
}
   
   

}

