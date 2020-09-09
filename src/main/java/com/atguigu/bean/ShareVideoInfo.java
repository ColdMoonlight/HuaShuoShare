package com.atguigu.bean;

public class ShareVideoInfo {
    private Integer tbShareVideoinfoId;

    private Integer tbShareVideoinfoType;

    private String tbShareVideoinfoName;

    private String tbShareVideoinfoVideourl;

    private String tbShareVideoinfoVideoimgurl;

    private Integer tbShareVideoinfoParentid;

    private String tbShareVideoinfoParentname;

    private String tbShareVideoinfoDesc;

    private String tbShareVideoinfoCreatetime;

    public Integer getTbShareVideoinfoId() {
        return tbShareVideoinfoId;
    }

    public void setTbShareVideoinfoId(Integer tbShareVideoinfoId) {
        this.tbShareVideoinfoId = tbShareVideoinfoId;
    }

    public Integer getTbShareVideoinfoType() {
        return tbShareVideoinfoType;
    }

    public void setTbShareVideoinfoType(Integer tbShareVideoinfoType) {
        this.tbShareVideoinfoType = tbShareVideoinfoType;
    }

    public String getTbShareVideoinfoName() {
        return tbShareVideoinfoName;
    }

    public void setTbShareVideoinfoName(String tbShareVideoinfoName) {
        this.tbShareVideoinfoName = tbShareVideoinfoName == null ? null : tbShareVideoinfoName.trim();
    }

    public String getTbShareVideoinfoVideourl() {
        return tbShareVideoinfoVideourl;
    }

    public void setTbShareVideoinfoVideourl(String tbShareVideoinfoVideourl) {
        this.tbShareVideoinfoVideourl = tbShareVideoinfoVideourl == null ? null : tbShareVideoinfoVideourl.trim();
    }

    public String getTbShareVideoinfoVideoimgurl() {
        return tbShareVideoinfoVideoimgurl;
    }

    public void setTbShareVideoinfoVideoimgurl(String tbShareVideoinfoVideoimgurl) {
        this.tbShareVideoinfoVideoimgurl = tbShareVideoinfoVideoimgurl == null ? null : tbShareVideoinfoVideoimgurl.trim();
    }

    public Integer getTbShareVideoinfoParentid() {
        return tbShareVideoinfoParentid;
    }

    public void setTbShareVideoinfoParentid(Integer tbShareVideoinfoParentid) {
        this.tbShareVideoinfoParentid = tbShareVideoinfoParentid;
    }

    public String getTbShareVideoinfoParentname() {
        return tbShareVideoinfoParentname;
    }

    public void setTbShareVideoinfoParentname(String tbShareVideoinfoParentname) {
        this.tbShareVideoinfoParentname = tbShareVideoinfoParentname == null ? null : tbShareVideoinfoParentname.trim();
    }

    public String getTbShareVideoinfoDesc() {
        return tbShareVideoinfoDesc;
    }

    public void setTbShareVideoinfoDesc(String tbShareVideoinfoDesc) {
        this.tbShareVideoinfoDesc = tbShareVideoinfoDesc == null ? null : tbShareVideoinfoDesc.trim();
    }

    public String getTbShareVideoinfoCreatetime() {
        return tbShareVideoinfoCreatetime;
    }

    public void setTbShareVideoinfoCreatetime(String tbShareVideoinfoCreatetime) {
        this.tbShareVideoinfoCreatetime = tbShareVideoinfoCreatetime == null ? null : tbShareVideoinfoCreatetime.trim();
    }

	public ShareVideoInfo() {
		super();
	}

	public ShareVideoInfo(Integer tbShareVideoinfoId, Integer tbShareVideoinfoType, String tbShareVideoinfoName,
			String tbShareVideoinfoVideourl, String tbShareVideoinfoVideoimgurl, Integer tbShareVideoinfoParentid,
			String tbShareVideoinfoParentname, String tbShareVideoinfoDesc, String tbShareVideoinfoCreatetime) {
		super();
		this.tbShareVideoinfoId = tbShareVideoinfoId;
		this.tbShareVideoinfoType = tbShareVideoinfoType;
		this.tbShareVideoinfoName = tbShareVideoinfoName;
		this.tbShareVideoinfoVideourl = tbShareVideoinfoVideourl;
		this.tbShareVideoinfoVideoimgurl = tbShareVideoinfoVideoimgurl;
		this.tbShareVideoinfoParentid = tbShareVideoinfoParentid;
		this.tbShareVideoinfoParentname = tbShareVideoinfoParentname;
		this.tbShareVideoinfoDesc = tbShareVideoinfoDesc;
		this.tbShareVideoinfoCreatetime = tbShareVideoinfoCreatetime;
	}

	@Override
	public String toString() {
		return "ShareVideoInfo [tbShareVideoinfoId=" + tbShareVideoinfoId + ", tbShareVideoinfoType="
				+ tbShareVideoinfoType + ", tbShareVideoinfoName=" + tbShareVideoinfoName
				+ ", tbShareVideoinfoVideourl=" + tbShareVideoinfoVideourl + ", tbShareVideoinfoVideoimgurl="
				+ tbShareVideoinfoVideoimgurl + ", tbShareVideoinfoParentid=" + tbShareVideoinfoParentid
				+ ", tbShareVideoinfoParentname=" + tbShareVideoinfoParentname + ", tbShareVideoinfoDesc="
				+ tbShareVideoinfoDesc + ", tbShareVideoinfoCreatetime=" + tbShareVideoinfoCreatetime + "]";
	}
    
}