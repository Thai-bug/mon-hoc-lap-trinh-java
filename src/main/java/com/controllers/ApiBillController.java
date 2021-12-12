package com.controllers;

import com.pojos.Bill;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiBillController {
    @Autowired
    private BillService billService;

    @GetMapping("/admin/bills/api/v1")
    public ResponseEntity<List<Bill>> bill() {
        List<Bill> bills = billService.getBills();
        System.out.println(bills.get(0).getEmployee().getId());
        return new ResponseEntity<>(
                bills,
                HttpStatus.OK);
    }

    @GetMapping("/admin/bills/api")
    public ResponseEntity<List<Bill>> get() {
        List<Bill> bills = billService.getBills();
        return new ResponseEntity<>(
                bills,
                HttpStatus.OK);
    }
}
