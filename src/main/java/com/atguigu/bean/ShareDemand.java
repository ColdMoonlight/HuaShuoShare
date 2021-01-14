package com.atguigu.bean;

public class ShareDemand {
    private Integer tbShareDemandId;	//需求通告id

    private String tbShareDemandName;	//更新通告

    private String tbShareDemandDetail;	//通告明细

    private String tbShareDemandCreatetime;

    private String tbShareDemandModifytime;

    private Integer tbShareDemandAdminid;	//更新操作人员id

    private String tbShareDemandAdminname;	//更新操作人员部门归属

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

	public ShareDemand() {
		super();
	}

	public ShareDemand(Integer tbShareDemandId, String tbShareDemandName, String tbShareDemandDetail,
			String tbShareDemandCreatetime, String tbShareDemandModifytime, Integer tbShareDemandAdminid,
			String tbShareDemandAdminname) {
		super();
		this.tbShareDemandId = tbShareDemandId;
		this.tbShareDemandName = tbShareDemandName;
		this.tbShareDemandDetail = tbShareDemandDetail;
		this.tbShareDemandCreatetime = tbShareDemandCreatetime;
		this.tbShareDemandModifytime = tbShareDemandModifytime;
		this.tbShareDemandAdminid = tbShareDemandAdminid;
		this.tbShareDemandAdminname = tbShareDemandAdminname;
	}

	@Override
	public String toString() {
		return "ShareDemand [tbShareDemandId=" + tbShareDemandId + ", tbShareDemandName=" + tbShareDemandName
				+ ", tbShareDemandDetail=" + tbShareDemandDetail + ", tbShareDemandCreatetime="
				+ tbShareDemandCreatetime + ", tbShareDemandModifytime=" + tbShareDemandModifytime
				+ ", tbShareDemandAdminid=" + tbShareDemandAdminid + ", tbShareDemandAdminname="
				+ tbShareDemandAdminname + "]";
	}
    
}