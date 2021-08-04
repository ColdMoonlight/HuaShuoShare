package com.atguigu.bean;

public class ShareDataRecord {
	
    private Integer datarecordId;	//主键

    private Integer datarecordType;	//类型手机或邮件

    private String datarecordTypedetail;	//手机用在哪里、邮件用在哪里

    private String datarecordExplain;	//用途

    private Integer datarecordAdminid;

    private String datarecordAdminname;	//使用者

    private String datarecordCreatetime;

    private String datarecordMotifytime;

    public Integer getDatarecordId() {
        return datarecordId;
    }

    public void setDatarecordId(Integer datarecordId) {
        this.datarecordId = datarecordId;
    }

    public Integer getDatarecordType() {
        return datarecordType;
    }

    public void setDatarecordType(Integer datarecordType) {
        this.datarecordType = datarecordType;
    }

    public String getDatarecordTypedetail() {
        return datarecordTypedetail;
    }

    public void setDatarecordTypedetail(String datarecordTypedetail) {
        this.datarecordTypedetail = datarecordTypedetail == null ? null : datarecordTypedetail.trim();
    }

    public String getDatarecordExplain() {
        return datarecordExplain;
    }

    public void setDatarecordExplain(String datarecordExplain) {
        this.datarecordExplain = datarecordExplain == null ? null : datarecordExplain.trim();
    }

    public Integer getDatarecordAdminid() {
        return datarecordAdminid;
    }

    public void setDatarecordAdminid(Integer datarecordAdminid) {
        this.datarecordAdminid = datarecordAdminid;
    }

    public String getDatarecordAdminname() {
        return datarecordAdminname;
    }

    public void setDatarecordAdminname(String datarecordAdminname) {
        this.datarecordAdminname = datarecordAdminname == null ? null : datarecordAdminname.trim();
    }

    public String getDatarecordCreatetime() {
        return datarecordCreatetime;
    }

    public void setDatarecordCreatetime(String datarecordCreatetime) {
        this.datarecordCreatetime = datarecordCreatetime == null ? null : datarecordCreatetime.trim();
    }

    public String getDatarecordMotifytime() {
        return datarecordMotifytime;
    }

    public void setDatarecordMotifytime(String datarecordMotifytime) {
        this.datarecordMotifytime = datarecordMotifytime == null ? null : datarecordMotifytime.trim();
    }

	public ShareDataRecord() {
		super();
	}

	public ShareDataRecord(Integer datarecordId, Integer datarecordType, String datarecordTypedetail,
			String datarecordExplain, Integer datarecordAdminid, String datarecordAdminname,
			String datarecordCreatetime, String datarecordMotifytime) {
		super();
		this.datarecordId = datarecordId;
		this.datarecordType = datarecordType;
		this.datarecordTypedetail = datarecordTypedetail;
		this.datarecordExplain = datarecordExplain;
		this.datarecordAdminid = datarecordAdminid;
		this.datarecordAdminname = datarecordAdminname;
		this.datarecordCreatetime = datarecordCreatetime;
		this.datarecordMotifytime = datarecordMotifytime;
	}

	@Override
	public String toString() {
		return "ShareDataRecord [datarecordId=" + datarecordId + ", datarecordType=" + datarecordType
				+ ", datarecordTypedetail=" + datarecordTypedetail + ", datarecordExplain=" + datarecordExplain
				+ ", datarecordAdminid=" + datarecordAdminid + ", datarecordAdminname=" + datarecordAdminname
				+ ", datarecordCreatetime=" + datarecordCreatetime + ", datarecordMotifytime=" + datarecordMotifytime
				+ "]";
	}
    
}