package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "friend", schema = "public")
@IdClass(Friend.Key.class)
public class Friend implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;

	@Id
	@ManyToOne
	@JoinColumn(name="friend_user_id", referencedColumnName = "user_id")
	private Users friend_user;

	@Column
	private String request_state;

	@Column
	private Timestamp accept_time;

	@Column
	private Timestamp request_time;

	public class Key implements Serializable{
		private Users user;
		private Users friend_user;
	}

}
