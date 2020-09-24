package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "message", schema = "public")
public class Message {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID message_id;

	@Column
	UUID topic_id;

	@Column
	String user_id;

	@Column
	String message;

	@Column
	Timestamp submit_time;
}
