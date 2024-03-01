package com.example.bank.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Account;
import com.example.bank.repo.AccountRepo;
@Service
public class AccountService {
    @Autowired
    AccountRepo accountRepo;
    
    public void createAccount(Account account) {
        accountRepo.save(account);
    }

   
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
      
    }


    public ResponseEntity<?> getAccountById(Long accountId) {
        Account account = accountRepo.findById(accountId).orElse(null);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
       
    }
    
     public ResponseEntity<String> deposit(Long accountId, Double amount) {
        Account account =accountRepo.findById(accountId).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance()+amount);
            accountRepo.save(account);
            String message = "Deposit successful. New balance: " + account.getBalance();
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
}


public ResponseEntity<String> draw(Long accountId, Double amount) {
    Account account =accountRepo.findById(accountId).orElse(null);
    if (account != null) {
        account.setBalance(account.getBalance()-amount);
        accountRepo.save(account);
        String message = "draw successful. New balance: " + account.getBalance();
        return ResponseEntity.ok(message);
    } else {
        return ResponseEntity.notFound().build();
    }
}

public void delete(Long id) {
    Optional<Account> optionalAccount = accountRepo.findById(id);
    if (optionalAccount.isPresent()) {
        accountRepo.deleteById(id);
    } else {
        throw new IllegalArgumentException("Account with id " + id + " does not exist");
    }
}


public void update(Account account) {
    Long accountId = account.getId();
    Optional<Account> optionalAccount = accountRepo.findById(accountId);
    if (optionalAccount.isPresent()) {
        Account existingAccount = optionalAccount.get();
        existingAccount.setBalance(account.getBalance());
        // Set other fields that you want to update here

        accountRepo.save(existingAccount);
    } else {
        throw new IllegalArgumentException("Account with id " + accountId + " does not exist");
    }
}

public void updateById(Long id, Account updatedAccount) {
    Optional<Account> account = accountRepo.findById(id);
    if (account.isPresent()) {
        Account existingAccount = account.get();
        existingAccount.setAccountHolderName(updatedAccount.getAccountHolderName());
        existingAccount.setBalance(updatedAccount.getBalance());
       

        accountRepo.save(existingAccount);
    } else {
        throw new IllegalArgumentException("Account with ID " + id + " does not exist");
    }
}






}