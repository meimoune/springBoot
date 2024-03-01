package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long> {

    
}