package Formcom.example.Taaw3iya.business.services;

import Formcom.example.Taaw3iya.dao.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicService {

    public Optional<Topic> getTopic(Long id);

    public Topic addTopic(Topic name);

    public Topic updateTopic(Topic p);

    public void deleteTopic(Long id);

    public List<Topic> getAllTopics();

    public List<Topic> addTopics(List<String> topicNames);

}
