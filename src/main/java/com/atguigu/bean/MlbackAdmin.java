package com.atguigu.bean;

public class MlbackAdmin {
    private Integer adminId;

    //private String adminAccname;
    
    private String adminAccount;

    private String adminPassword;

    private String adminOperatername;

    private String adminPower;//0下载;1下载-上传2下载-上传-删除-新建-文件夹重命名
    
    private String adminPowerItemStr;//具体都有哪些视图
    
    
    
//    private Integer adminId;
//
//    private String adminAccount;
//
//    private String adminPassword;
//
//    private String adminName;
//    
//    private Integer adminDepartmentId;
//
//    private String adminDepartmentName;
//
//    private String adminDepartmentIdStr;
//
//    private String adminDepartmentNameStr;
//
//    private String adminShopIdStr;
//
//    private String adminShopNameStr;
//
//    private Integer adminStatus;
//
//    private String adminMenuIdstr;
//
//    private String adminMenuNamestr;
//
//    private String adminMenuUrlstr;
//
//    private String adminCreatetime;
//
//    private String adminMotifytime;
    
    
    
    
    
    
    public MlbackAdmin(Integer adminId, String adminAccount, String adminPassword, String adminOperatername, String adminPower,String adminPowerItemStr) {
        this.adminId = adminId;
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminOperatername = adminOperatername;
        this.adminPower = adminPower;
        this.adminPowerItemStr = adminPowerItemStr;
    }

    public MlbackAdmin() {
        super();
    }

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
		this.adminPowerItemStr = adminOperatername == null ? null : adminOperatername.trim();
	}

	
	@Override
	public String toString() {
		return "MlbackAdmin [adminId=" + adminId + ", adminAccount=" + adminAccount + ", adminPassword=" + adminPassword
				+ ", adminOperatername=" + adminOperatername + ", adminPower=" + adminPower + ", adminPowerItemStr="
				+ adminPowerItemStr + "]";
	}
    
}