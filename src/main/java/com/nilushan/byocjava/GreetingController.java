package com.nilushan.byocjava;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.logging.*;

@RestController
public class GreetingController {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/srvc/success")
    public String success(){
        //System.out.println("Received a request to /success");
        LOGGER.log(Level.INFO, "Received a request to /success");
        return "Success";
    }

    @GetMapping("/srvc/failure")
    public String failure(){
        //System.out.println("Received a request to /failure");
        LOGGER.log(Level.INFO, "Received a request to /failure");
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
    }
}
