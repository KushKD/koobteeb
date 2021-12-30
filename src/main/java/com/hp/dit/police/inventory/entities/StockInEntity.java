package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_stockin")
public class StockInEntity {

    @Id
    @GeneratedValue(generator = "mst_stockin_stockin_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_stockin_stockin_id_seq", sequenceName = "public.mst_stockin_stockin_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "stockin_id")
    private Integer stockId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policeline_id")
    private PoliceLines policeLines;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id")
    private ItemsEntity items;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public PoliceLines getPoliceLines() {
        return policeLines;
    }

    public void setPoliceLines(PoliceLines policeLines) {
        this.policeLines = policeLines;
    }

    public ItemsEntity getItems() {
        return items;
    }

    public void setItems(ItemsEntity items) {
        this.items = items;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "StockInEntity{" +
                "stockId=" + stockId +
                ", policeLines=" + policeLines +
                ", items=" + items +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                '}';
    }
}
