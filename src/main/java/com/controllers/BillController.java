package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/bill")
public class BillController {

    @RequestMapping("")
    public String bills(
            Model model
    ) {
        return "bill";
    }

}
