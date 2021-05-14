package com.example.cosu_pra.DTO;

public class User{

private String id;
private String pwd;
private String realName;
private String nickName;

public User(String id,String pwd,String realName, String nickName){
    this.id = id;
    this.pwd = pwd;
    this.realName = realName;
    this.nickName = nickName;
}
    // getter
    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRealName() {
        return realName;
    }

    public String getNickName() {
        return nickName;
    }

    //setter
    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}