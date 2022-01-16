package com.services.impl;

import com.pojos.Bill;
import com.repositories.BillRepository;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Set<Bill> getBills(int page) {
        return billRepository.getBills(page);
    }

    @Override
    public Bill getBill(int id) {
        return billRepository.getBill(id);
    }

    @Override
    public Bill getBill(String code) {
        return billRepository.getBill(code);
    }

    @Override
    public boolean update(Bill bill) {
        return billRepository.update(bill);
    }

    @Override
    public boolean create(Bill bill) {
        return billRepository.create(bill);
    }
}
