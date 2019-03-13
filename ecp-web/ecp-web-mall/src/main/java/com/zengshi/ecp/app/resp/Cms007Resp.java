package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.cms.CategoryRespVO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP全部分类服务-出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms007Resp extends IBody {
    
    List<CategoryRespVO> catgList;

    public List<CategoryRespVO> getCatgList() {
        return catgList;
    }

    public void setCatgList(List<CategoryRespVO> catgList) {
        this.catgList = catgList;
    }

}

