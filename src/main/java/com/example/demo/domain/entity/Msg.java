package com.example.demo.domain.entity;

import java.util.Date;

public class Msg {
    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public long getStuid() {
        return stuid;
    }

    public void setStuid(long stuid) {
        this.stuid = stuid;
    }

    public long getHouseid() {
        return houseid;
    }

    public void setHouseid(long houseid) {
        this.houseid = houseid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    private long msgid;
    private long stuid;
    private long houseid;
    private long userid;
    private String address;
    private Date posttime;
}
