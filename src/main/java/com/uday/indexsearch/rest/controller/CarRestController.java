package com.uday.indexsearch.rest.controller;

import com.uday.indexsearch.repository.CarElasticRepository;
import com.uday.indexsearch.rest.domain.Car;
import com.uday.indexsearch.rest.domain.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> findCarbyColorBrand(@PathVariable String brand, @PathVariable String color){
        HttpHeaders headers=new HttpHeaders();
        if(StringUtils.isNumeric(color)){
            ErrorResponse errorResponse=new ErrorResponse("Invalid color", System.currentTimeMillis());
            headers.set("Error","Color is passed as numeric");
            return new ResponseEntity<>(errorResponse, headers,HttpStatus.BAD_REQUEST);
        }
        headers.set("Success","Request executed successfully");
        List<Car> car= carElasticRepository.findByBrandAndColor(brand,color);
        return ResponseEntity.ok().headers(headers).body(car);
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

    @GetMapping(path = "/carspage")
    public List<Car> findCarByType(@RequestParam String type,
                                   @RequestParam(defaultValue = "0") int start,
                                   @RequestParam(defaultValue = "3") int size){
        Pageable pageable= PageRequest.of(start,size, Sort.by(Sort.Direction.DESC,"price"));
        return carElasticRepository.findByType(type,pageable).getContent();
    }
}
