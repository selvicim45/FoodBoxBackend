package com.simplilearn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.Food;

import com.simplilearn.repository.FoodRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class FoodController {
	
	@Autowired
	public FoodRepository foodRepository;
	
	//Method to Display All Food Items
	@GetMapping("/food")
	public List<Food> displayAllFood()
	{
		System.out.println("Iam inside this method") ;
		List<Food> food = new ArrayList<>();		
		foodRepository.findAll().forEach(f -> food.add(f));
		return foodRepository.findByAscendingOrder(food);
	}
	
	//Method to Delete Food Item
	@DeleteMapping("/food/{id}")
	public void deleteFoodFromDatabase(@PathVariable Integer id)
	{
		foodRepository.deleteById(id);
		
	}
	
	//Method to Add a new FoodItem by Admin
	@PostMapping("/food")
	public Food addNewFoodItem(@RequestBody Food foodData)
	{
		return foodRepository.save(foodData);
	}
	
	//Method to get Food from Database By ID
		@GetMapping("/food/{id}")
		public Food displayById(@PathVariable Integer id) throws NotFoundException
		{
			
			return foodRepository.findById(id).orElseThrow(NotFoundException::new);
		}
		
   //Method to update food item in the database
		@PutMapping("/food/{id}")
		public ResponseEntity<Food> updateFood(@PathVariable Integer id,@RequestBody Food foodData) throws NotFoundException
		{
			System.out.println("Inside update XXXXXXXXXXXXXXX"); 
			String name= foodData.getName();
			String cuisine =foodData.getCuisine();
			String description=foodData.getDescription();
			boolean enable = foodData.isEnable();
			String offers=foodData.getOffers();
			long price = foodData.getPrice();
			
			Food foodFromDB  = foodRepository.findById(id).orElseThrow(NotFoundException::new);
			
			foodFromDB.setName(name);
			foodFromDB.setCuisine(cuisine);
			foodFromDB.setDescription(description);
			foodFromDB.isEnable();
			foodFromDB.setOffers(offers);
			foodFromDB.setPrice(price);
			
			 Food updatedFood =  foodRepository.save(foodFromDB);
			 return ResponseEntity.ok(updatedFood);
		}
		

	//To find Food based on search by cuisine
		@GetMapping("/food/selection/{selection}")
		public List<Food> displayBySelection(@PathVariable String selection)
		{ 
			System.out.println("I am called");
			System.out.println("The selection variable is "+ selection);
			
			return foodRepository.findAllBycuisine(selection);
			
		}
		
}
