package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Friend;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repo.FriendRepo;
import repo.UserRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend")
public class FriendController {

	@Data
	@NoArgsConstructor
	private static class FriendForm{
		private String user_id;
		private String friend_user_name;
		private Timestamp request_time;
	}

	@Autowired
	FriendRepo friendRepo;

	@Autowired
	UserRepo userRepo;

	@PostMapping("/request")
	public List<Friend> request(@RequestBody FriendForm friendForm){
		List<Users> friend_user = userRepo.findByUserName(friendForm.getFriend_user_name());
		if(friend_user.isEmpty()) return new ArrayList<>();
		System.out.println(friendForm);
		Users user = userRepo.findById(friendForm.getUser_id()).get();

		Friend friend = new Friend();
		friend.setUser(user);
		friend.setFriend_user(friend_user.get(0));
		friend.setRequest_state("REQUEST");
		friend.setRequest_time(friendForm.getRequest_time());
		friendRepo.save(friend);

		return friendRepo.findByUserId(friendForm.getUser_id());
	}
}
