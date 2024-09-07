package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/transactions")
    public List<Transaction> getIncomeTransactions(@RequestParam String userId) {
        return incomeService.getIncomeTransactions(userId);
    }
}
