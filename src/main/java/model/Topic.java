package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "topic", schema = "public")
public class Topic {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID topic_id;

	@Column
	String topic_name;

	@ManyToOne
	@JoinColumn(name = "create_user_id", referencedColumnName = "user_id")
	Users create_user;

	@Column
	Timestamp create_time;
}
