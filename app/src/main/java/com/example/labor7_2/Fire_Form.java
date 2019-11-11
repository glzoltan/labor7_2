package com.example.labor7_2;

public class Fire_Form {

    private String ID;
    private String NAME;
    private String LOCATION;
    private String PROFILE_PICTURE;
    private String DATE_OF_BIRTH;
    private String GENDER;
    private String HOBBIES;
    private String DEPARTMENT;
    private String YEAR_OF_STUDY;

    public Fire_Form(){

    }

    public Fire_Form(String ID, String NAME, String LOCATION, String PROFILE_PICTURE, String DATE_OF_BIRTH, String GENDER, String HOBBIES, String DEPARTMENT, String YEAR_OF_STUDY) {
        this.ID = ID;
        this.NAME = NAME;
        this.LOCATION = LOCATION;
        this.PROFILE_PICTURE = PROFILE_PICTURE;
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        this.GENDER = GENDER;
        this.HOBBIES = HOBBIES;
        this.DEPARTMENT = DEPARTMENT;
        this.YEAR_OF_STUDY = YEAR_OF_STUDY;
    }

    public String getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public String getPROFILE_PICTURE() {
        return PROFILE_PICTURE;
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public String getGENDER() {
        return GENDER;
    }

    public String getHOBBIES() {
        return HOBBIES;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public String getYEAR_OF_STUDY() {
        return YEAR_OF_STUDY;
    }
}
