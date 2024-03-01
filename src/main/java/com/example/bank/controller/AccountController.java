package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        try {
            accountService.createAccount(account);
            String message = "Account created successfully";
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            String errorMessage = "Error creating account: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/getAllAccounts")
public ResponseEntity<?> getAllAccounts() {
    try {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    } catch (Exception e) {
        String errorMessage = "Error retrieving accounts: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
@GetMapping("/getAccountById/{accountId}")
public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
    try {
        return accountService.getAccountById(accountId);
        
    } catch (Exception e) {
        String errorMessage = "Error retrieving account: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}

@PostMapping("/deposit")
public ResponseEntity<?> deposit(@RequestParam Long accountId, @RequestParam Double amount) {
    try {
       return accountService.deposit(accountId,amount);
    } catch (Exception e) {
        String errorMessage = "Error depositing amount: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}




    @PostMapping("/draw")
public ResponseEntity<?> draw(@RequestParam Long accountId, @RequestParam Double amount) {
    try {
       return accountService.draw(accountId,amount);
    } catch (Exception e) {
        String errorMessage = "Error draw amount: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            accountService.delete(id);
            String message = "Account deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            String errorMessage = "Error deleting account: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



 @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccountById(@PathVariable Long id, @RequestBody Account updatedAccount) {
        try {
            accountService.updateById(id, updatedAccount);
            String message = "Account with ID " + id + " updated successfully";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String errorMessage = "Error updating account: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
    



}
