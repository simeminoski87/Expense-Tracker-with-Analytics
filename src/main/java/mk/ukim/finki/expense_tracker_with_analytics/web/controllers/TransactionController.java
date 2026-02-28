package mk.ukim.finki.expense_tracker_with_analytics.web.controllers;

import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import mk.ukim.finki.expense_tracker_with_analytics.service.domain.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id){
        return transactionService.findById(id).map(
                t->ResponseEntity.ok().body(t))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Transaction> save(@RequestBody Transaction Transaction) {
        return this.transactionService.save(Transaction)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction Transaction) {
        return this.transactionService.update(id, Transaction)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (transactionService.findById(id).isPresent()) {
            transactionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
