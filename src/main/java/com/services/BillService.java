package com.services;

import com.pojos.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getBills(int page);

    Bill getBill(int id);

    Bill getBill(String code);
}
