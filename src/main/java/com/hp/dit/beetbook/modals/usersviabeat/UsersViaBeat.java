package com.hp.dit.beetbook.modals.usersviabeat;

import java.io.Serializable;

public class UsersViaBeat implements Serializable {

    private Long userId;
    private String username;

    public UsersViaBeat(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UsersViaBeat{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
