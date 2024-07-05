package Formcom.example.Taaw3iya.business.services;

import Formcom.example.Taaw3iya.dao.entities.Comment;


import java.util.List;
import java.util.Optional;

public interface ICommentService {
    public Optional<Comment> getComment(Long id);

    public Comment addComment(Comment p);

    public Comment updateComment(Comment p);

    public void deleteComment(Long id);

    public List<Comment> getAllComments();

  public Optional<Comment> gettAllCommentforPost(long idpost);

    public void DeleteAllCommentofpost(Long id);
}
