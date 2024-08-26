package com.Banking.Retail_Banking.integration;

import com.Banking.Retail_Banking.controllers.TransactionRestController;
import com.Banking.Retail_Banking.utils.AccountInput;
import com.Banking.Retail_Banking.utils.TransactionInput;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class MakeTransferIntegrationTest {

    @Autowired
    private TransactionRestController transactionRestController;

    @Test
    void givenTransactionDetails_whenMakeTransaction_thenVerifyTransactionIsProcessed() {
        
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("73084635");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("65-93-37");
        targetAccount.setAccountNumber("21956204");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(27.5);
        input.setReference("My reference");
        input.setLatitude(45.0000000);
        input.setLongitude(90.0000000);

        
        var body = transactionRestController.makeTransfer(input).getBody();

       
        var isComplete = (Boolean) body;
        assertThat(isComplete).isTrue();
    }
}
