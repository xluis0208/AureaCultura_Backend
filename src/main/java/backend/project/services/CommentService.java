package backend.project.services;

import backend.project.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment insertComment(Comment comment);
    Comment insertComment(String commentType, String description, Long clientId);
    void deleteComment(Long id);
    List<Comment> listAllComments();
    Comment findById(Long id);
    List<Comment> findByCommentType(String type);
}
