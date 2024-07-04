package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.serviceslmpl.TopicServiceImpl;
import Formcom.example.Taaw3iya.dao.entities.Comment;
import Formcom.example.Taaw3iya.dao.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RequestMapping("/api/topic")
@RestController
public class TopicController {

    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
  private final  TopicServiceImpl topicService;
  static Long idtopic=0L;
  public TopicController(TopicServiceImpl topicService) {
      this.topicService = topicService;
    

  }

    @PostMapping
    public ResponseEntity<Topic> addTopic(@RequestBody String topicName) {
      return new ResponseEntity<>(topicService.addTopic(topicName), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics() {
      return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping(value="getopic/{id}")
    public ResponseEntity<Topic> getTopicbyid(@PathVariable("id")Long id)  {
        return new ResponseEntity<Topic>(topicService.getTopic(id).get(), HttpStatus.OK);
    }

  @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deletetopic(@PathVariable("id")Long id) {
        Optional<Topic> topic=topicService.getTopic(id);
        if(topic.isPresent()){
          topicService.deleteTopic(id);

            return new ResponseEntity<>("topic deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed: topic not found",HttpStatus.NOT_FOUND);
        }
    }
}
