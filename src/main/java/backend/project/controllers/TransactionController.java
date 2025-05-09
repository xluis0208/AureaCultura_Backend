package backend.project.controllers;

import backend.project.dtos.ReportDTO;
import backend.project.entities.Transaction;
import backend.project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.insertTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransactionByDetails(@RequestParam LocalDate date, @RequestParam Double amount,
                                                                  @RequestParam Integer quantity, @RequestParam Long clientId) {
        Transaction savedTransaction = transactionService.insertTransaction(date, amount, quantity, clientId);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction obj){
        obj.setDate(LocalDate.now());
        Transaction savedTransaction = transactionService.insertTransaction(obj);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        List<Transaction> transactions = transactionService.listAllTransactions();
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<List<Transaction>> getTransactionsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Transaction> transactions = transactionService.findTransactionsBetweenDates(startDate, endDate);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/listByIdUsu/{id}")
    public ResponseEntity<List<Transaction>> listAllTransactionsByIdUsu(@PathVariable("id") Long id) {
        List<Transaction> transactions = transactionService.findAllByUsuId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/monthlyAmounts")
    public ResponseEntity<?> getMonthlyAmounts(@RequestParam int year) {
        List<ReportDTO> monthlyAmounts = transactionService.getMonthlyTransactionAmounts(year);
        return ResponseEntity.ok(monthlyAmounts);
    }


    @GetMapping("/top-clients")
    public ResponseEntity<List<ReportDTO>> getTopClients(@RequestParam int limit) {
        List<ReportDTO> reporte = transactionService.getTopClientsWithMostTransactions(limit);
        return ResponseEntity.ok(reporte);
    }
}
