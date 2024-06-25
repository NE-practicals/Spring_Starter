package rw.ac.rca.springstarter.services;

import rw.ac.rca.springstarter.dto.requests.CreateAccountDto;
import rw.ac.rca.springstarter.dto.requests.DepositDto;
import rw.ac.rca.springstarter.dto.requests.TransferDto;
import rw.ac.rca.springstarter.dto.requests.WithdrawDto;
import rw.ac.rca.springstarter.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(CreateAccountDto accountDto);
    Account updateAccount(Long id, CreateAccountDto accountDto);
    void deleteAccount(Long id);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account deposit(DepositDto depositDto);
    Account withdraw(WithdrawDto withdrawDto);
    void transfer(TransferDto transferDto);
}
