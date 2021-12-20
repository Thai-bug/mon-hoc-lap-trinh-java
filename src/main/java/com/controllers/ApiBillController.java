package com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pojos.Bill;
import com.pojos.Drink;
import com.pojos.Food;
import com.pojos.Service;
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

    @GetMapping("/detail/{id}")
    public ResponseEntity<Bill> getDetail(
            @PathVariable(value = "id") int id
    ) {
        Bill bill = billService.getBill(id);
        return new ResponseEntity<>(
                bill,
                HttpStatus.OK);
    }

    @PostMapping(value = "/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody
    ResponseEntity<Bill> updateBill(@RequestBody BillRequest billRequest) {
        try {
            Bill bill = billService.getBill(billRequest.getCode());
            List<Food> orderedFood = bill.getFoodList();
            List<Drink> orderedDrinks = bill.getDrinkList();
            List<Service> orderedServices = bill.getServiceList();

            orderedFood.removeAll(billRequest.getDeletedFoods());
//
//            bill.setDrinkList(orderedDrinks);
            bill.setFoodList(orderedFood);
            return new ResponseEntity<Bill>(bill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Bill>((Bill) null, HttpStatus.BAD_REQUEST);
        }
    }

}
