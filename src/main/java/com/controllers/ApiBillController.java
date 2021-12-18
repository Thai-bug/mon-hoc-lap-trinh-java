package com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pojos.Bill;
import com.pojos.Drink;
import com.request.BillRequest;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/rol/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody ResponseEntity<Object> updateBill(@RequestBody BillRequest billRequest) {
        try{
            System.out.println(billRequest.getAddFoods().get(0));

            return new ResponseEntity<Object>(billRequest, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
