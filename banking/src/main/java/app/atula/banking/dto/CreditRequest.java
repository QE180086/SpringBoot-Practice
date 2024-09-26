package app.atula.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest {
    private String accountNumber;
    private BigDecimal accountBalance;
    private int amount;
    private String message;


}
