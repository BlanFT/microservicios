package com.microservice.user.feingclients;

import com.microservice.user.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-car", url = "http://localhost:8002")
public interface CarClientFeing {

    @PostMapping("/car/new")
    Car save(@RequestBody Car car);

    @GetMapping("/car/by-user/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
