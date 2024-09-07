package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getExpenseTransactions(String userId) {
        return transactionRepository.findByTypeAndUserId("expense", userId);
    }
}
