package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mst_comments")
public class CommentsMaster implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_comments_comments_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_comments_comments_id_seq", sequenceName = "public.mst_comments_comments_id_seq", initialValue = 1, allocationSize = 1)

    @Column(name="comments_id")
    private Integer commnetsId;

    @Column(name="comments")
    private String comments;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userId;


    @Column(name="id")
    private Integer informationId;

    @Column(name="is_active")
    private boolean active;

    @Column(name="is_deleted")
    private boolean deleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


    public Integer getCommnetsId() {
        return commnetsId;
    }

    public void setCommnetsId(Integer commnetsId) {
        this.commnetsId = commnetsId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "CommentsMaster{" +
                "commnetsId=" + commnetsId +
                ", comments='" + comments + '\'' +
                ", userId=" + userId +
                ", informationId=" + informationId +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
