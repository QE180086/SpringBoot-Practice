package app.atula.banking.service;

import app.atula.banking.dto.*;
import app.atula.banking.entity.Transaction;
import app.atula.banking.entity.User;
import app.atula.banking.repository.UserRepository;
import app.atula.banking.response.AccountInfo;
import app.atula.banking.response.BankResponse;
import app.atula.banking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private  TransactionService transactionService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())){
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
            return bankResponse;
        }
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(newUser);
        // send email
        EmailDetail emailDetail = EmailDetail.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("Congragulation!! You has been account successfull!!!" + "\n " +"FirstName: " +savedUser.getFirstName() + " \n" +"LastName: "+ savedUser.getLastName()+ " \n" + "OtherName: " +savedUser.getOtherName()+ " \n" +"Account Number: " +savedUser.getAccountNumber())
                .build();
        emailService.sendEmail(emailDetail);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(savedUser.getFirstName()+savedUser.getLastName()+savedUser.getOtherName())
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public BankResponse findAccountNumber(CreditRequest creditRequest) {
        User user = userRepository.findByAccountNumber(creditRequest.getAccountNumber());

        if (user == null) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_FAIL)
                    .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGEFAIL)
                    .accountInfo(null)
                    .build();
            return bankResponse;
        }
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(creditRequest.getAccountNumber())
                        .accountName(user.getFirstName() + " " + user.getLastName() + " " + user.getOtherName())
                        .build())
                .build();
    }

    @Override
    public BankResponse transferMoney(TransferRequest transferRequest) {
        User userSource = userRepository.findByAccountNumber(transferRequest.getSourceAccountNumber());
        User userDestination = userRepository.findByAccountNumber(transferRequest.getDestinationAccountNumber());

        if (userSource == null || userDestination==null) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_FAIL)
                    .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGEFAIL)
                    .accountInfo(null)
                    .build();
            return bankResponse;
        }
        String stringAmount = userSource.getAccountBalance().subtract(transferRequest.getAmount()).toString();
        double amountRequest = Double.valueOf(stringAmount);
        if(amountRequest<0) {
           return BankResponse.builder()
                   .responseCode(AccountUtils.ACCOUNT_TRANSFER_FAIL)
                   .responseMessage(AccountUtils.ACCOUNT_TRANSFER_MESSAGEFAIL)
                   .accountInfo(null)
                   .build();
        }



        userSource.setAccountBalance(userSource.getAccountBalance().subtract(transferRequest.getAmount()));
        userDestination.setAccountBalance(userDestination.getAccountBalance().add(transferRequest.getAmount()));

        // History Transaction
        TransactionDto transactionDto = TransactionDto.builder()
                .transactionType("Credit")
                .accountNumber(userDestination.getAccountNumber())
                .amount(transferRequest.getAmount())
                .accountName(userDestination.getFirstName()+" " + userDestination.getLastName())
                .status("SUCCESS")
                .build();

        transactionService.historyTransaction(transactionDto);

        User savedSource = userRepository.save(userSource);
        User savedDestination = userRepository.save(userDestination);

        // Send Email
        EmailDetail emailDetail = EmailDetail.builder()
                .recipient(userSource.getEmail())
                .subject("You has TRANSFER SUCCESSFULL. Please check your account !!!")
                .messageBody("Congragulation!! You has been TRANSFER successfull!!!" + "\n " +"FirstName: " +userSource.getFirstName()
                        + " \n" +"LastName: "+ userSource.getLastName()
                        + " \n" +"OtherName: " +userSource.getOtherName()
                        + " \n" +"Account Number: " +userSource.getAccountNumber()
                        + " \n" +"Bạn vừa chuyển tiền đến tài khoản: " + userDestination.getAccountNumber()
                        + " \n" +"Số tiền bạn vừa chuyển là: " + transferRequest.getAmount() +"vnđ"
                        + " \n" +"Số dư tài khoản của bạn: " + userSource.getAccountBalance() +"vnđ"
                )
                .build();
        emailService.sendEmail(emailDetail);

        return  BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_TRANSFER_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_TRANSFER_MESSAGESUCCESS)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(userSource.getAccountNumber())
                        .accountName(userSource.getFirstName()+" "+userSource.getLastName()+" "+userSource.getOtherName())
                        .message("Số dư hiện tại: " +savedSource.getAccountBalance())
                        .accountBalance(savedSource.getAccountBalance())
                        .build())
        .build();
    }
// rút tiền
    // truyền tiền vào
    // trừ tiền tài khoản
    // xuất form by txt
    @Override
    public BankResponse withdraw_money(BigDecimal money,String accountNumber, Long id) {

        Optional<User> u = Optional.of(new User());

        if(userRepository.findById(id) ==null){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FINDACCOUNT_FAIL)
                    .responseMessage(AccountUtils.ACCOUNT_FINDACCOUNT_MESSAGEFAIL)
                    .build();
        }
        u = userRepository.findById(id);
        if(u.get().getAccountNumber().equals(accountNumber)==false){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNTNUMBER_NOTFOUND_FAIL)
                    .responseMessage(AccountUtils.ACCOUNTNUMBER_NOTFOUND_MESSAGEFAIL)
                    .build();
        }
        u.get().setAccountBalance(u.get().getAccountBalance().subtract(money));
        User user =u.get();
        userRepository.save(user);

        return BankResponse.builder()
                .responseCode(AccountUtils.WITHDRAW_SUCCESSL)
                .responseMessage(AccountUtils.WITHDRAW_MESSAGESUCCESSL)
                .build();
    }


}
