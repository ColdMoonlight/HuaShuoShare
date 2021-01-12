package com.atguigu.bean;

public class EmailNoneed {
    private Integer emailnoneedId;

    private String emailnoneedEmail;

    public Integer getEmailnoneedId() {
        return emailnoneedId;
    }

    public void setEmailnoneedId(Integer emailnoneedId) {
        this.emailnoneedId = emailnoneedId;
    }

    public String getEmailnoneedEmail() {
        return emailnoneedEmail;
    }

    public void setEmailnoneedEmail(String emailnoneedEmail) {
        this.emailnoneedEmail = emailnoneedEmail == null ? null : emailnoneedEmail.trim();
    }
}