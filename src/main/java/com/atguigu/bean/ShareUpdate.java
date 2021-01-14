package com.atguigu.bean;

public class ShareUpdate {
    private Integer tbShareUpdateId;	//更新通告id

    private String tbShareUpdateName;	//更新通告

    private String tbShareUpdateDetail;	//通告明细

    private String tbShareUpdateCreatetime;

    private String tbShareUpdateModifytime;

    private Integer tbShareUpdateAdminid;	//更新操作人员id

    private String tbShareUpdateAdminname;	//更新操作人员部门归属

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

	public ShareUpdate() {
		super();
	}

	public ShareUpdate(Integer tbShareUpdateId, String tbShareUpdateName, String tbShareUpdateDetail,
			String tbShareUpdateCreatetime, String tbShareUpdateModifytime, Integer tbShareUpdateAdminid,
			String tbShareUpdateAdminname) {
		super();
		this.tbShareUpdateId = tbShareUpdateId;
		this.tbShareUpdateName = tbShareUpdateName;
		this.tbShareUpdateDetail = tbShareUpdateDetail;
		this.tbShareUpdateCreatetime = tbShareUpdateCreatetime;
		this.tbShareUpdateModifytime = tbShareUpdateModifytime;
		this.tbShareUpdateAdminid = tbShareUpdateAdminid;
		this.tbShareUpdateAdminname = tbShareUpdateAdminname;
	}

	@Override
	public String toString() {
		return "ShareUpdate [tbShareUpdateId=" + tbShareUpdateId + ", tbShareUpdateName=" + tbShareUpdateName
				+ ", tbShareUpdateDetail=" + tbShareUpdateDetail + ", tbShareUpdateCreatetime="
				+ tbShareUpdateCreatetime + ", tbShareUpdateModifytime=" + tbShareUpdateModifytime
				+ ", tbShareUpdateAdminid=" + tbShareUpdateAdminid + ", tbShareUpdateAdminname="
				+ tbShareUpdateAdminname + "]";
	}
    
}