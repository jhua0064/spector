package com.monash.spector.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_plant")
public class Plant {
    private Integer id;
    private String type;
    @Column(name = "t_detail")
    private String tDetail;
    @Column(name = "b_name")
    private String bName;
    @Column(name = "c_name")
    private String cName;
    private String color;
    private String season;
    @Column(name = "img_link")
    private String imgLink;

    public Plant() {
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettDetail() {
        return tDetail;
    }

    public void settDetail(String tDetail) {
        this.tDetail = tDetail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
