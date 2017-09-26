package org.tourofheroes.services.rest;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourofheroes.dominio.Hero;
import org.tourofheroes.exception.DataNotFound;
import org.tourofheroes.repositorio.TourofHeroesRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/tourofheroes")
public class TourofHeroesRestController {
	private static final ObjectMapper mapper = new ObjectMapper();
	Logger log = Logger.getLogger(TourofHeroesRestController.class.getName());
	
	//@Autowired
	private TourofHeroesRepository tourofHeroesRepository;
	
	public TourofHeroesRestController(TourofHeroesRepository tourofHeroesRepository){
		this.tourofHeroesRepository = tourofHeroesRepository;
	}

	@RequestMapping("listar")
	public List<Hero> getHeroes(){
		return tourofHeroesRepository.findAll();
	}
	
	@RequestMapping("consultar")
	public Hero getHero(int id) throws DataNotFound{
		log.info("Entro a consultar");
		Hero hero = tourofHeroesRepository.findById(id);
		if(hero == null){
			throw new DataNotFound("No existe hero");
		}
		return hero;
	}
	
	
	@RequestMapping("buscar")
	public String getHeroe(String name) throws DataNotFound, JsonProcessingException{
		log.info("Entro a consultar");
		List<Hero> hero = tourofHeroesRepository.findByNameContainingOrderByName(name);
		if(hero == null){
			throw new DataNotFound("No existe hero");
		}
		//return hero.get();
		return mapper.writeValueAsString(hero);
	}
		
	
	@RequestMapping("crear")
	 public String addHero(String name)throws JsonProcessingException{
	  if (name != null) {
	            Hero hero = new Hero(name);
	            tourofHeroesRepository.save(hero);
	            return mapper.writeValueAsString(hero);
	        } 
	  return null;
	 }
	
	
	@RequestMapping("actualizar")
	 public String save(int id,String name) throws JsonProcessingException {
	     if (name != null) {
	     Hero hero = tourofHeroesRepository.findById(id);
	     hero.setName(name);
	     tourofHeroesRepository.save(hero);
	     return mapper.writeValueAsString(hero);
	     }
	     throw new IllegalStateException("NOT_FOUND");
	     }
	
	@RequestMapping("borrar")
	  public void delete(int id) throws JsonProcessingException {
	  log.info("Entro a borrar");
	     Hero hero=tourofHeroesRepository.findById(id);
	     tourofHeroesRepository.delete(hero);
	     //return mapper.writeValueAsString(id);  
	 }
	
}
