package com.atguigu.bean;

public class ShareOperationRecord {
	
	//操作id
    private Integer operationRecordId;
	//操作adminid
    private Integer operationRecordAdminid;
	//操作admin身份
    private String operationRecordAdminName;
	//操作工作类型
    private Integer operationRecordDataType;
	//操作-文件名
    private String operationRecordDataName;
	//操作记录描述
    private String operationRecordDesc;
	//操作时间
    private String operationRecordCreatetime;

    public Integer getOperationRecordId() {
        return operationRecordId;
    }

    public void setOperationRecordId(Integer operationRecordId) {
        this.operationRecordId = operationRecordId;
    }

    public Integer getOperationRecordAdminid() {
        return operationRecordAdminid;
    }

    public void setOperationRecordAdminid(Integer operationRecordAdminid) {
        this.operationRecordAdminid = operationRecordAdminid;
    }

    public String getOperationRecordAdminName() {
        return operationRecordAdminName;
    }

    public void setOperationRecordAdminName(String operationRecordAdminName) {
        this.operationRecordAdminName = operationRecordAdminName == null ? null : operationRecordAdminName.trim();
    }

    public Integer getOperationRecordDataType() {
        return operationRecordDataType;
    }

    public void setOperationRecordDataType(Integer operationRecordDataType) {
        this.operationRecordDataType = operationRecordDataType;
    }

    public String getOperationRecordDataName() {
        return operationRecordDataName;
    }

    public void setOperationRecordDataName(String operationRecordDataName) {
        this.operationRecordDataName = operationRecordDataName == null ? null : operationRecordDataName.trim();
    }

    public String getOperationRecordDesc() {
        return operationRecordDesc;
    }

    public void setOperationRecordDesc(String operationRecordDesc) {
        this.operationRecordDesc = operationRecordDesc == null ? null : operationRecordDesc.trim();
    }

    public String getOperationRecordCreatetime() {
        return operationRecordCreatetime;
    }

    public void setOperationRecordCreatetime(String operationRecordCreatetime) {
        this.operationRecordCreatetime = operationRecordCreatetime == null ? null : operationRecordCreatetime.trim();
    }

	public ShareOperationRecord() {
		super();
	}

	public ShareOperationRecord(Integer operationRecordId, Integer operationRecordAdminid,
			String operationRecordAdminName, Integer operationRecordDataType, String operationRecordDataName,
			String operationRecordDesc, String operationRecordCreatetime) {
		super();
		this.operationRecordId = operationRecordId;
		this.operationRecordAdminid = operationRecordAdminid;
		this.operationRecordAdminName = operationRecordAdminName;
		this.operationRecordDataType = operationRecordDataType;
		this.operationRecordDataName = operationRecordDataName;
		this.operationRecordDesc = operationRecordDesc;
		this.operationRecordCreatetime = operationRecordCreatetime;
	}

	@Override
	public String toString() {
		return "ShareOperationRecord [operationRecordId=" + operationRecordId + ", operationRecordAdminid="
				+ operationRecordAdminid + ", operationRecordAdminName=" + operationRecordAdminName
				+ ", operationRecordDataType=" + operationRecordDataType + ", operationRecordDataName="
				+ operationRecordDataName + ", operationRecordDesc=" + operationRecordDesc
				+ ", operationRecordCreatetime=" + operationRecordCreatetime + "]";
	}
    
}