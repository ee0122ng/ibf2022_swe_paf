package iss.paf.pafday05transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import iss.paf.pafday05transaction.model.BankAccount;
import iss.paf.pafday05transaction.repository.BankAccountRepo;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepo bankAccRepo;
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean transferMoney (Integer accFromId, Integer accToId, Float amount) {

        // conditions to meet:
        // 1. check both accounts are active
        // 2. check accFrom have sufficient money
        // 3. check transfer is successful 
        Boolean bTransferred = false;
        Boolean bWithdrawn = false;
        Boolean bDeposited = false;
        Boolean bCanWithdraw = false;

        // check condition 1
        BankAccount withdrawalAcc = bankAccRepo.retrieveAccountDetails(accFromId);
        BankAccount depositAcc = bankAccRepo.retrieveAccountDetails(accToId);
        bWithdrawn = withdrawalAcc.getIsActive();
        bDeposited = depositAcc.getIsActive();

        // check condition 2
        if (bWithdrawn) {
            bCanWithdraw = bankAccRepo.checkBalance(accFromId, amount);
        }
        
        // check conditon 3 - involve actions that should meet certain requirements first
        if (bCanWithdraw) {

            // simulate error happens during withdrawal
            // NOTE: for this, if transaction not defined, there will be no consistency
            if (!bankAccRepo.withdrawAmount(accFromId, amount)) {
                throw new IllegalArgumentException("Simulate error before withdrawal");
            }

            bTransferred = (bankAccRepo.withdrawAmount(accFromId, amount) && bankAccRepo.depositAmount(accToId, amount));
        }
        

        return bTransferred && bWithdrawn && bDeposited && bCanWithdraw;
    }
}
