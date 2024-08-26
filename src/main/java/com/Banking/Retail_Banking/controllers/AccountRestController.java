package com.Banking.Retail_Banking.controllers;

import com.Banking.Retail_Banking.constants.constants;
import com.Banking.Retail_Banking.models.Account;
import com.Banking.Retail_Banking.services.AccountService;
import com.Banking.Retail_Banking.utils.AccountInput;
import com.Banking.Retail_Banking.utils.CreateAccountInput;
import com.Banking.Retail_Banking.utils.InputValidator;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AccountRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            
            @Valid @RequestBody AccountInput accountInput) {
        LOGGER.debug("Triggered AccountRestController.accountInput");

        
        if (InputValidator.isSearchCriteriaValid(accountInput)) {
            
            Account account = accountService.getAccount(
                    accountInput.getSortCode(), accountInput.getAccountNumber());

            
            if (account == null) {
                return new ResponseEntity<>(constants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountInput createAccountInput) {
        LOGGER.debug("Triggered AccountRestController.createAccountInput");

        
        if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
           
            Account account = accountService.createAccount(
                    createAccountInput.getBankName(), createAccountInput.getOwnerName());

            
            if (account == null) {
                return new ResponseEntity<>(constants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
