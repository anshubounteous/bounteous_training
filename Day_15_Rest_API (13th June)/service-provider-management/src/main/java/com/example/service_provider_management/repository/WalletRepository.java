package com.example.service_provider_management.repository;

import com.example.service_provider_management.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
