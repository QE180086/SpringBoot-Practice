package app.atula.banking.service;

import app.atula.banking.dto.CreditRequest;
import app.atula.banking.entity.User;
import app.atula.banking.response.BankResponse;

public interface AdminService {
    public BankResponse addMoney(CreditRequest creditRequest);
    public BankResponse transMoney(CreditRequest creditRequest);
}
