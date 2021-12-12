package com.repositories;

import com.pojos.Service;

import java.util.List;

public interface ServiceRepository {
    List<Service> getServices(String kw, int pgae);

    int getServicesCount(String kw);

    Service getServiceById(int id);

    boolean update(Service service);

    boolean add(Service service);
}
