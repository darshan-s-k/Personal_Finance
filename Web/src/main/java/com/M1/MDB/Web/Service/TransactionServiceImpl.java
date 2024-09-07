package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Transaction;
import com.M1.MDB.Web.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryService categoryService; // Inject CategoryService

    @Override
    public List<Transaction> findTransactionsByTypeAndUserId(String type, String userId) {
        return transactionRepository.findByTypeAndUserId(type, userId);
    }

    @Override
    public List<Transaction> findTransactionsByUserId(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        // Save the transaction without setting category initially
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Update category spent amount if it's an expense
        if ("expense".equals(transaction.getType())) {
            categoryService.updateCategorySpent(transaction.getUserId(), transaction.getCategory(), transaction.getAmount());
        }

        return savedTransaction;
    }

    @Override
    public Transaction updateTransactionCategory(String transactionId, String category) {
        // Find transaction by transactionId
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Update category of the transaction
        transaction.setCategory(category);

        // Save updated transaction
        Transaction updatedTransaction = transactionRepository.save(transaction);

        // Update category spent amount if it's an expense
        if ("expense".equals(transaction.getType())) {
            categoryService.updateCategorySpent(transaction.getUserId(), transaction.getCategory(), transaction.getAmount());
        }

        return updatedTransaction;
    }
}
