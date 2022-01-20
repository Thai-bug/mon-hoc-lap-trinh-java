package com.services.impl;

import com.repositories.ServiceRepository;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    public com.pojos.Service getServiceById(int id) {
        return serviceRepository.getServiceById(id);
    }

    @Override
    public boolean add(com.pojos.Service service) {
        return serviceRepository.add(service);
    }

    @Override
    public boolean update(com.pojos.Service service) {
        return serviceRepository.update(service);
    }

    @Override
    public Set<com.pojos.Service> getServicesByName(String name, boolean status, int page) {
        return serviceRepository.getServicesByName(name, status, page);
    }
}
