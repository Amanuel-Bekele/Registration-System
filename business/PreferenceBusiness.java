package com.waaproject.registrationsystem.business;

import java.util.HashMap;
import java.util.List;

public class PreferenceBusiness {
//    private String priorityNo;
//
//    private int sectionId;
    String p1;
    String p2;
    String p3;
    String p4;

//
//    public Preference(List<HashMap<Integer, Integer>> maps){
//
//    }
//
//    public int getPriorityNo() {
//        return priorityNo;
//    }
//
//    public int getSectionId() {
//        return sectionId;
//    }
//
//    public void setPriorityNo(int priorityNo) {
//        this.priorityNo = priorityNo;
//    }
//
//    public void setSectionId(int sectionId) {
//        this.sectionId = sectionId;
//    }


    public PreferenceBusiness(String p1, String p2, String p3, String p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public PreferenceBusiness() {
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4() {
        return p4;
    }


    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }
}
