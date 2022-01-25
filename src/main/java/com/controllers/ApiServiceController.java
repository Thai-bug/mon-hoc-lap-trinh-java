package com.controllers;

import com.pojos.Drink;
import com.pojos.Food;
import com.pojos.Service;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/admin/services/select2/service-by-name")
    public ResponseEntity<Set<Service>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Set<Service> services = serviceService.getServicesByName(name, status, page);
        return new ResponseEntity<Set<Service>>(
                services,
                HttpStatus.OK);
    }

    @PostMapping(value = "/admin/services/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestBody Map<String, Object> json
    ) {
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString()) + 1;
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj= (Map<String, String>) json.get("search");
        long total = serviceService.getServicesCount(searchObj.get("value"));
        Set<Service> data = serviceService.getServices(searchObj.get("value"), start, length);
        Map<String, Object> result = new HashMap<>() ;
        result.put("data", data);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));
        return new ResponseEntity(
                result,
                HttpStatus.OK);
    }

    @PostMapping(value = "/admin/services/create")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> createFood(@RequestBody Map<String, Object> json){
        Map<String, Object> response = new HashMap<>();
        try{
            boolean result = serviceService.createService(json);

            if(result) {
                response.put("message", "Thêm dịch vụ thành công");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("message", "Thêm dịch vụ thất bại");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            System.out.println(e);
            response.put("message", "Thêm dịch vụ thất bạii");
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/admin/services/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getDrink(
            @PathVariable(value = "code") String code
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Service service = serviceService.getServiceByCode(code);
            response.put("result", service);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(value = "/admin/services/update")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> updateFood(@RequestBody Map<String, Object> json) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean result = serviceService.updateService(json);
            if (result) {
                response.put("result", "Cập nhật thành công");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("result", "Cập nhật thất bại");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/services")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getServicesForClient(
            @RequestParam(required = false) Map<String, String> params
    ){
        try{
            int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
            int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit"));
            String kw = params.get("kw") == null ? "" : params.get("kw").toLowerCase(Locale.ROOT);
            Map<String, Object> result = serviceService.getServicesForClient(page, limit, kw);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping(value = "/services/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getServiceForClient(
            @PathVariable(value = "code") String code
    ){
        try{
            return new ResponseEntity<>(serviceService.getClientServiceByCode(code), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }
}
