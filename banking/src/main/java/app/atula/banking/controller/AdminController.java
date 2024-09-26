package app.atula.banking.controller;

import app.atula.banking.dto.CreditRequest;
import app.atula.banking.response.BankResponse;
import app.atula.banking.service.AdminService;
import app.atula.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @GetMapping("/findAccount")
    public BankResponse findAccount(@RequestBody CreditRequest creditRequest){
        return  userService.findAccountNumber(creditRequest);
    }
    @PostMapping("/addMoney")
    public BankResponse addMoney(@RequestBody CreditRequest creditRequest){
        return adminService.addMoney(creditRequest);
    }
}
