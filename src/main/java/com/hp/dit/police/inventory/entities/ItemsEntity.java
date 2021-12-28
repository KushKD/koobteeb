package com.hp.dit.police.inventory.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_item")
public class ItemsEntity {

    @Id
    @GeneratedValue(generator = "mst_item_items_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_item_items_id_seq", sequenceName = "public.mst_item_items_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "items_id")
    private Integer itemsId;

    @Column(name = "items_name")
    private String itemsName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policeline_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private PoliceLines policeLines;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ps_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private PoliceStationMaster policeStationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private StoreEntity store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private CategoryItemsEntity categoryItemsEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private UnitsEntity units;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "items_desc")
    private String itemsDesc;

    @Column(name = "items_letterrefdoc")
    private String itemsLetterdoc;

    @Column(name = "items_letterrefno")
    private String itemsLetterno;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodifiedDate;

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public PoliceLines getPoliceLines() {
        return policeLines;
    }

    public void setPoliceLines(PoliceLines policeLines) {
        this.policeLines = policeLines;
    }

    public PoliceStationMaster getPoliceStationMaster() {
        return policeStationMaster;
    }

    public void setPoliceStationMaster(PoliceStationMaster policeStationMaster) {
        this.policeStationMaster = policeStationMaster;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public CategoryItemsEntity getCategoryItemsEntity() {
        return categoryItemsEntity;
    }

    public void setCategoryItemsEntity(CategoryItemsEntity categoryItemsEntity) {
        this.categoryItemsEntity = categoryItemsEntity;
    }

    public UnitsEntity getUnits() {
        return units;
    }

    public void setUnits(UnitsEntity units) {
        this.units = units;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(String itemsDesc) {
        this.itemsDesc = itemsDesc;
    }

    public String getItemsLetterdoc() {
        return itemsLetterdoc;
    }

    public void setItemsLetterdoc(String itemsLetterdoc) {
        this.itemsLetterdoc = itemsLetterdoc;
    }

    public String getItemsLetterno() {
        return itemsLetterno;
    }

    public void setItemsLetterno(String itemsLetterno) {
        this.itemsLetterno = itemsLetterno;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastmodifiedDate() {
        return lastmodifiedDate;
    }

    public void setLastmodifiedDate(Date lastmodifiedDate) {
        this.lastmodifiedDate = lastmodifiedDate;
    }

    @Override
    public String toString() {
        return "ItemsEntity{" +
                "itemsId=" + itemsId +
                ", itemsName='" + itemsName + '\'' +
                ", policeLines=" + policeLines +
                ", policeStationMaster=" + policeStationMaster +
                ", store=" + store +
                ", categoryItemsEntity=" + categoryItemsEntity +
                ", units=" + units +
                ", quantity='" + quantity + '\'' +
                ", itemsDesc='" + itemsDesc + '\'' +
                ", itemsLetterdoc='" + itemsLetterdoc + '\'' +
                ", itemsLetterno='" + itemsLetterno + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", lastmodifiedDate=" + lastmodifiedDate +
                '}';
    }
}
