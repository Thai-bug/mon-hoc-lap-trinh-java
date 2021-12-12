package com.controllers;

import com.pojos.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiBillController {
    @RequestMapping("/admin/bills/api/v1")
    public ResponseEntity<Bill> bill() {
        return new ResponseEntity<>(new Bill(), HttpStatus.OK);
    }
}
