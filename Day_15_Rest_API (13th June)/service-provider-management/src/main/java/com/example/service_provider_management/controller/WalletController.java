package com.example.service_provider_management.controller;

import com.example.service_provider_management.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/add")
    public ResponseEntity<String> addFunds(@RequestParam Long userId, @RequestParam Double amount) {
        walletService.addMoney(userId, amount);
        return ResponseEntity.ok("Money added to wallet.");
    }

    @PostMapping("/pay")
    public ResponseEntity<String> makePayment(@RequestParam Long fromUserId,
                                              @RequestParam Long toUserId,
                                              @RequestParam Double amount,
                                              @RequestParam String category) {
        walletService.transfer(fromUserId, toUserId, amount, category);
        return ResponseEntity.ok("Payment successful.");
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getBalance(userId));
    }
}
