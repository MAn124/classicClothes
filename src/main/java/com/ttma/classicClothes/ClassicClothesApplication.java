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
public class ClassicClothesApplication {
	private  final UserRepository userRespository;
	public static void main(String[] args) {
		SpringApplication.run(ClassicClothesApplication.class, args);
	}


}
