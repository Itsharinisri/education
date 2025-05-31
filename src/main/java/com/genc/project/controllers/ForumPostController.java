package com.genc.project.controllers;

import com.genc.project.entities.ForumPost;
import com.genc.project.services.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum-posts")
public class ForumPostController {

    private final ForumPostService forumPostService;

    @Autowired
    public ForumPostController(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    // You likely have a @PostMapping for creating posts here already.
    // For example:
    // @PostMapping
    // public ResponseEntity<ForumPost> createPost(@RequestBody ForumPostRequest request) {
    //     ForumPost newPost = forumPostService.createForumPost(request);
    //     return ResponseEntity.ok(newPost);
    // }

    // Get posts by course ID
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ForumPost>> getPostsByCourse(@PathVariable Long courseId) {
        // Corrected method name to match service
        List<ForumPost> posts = forumPostService.getForumPostsByCourseId(courseId);
        return ResponseEntity.ok(posts);
    }

    // Get posts by student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ForumPost>> getPostsByStudent(@PathVariable Long studentId) {
        // Corrected method name to match service
        List<ForumPost> posts = forumPostService.getForumPostsByStudentId(studentId);
        return ResponseEntity.ok(posts);
    }

    // Mark a forum post as resolved
    // This endpoint now expects the ID of the user who is resolving the post.
    // In a real application, resolvedById would usually come from the authenticated user's session
    // (e.g., via Spring Security's Principal or @AuthenticationPrincipal).
    // For demonstration, we'll accept it as a request parameter.
    @PutMapping("/{postId}/resolve")
    public ResponseEntity<ForumPost> resolvePost(
            @PathVariable Long postId,
            @RequestParam Long resolvedById) { // <--- Added resolvedById as a RequestParam
        ForumPost resolvedPost = forumPostService.markPostResolved(postId, resolvedById);
        return ResponseEntity.ok(resolvedPost);
    }
}