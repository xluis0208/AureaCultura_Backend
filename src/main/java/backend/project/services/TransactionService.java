package backend.project.services;

import backend.project.dtos.ReportDTO;
import backend.project.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Transaction insertTransaction(Transaction transaction);
    Transaction insertTransaction(LocalDate date, Double amount, Integer quantity, Long clientId);
    void deleteTransaction(Long id);
    List<Transaction> listAllTransactions();
    Transaction findById(Long id);
    List<Transaction> findTransactionsBetweenDates(LocalDate startDate, LocalDate endDate);
    List<Transaction> findAllByUsuId(Long usuId);
    public List<ReportDTO> getMonthlyTransactionAmounts(int year);
    public List<ReportDTO> getTopClientsWithMostTransactions(int limit);
}
