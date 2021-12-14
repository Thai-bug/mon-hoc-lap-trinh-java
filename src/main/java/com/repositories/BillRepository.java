package com.repositories;

import com.pojos.Bill;

import java.util.List;

public interface BillRepository {
    List<Bill> getBills(int page);

    Bill getBIllById(int id);
}
