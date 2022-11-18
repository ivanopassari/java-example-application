package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);
    @Value("${app.instance-id}")
    String instanceId;

    @Autowired(required = false)
    UserRepository userRepository;

    @RequestMapping(value = "/app/id", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String createSubscription() {
        logger.info("Hello from instance: " +instanceId);
        return "Hello from instance: " +instanceId;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    void createUser(@RequestBody User user) {
        logger.info("Hello from instance: " +instanceId);
        userRepository.save(user);
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Optional<User> getUser(@PathVariable Integer id) {
        logger.info("Hello from instance: " +instanceId);
       return userRepository.findById(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    void deleteUser(@PathVariable Integer id) {
        logger.info("Hello from instance: " +instanceId);
       if(userRepository.findById(id).isPresent())
           userRepository.deleteById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Iterable<User> getUser() {
        logger.info("Hello from instance: " +instanceId);
       return userRepository.findAll();
    }
}
