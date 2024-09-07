package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;

import java.util.List;

public interface IncomeService {
    List<Transaction> getIncomeTransactions(String userId);
}
