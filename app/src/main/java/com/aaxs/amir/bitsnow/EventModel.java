package com.aaxs.amir.bitsnow;

/**
 *  Java class modeled after the API. Used for creating objects from the API.
 */

public class EventModel {

    private String id;
    private String eTitle;
    private String eDesc;
    private String cName;
    private String cId;
    private String eStartDate;
    private String eEndDate;

    public EventModel(String id, String eTitle, String eDesc) {
        this.id = id;
        this.eTitle = eTitle;
        this.eDesc = eDesc;
    }

    public EventModel(String id, String eTitle, String eDesc, String cName, String cId, String eStartDate, String eEndDate) {
        this.id = id;
        this.eTitle = eTitle;
        this.eDesc = eDesc;
        this.cName = cName;
        this.cId = cId;
        this.eStartDate = eStartDate;
        this.eEndDate = eEndDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteTitle() {
        return eTitle;
    }

    public void seteTitle(String eTitle) {
        this.eTitle = eTitle;
    }

    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String geteStartDate() {
        return eStartDate;
    }

    public void seteStartDate(String eStartDate) {
        this.eStartDate = eStartDate;
    }

    public String geteEndDate() {
        return eEndDate;
    }

    public void seteEndDate(String eEndDate) {
        this.eEndDate = eEndDate;
    }
}
