package Formcom.example.Taaw3iya.business.services;

import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.entities.Post;

import java.util.List;
import java.util.Optional;

public interface ILikeService {
    public Optional<Like> getLike(Long id);

    public Like addLike(Like p);

    public Like updateLike(Like p);

    public void deleteLike(Long id);

    public List<Like> getAllLikes();

    public Boolean userAlreadylikethepost(Long iduser, Long idpost);

    public Long getIdOfLikeByuUserandPost(Long iduser ,Long idpost);

    public void DeleteAllLikeofpost(Long id);
}
