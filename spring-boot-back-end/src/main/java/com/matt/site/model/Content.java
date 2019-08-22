package com.matt.site.model;

public class Content {

    private String subjectTitle;
    private float sectionNumber;
    private String sectionTitle;
    private long paragraphNumber;
    private String paragraphText;
    private String codeExample;
    private String imagePathway;
    
    public Content(
        String subjectTitle,
        float sectionNumber,
        String sectionTitle,
        long paragraphNumber,
        String paragraphText,
        String codeExample,
        String imagePathway){
    
        this.subjectTitle = subjectTitle;
        this.sectionNumber = sectionNumber;
        this.sectionTitle = sectionTitle;
        this.paragraphNumber = paragraphNumber;
        this.paragraphText = paragraphText;
        this.codeExample = codeExample;
        this.imagePathway = imagePathway;
        
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

    public long getParagraphNumber() {
        return paragraphNumber;
    }

    public void setParagraphNumber(long paragraphNumber) {
        this.paragraphNumber = paragraphNumber;
    }

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }

    public String getCodeExample() {
        return codeExample;
    }

    public void setCodeExample(String codeExample) {
        this.codeExample = codeExample;
    }

    public String getImagePathway() {
        return imagePathway;
    }

    public void setImagePathway(String imagePathway) {
        this.imagePathway = imagePathway;
    }

    
}
