package controller;

import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repo.UserRepo;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UsersController{
	@Autowired
	UserRepo userRepo;

//	암호화 logic을 통한 로그인 필요
	@PostMapping("/login")
	public boolean login(@RequestBody Users loginInfo){
		Users user = userRepo.findById(loginInfo.getUser_id()).get();
		return loginInfo.getPassword().equals(user.getPassword());
	}

	@PostMapping("/signed")
	public boolean isSigned(@RequestBody Map<String, Object> userId){
		System.out.println(userId);
		return userRepo.findById((String)userId.get("user_id")).isPresent();
	}

	@GetMapping("/all")
	public List<Users> getUser(){
		return userRepo.findAll();
	}

	@PostMapping("/create")
	public void createUser(@RequestBody Users createInfo) throws GeneralSecurityException, UnsupportedEncodingException {
		userRepo.save(createInfo);
	}

	@PutMapping("/update")
	public void updateUser(@RequestBody Users updateInfo){
		userRepo.save(updateInfo);
	}

	@PutMapping("/delete")
	public void deleteUser(@RequestBody Users deleteInfo){
		userRepo.deleteById(deleteInfo.getUser_id());
	}
}
