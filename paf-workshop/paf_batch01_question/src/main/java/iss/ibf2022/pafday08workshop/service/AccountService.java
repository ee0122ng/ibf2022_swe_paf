package iss.ibf2022.pafday08workshop.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import iss.ibf2022.pafday08workshop.exception.TransferInvalidException;
import iss.ibf2022.pafday08workshop.model.Account;
import iss.ibf2022.pafday08workshop.payload.TransferPayload;
import iss.ibf2022.pafday08workshop.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accRepo;

    public Account retrieveAccountById(String id) {

        return accRepo.getAccountById(id);
    }

    public List<Account> retrieveAllAccount() {
        
        return accRepo.getAllAccount();
    }

    @Transactional
    public TransferPayload performTransfer(MultiValueMap<String, String> form) {

        String accountToId = form.getFirst("accountTo");
        String accountFromId = form.getFirst("accountFrom");
        String comment = form.getFirst("comment");

        // check if amount if blank
        if (null==form.getFirst("amount") || form.getFirst("amount").isEmpty()) {
            throw new TransferInvalidException("Amount cannot be blank");
        }
        Float amount = Float.valueOf(form.getFirst("amount"));

        // conditions to check
        // c0: both accounts exist
        // c1: account id should be at least 10 characters
        // c2: accounts number should not be the same for both from and to
        // c3: the transfer amount is not <=0
        // c4: the minimum transfer amount should be 10
        // c5: the from account has sufficient fund
        Boolean validAccounts = false;
        Boolean validTransferToAccount = false;
        Boolean validTransferAmount = false;
        Boolean validMinimumAmount = false;
        Boolean isSufficient = false;

        // check c0
        try {

            Account accountFrom = accRepo.getAccountById(accountFromId);
            Account accountTo = accRepo.getAccountById(accountToId);
            TransferPayload payload = new TransferPayload();

            // check c1
            if (accountFrom.getAccountId().length() != 10) {
                throw new TransferInvalidException("Account from id should have 10 characters");
            } else if (accountTo.getAccountId().length() != 10) {
                throw new TransferInvalidException("Account to id should have 10 characters");
            } else {
                validAccounts = true; 
            }

            // check c2
            if (validAccounts) {

                validTransferToAccount = !accountFrom.getAccountId().equals(accountTo.getAccountId());

                if (validTransferToAccount) {

                    // check c3
                    validTransferAmount = amount > 0f;
                    if (validTransferAmount) {

                        // check c4
                        validMinimumAmount = amount >= 10f;
                        if (validMinimumAmount) {

                            // check c5
                            isSufficient = accountFrom.getBalance() >= amount;
                            if (isSufficient) {

                                // check if transaction is successful
                                Boolean updatedAccountFrom = accRepo.updateAccountFrom(accountFromId, amount);
                                Boolean updatedAccountTo = accRepo.updateAccountTo(accountToId, amount);
                                if (updatedAccountFrom && updatedAccountTo) {

                                    payload.setAmount(amount);
                                    payload.setAccountFromId(accountFrom.getAccountId());
                                    payload.setAccountFromName(accountFrom.getAccountName());
                                    payload.setAccountToId(accountTo.getAccountId());
                                    payload.setAccountToName(accountTo.getAccountName());
                                    payload.setDate(new Date());
                                    payload.setTransactionId(UUID.randomUUID().toString().substring(0, 8));

                                } else {

                                    throw new TransferInvalidException("Update transaction failed");

                                }


                            } else {

                                throw new TransferInvalidException("There is insufficient amount");

                            }

                        } else {

                            throw new TransferInvalidException("Minimum transfer amount is 10");

                        }


                    } else {

                        throw new TransferInvalidException("Transfer amount should not be less than 0");
                    }

                } else {

                    throw new TransferInvalidException("Transfer invalid. Same account are selected");

                }

            }

            return payload;

        } catch (Exception ex) {

            throw new TransferInvalidException(ex.getLocalizedMessage());
        }

    }
    
}
