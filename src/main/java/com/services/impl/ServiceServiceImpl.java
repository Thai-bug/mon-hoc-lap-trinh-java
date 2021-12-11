package com.services.impl;

import com.repositories.ServiceRepository;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<com.pojos.Service> getServices(String kw, int page) {
        return serviceRepository.getServices(kw, page);
    }

    @Override
    public int getServicesCount(String kw) {
        return serviceRepository.getServicesCount(kw);
    }

    @Override
    public com.pojos.Service getServiceById(int id) {
        return serviceRepository.getServiceById(id);
    }
}
