package backend.project.servicesImplemetation;

import backend.project.entities.Client;
import backend.project.entities.Comment;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.repositories.CommentRepository;
import backend.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ClientRepository clienterepository;

    @Override
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment insertComment(String commentType, String description, Long clientId) {

        Client clientaux = new Client();
        clientaux = clienterepository.findByUserId(clientId);

        Comment comment = new Comment();
        comment.setCommentType(commentType);
        comment.setDescription(description);
        comment.setClient(clientaux);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + id));
    }

    @Override
    public List<Comment> findByCommentType(String type) {
        return commentRepository.findByCommentType(type);
    }
}
