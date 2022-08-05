package com.io.leonmbano.fullstarkproject;

import com.io.leonmbano.fullstarkproject.domain.Server;
import com.io.leonmbano.fullstarkproject.enumaration.Status;
import com.io.leonmbano.fullstarkproject.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullStarkProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStarkProjectApplication.class, args);
	}
@Bean
CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.168","Ubuntu Linux","32 GB","Work PC",
					"http://localhost:8080/server/image/server1.jpg", Status.SERVER_UP));
			serverRepository.save(new Server(null, "11.14.1.3","Ubuntu Linux","16 GB","Personal PC",
					"http://localhost:8080/server/image/server2.jpg", Status.SERVER_UP));
			serverRepository.save(new Server(null, "122.678.34.12","Mac IO","64 GB","Workstation PC",
					"http://localhost:8080/server/image/server3.jpg", Status.SERVER_UP));

			serverRepository.save(new Server(null, "134.123.12.10","Windows","32 GB","Gaming PC",
					"http://localhost:8080/server/image/server4.jpg", Status.SERVER_UP));


		};

}


}
