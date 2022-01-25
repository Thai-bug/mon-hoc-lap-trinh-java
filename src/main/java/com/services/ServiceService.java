package com.services;

import com.pojos.Service;

import java.util.Map;
import java.util.Set;

public interface ServiceService {
    Set<Service> getServices(String kw, int page, int length);

    int getServicesCount(String kw);

    Service getServiceByCode(String code);

    boolean updateService(Map<String, Object> json);

    boolean createService(Map<String, Object> json);

    Set<Service> getServicesByName(String name, boolean status, int page);

    Map<String, Object> getServicesForClient(int page, int limit, String kw);

    Map<String, Object> getClientServiceByCode(String code);
}
