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
public class ClassicClothesApplication implements CommandLineRunner{
	private  final UserRepository userRespository;
	public static void main(String[] args) {
		SpringApplication.run(ClassicClothesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		User user = new User("minh","an","admin@gmail.com","minhan","$2a$12$eln8KLoNcnjK8F5RpBFlduzSfu0qokNEa1bFQgI4a5LziqqX7YHHm",RoleEnum.ADMIN,null,null,null);
//		userRespository.save(user);
		log.warn("Ok");
	}
}
