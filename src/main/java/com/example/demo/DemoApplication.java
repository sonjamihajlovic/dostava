package com.example.demo;

import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	/* Da bismo testirali repozitorijum, direktno smo pozvali u glavnoj klasi metode,
	   inace bi pozivi bili u npr. nekom od servisa.
	 */

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public void run(String... args) {



	List<Korisnik> korisnici = this.korisnikRepository.findAll();
//		List<Employee> employees = this.employeeRepository.findAllByPositionOrderByFirstName("radnik");
//		List<Employee> employees = this.employeeRepository.findByFirstNameOrLastName("Aleksandar", "Milić");
//		List<Employee> employees = this.employeeRepository.findByFirstNameIgnoreCase("jovanka");
//		List<Employee> employees = this.employeeRepository.findByDepartmentName("Menadžment");


	for (Korisnik e : korisnici){
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
