package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public List<Transaction> getTransactionsByUserId(@RequestParam String userId) {
        return transactionService.findTransactionsByUserId(userId);
    }

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/update")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }

    @PutMapping("/updateCategory/{transactionId}")
    public Transaction updateTransactionCategory(@PathVariable String transactionId, @RequestParam String category) {
        return transactionService.updateTransactionCategory(transactionId, category);
    }
}
