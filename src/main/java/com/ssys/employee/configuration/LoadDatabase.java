package com.ssys.employee.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssys.employee.entity.Employee;
import com.ssys.employee.repository.EmployeeRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  
  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Employee("Anakin Skywalker", "skywalker@ssys.com.br", "Architecture", 4000.00, new Date(983-01-01))));
      log.info("Preloading " + repository.save(new Employee("Obi-Wan Kenobi", "kenobi@ssys.com.br", "Back-End", 3000.00, new Date(1977-01-01))));
      log.info("Preloading " + repository.save(new Employee("Leia Organa", "organa@ssys.com.br", "DevOps", 5000.00, new Date(1988-01-01))));
    };
  }
}