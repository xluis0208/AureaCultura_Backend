package backend.project.repositories;
// cambios en este documento
import backend.project.entities.Promoter;
import backend.project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsBetweenDates(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t FROM Transaction t WHERE t.client.user.id = :usuId")
    List<Transaction> findAllByUsuId(Long usuId);

    @Query("SELECT EXTRACT(MONTH FROM t.date) AS month, SUM(t.amount) AS totalAmount " +
            "FROM Transaction t " +
            "WHERE EXTRACT(YEAR FROM t.date) = :year " +
            "GROUP BY EXTRACT(MONTH FROM t.date) " +
            "ORDER BY month")
    List<Object[]> findMonthlyTransactionAmountsByYear(@Param("year") int year);

    @Query(value = "SELECT c.first_name, c.last_name, COUNT(t.id) as transactionCount " +
            "FROM transaction t " +
            "INNER JOIN client c ON c.id = t.client_id " +
            "GROUP BY c.first_name, c.last_name " +
            "ORDER BY transactionCount DESC " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Object[]> findTopClientsWithMostTransactions(@Param("limit") int limit);
}
