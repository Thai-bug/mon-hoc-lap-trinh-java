package com.services;

import com.pojos.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getServices(String kw, int page);

    int getServicesCount(String kw);

    Service getServiceById(int id);

    boolean update(Service service);

    boolean add(Service service);

    List<Service> getServicesByName(String name, boolean status, int page);
}
