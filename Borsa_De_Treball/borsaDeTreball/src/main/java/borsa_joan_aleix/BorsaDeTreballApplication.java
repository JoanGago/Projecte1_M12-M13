package borsa_joan_aleix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("borsa_joan_aleix.repository")
@EntityScan("borsa_joan_aleix")
@SpringBootApplication
//@ComponentScan(basePackages = {"Service", "borsa_joan_aleix.security"})
public class BorsaDeTreballApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorsaDeTreballApplication.class, args);
	}

}
