package org.tourofheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="org.tourofheroes")
@EntityScan(basePackages = { "org.tourofheroes.dominio" }, basePackageClasses = { Jsr310JpaConverters.class })
@EnableJpaRepositories(basePackages = { "org.tourofheroes.repositorio" })
public class TourofHeroesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourofHeroesApiApplication.class, args);
	}
}
