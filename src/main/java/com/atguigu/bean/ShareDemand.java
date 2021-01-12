package com.atguigu.bean;

public class ShareDemand {
    private Integer tbShareDemandId;

    private String tbShareDemandName;

    private String tbShareDemandDetail;

    private String tbShareDemandCreatetime;

    private String tbShareDemandModifytime;

    private Integer tbShareDemandAdminid;

    private String tbShareDemandAdminname;

    public Integer getTbShareDemandId() {
        return tbShareDemandId;
    }

    public void setTbShareDemandId(Integer tbShareDemandId) {
        this.tbShareDemandId = tbShareDemandId;
    }

    public String getTbShareDemandName() {
        return tbShareDemandName;
    }

    public void setTbShareDemandName(String tbShareDemandName) {
        this.tbShareDemandName = tbShareDemandName == null ? null : tbShareDemandName.trim();
    }

    public String getTbShareDemandDetail() {
        return tbShareDemandDetail;
    }

    public void setTbShareDemandDetail(String tbShareDemandDetail) {
        this.tbShareDemandDetail = tbShareDemandDetail == null ? null : tbShareDemandDetail.trim();
    }

    public String getTbShareDemandCreatetime() {
        return tbShareDemandCreatetime;
    }

    public void setTbShareDemandCreatetime(String tbShareDemandCreatetime) {
        this.tbShareDemandCreatetime = tbShareDemandCreatetime == null ? null : tbShareDemandCreatetime.trim();
    }

    public String getTbShareDemandModifytime() {
        return tbShareDemandModifytime;
    }

    public void setTbShareDemandModifytime(String tbShareDemandModifytime) {
        this.tbShareDemandModifytime = tbShareDemandModifytime == null ? null : tbShareDemandModifytime.trim();
    }

    public Integer getTbShareDemandAdminid() {
        return tbShareDemandAdminid;
    }

    public void setTbShareDemandAdminid(Integer tbShareDemandAdminid) {
        this.tbShareDemandAdminid = tbShareDemandAdminid;
    }

    public String getTbShareDemandAdminname() {
        return tbShareDemandAdminname;
    }

    public void setTbShareDemandAdminname(String tbShareDemandAdminname) {
        this.tbShareDemandAdminname = tbShareDemandAdminname == null ? null : tbShareDemandAdminname.trim();
    }
}