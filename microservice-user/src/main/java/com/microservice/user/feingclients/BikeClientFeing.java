package com.microservice.user.feingclients;

import com.microservice.user.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeClientFeing {

    @PostMapping("/bike/new")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/by-user/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
