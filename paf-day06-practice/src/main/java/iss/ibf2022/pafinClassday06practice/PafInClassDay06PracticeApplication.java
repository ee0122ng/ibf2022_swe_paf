package iss.ibf2022.pafinClassday06practice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iss.ibf2022.pafinClassday06practice.repository.RestaurantRepository;

@SpringBootApplication
public class PafInClassDay06PracticeApplication implements CommandLineRunner {

	@Autowired
	RestaurantRepository restaurantRepo;

	public static void main(String[] args) {
		SpringApplication.run(PafInClassDay06PracticeApplication.class, args);
	}

	@Override
	public void run(String... args) {

		List<String> typeList = restaurantRepo.retrieveUniqueTypeOfFood();
		System.out.println(">>> type of food: " + typeList.toString());
	}

}