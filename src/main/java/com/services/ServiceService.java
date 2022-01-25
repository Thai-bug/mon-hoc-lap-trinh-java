package com.services;

import com.pojos.Service;

import java.util.Map;
import java.util.Set;

public interface ServiceService {
    Set<Service> getServices(String kw, int page, int length);

    int getServicesCount(String kw);

    Service getServiceById(int id);

    boolean update(Service service);

    boolean add(Service service);

    Set<Service> getServicesByName(String name, boolean status, int page);


    Map<String, Object> getServicesForClient(int page, int limit, String kw);

    Map<String, Object> getClientServiceByCode(String code);
}
