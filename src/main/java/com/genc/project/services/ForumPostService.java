package com.genc.project.services;

import com.genc.project.dto.ForumPostRequest;
import com.genc.project.entities.Course;
import com.genc.project.entities.ForumPost;
import com.genc.project.entities.User;
import com.genc.project.repositories.CourseRepository;
import com.genc.project.repositories.ForumPostRepository;
import com.genc.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostService {

    private final ForumPostRepository forumPostRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ForumPostService(ForumPostRepository forumPostRepository,
                            UserRepository userRepository,
                            CourseRepository courseRepository) {
        this.forumPostRepository = forumPostRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public ForumPost createForumPost(ForumPostRequest request) {
        User student = userRepository.findById(request.getStudentId().intValue())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId().intValue())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + request.getCourseId()));

        ForumPost forumPost = new ForumPost();
        forumPost.setTitle(request.getTitle());
        forumPost.setContent(request.getContent());
        forumPost.setStudent(student);
        forumPost.setCourse(course);

        return forumPostRepository.save(forumPost);
    }

    @Transactional(readOnly = true)
    public List<ForumPost> getForumPostsByCourseId(Long courseId) {
        // CORRECTED LINE: Using findByCourseId as defined in your ForumPostRepository
        return forumPostRepository.findByCourseId(courseId);
    }

    @Transactional(readOnly = true)
    public List<ForumPost> getForumPostsByStudentId(Long studentId) {
        return forumPostRepository.findByStudentId(studentId);
    }

    @Transactional
    public ForumPost markPostResolved(Long postId, Long resolvedById) {
        Optional<ForumPost> optionalPost = forumPostRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Forum Post not found with ID: " + postId);
        }

        ForumPost forumPost = optionalPost.get();
        if (forumPost.isResolved()) {
            return forumPost;
        }

        User resolvedBy = userRepository.findById(resolvedById.intValue())
                .orElseThrow(() -> new RuntimeException("User (resolver) not found with ID: " + resolvedById));

        forumPost.setResolved(true);
        forumPost.setResolvedBy(resolvedBy);

        return forumPostRepository.save(forumPost);
    }

    @Transactional(readOnly = true)
    public Optional<ForumPost> getForumPostById(Long postId) {
        return forumPostRepository.findById(postId);
    }
}