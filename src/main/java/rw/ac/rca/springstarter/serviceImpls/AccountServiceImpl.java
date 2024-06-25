package rw.ac.rca.springstarter.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.dto.requests.CreateAccountDto;
import rw.ac.rca.springstarter.dto.requests.DepositDto;
import rw.ac.rca.springstarter.dto.requests.TransferDto;
import rw.ac.rca.springstarter.dto.requests.WithdrawDto;
import rw.ac.rca.springstarter.enums.ETransactionType;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.exceptions.NotFoundException;
import rw.ac.rca.springstarter.model.Account;
import rw.ac.rca.springstarter.model.Customer;
import rw.ac.rca.springstarter.model.Message;
import rw.ac.rca.springstarter.model.Transaction;
import rw.ac.rca.springstarter.repositories.IAccountRepository;
import rw.ac.rca.springstarter.repositories.ICustomerRepository;
import rw.ac.rca.springstarter.repositories.IMessageRepository;
import rw.ac.rca.springstarter.repositories.ITransactionRepository;
import rw.ac.rca.springstarter.services.AccountService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final IAccountRepository accountRepository;
    private final ICustomerRepository customerRepository;
    private final ITransactionRepository transactionRepository;
    private final MailServiceImpl mailService;
    private final IMessageRepository messageRepository;
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
    public Account getAccountByCustomerId(Long customerId) {
        try{
            //if account does not exist

            return accountRepository.findByCustomerId(customerId).orElseThrow(()->new NotFoundException("Account not found"));
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Account deposit(DepositDto depositDto) {
          try{
    //Retrieve the account
              Account account = accountRepository.findById(depositDto.getAccountId()).orElseThrow(() -> new NotFoundException("Account not found"));
              //Deposit the amount
              account.setBalance(account.getBalance() + depositDto.getAmount());
              Transaction transaction = new Transaction();
              transaction.setAmount(depositDto.getAmount());
              transaction.setTransactionType(ETransactionType.DEPOSIT);
              transaction.setAccount(account);
                //save the transaction
                transactionRepository.save(transaction);
                mailService.sendBankOperationEmail(
                        "Deposit",
                        depositDto.getAmount(),
                        account.getAccountNumber(),
                        "Saving Account",
                        account.getCustomer().getUser().getUsername(),
                        account.getCustomer().getUser().getEmail()
                );
                Message message= new Message();
                message.setCustomer(account.getCustomer());
                message.setMessage("You have deposited "+depositDto.getAmount()+" RWF to your account");
                messageRepository.save(message);

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
            Transaction transaction = new Transaction();
            transaction.setAmount(withdrawDto.getAmount());
            transaction.setTransactionType(ETransactionType.WITHDRAWAL);
            transaction.setAccount(account);
            //save the transaction
            transactionRepository.save(transaction);
            Account account1= accountRepository.save(account);
            mailService.sendBankOperationEmail(
                    "Withdraw",
                    withdrawDto.getAmount(),
                    account.getAccountNumber(),
                    "Saving Account",
                    account.getCustomer().getUser().getUsername(),
                    account.getCustomer().getUser().getEmail()
            );
            Message message = new Message();
            message.setCustomer(account.getCustomer());
            message.setMessage("You have withdrawn "+withdrawDto.getAmount()+" RWF from your account");
            messageRepository.save(message);
            return account1;
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
            //save the transaction
            Transaction transactionFrom = new Transaction();
            transactionFrom.setAmount(transferDto.getAmount());
            transactionFrom.setTransactionType(ETransactionType.TRANSFER);
            transactionFrom.setAccount(accountFrom);
            Transaction transaction1=transactionRepository.save(transactionFrom);
            if(transaction1!=null){
                mailService.sendBankOperationEmail(
                        "Transfer",
                        transferDto.getAmount(),
                        accountFrom.getAccountNumber(),
                        "Saving Account",
                        accountFrom.getCustomer().getUser().getUsername(),
                        accountFrom.getCustomer().getUser().getEmail()
                );            }
                Message message = new Message();
                message.setCustomer(accountFrom.getCustomer());
                message.setMessage("You have transferred "+transferDto.getAmount()+" RWF to "+accountTo.getAccountNumber());
                messageRepository.save(message);

            Transaction transactionTo = new Transaction();
            transactionTo.setAmount(transferDto.getAmount());
            transactionTo.setTransactionType(ETransactionType.TRANSFER);
            transactionTo.setAccount(accountTo);
            Transaction transaction=transactionRepository.save(transactionTo);
            if(transaction!=null){
                mailService.sendBankOperationEmail(
                        "Transfer",
                        transferDto.getAmount(),
                        accountTo.getAccountNumber(),
                        "Saving Account",
                        accountTo.getCustomer().getUser().getUsername(),
                        accountTo.getCustomer().getUser().getEmail()

                );
                Message message1 = new Message();
                message1.setCustomer(accountTo.getCustomer());
                message1.setMessage("You have received "+transferDto.getAmount()+" RWF from "+accountFrom.getAccountNumber());
                messageRepository.save(message1);

            }


        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
        }

    }

    @Override
    public List<Transaction> getAllTransactions() {
        try{
            return transactionRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

}
