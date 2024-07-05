package Formcom.example.Taaw3iya.business.serviceslmpl;

import Formcom.example.Taaw3iya.business.services.ITopicService;
import Formcom.example.Taaw3iya.dao.entities.Topic;
import Formcom.example.Taaw3iya.dao.repository.TopicRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TopicServiceImpl implements ITopicService {

@Autowired
private TopicRepository topicRepository;


    public Optional<Topic> getTopic(Long id){
        return topicRepository.findById(id);
    }

    public Topic addTopic(Topic top){
        return topicRepository.save(top);
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


    @Override
    public List<Topic> addTopics(List<String> topicNames) {
        List<Topic> topics = new ArrayList<>();
        for (String topicName : topicNames) {
            Topic topic = Topic.builder().title(topicName).build();
            topics.add(topic);
        }
        return topicRepository.saveAll(topics);
    }

}
