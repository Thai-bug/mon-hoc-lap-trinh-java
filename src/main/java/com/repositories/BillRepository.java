package com.repositories;

import com.pojos.Bill;

import java.util.Set;

public interface BillRepository {
    Set<Bill> getBills(int page);

    Bill getBill(int id);

    Bill getBill(String code);

    boolean update(Bill bill);
}
