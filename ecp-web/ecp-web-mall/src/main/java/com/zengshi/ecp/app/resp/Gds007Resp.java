package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Gds007Resp extends IBody {
    private List<String> hotkeywords;

    public List<String> getHotkeywords() {
        return hotkeywords;
    }

    public void setHotkeywords(List<String> hotkeywords) {
        this.hotkeywords = hotkeywords;
    }
     
}

