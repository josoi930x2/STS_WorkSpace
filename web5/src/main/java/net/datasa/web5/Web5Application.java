package net.datasa.web5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//자동으로 날짜가 생성되게 하려면 항상 실행되는 부분에 어노테이션 (1)스프링어플리케이션 (2)엔티티 (3)컬럼
@EnableJpaAuditing
@SpringBootApplication
public class Web5Application {

	public static void main(String[] args) {
		SpringApplication.run(Web5Application.class, args);
	}

}
