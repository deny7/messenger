package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users", schema = "public")
public class Users {
	@Id
	@Column
	String user_id;

	@Column
	String user_name;

	@Column
	String password;

	@Column
	Timestamp create_time;

	@Column
	Timestamp pw_update_time;
}
