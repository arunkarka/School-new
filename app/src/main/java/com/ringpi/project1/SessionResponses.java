package com.ringpi.project1;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SessionResponses {
    private String name;
    private String title;
    private String company;


    public SessionResponses() {
    }

    public SessionResponses(String name, String title, String company) {
        this.name = name;
        this.title = title;
        this.company = company;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }




}