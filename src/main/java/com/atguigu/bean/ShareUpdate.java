package com.atguigu.bean;

public class ShareUpdate {
    private Integer tbShareUpdateId;

    private String tbShareUpdateName;

    private String tbShareUpdateDetail;

    private String tbShareUpdateCreatetime;

    private String tbShareUpdateModifytime;

    private Integer tbShareUpdateAdminid;

    private String tbShareUpdateAdminname;

    public Integer getTbShareUpdateId() {
        return tbShareUpdateId;
    }

    public void setTbShareUpdateId(Integer tbShareUpdateId) {
        this.tbShareUpdateId = tbShareUpdateId;
    }

    public String getTbShareUpdateName() {
        return tbShareUpdateName;
    }

    public void setTbShareUpdateName(String tbShareUpdateName) {
        this.tbShareUpdateName = tbShareUpdateName == null ? null : tbShareUpdateName.trim();
    }

    public String getTbShareUpdateDetail() {
        return tbShareUpdateDetail;
    }

    public void setTbShareUpdateDetail(String tbShareUpdateDetail) {
        this.tbShareUpdateDetail = tbShareUpdateDetail == null ? null : tbShareUpdateDetail.trim();
    }

    public String getTbShareUpdateCreatetime() {
        return tbShareUpdateCreatetime;
    }

    public void setTbShareUpdateCreatetime(String tbShareUpdateCreatetime) {
        this.tbShareUpdateCreatetime = tbShareUpdateCreatetime == null ? null : tbShareUpdateCreatetime.trim();
    }

    public String getTbShareUpdateModifytime() {
        return tbShareUpdateModifytime;
    }

    public void setTbShareUpdateModifytime(String tbShareUpdateModifytime) {
        this.tbShareUpdateModifytime = tbShareUpdateModifytime == null ? null : tbShareUpdateModifytime.trim();
    }

    public Integer getTbShareUpdateAdminid() {
        return tbShareUpdateAdminid;
    }

    public void setTbShareUpdateAdminid(Integer tbShareUpdateAdminid) {
        this.tbShareUpdateAdminid = tbShareUpdateAdminid;
    }

    public String getTbShareUpdateAdminname() {
        return tbShareUpdateAdminname;
    }

    public void setTbShareUpdateAdminname(String tbShareUpdateAdminname) {
        this.tbShareUpdateAdminname = tbShareUpdateAdminname == null ? null : tbShareUpdateAdminname.trim();
    }
}