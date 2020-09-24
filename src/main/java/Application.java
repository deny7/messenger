import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "config"})
@EnableJpaRepositories(basePackages = {"repo"})
@EntityScan("model")
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) throws Exception {
//		JschConnector.connectSSH();
		SpringApplication.run(Application.class, args);
	}
}
