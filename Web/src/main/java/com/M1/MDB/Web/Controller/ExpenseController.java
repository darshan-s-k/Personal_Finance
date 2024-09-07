package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/transactions")
    public List<Transaction> getExpenseTransactions(@RequestParam String userId) {
        return expenseService.getExpenseTransactions(userId);
    }
}
