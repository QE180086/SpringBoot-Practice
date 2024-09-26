package app.atula.banking.service;

import app.atula.banking.dto.TransactionDto;
import app.atula.banking.entity.Transaction;
import app.atula.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImp implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void historyTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .accountName(transactionDto.getAccountName())
                .amount(transactionDto.getAmount())
                .status(transactionDto.getStatus())
                .build();

        transactionRepository.save(transaction);
        System.out.println("Transaction saved successfull");
    }


}
