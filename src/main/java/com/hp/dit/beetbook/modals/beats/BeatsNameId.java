package com.hp.dit.beetbook.modals.beats;

import java.io.Serializable;

public class BeatsNameId implements Serializable {

    private Integer beatId;
    private String beatName;

    public BeatsNameId(Integer beatId, String beatName) {
        this.beatId = beatId;
        this.beatName = beatName;
    }

    public Integer getBeatId() {
        return beatId;
    }

    public void setBeatId(Integer beatId) {
        this.beatId = beatId;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    @Override
    public String toString() {
        return "BeatsNameId{" +
                "beatId=" + beatId +
                ", beatName='" + beatName + '\'' +
                '}';
    }
}
