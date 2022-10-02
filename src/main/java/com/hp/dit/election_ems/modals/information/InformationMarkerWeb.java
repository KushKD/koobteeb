package com.hp.dit.election_ems.modals.information;

import java.io.Serializable;

public class InformationMarkerWeb implements Serializable {

    private Integer id;
    private Integer submoduleId;
    private String submoduleName;
    private String name;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(Integer submoduleId) {
        this.submoduleId = submoduleId;
    }

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InformationMarkerWeb{" +
                "id=" + id +
                ", submoduleId=" + submoduleId +
                ", submoduleName='" + submoduleName + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
