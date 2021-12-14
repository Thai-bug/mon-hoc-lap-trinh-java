package com.controllers;

import com.pojos.Bill;
import com.pojos.Drink;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/bills")
public class ApiBillController {
    @Autowired
    private BillService billService;

//    @GetMapping("")
//    public ResponseEntity<List<Bill>> bills() {
//        List<Bill> bills = billService.getBills();
//        System.out.println(bills.get(0).getEmployee().getId());
//        return new ResponseEntity<>(
//                bills,
//                HttpStatus.OK);
//    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Bill> getDetail(
            @PathVariable(value = "id")int id
    ) {
        Bill bill = billService.getBillById(id);
        return new ResponseEntity<>(
                bill,
                HttpStatus.OK);
    }
}
