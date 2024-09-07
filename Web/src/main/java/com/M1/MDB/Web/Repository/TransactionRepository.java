package com.M1.MDB.Web.Repository;

import com.M1.MDB.Web.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);

    List<Transaction> findByTypeAndUserId(String type, String userId);
}
