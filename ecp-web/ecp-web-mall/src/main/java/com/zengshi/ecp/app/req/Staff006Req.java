/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoReq.java 
 * Package Name:com.zengshi.ecp.app.req 
 * Date:2016-2-22下午6:52:57 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 修改用户信息入参<br>
 * Date:2016-2-22下午6:52:57  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6 
 */
public class Staff006Req extends IBody {
    
    private String custPic;
    
    private String nickname;
    
    private String gender;
    
    private String custBirthday;

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCustBirthday() {
        return custBirthday;
    }

    public void setCustBirthday(String custBirthday) {
        this.custBirthday = custBirthday;
    }
    
}

