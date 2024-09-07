package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;

import java.util.Date;
import java.util.List;

public interface DashboardService {
    List<Transaction> getTransactionsByDateRange(String userId, Date startDate, Date endDate);
}
