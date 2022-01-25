package com.services.impl;

import com.SubClass;
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

    @Override
    public Set<Object> countBillsByType() {
        return billRepository.countBillsByTypes();
    }

    @Override
    public Set<SubClass> staticBill(String type) {
        switch (type) {
            case "month":
                return billRepository.totalMoneyBillsByMonths();
            case "quarter":
                return billRepository.totalMoneyBillsByQuarter();
            default:
                return billRepository.totalMoneyBillsByDays();
        }
    }

    @Override
    public int countBill(String keyword) {
        return billRepository.countBill(keyword);
    }

    @Override
    public Set<Bill> getBills(String keyword, int page, int length) {
        return billRepository.getBills(keyword, page, length);
    }

    @Override
    public Set<Bill> getBillsByLobbyCode(String lobbyCode, int page, int length) {
        return billRepository.getBillByLobbyCode(lobbyCode, page, length);
    }

    @Override
    public int countBillsByLobbyCode(String lobbyCode) {
        return billRepository.countBillsByLobbyCode(lobbyCode);
    }
}
