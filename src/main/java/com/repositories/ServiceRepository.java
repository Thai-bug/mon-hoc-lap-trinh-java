package com.repositories;

import com.pojos.Service;

import java.util.Set;

public interface ServiceRepository {
    Set<Service> getServices(String kw, int page, int length);

    int getServicesCount(String kw);

    Service getServiceById(int id);

    boolean update(Service service);

    boolean add(Service service);

    Set<Service> getServicesByName(String name, boolean status, int page);
}
