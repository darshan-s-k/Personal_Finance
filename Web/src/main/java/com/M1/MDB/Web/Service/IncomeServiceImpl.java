package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getIncomeTransactions(String userId) {
        return transactionRepository.findByTypeAndUserId("income", userId);
    }
}
