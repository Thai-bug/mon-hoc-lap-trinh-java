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

import java.util.LinkedHashMap;
import java.util.Set;
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
    ResponseEntity<Object> updateBill(@RequestBody BillRequest billRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Bill bill = billService.getBill(billRequest.getCode());
            Set<Food> orderedFood = bill.getFoodList();
            Set<Drink> orderedDrinks = bill.getDrinkList();
            Set<Service> orderedServices = bill.getServiceList();

            if (billRequest.getDeletedFoods() != null)
                orderedFood.removeAll(billRequest.getDeletedFoods());
            if (billRequest.getAddedFoods() != null)
                orderedFood.addAll(billRequest.getAddedFoods());

            if(billRequest.getDeletedDrinks() != null)
                orderedDrinks.removeAll(billRequest.getDeletedDrinks());
            if(billRequest.getAddedDrinks() != null)
                orderedDrinks.addAll(billRequest.getAddedDrinks());

            if (billRequest.getDeletedServices() != null)
                orderedServices.removeAll(billRequest.getDeletedServices());
            if (billRequest.getAddedServices() != null)
                orderedServices.addAll(billRequest.getAddedServices());

            bill.setDrinkList(orderedDrinks);
            bill.setFoodList(orderedFood);
            bill.setServiceList(orderedServices);

            System.out.println(bill.getDrinkList().size());

            billService.update(bill);

            response.put("message", "Cập nhật đơn hàng thành công");

            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("message", "Cập nhật đơn hàng thất bại");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
