package com.example.cosu_pra.DTO;
import java.io.Serializable;

//DTO
public class ChatData{

    private String msg;
    private String realName;

    //getter
    public String getMsg() {
        return msg;
    }

    public String getRealName() {
        return realName;
    }

    //setter
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}

