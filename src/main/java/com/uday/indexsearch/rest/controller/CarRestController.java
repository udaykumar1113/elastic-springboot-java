package com.uday.indexsearch.rest.controller;

import com.uday.indexsearch.repository.CarElasticRepository;
import com.uday.indexsearch.rest.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car/v1")
public class CarRestController {

    @Autowired
    private CarElasticRepository carElasticRepository;

    @GetMapping(path = "/count")
    public long getCarCount(){
        return carElasticRepository.count();
    }

    @PostMapping(path = "/car", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createCar(@RequestBody Car car){
        return carElasticRepository.save(car);
    }

    @PutMapping(path = "/car/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car updateCar(@RequestBody Car car, @PathVariable String id){
        car.setId(id);
        return carElasticRepository.save(car);
    }

    @GetMapping(path = "/cars/{brand}/{color}")
    public List<Car> findCarbyColorBrand(@PathVariable String brand, @PathVariable String color){
        return carElasticRepository.findByBrandAndColor(brand,color);
    }

    @GetMapping(path = "/cars")
    public List<Car> findCarsByParam(@RequestParam String brand, @RequestParam String color){
        return carElasticRepository.findByBrandAndColor(brand, color);
    }

    @GetMapping(path = "/carscolor")
    public List<Car> findCarbyTypeBrand(@RequestParam String color, @RequestParam String brand){
        System.out.println("Inside request method "+color+" "+brand);
        return carElasticRepository.findByColor(color);
    }

    @GetMapping(path = "/carscolorbrand")
    public List<Car> findCarbyTypeBrandColor(@RequestParam String color, @RequestParam String brand){
        System.out.println("Inside request method "+color+" "+brand);
        return carElasticRepository.findByColorAndBrand(color,brand);
    }
}
