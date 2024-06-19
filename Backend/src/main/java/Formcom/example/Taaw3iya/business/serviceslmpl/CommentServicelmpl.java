package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.ICommentService;
import Formcom.example.Taaw3iya.dao.entities.Comment;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.repository.CommentRepository;
import Formcom.example.Taaw3iya.dao.repository.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CommentServicelmpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;
    @Override
    public Optional<Comment> getComment(Long id){
        return commentRepository.findById(id);
    }
    @Override
    public Comment addComment(Comment p){
        return commentRepository.save(p);
    }
    @Override
    public Comment updateComment(Comment p){
        return commentRepository.save(p);
    }
    @Override
    public void deleteComment(Long p){
        commentRepository.deleteById(p);
    }
    @Override
    public List<Comment> getAllComments(){
        return  commentRepository.findAll();
    }
//    @Override
//    public List<Comment> gettAllCommentforPost(long idpost){
//
//        return commentRepository.findAll(Sort.by(idpost));
//    }
}
