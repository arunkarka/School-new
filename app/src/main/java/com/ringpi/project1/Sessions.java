package com.ringpi.project1;

public class Sessions {
    private String currentTeacher;
    private String classname;
    private String subject;


    private Sessions(){

    }
    private Sessions(String currentTeacher,String classname,String subject){
   this.currentTeacher=currentTeacher;
   this.classname=classname;
   this.subject=subject;
    }

    public String getCurrentTeacher() {
        return currentTeacher;
    }


    public void setCurrentTeacher(String currentTeacher) {
        this.currentTeacher = currentTeacher;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
