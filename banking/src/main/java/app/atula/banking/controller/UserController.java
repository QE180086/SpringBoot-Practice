package app.atula.banking.controller;

import app.atula.banking.dto.CreditRequest;
import app.atula.banking.dto.TransferRequest;
import app.atula.banking.dto.UserRequest;
import app.atula.banking.response.BankResponse;
import app.atula.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/createAccount")
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
    @GetMapping("/findAccount")
    public BankResponse findAccount(@RequestBody CreditRequest creditRequest){
        return  userService.findAccountNumber(creditRequest);
    }
    @PostMapping("/transferMoney")
    public BankResponse transferMoney(@RequestBody TransferRequest transferRequest){
        return userService.transferMoney(transferRequest);
    }

    @PostMapping("/withdraw")
    public BankResponse transferMoney(@RequestParam  BigDecimal money,
                                      @RequestParam String accountNumber,
                                      @RequestParam Long id
                                      ){
        return userService.withdraw_money(money,accountNumber,id);
    }


}
