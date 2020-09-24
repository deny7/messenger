package controller;

import model.Message;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repo.MessageRepo;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MessagesController {

	@Autowired
	MessageRepo messageRepo;

	@PostMapping("/get/message/topic_id")
	public List<Message> getMessageById(@RequestBody Topic topic){
		List<Message> list = messageRepo.getMessageByTopicId(topic.getTopic_id());
		return messageRepo.getMessageByTopicId(topic.getTopic_id());
	}
}
