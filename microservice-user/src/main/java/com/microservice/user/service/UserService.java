package com.microservice.user.service;

import com.microservice.user.entity.User;
import com.microservice.user.feingclients.BikeClientFeing;
import com.microservice.user.feingclients.CarClientFeing;
import com.microservice.user.model.Bike;
import com.microservice.user.model.Car;
import com.microservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarClientFeing carClientFeing;

    @Autowired
    private BikeClientFeing bikeClientFeing;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user) {
        User newUser = userRepository.save(user);

        return newUser;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://car-service/car/by-user/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/by-user/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car newCar = carClientFeing.save(car);
        return newCar;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike newBike = bikeClientFeing.save(bike);
        return newBike;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "No existe el usuario");
            return result;
        }

        result.put("User", user);

        List<Car> cars = carClientFeing.getCars(userId);
        if (cars.isEmpty()) {
            result.put("Cars", "El usuario no tiene carros");
        } else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeClientFeing.getBikes(userId);
        if (bikes.isEmpty()) {
            result.put("Bikers", "El usuario no tiene motos");
        } else {
            result.put("Bikers", bikes);
        }

        return result;
    }


}
