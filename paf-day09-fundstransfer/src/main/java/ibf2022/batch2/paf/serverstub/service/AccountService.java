package ibf2022.batch2.paf.serverstub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.Repository.AccountRepository;
import ibf2022.batch2.paf.serverstub.exception.ResourceNotFoundException;
import ibf2022.batch2.paf.serverstub.model.Account;
import ibf2022.batch2.paf.serverstub.model.Transaction;

@Service
public class AccountService {

    @Autowired
    AccountRepository accRepo;

    public List<Account> retrieveAllAccount() {

        return accRepo.getAllAccount();
    }

    @Transactional
    public Transaction transferFund(String nameAccountFrom, String nameAccountTo, Float amount) {

        // 1. check if the accounts is valid
        // 2. check if the balance for the accountFrom is sufficient
        // 3. check if updates to and from are valid
        // 4. check if the transfer completed

        Boolean bAccountFromSufficient = false;
        Boolean bUpdateAccountFrom = false;
        Boolean bUpdateAccountTo = false;

        try {
            Account accountFrom = accRepo.getCountByName(nameAccountFrom);
            Account accountTo = accRepo.getCountByName(nameAccountTo);

            bAccountFromSufficient = accountFrom.getBalance() >= amount;

            if (bAccountFromSufficient) {

                // check if update for accountFrom successful
                bUpdateAccountFrom = accRepo.updateFromAccount(accountFrom.getId(), amount);
                bUpdateAccountTo = accRepo.updateToAccount(accountTo.getId(), amount);

                // update failed

                if (bUpdateAccountFrom && bUpdateAccountTo) {
                    
                    Transaction transaction = new Transaction();
                    transaction.setAccountId(accountFrom.getId());
                    transaction.setAmount(amount);
                    transaction.setUserFrom(accountFrom.getAccountName());
                    transaction.setUserTo(accountTo.getAccountName());

                    Integer transactionId = accRepo.insertTransaction(transaction);
                    if (null != transactionId) {
                        transaction.setId(transactionId);
                        return transaction;

                    } else {

                        throw new ResourceNotFoundException("Transaction failed, please try again.");
                    }
                    

                } else {

                    throw new ResourceNotFoundException("Update to accounts failed, please try again.");

                }

            } else {

                throw new ResourceNotFoundException("Insufficient amount to be transferred from account = '%d'".formatted(accountFrom.getId()));
            }


        } catch (Exception ex) {

            throw new ResourceNotFoundException(ex.getLocalizedMessage());
        }



    }
    
}
