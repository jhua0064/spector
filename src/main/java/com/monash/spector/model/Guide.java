package com.monash.spector.model;
import javax.persistence.*;

@Entity
@Table(name = "tb_guide")
public class Guide {
    private Integer id;
    @Column(name = "water_need")
    private String waterNeed;
    @Column(name = "light_need")
    private String lightNeed;
    @Column(name = "soil_type")
    private String soilType;
    @Column(name = "soil_add")
    private String soilAdd;
    @Column(name = "h_range")
    private String hRange;
    @Column(name = "s_range")
    private String sRange;
    private String climate;
    private String maintenance;
    private Integer smell;


    public Guide() {
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaterNeed() {
        return waterNeed;
    }

    public void setWaterNeed(String waterNeed) {
        this.waterNeed = waterNeed;
    }

    public String getLightNeed() {
        return lightNeed;
    }

    public void setLightNeed(String lightNeed) {
        this.lightNeed = lightNeed;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSoilAdd() {
        return soilAdd;
    }

    public void setSoilAdd(String soilAdd) {
        this.soilAdd = soilAdd;
    }

    public String gethRange() {
        return hRange;
    }

    public void sethRange(String hRange) {
        this.hRange = hRange;
    }

    public String getsRange() {
        return sRange;
    }

    public void setsRange(String sRange) {
        this.sRange = sRange;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public Integer getSmell() {
        return smell;
    }

    public void setSmell(Integer smell) {
        this.smell = smell;
    }


}
