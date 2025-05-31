package com.genc.project.services;

import com.genc.project.dto.ForumReplyRequest;
import com.genc.project.entities.ForumPost;
import com.genc.project.entities.ForumReply;
import com.genc.project.entities.User; // Ensure User entity is imported
import com.genc.project.repositories.ForumPostRepository;
import com.genc.project.repositories.ForumReplyRepository;
import com.genc.project.repositories.UserRepository; // Ensure UserRepository is imported
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumReplyService {

    private final ForumReplyRepository forumReplyRepository;
    private final UserRepository userRepository;
    private final ForumPostRepository forumPostRepository;

    @Autowired
    public ForumReplyService(ForumReplyRepository forumReplyRepository,
                             UserRepository userRepository,
                             ForumPostRepository forumPostRepository) {
        this.forumReplyRepository = forumReplyRepository;
        this.userRepository = userRepository;
        this.forumPostRepository = forumPostRepository;
    }

    public ForumReply createForumReply(ForumReplyRequest request) {
        // Find the student (user) who is replying
        // IMPORTANT: Convert Long from DTO to Integer if your User entity's ID is Integer
        User student = userRepository.findById(request.getStudentId().intValue()) // <--- CORRECTED LINE HERE
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + request.getStudentId()));

        // Find the forum post to which this reply belongs
        ForumPost forumPost = forumPostRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Forum post not found with ID: " + request.getPostId()));

        ForumReply reply = new ForumReply();
        reply.setContent(request.getContent());
        reply.setStudent(student);
        reply.setForumPost(forumPost);

        return forumReplyRepository.save(reply);
    }

    public List<ForumReply> getRepliesByForumPostId(Long forumPostId) {
        return forumReplyRepository.findByForumPost_Id(forumPostId);
    }
}