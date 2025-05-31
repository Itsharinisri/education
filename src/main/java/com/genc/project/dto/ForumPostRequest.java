package com.genc.project.dto;

public class ForumPostRequest {

    private Long studentId;

    private Long courseId;

    private String title;

    private String content;

    // Constructors (optional)

    public ForumPostRequest() {}

    public ForumPostRequest(Long studentId, Long courseId, String title, String content) {

        this.studentId = studentId;

        this.courseId = courseId;

        this.title = title;

        this.content = content;

    }

    // Getters and Setters

    public Long getStudentId() {

        return studentId;

    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;

    }

    public Long getCourseId() {

        return courseId;

    }

    public void setCourseId(Long courseId) {

        this.courseId = courseId;

    }

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public String getContent() {

        return content;

    }

    public void setContent(String content) {

        this.content = content;

    }

}
 