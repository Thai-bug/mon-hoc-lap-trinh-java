package com.services.impl;

import com.pojos.Bill;
import com.repositories.BillRepository;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getBills(int page) {
        return billRepository.getBills(page);
    }

    @Override
    public Bill getBillById(int id) {
        return billRepository.getBIllById(id);
    }
}
