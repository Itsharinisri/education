package com.genc.project.repositories;
import com.genc.project.entities.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {

    // Find all forum posts by course id

    List<ForumPost> findByCourseId(Long courseId);

    // Find all forum posts by student id

    List<ForumPost> findByStudentId(Long studentId);

    // Optionally, find all unresolved posts for a course

    List<ForumPost> findByCourseIdAndResolvedFalse(Long courseId);

}
 