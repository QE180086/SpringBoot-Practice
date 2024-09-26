package app.atula.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;

}
