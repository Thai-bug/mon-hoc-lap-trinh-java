package com.repositories;

import com.SubClass;
import com.pojos.Bill;

import java.util.Set;

public interface BillRepository {
    Set<Bill> getBills(int page);

    Bill getBill(int id);

    Bill getBill(String code);

    boolean update(Bill bill);

    boolean create(Bill bill);

    Set<Object> countBillsByTypes();

    Set<SubClass> totalMoneyBillsByDays();

    Set<SubClass> totalMoneyBillsByMonths();

    Set<SubClass> totalMoneyBillsByQuarter();

    int countBill(String keyword);

    Set<Bill> getBills(String keyword, int page, int length);
}
