package com.company.app;

import com.company.app.services.AddressLoader;
import com.company.app.services.PersonDataLoader;
import com.company.app.services.SalaryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.el.BeanNameResolver;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {


	@Autowired
	public ApplicationContextProvider applicationContextProvider;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean(name = "PersonDataLoader")
	public PersonDataLoader getPersonDataLoader(){
		PersonDataLoader personDataLoader = new PersonDataLoader();
		return personDataLoader;
	}

	@Bean
	public AddressLoader getAddressLoader(){
		AddressLoader addressLoader = new AddressLoader();
		return addressLoader;
	}

	@Bean
	public SalaryLoader getSalaryLoader(){
		SalaryLoader salaryLoader = new SalaryLoader();
		return salaryLoader;
	}

	@Override
	public void run(String... args) throws Exception {
		PersonDataLoader dataLoader = applicationContextProvider
				.getApplicationContext()
				.getBean("PersonDataLoader",PersonDataLoader.class);

		dataLoader.loadData();
	}
}
