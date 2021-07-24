package com.hp.dit.beetbook.modals;

import java.io.Serializable;

public class RUsersPojo implements Serializable {

    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RUsersPojo{" +
                "userId=" + userId +
                ", userName=" + userName +
                '}';
    }
}
