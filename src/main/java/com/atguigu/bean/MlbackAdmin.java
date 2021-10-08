package com.atguigu.bean;

public class MlbackAdmin {
    private Integer adminId;

    //private String adminAccname;
    
    private String adminAccount;

    private String adminPassword;

    private String adminOperatername;

    private String adminPower;//0下载;1下载-上传2下载-上传-删除-新建-文件夹重命名
    
    private String adminPowerItemStr;//具体都有哪些视图
    
    private String adminName;
    
    private Integer adminDepartmentId;

    private String adminDepartmentName;

    private String adminDepartmentIdStr;

    private String adminDepartmentNameStr;

    private String adminShopIdStr;

    private String adminShopNameStr;

    private Integer adminStatus;

    private String adminMenuIdstr;

    private String adminMenuNamestr;

    private String adminMenuUrlstr;
    
    private String adminHomePageIdstr;

    private String adminCreatetime;

    private String adminMotifytime;
    
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount == null ? null : adminAccount.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminOperatername() {
        return adminOperatername;
    }

    public void setAdminOperatername(String adminOperatername) {
        this.adminOperatername = adminOperatername == null ? null : adminOperatername.trim();
    }

    public String getAdminPower() {
        return adminPower;
    }

    public void setAdminPower(String adminPower) {
        this.adminPower = adminPower;
    }

	public String getAdminPowerItemStr() {
		return adminPowerItemStr;
	}

	public void setAdminPowerItemStr(String adminPowerItemStr) {
		this.adminPowerItemStr = adminPowerItemStr == null ? null : adminPowerItemStr.trim();
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getAdminDepartmentId() {
		return adminDepartmentId;
	}

	public void setAdminDepartmentId(Integer adminDepartmentId) {
		this.adminDepartmentId = adminDepartmentId;
	}

	public String getAdminDepartmentName() {
		return adminDepartmentName;
	}

	public void setAdminDepartmentName(String adminDepartmentName) {
		this.adminDepartmentName = adminDepartmentName;
	}

	public String getAdminDepartmentIdStr() {
		return adminDepartmentIdStr;
	}

	public void setAdminDepartmentIdStr(String adminDepartmentIdStr) {
		this.adminDepartmentIdStr = adminDepartmentIdStr;
	}

	public String getAdminDepartmentNameStr() {
		return adminDepartmentNameStr;
	}

	public void setAdminDepartmentNameStr(String adminDepartmentNameStr) {
		this.adminDepartmentNameStr = adminDepartmentNameStr;
	}

	public String getAdminShopIdStr() {
		return adminShopIdStr;
	}

	public void setAdminShopIdStr(String adminShopIdStr) {
		this.adminShopIdStr = adminShopIdStr;
	}

	public String getAdminShopNameStr() {
		return adminShopNameStr;
	}

	public void setAdminShopNameStr(String adminShopNameStr) {
		this.adminShopNameStr = adminShopNameStr;
	}

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getAdminMenuIdstr() {
		return adminMenuIdstr;
	}

	public void setAdminMenuIdstr(String adminMenuIdstr) {
		this.adminMenuIdstr = adminMenuIdstr;
	}

	public String getAdminMenuNamestr() {
		return adminMenuNamestr;
	}

	public void setAdminMenuNamestr(String adminMenuNamestr) {
		this.adminMenuNamestr = adminMenuNamestr;
	}

	public String getAdminMenuUrlstr() {
		return adminMenuUrlstr;
	}

	public void setAdminMenuUrlstr(String adminMenuUrlstr) {
		this.adminMenuUrlstr = adminMenuUrlstr;
	}

	public String getAdminHomePageIdstr() {
		return adminHomePageIdstr;
	}

	public void setAdminHomePageIdstr(String adminHomePageIdstr) {
		this.adminHomePageIdstr = adminHomePageIdstr;
	}

	public String getAdminCreatetime() {
		return adminCreatetime;
	}

	public void setAdminCreatetime(String adminCreatetime) {
		this.adminCreatetime = adminCreatetime;
	}

	public String getAdminMotifytime() {
		return adminMotifytime;
	}

	public void setAdminMotifytime(String adminMotifytime) {
		this.adminMotifytime = adminMotifytime;
	}
	
    public MlbackAdmin() {
        super();
    }

	public MlbackAdmin(Integer adminId, String adminAccount, String adminPassword, String adminOperatername,
			String adminPower, String adminPowerItemStr, String adminName, Integer adminDepartmentId,
			String adminDepartmentName, String adminDepartmentIdStr, String adminDepartmentNameStr,
			String adminShopIdStr, String adminShopNameStr, Integer adminStatus, String adminMenuIdstr,
			String adminMenuNamestr, String adminMenuUrlstr, String adminHomePageIdstr, String adminCreatetime,
			String adminMotifytime) {
		super();
		this.adminId = adminId;
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
		this.adminOperatername = adminOperatername;
		this.adminPower = adminPower;
		this.adminPowerItemStr = adminPowerItemStr;
		this.adminName = adminName;
		this.adminDepartmentId = adminDepartmentId;
		this.adminDepartmentName = adminDepartmentName;
		this.adminDepartmentIdStr = adminDepartmentIdStr;
		this.adminDepartmentNameStr = adminDepartmentNameStr;
		this.adminShopIdStr = adminShopIdStr;
		this.adminShopNameStr = adminShopNameStr;
		this.adminStatus = adminStatus;
		this.adminMenuIdstr = adminMenuIdstr;
		this.adminMenuNamestr = adminMenuNamestr;
		this.adminMenuUrlstr = adminMenuUrlstr;
		this.adminHomePageIdstr = adminHomePageIdstr;
		this.adminCreatetime = adminCreatetime;
		this.adminMotifytime = adminMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackAdmin [adminId=" + adminId + ", adminAccount=" + adminAccount + ", adminPassword=" + adminPassword
				+ ", adminOperatername=" + adminOperatername + ", adminPower=" + adminPower + ", adminPowerItemStr="
				+ adminPowerItemStr + ", adminName=" + adminName + ", adminDepartmentId=" + adminDepartmentId
				+ ", adminDepartmentName=" + adminDepartmentName + ", adminDepartmentIdStr=" + adminDepartmentIdStr
				+ ", adminDepartmentNameStr=" + adminDepartmentNameStr + ", adminShopIdStr=" + adminShopIdStr
				+ ", adminShopNameStr=" + adminShopNameStr + ", adminStatus=" + adminStatus + ", adminMenuIdstr="
				+ adminMenuIdstr + ", adminMenuNamestr=" + adminMenuNamestr + ", adminMenuUrlstr=" + adminMenuUrlstr
				+ ", adminHomePageIdstr=" + adminHomePageIdstr + ", adminCreatetime=" + adminCreatetime
				+ ", adminMotifytime=" + adminMotifytime + "]";
	}

    
}