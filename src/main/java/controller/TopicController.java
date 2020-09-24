package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Topic;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repo.TopicRepo;
import repo.UserRepo;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TopicController{

	@Autowired
	TopicRepo topicRepo;

	@Autowired
	UserRepo userRepo;

	@Data
	@NoArgsConstructor
	private static class TopicBody{
		private String topic_name;
		private String create_user_id;
	}

	@GetMapping("/topics/{user_id}")
	private List<Topic> getTopicsById(@PathVariable("user_id") String user_id){
		System.out.println(topicRepo.getTopicsByUserId(user_id));
		return topicRepo.getTopicsByUserId(user_id);
	}

	@PostMapping("/topic")
	public List<Topic> create(@RequestBody TopicBody topicBody){
		Users create_user = userRepo.findById(topicBody.getCreate_user_id()).get();
		String topic_name = topicBody.getTopic_name();

		Topic topic = new Topic();
		topic.setTopic_name(topic_name);
		topic.setCreate_user(create_user);

		topicRepo.save(topic);
		return getTopicsById(create_user.getUser_id());
	}

	@PostMapping("/topic/delete")
	public List<Topic> delete(@RequestBody Topic topic){
		topicRepo.delete(topic);
		return getTopicsById(topic.getCreate_user().getUser_id());
	}
}
