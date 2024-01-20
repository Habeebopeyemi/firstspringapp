package com.example.firstspring.controllers;

import com.example.firstspring.entity.Staff;
import com.example.firstspring.services.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/StaffAPI")
public class StaffAPI {
    @Autowired
    private StaffServices staffServicesVar;

    @RequestMapping(value = "/list")
    public ResponseEntity<?> getAllStaff(){
        List<Staff> staffList = staffServicesVar.getAllStaff();
       ResponseEntity response= new ResponseEntity(staffList, HttpStatus.OK);
       return response;
    }
}
