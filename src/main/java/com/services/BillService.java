package com.services;

import com.pojos.Bill;

import java.util.Set;

public interface BillService {
    Set<Bill> getBills(int page);

    Bill getBill(int id);

    Bill getBill(String code);

    boolean update(Bill bill);

    boolean create(Bill bill);

    Set<Object> countBillsByType();
}
