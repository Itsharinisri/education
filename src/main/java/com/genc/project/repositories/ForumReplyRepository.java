package com.genc.project.repositories;

import com.genc.project.entities.ForumReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {

    // Find all replies for a given forum post ID
    List<ForumReply> findByForumPost_Id(Long forumPostId);
}
