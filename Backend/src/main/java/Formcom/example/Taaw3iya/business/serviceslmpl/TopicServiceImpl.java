package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.ITopicService;
import Formcom.example.Taaw3iya.dao.entities.Topic;
import Formcom.example.Taaw3iya.dao.repository.TopicRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TopicServiceImpl implements ITopicService {

private TopicRepository topicRepository;
    public Optional<Topic> getTopic(Long id){
        return topicRepository.findById(id);
    }

    public Topic addTopic(Topic p){
        return topicRepository.save(p);
    }

    public Topic updateTopic(Topic p){
        return topicRepository.save(p);
    }

    public void deleteTopic(Long id){
         topicRepository.deleteById(id);
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

//
//    public void init() {
//
//
//            Long idtopic = 0L;
//            Topic topicSport = new Topic(idtopic++, "topicSport", null);
//            Topic topicSonter = new Topic(idtopic++, "topicSonter", null);
//            Topic topicPolitique = new Topic(idtopic++, "topicPolitique", null);
//            Topic topicIndustrie = new Topic(idtopic++, "topicIndustrie", null);
//
//            this.addTopic(topicSport);
//            this.addTopic(topicSonter);
//            this.addTopic(topicPolitique);
//            this.addTopic(topicIndustrie);
//
//    }
}
