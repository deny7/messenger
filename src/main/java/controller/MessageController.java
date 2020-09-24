package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import repo.MessageRepo;

import java.sql.Timestamp;
import java.util.UUID;


//@CrossOrigin(origins="http://localhost:4201")
@RestController
public class MessageController{

	@Data
	private static class GetMessage{
		String type;
		String user_id;
		UUID topic_id;
		String message;
		Timestamp time;

		private final static ObjectMapper mapper = new ObjectMapper();

		private GetMessage(String message) throws JsonProcessingException {
			GetMessage clazz = mapper.readValue(message, this.getClass());
			this.type = clazz.type;
			this.user_id = clazz.user_id;
			this.topic_id = clazz.topic_id;
			this.message = clazz.message;
			this.time = clazz.time;
		}

		public static GetMessage valueOf(String message) throws JsonProcessingException {
			return new GetMessage(message);
		}
	}

	private final SimpMessagingTemplate template;

	@Autowired
	MessageController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@Autowired
	MessageRepo messageRepo;

	@MessageMapping("/send/message")
	public void sendMessage(String message) throws JsonProcessingException {

		GetMessage saveMessageMap = GetMessage.valueOf(message);
		System.out.println(saveMessageMap);
		Message saveMessage = new Message();
		saveMessage.setUser_id(saveMessageMap.getUser_id());
		saveMessage.setMessage(saveMessageMap.getMessage());
		saveMessage.setTopic_id(saveMessageMap.getTopic_id());
		saveMessage.setSubmit_time(saveMessageMap.getTime());

		messageRepo.save(saveMessage);

		System.out.println("message");
		System.out.println(message);
		this.template.convertAndSend("/message", message);
	}
}
