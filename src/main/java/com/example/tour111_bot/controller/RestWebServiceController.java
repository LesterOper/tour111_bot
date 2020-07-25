package com.example.tour111_bot.controller;

import com.example.tour111_bot.restModel.City;
import com.example.tour111_bot.sql.SQLSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestWebServiceController {

    private SQLSupport sqlSupport;

    public RestWebServiceController(SQLSupport sqlSupport) {
        this.sqlSupport = sqlSupport;
    }

    @PutMapping(value = "/updateInfo")
    public ResponseEntity<?> updateCityInfo(@RequestBody City city) {
        boolean check = sqlSupport.updateInfoOfCity(city);
        if(check) {
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Check your request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewCity(@RequestBody City city) {
        boolean check = sqlSupport.addCity(city);
        if(check) {
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Check your request or such city has already existed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteCity(@RequestParam(value = "city") String city) {
        boolean check = sqlSupport.deleteCity(city);
        if(check) {
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Such city doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
