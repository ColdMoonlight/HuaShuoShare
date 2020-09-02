package com.atguigu.bean;

public class ShareImageLabel {
    private Integer imageLabelId;

    private String imageLabelAccname;

    private String imageLabelHang;

    private String imageLabelCreatetime;

    public Integer getImageLabelId() {
        return imageLabelId;
    }

    public void setImageLabelId(Integer imageLabelId) {
        this.imageLabelId = imageLabelId;
    }

    public String getImageLabelAccname() {
        return imageLabelAccname;
    }

    public void setImageLabelAccname(String imageLabelAccname) {
        this.imageLabelAccname = imageLabelAccname == null ? null : imageLabelAccname.trim();
    }

    public String getImageLabelHang() {
        return imageLabelHang;
    }

    public void setImageLabelHang(String imageLabelHang) {
        this.imageLabelHang = imageLabelHang == null ? null : imageLabelHang.trim();
    }

    public String getImageLabelCreatetime() {
        return imageLabelCreatetime;
    }

    public void setImageLabelCreatetime(String imageLabelCreatetime) {
        this.imageLabelCreatetime = imageLabelCreatetime == null ? null : imageLabelCreatetime.trim();
    }

	public ShareImageLabel() {
		super();
	}

	public ShareImageLabel(Integer imageLabelId, String imageLabelAccname, String imageLabelHang,
			String imageLabelCreatetime) {
		super();
		this.imageLabelId = imageLabelId;
		this.imageLabelAccname = imageLabelAccname;
		this.imageLabelHang = imageLabelHang;
		this.imageLabelCreatetime = imageLabelCreatetime;
	}

	@Override
	public String toString() {
		return "ShareImageLabel [imageLabelId=" + imageLabelId + ", imageLabelAccname=" + imageLabelAccname
				+ ", imageLabelHang=" + imageLabelHang + ", imageLabelCreatetime=" + imageLabelCreatetime + "]";
	}
    
}