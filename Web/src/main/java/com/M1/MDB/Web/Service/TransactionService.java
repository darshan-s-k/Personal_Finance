package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findTransactionsByTypeAndUserId(String type, String userId);
    List<Transaction> findTransactionsByUserId(String userId);
    Transaction addTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    Transaction saveTransaction(Transaction transaction);

    Transaction updateTransactionCategory(String transactionId, String category);
}
