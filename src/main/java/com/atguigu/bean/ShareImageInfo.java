package com.atguigu.bean;

public class ShareImageInfo {
    private Integer tbShareImageinfoId;

    private Integer tbShareImageinfoType;

    private String tbShareImageinfoName;

    private String tbShareImageinfoUrl;

    private String tbShareImageinfoParentid;

    private String tbShareImageinfoDesc;

    private String tbShareImageinfoCreatetime;

    public Integer getTbShareImageinfoId() {
        return tbShareImageinfoId;
    }

    public void setTbShareImageinfoId(Integer tbShareImageinfoId) {
        this.tbShareImageinfoId = tbShareImageinfoId;
    }

    public Integer getTbShareImageinfoType() {
        return tbShareImageinfoType;
    }

    public void setTbShareImageinfoType(Integer tbShareImageinfoType) {
        this.tbShareImageinfoType = tbShareImageinfoType;
    }

    public String getTbShareImageinfoName() {
        return tbShareImageinfoName;
    }

    public void setTbShareImageinfoName(String tbShareImageinfoName) {
        this.tbShareImageinfoName = tbShareImageinfoName == null ? null : tbShareImageinfoName.trim();
    }

    public String getTbShareImageinfoUrl() {
        return tbShareImageinfoUrl;
    }

    public void setTbShareImageinfoUrl(String tbShareImageinfoUrl) {
        this.tbShareImageinfoUrl = tbShareImageinfoUrl == null ? null : tbShareImageinfoUrl.trim();
    }

    public String getTbShareImageinfoParentid() {
        return tbShareImageinfoParentid;
    }

    public void setTbShareImageinfoParentid(String tbShareImageinfoParentid) {
        this.tbShareImageinfoParentid = tbShareImageinfoParentid == null ? null : tbShareImageinfoParentid.trim();
    }

    public String getTbShareImageinfoDesc() {
        return tbShareImageinfoDesc;
    }

    public void setTbShareImageinfoDesc(String tbShareImageinfoDesc) {
        this.tbShareImageinfoDesc = tbShareImageinfoDesc == null ? null : tbShareImageinfoDesc.trim();
    }

    public String getTbShareImageinfoCreatetime() {
        return tbShareImageinfoCreatetime;
    }

    public void setTbShareImageinfoCreatetime(String tbShareImageinfoCreatetime) {
        this.tbShareImageinfoCreatetime = tbShareImageinfoCreatetime == null ? null : tbShareImageinfoCreatetime.trim();
    }
}