package com.services.impl;

import com.pojos.Food;
import com.repositories.ServiceRepository;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Set<com.pojos.Service> getServices(String kw, int page, int length) {
        return serviceRepository.getServices(kw, page, length);
    }

    @Override
    public int getServicesCount(String kw) {
        return serviceRepository.getServicesCount(kw);
    }

    @Override
    public com.pojos.Service getServiceByCode(String code) {
        return serviceRepository.getServiceByCode(code);
    }

    @Override
    public boolean createService(Map<String, Object> json) {
        com.pojos.Service service = new com.pojos.Service ();
        service.setCode(UUID.randomUUID().toString());
        service.setName( json.get("name").toString());
        service.setPrice(Integer.parseInt(json.get("price").toString()));
        service.setStatus((boolean) json.get("status"));
        service.setCreatedAt(new Date());
        service.setDescription( json.get("description").toString());

        return serviceRepository.add(service);
    }

    @Override
    public boolean updateService(Map<String, Object> json) {
        com.pojos.Service service = serviceRepository.getServiceByCode(json.get("code").toString());
        service.setName( json.get("name").toString());
        service.setDescription( json.get("description").toString());
        service.setPrice(Integer.parseInt(json.get("price").toString()));
        service.setStatus((boolean) json.get("status"));
        return serviceRepository.update(service);
    }

    @Override
    public Set<com.pojos.Service> getServicesByName(String name, boolean status, int page) {
        return serviceRepository.getServicesByName(name, status, page);
    }

    @Override
    public Map<String, Object> getServicesForClient(int page, int limit, String kw) {
        Set<com.pojos.Service> services = serviceRepository.getServicesForClient( page, limit, kw);

        int total = serviceRepository.countServiceClient(kw);

        Map<String, Object> result = new HashMap<>() ;

        result.put("total", total);
        result.put("data", services);
        return result;
    }

    @Override
    public Map<String, Object> getClientServiceByCode(String code) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", serviceRepository.getClientServiceByCode(code));
        return data;
    }
}
