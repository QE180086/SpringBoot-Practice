package app.atula.banking.service;

import app.atula.banking.dto.CreditRequest;
import app.atula.banking.entity.User;
import app.atula.banking.repository.AdminRepository;
import app.atula.banking.repository.UserRepository;
import app.atula.banking.response.AccountInfo;
import app.atula.banking.response.BankResponse;
import app.atula.banking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public BankResponse addMoney(CreditRequest creditRequest) {
        User user = userRepository.findByAccountNumber(creditRequest.getAccountNumber());

        if (user == null) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_FAIL)
                    .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGEFAIL)
                    .accountInfo(null)
                    .build();
            return bankResponse;
        }
        user.setAccountBalance(user.getAccountBalance().add(creditRequest.getAccountBalance()));
        User savedUser = userRepository.save(user);
        return  BankResponse.builder()
                .responseCode("200")
                .responseMessage("Chúc mừng bạn đã nạp thành công: " + creditRequest.getAccountBalance() +"vnđ")
                .accountInfo(AccountInfo.builder()
                        .accountNumber(savedUser.getAccountNumber())
                        .accountBalance(savedUser.getAccountBalance())
                        .accountName(user.getFirstName()+ " "+user.getLastName()+" " +user.getOtherName())
                        .message("Số dư hiện tại: " + savedUser.getAccountBalance() +"vnđ")
                        .build())
                .build();


    }

    @Override
    public BankResponse transMoney(CreditRequest creditRequest) {
        User user = userRepository.findByAccountNumber(creditRequest.getAccountNumber());

        if (user == null) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_FAIL)
                    .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGEFAIL)
                    .accountInfo(null)
                    .build();
            return bankResponse;
        }

        


        return null;
    }


}
