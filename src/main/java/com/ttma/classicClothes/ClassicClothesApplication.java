package com.ttma.classicClothes;

import com.ttma.classicClothes.enums.RoleEnum;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ClassicClothesApplication implements CommandLineRunner {
	private  final UserRepository userRespository;
	public static void main(String[] args) {
		SpringApplication.run(ClassicClothesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.warn("hi");
		User user = new User("minh","an","an@gmail.com","minhan","$2a$12$lDPef2d6AFHb/8zNCYoHsegBRvN/CsFGt7O0xVGE2//X62UdxpUdK", RoleEnum.ADMIN,null,null);
		userRespository.save(user);
	}
}
