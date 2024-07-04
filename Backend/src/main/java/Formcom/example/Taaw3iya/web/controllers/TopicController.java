package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.serviceslmpl.TopicServiceImpl;
import Formcom.example.Taaw3iya.dao.entities.Topic;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
