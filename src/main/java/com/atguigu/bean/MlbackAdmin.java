package com.atguigu.bean;

public class MlbackAdmin {
    private Integer adminId;

    private String adminAccname;

    private String adminPassword;

    private String adminOperatername;

    private String adminPower;//0下载;1下载-上传2下载-上传-删除-新建-文件夹重命名
    
    private String adminPowerItemStr;//具体都有哪些视图
    
    public MlbackAdmin(Integer adminId, String adminAccname, String adminPassword, String adminOperatername, String adminPower,String adminPowerItemStr) {
        this.adminId = adminId;
        this.adminAccname = adminAccname;
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

    public String getAdminAccname() {
        return adminAccname;
    }

    public void setAdminAccname(String adminAccname) {
        this.adminAccname = adminAccname == null ? null : adminAccname.trim();
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
		return "MlbackAdmin [adminId=" + adminId + ", adminAccname=" + adminAccname + ", adminPassword=" + adminPassword
				+ ", adminOperatername=" + adminOperatername + ", adminPower=" + adminPower + ", adminPowerItemStr="
				+ adminPowerItemStr + "]";
	}
    
}