package com.genc.project.controllers;

import com.genc.project.dto.ForumReplyRequest;
import com.genc.project.entities.ForumReply;
import com.genc.project.services.ForumReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum-replies")
public class ForumReplyController {

    private final ForumReplyService forumReplyService;

    @Autowired
    public ForumReplyController(ForumReplyService forumReplyService) {
        this.forumReplyService = forumReplyService;
    }

    // Create a new forum reply
    @PostMapping
    public ResponseEntity<ForumReply> createReply(@RequestBody ForumReplyRequest request) {
        ForumReply createdReply = forumReplyService.createForumReply(request);
        return ResponseEntity.ok(createdReply);
    }

    // Get replies for a specific forum post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ForumReply>> getRepliesForPost(@PathVariable Long postId) {
        List<ForumReply> replies = forumReplyService.getRepliesByForumPostId(postId);
        return ResponseEntity.ok(replies);
    }
}