package ru.otus.l51.tests;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public class Education {
    private int numLessons = 20;
    private String courseName = "java";

    public Education(){

    }

    public Education(int numLessons){
        this.numLessons = numLessons;
    }

    public Education(int numLessons, String courseName) {
        this.numLessons = numLessons;
        this.courseName = courseName;
    }

    public int getNumLessons() {
        return numLessons;
    }

    public void setNumLessons(int numLessons) {
        this.numLessons = numLessons;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
