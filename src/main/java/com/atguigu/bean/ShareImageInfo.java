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

	public ShareImageInfo() {
		super();
	}

	public ShareImageInfo(Integer tbShareImageinfoId, Integer tbShareImageinfoType, String tbShareImageinfoName,
			String tbShareImageinfoUrl, String tbShareImageinfoParentid, String tbShareImageinfoDesc,
			String tbShareImageinfoCreatetime) {
		super();
		this.tbShareImageinfoId = tbShareImageinfoId;
		this.tbShareImageinfoType = tbShareImageinfoType;
		this.tbShareImageinfoName = tbShareImageinfoName;
		this.tbShareImageinfoUrl = tbShareImageinfoUrl;
		this.tbShareImageinfoParentid = tbShareImageinfoParentid;
		this.tbShareImageinfoDesc = tbShareImageinfoDesc;
		this.tbShareImageinfoCreatetime = tbShareImageinfoCreatetime;
	}

	@Override
	public String toString() {
		return "ShareImageInfo [tbShareImageinfoId=" + tbShareImageinfoId + ", tbShareImageinfoType="
				+ tbShareImageinfoType + ", tbShareImageinfoName=" + tbShareImageinfoName + ", tbShareImageinfoUrl="
				+ tbShareImageinfoUrl + ", tbShareImageinfoParentid=" + tbShareImageinfoParentid
				+ ", tbShareImageinfoDesc=" + tbShareImageinfoDesc + ", tbShareImageinfoCreatetime="
				+ tbShareImageinfoCreatetime + "]";
	}
    
}