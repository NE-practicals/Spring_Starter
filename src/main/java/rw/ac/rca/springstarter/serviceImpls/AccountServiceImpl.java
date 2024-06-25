package rw.ac.rca.springstarter.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.dto.requests.CreateAccountDto;
import rw.ac.rca.springstarter.dto.requests.DepositDto;
import rw.ac.rca.springstarter.dto.requests.TransferDto;
import rw.ac.rca.springstarter.dto.requests.WithdrawDto;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.exceptions.NotFoundException;
import rw.ac.rca.springstarter.model.Account;
import rw.ac.rca.springstarter.model.Customer;
import rw.ac.rca.springstarter.repositories.AccountRepository;
import rw.ac.rca.springstarter.repositories.CustomerRepository;
import rw.ac.rca.springstarter.services.AccountService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    public Account createAccount(CreateAccountDto accountDto) {
           try{
               //check if account does not exist
                if(accountRepository.findByAccountNumber(accountDto.getAccountNumber()).isPresent()){
                     throw new BadRequestException("Account already exist");
                }
               Customer customer= customerRepository.findById(accountDto.getCustomerId()).orElseThrow(()->new NotFoundException("customer not found"));
                //create a new account
                Account account= new Account();
                account.setAccountNumber(accountDto.getAccountNumber());
                account.setBalance(accountDto.getBalance());
                account.setCustomer(customer);

                //save the account
                return accountRepository.save(account);


           }catch (Exception e){
               ExceptionUtils.handleServiceExceptions(e);
               return null;
           }
    }

    @Override
    public Account updateAccount(Long id, CreateAccountDto accountDto) {
        try {
            //check if account does not exist
            Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
            Customer customer = customerRepository.findById(accountDto.getCustomerId()).orElseThrow(() -> new NotFoundException("customer not found"));
            //update the account
            account.setAccountNumber(accountDto.getAccountNumber());
            account.setBalance(accountDto.getBalance());
            account.setCustomer(customer);
            return accountRepository.save(account);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
    @Override
    public void deleteAccount(Long id) {
        try {
            //check if account does not exist
            Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
            //delete the account
            accountRepository.delete(account);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }

    }

    @Override
    public List<Account> getAllAccounts() {
        try{
            return accountRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    public Account deposit(DepositDto depositDto) {
          try{
    //Retrieve the account
              Account account = accountRepository.findById(depositDto.getAccountId()).orElseThrow(() -> new NotFoundException("Account not found"));
              //Deposit the amount
              account.setBalance(account.getBalance() + depositDto.getAmount());
              return accountRepository.save(account);
          }catch (Exception e){
              ExceptionUtils.handleServiceExceptions(e);
              return null;
          }

    }

    @Override
    public Account withdraw(WithdrawDto withdrawDto) {

        try{
            //Retrieve the account
            Account account = accountRepository.findById(withdrawDto.getAccountId()).orElseThrow(() -> new NotFoundException("Account not found"));
            //check if the account has enough balance
            if(account.getBalance()<withdrawDto.getAmount()){
                throw new BadRequestException("Insufficient balance");
            }
            //Withdraw the amount
            account.setBalance(account.getBalance() - withdrawDto.getAmount());
            return accountRepository.save(account);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public void transfer(TransferDto transferDto) {

        try{
            //Retrieve the accounts
            Account accountFrom = accountRepository.findById(transferDto.getFromAccountId()).orElseThrow(() -> new NotFoundException("Account not found"));
            Account accountTo = accountRepository.findById(transferDto.getToAccountId()).orElseThrow(() -> new NotFoundException("Account not found"));
            //check if the account has enough balance
            if(accountFrom.getBalance()<transferDto.getAmount()){
                throw new BadRequestException("Insufficient balance");
            }
            //Withdraw the amount
            accountFrom.setBalance(accountFrom.getBalance() - transferDto.getAmount());
            accountRepository.save(accountFrom);
            //Deposit the amount
            accountTo.setBalance(accountTo.getBalance() + transferDto.getAmount());
            accountRepository.save(accountTo);

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
        }

    }
}
