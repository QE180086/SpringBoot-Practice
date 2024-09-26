package app.atula.banking.service;

import app.atula.banking.dto.CreditRequest;
import app.atula.banking.dto.TransferRequest;
import app.atula.banking.dto.UserRequest;
import app.atula.banking.response.BankResponse;

import java.math.BigDecimal;

public interface UserService {
    public BankResponse createAccount(UserRequest userRequest);
    public BankResponse findAccountNumber(CreditRequest creditRequest);
    public BankResponse transferMoney(TransferRequest transferRequest);

    public BankResponse withdraw_money(BigDecimal money,String accountNumber, Long id);

}
