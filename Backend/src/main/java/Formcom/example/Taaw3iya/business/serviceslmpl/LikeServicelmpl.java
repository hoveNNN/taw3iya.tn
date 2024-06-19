package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.ILikeService;
import Formcom.example.Taaw3iya.dao.entities.Like;
import Formcom.example.Taaw3iya.dao.repository.LikeRepository;
import Formcom.example.Taaw3iya.dao.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class LikeServicelmpl implements ILikeService {

    @Autowired
    LikeRepository likerepository;
    @Override
    public Optional<Like> getLike(Long id){
        return likerepository.findById(id);
    }
    @Override
    public Like addLike(Like p){
        return likerepository.save(p);
    }
    @Override
    public Like updateLike(Like p){
        return likerepository.save(p);
    }
    @Override
    public void deleteLike(Long p){
        likerepository.deleteById(p);
    }
    @Override
    public List<Like> getAllLikes(){
        return  likerepository.findAll();
    }
}

