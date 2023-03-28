package com.simplilearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.entity.Food;



@Repository
public interface FoodRepository extends CrudRepository<Food, Integer>{
	
	@Query(value = "SELECT f FROM Food as f order by f.name")
	List<Food> findByAscendingOrder(List<Food> food);
	
	List<Food> findByname(String name);
	
	List<Food> findAllBycuisine(String selection);

}
