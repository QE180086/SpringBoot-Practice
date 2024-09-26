package app.atula.banking.service;

import app.atula.banking.dto.TransactionDto;

public interface TransactionService {
    public void historyTransaction(TransactionDto transactionDto);
}
