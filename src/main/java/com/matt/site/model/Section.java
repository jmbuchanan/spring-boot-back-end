package com.matt.site.model;

public class Section {

    private String subjectTitle;
    private float sectionNumber;
    private String sectionTitle;

    
    public Section (
        
        String subjectTitle,
        float sectionNumber,
        String sectionTitle) {
        
        this.subjectTitle = subjectTitle;
        this.sectionNumber = sectionNumber;
        this.sectionTitle = sectionTitle;
        
    }
    
    
    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public float getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(float sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }
       
}
