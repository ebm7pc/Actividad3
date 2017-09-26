package org.tourofheroes.repositorio;

import org.tourofheroes.dominio.Hero;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TourofHeroesRepository extends JpaRepository<Hero, String>{

	public Hero findById(int id);
	public Optional<Hero> findByName(String name);
	public List<Hero> findByNameContainingOrderByName(String name);

}
