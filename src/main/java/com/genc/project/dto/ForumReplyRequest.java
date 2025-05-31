package com.genc.project.dto;

public class ForumReplyRequest {

    private Long studentId;
    private Long postId; // The ID of the ForumPost this reply is for
    private String content;

    // Constructors
    public ForumReplyRequest() {}

    public ForumReplyRequest(Long studentId, Long postId, String content) {
        this.studentId = studentId;
        this.postId = postId;
        this.content = content;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}