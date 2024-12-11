package com.microservice.car.service;

import com.microservice.car.entity.Car;
import com.microservice.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

   @Autowired
   private CarRepository carRepository;

   public List<Car> getAll(){
       return carRepository.findAll();
   }

   public Car getCarById(int id){
       return carRepository.findById(id).orElseThrow();
   }

   public Car save(Car car){
       Car newCar = carRepository.save(car);
       return newCar;
   }

   public List<Car> byUserId(int userId){
       return carRepository.findByUserId(userId);
   }
}
