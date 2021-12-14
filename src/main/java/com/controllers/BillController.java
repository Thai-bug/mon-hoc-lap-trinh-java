package com.controllers;

import com.pojos.Bill;
import com.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping("")
    public String bills(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        List<Bill> bills = billService.getBills(page);
        model.addAttribute("bills", bills);
        return "bills";
    }

    @RequestMapping("/detail")
    public String detail(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "billDetail";
    }

}
