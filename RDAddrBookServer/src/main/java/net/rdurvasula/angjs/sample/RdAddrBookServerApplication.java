package net.rdurvasula.angjs.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RdAddrBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdAddrBookServerApplication.class, args);
	}

}

