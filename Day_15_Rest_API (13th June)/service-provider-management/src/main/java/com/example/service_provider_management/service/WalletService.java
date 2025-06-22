package com.example.service_provider_management.service;

import com.example.service_provider_management.model.Transaction;
import com.example.service_provider_management.model.Wallet;
import com.example.service_provider_management.repository.TransactionRepository;
import com.example.service_provider_management.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    /**
     * Add money to a user's wallet.
     */
    public void addMoney(Long userId, Double amount) {
        Wallet wallet = walletRepository.findById(userId).orElse(new Wallet(userId, 0.0));
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    /**
     * Transfer money from customer to provider after deducting a fee.
     */
    @Transactional
    public void transfer(Long fromUserId, Long toUserId, Double amount, String category) {
        Wallet sender = walletRepository.findById(fromUserId)
                .orElseThrow(() -> new NoSuchElementException("Sender wallet not found"));

        Wallet receiver = walletRepository.findById(toUserId)
                .orElse(new Wallet(toUserId, 0.0));

        double fee = category.equalsIgnoreCase("premium") ? 0.15 : 0.10;
        double feeAmount = amount * fee;
        double creditedAmount = amount - feeAmount;

        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + creditedAmount);

        walletRepository.save(sender);
        walletRepository.save(receiver);

        Transaction txn = Transaction.builder()
                .senderId(fromUserId)
                .receiverId(toUserId)
                .amount(amount)
                .feePercentage(fee * 100)
                .category(category)
                .build();

        transactionRepository.save(txn);
    }

    /**
     * Get a user's current wallet balance.
     */
    public Double getBalance(Long userId) {
        return walletRepository.findById(userId)
                .map(Wallet::getBalance)
                .orElse(0.0);
    }
}
