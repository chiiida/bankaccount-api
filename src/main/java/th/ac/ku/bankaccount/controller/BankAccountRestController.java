package th.ac.ku.bankaccount.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.bankaccount.data.BankAccountRepository;
import th.ac.ku.bankaccount.model.BankAccount;
import th.ac.ku.bankaccount.model.Transaction;
import th.ac.ku.bankaccount.model.TransactionType;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount) {
        repository.save(bankAccount);
        return bankAccount;
    }

    @PutMapping("transaction/{id}")
    public BankAccount makeTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        BankAccount record = repository.findById(id).get();

        if (transaction.getType() == TransactionType.DEPOSIT) {
            record.deposit(transaction.getAmount());
        } else if (transaction.getType() == TransactionType.WITHDRAW) {
            record.withdraw(transaction.getAmount());
        }

        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id) {
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }
}
