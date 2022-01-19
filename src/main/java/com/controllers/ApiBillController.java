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
import java.util.UUID;

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

    @GetMapping("/total-by-type")
    public ResponseEntity<Set<Object>> countBillsByType(
    ) {
        Set<Object> result = billService.countBillsByType();
        return new ResponseEntity<>(
                result,
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

            if(billRequest.getLobby() != null)
                bill.setLobby(billRequest.getLobby());

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

    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody
    ResponseEntity<Object> createBill(@RequestBody BillRequest billRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Bill bill = new Bill();
            Set<Food> orderedFood = bill.getFoodList();
            Set<Drink> orderedDrinks = bill.getDrinkList();
            Set<Service> orderedServices = bill.getServiceList();

            if (billRequest.getAddedFoods() != null)
                orderedFood.addAll(billRequest.getAddedFoods());

            if(billRequest.getAddedDrinks() != null)
                orderedDrinks.addAll(billRequest.getAddedDrinks());

            if (billRequest.getAddedServices() != null)
                orderedServices.addAll(billRequest.getAddedServices());

            if(billRequest.getLobby() != null)
                bill.setLobby(billRequest.getLobby());

            bill.setDrinkList(orderedDrinks);
            bill.setFoodList(orderedFood);
            bill.setServiceList(orderedServices);
            bill.setStatus(billRequest.getStatus());
            bill.setTotalTable(billRequest.getTables());
            bill.setProvisionalMoney(billRequest.getDeposit());
            bill.setFinalMoney(billRequest.getTotal());
            bill.setLobby(billRequest.getLobby());
            bill.setName(billRequest.getName());
            bill.setCode(UUID.randomUUID().toString());
            bill.setCustomerName(billRequest.getCustomerName());
            bill.setEmployee(billRequest.getEmployee());
            bill.setBeginDate(billRequest.getBeginDate());
            bill.setEndDate(billRequest.getEndDate());

            response.put("message", "Tạo đơn hàng thành công");

            boolean status = billService.create(bill);
            if (status)
                return new ResponseEntity<Object>(response, HttpStatus.OK);

            response.put("message", "Tạo đơn hàng thất bại");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("message", "Tạo đơn hàng thất bại");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
