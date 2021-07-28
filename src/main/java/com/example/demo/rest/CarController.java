package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@RequestMapping("/api")
@RestController
public class CarController {
	
	private CarRepository carRepository;
	public CarController(CarRepository carRepository) {// spring inyecta dependencia
		this.carRepository = carRepository;
	}
	/**
	 * http://localhost:8080/api/cars/1
	 * @return
	 */
	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	
	// find one
	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> findOne(@PathVariable("id") Long id){
		log.info("REST request to find one car");
		Optional<Car> carOpt = this.carRepository.findById(id);
		if(carOpt.isPresent())
			return ResponseEntity.ok(carOpt.get());
		return ResponseEntity.notFound().build();
		//Car car1 = new Car(id,"Ford","Mondeo",2.0,3);
		//return ResponseEntity.ok(car1);
	}
	
	// find all
	/**
	 * http://localhost:8080/api/cars
	 * @return
	 */
	@GetMapping("/cars")
	public List<Car> findAll(){
		log.info("REST request to find all cars");
//		Car car1 = new Car(1L,"Ford","Mondeo",1.5,5);
//		Car car2 = new Car(2L,"Toyota","Prius",1.5,5);
//		return List.of(car1,car2);
		
		return this.carRepository.findAll();
				
				
	}
	
	// create one
	/**
	 * http://localhost:8080/api/cars
	 * @return
	 */
	@PostMapping("/cars")
	public ResponseEntity<Car> create(@RequestBody Car car){
		log.info("REST request to create new car");
		if(car.getId() != null) {// quiere decir que ya existe
			log.warn("Trying to create a new car with exixtent id");
			return ResponseEntity.badRequest().build();
		}
		//Car car1 = new Car(5L,"Ford","Mondeo",0.5,6);
		
		//return ResponseEntity.ok(new Car(5L,"Ford","Mondeo",0.5,6));
		
//		car.setId(3L);// simular creacion de id
//		return ResponseEntity.ok(car);
		return ResponseEntity.ok(this.carRepository.save(car));
		
	}
	
	// update
	/**
	 * http://localhost:8080/api/cars
	 * @return
	 */
	@PutMapping("/cars")
	public ResponseEntity<Car> update(@RequestBody Car car){
		log.info("REST request to update an existing  car");
		if(car.getId() == null) {// quiere decir que no existe a actualizar porque no existe id
			log.warn("Trying to update a  car do not exixtent id");
			return ResponseEntity.badRequest().build();
		}
			//simular actualizacion
//		car.setManufacturer(car.getManufacturer() + " Editado");
//		return ResponseEntity.ok(car);
		return ResponseEntity.ok(this.carRepository.save(car));
	}
	
	
	// delete one
	/**
	 * http://localhost:8080/api/cars/1
	 * @return
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Car> deleteOne(@PathVariable("id") Long id){
		log.info("REST request to delete an existing car");
		
		this.carRepository.deleteById(id);
		//simular borra de bbdd
		//deletebyId(id)
		
		return ResponseEntity.noContent().build();// nos dice que se ha borrado	
	}
	
	// delete all
	/**
	 * http://localhost:8080/api/cars
	 * @return
	 */
	@DeleteMapping("/cars")
	public ResponseEntity<Car> deleteAll(){
		log.info("REST request to delete an existing car");
		
		//simular borra de bbdd
		//deleteAll
		this.carRepository.deleteAll();
		return ResponseEntity.noContent().build();// nos dice que se ha borrado	
	}
	

}
