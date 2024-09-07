package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByDateRange(String userId, Date startDate, Date endDate) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream()
                .filter(transaction -> !transaction.getDate().before(startDate) &&
                        !transaction.getDate().after(endDate))
                .collect(Collectors.toList());
    }
}

