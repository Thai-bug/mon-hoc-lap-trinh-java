package com.controllers;

import com.SubClass;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @GetMapping("/bills-by-lobby")
    public ResponseEntity<Map<String, Object>> getBillsByLobbyCode(
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        int limit = params.get("limit") == null ? 0 : Integer.parseInt(params.get("limit"));
        String lobbyCode = params.get("lobbyCode").toString();
        int total = billService.countBillsByLobbyCode(lobbyCode);
        Set<Bill> bills = billService.getBillsByLobbyCode(lobbyCode, page, limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", bills);
        response.put("total", total);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @PostMapping(value = "/static", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Set<SubClass>> staticBills( @RequestBody Map<String, Object> json){
        String type = (String) json.get("type");
        Set<SubClass> result = billService.staticBill(type);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
            bill.setTotalTable(billRequest.getTables());
            bill.setType(billRequest.getType());
            bill.setName(billRequest.getName());
            bill.setFinalMoney(billRequest.getTotal());
            bill.setProvisionalMoney(billRequest.getDeposit());

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
            bill.setType(billRequest.getType());

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

    @PostMapping(value = "/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestBody Map<String, Object> json
    ) {
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString());
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj= (Map<String, String>) json.get("search");
        long total = billService.countBill(searchObj.get("value"));
        Set<Bill> data = billService.getBills(searchObj.get("value"), start, length);
        Map<String, Object> result = new HashMap<>() ;
        result.put("data", data);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));
        return new ResponseEntity(
                result,
                HttpStatus.OK);
    }
}
