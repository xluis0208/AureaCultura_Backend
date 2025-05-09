package backend.project.servicesImplemetation;

import backend.project.dtos.ReportDTO;
import backend.project.entities.Client;
import backend.project.entities.Transaction;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.repositories.TransactionRepository;
import backend.project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Transaction insertTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction insertTransaction(LocalDate date, Double amount, Integer quantity, Long clientId) {
        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setAmount(amount);
        transaction.setQuantity(quantity);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));

        transaction.setClient(client);

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
    }

    @Override
    public List<Transaction> findTransactionsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findTransactionsBetweenDates(startDate, endDate);
    }

    @Override
    public List<Transaction> findAllByUsuId(Long usuId) {
        return transactionRepository.findAllByUsuId(usuId);
    }

    @Override
    public List<ReportDTO> getMonthlyTransactionAmounts(int year) {
        List<Object[]> results = transactionRepository.findMonthlyTransactionAmountsByYear(year);

        List<ReportDTO> listaResultante = new ArrayList<>();
        for (Object[] row : results) {
            ReportDTO dto = new ReportDTO();
            dto.setNroMes(Integer.parseInt(row[0].toString()));
            dto.setMonto(Double.parseDouble(row[1].toString()));
            dto.CargarNombreMes();
            listaResultante.add(dto);
        }
        return listaResultante;
    }

    @Override
    public List<ReportDTO> getTopClientsWithMostTransactions(int limit) {
        List<Object[]> results = transactionRepository.findTopClientsWithMostTransactions(limit);

        List<ReportDTO> listaResultante = new ArrayList<>();
        for (Object[] row : results) {
            ReportDTO dto = new ReportDTO();
            dto.setNombre(row[0].toString() + " " + row[1].toString());
            dto.setCantidad(Integer.parseInt(row[2].toString()));
            listaResultante.add(dto);
        }
        return listaResultante;
    }
}
