package iss.ibf2022.pafday08workshop.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.ibf2022.pafday08workshop.model.Account;
import iss.ibf2022.pafday08workshop.model.FundTransfer;
import iss.ibf2022.pafday08workshop.payload.TransferPayload;
import iss.ibf2022.pafday08workshop.service.AccountService;
import iss.ibf2022.pafday08workshop.service.LogAuditService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path={""})
public class FundTransferController {

    @Autowired
    AccountService accSvc;

    @Autowired
    LogAuditService auditSvc;

    @GetMapping(path={"/transfer"})
    public String getLandingPage(Model model, HttpSession session) {

        FundTransfer fundTransfer = new FundTransfer();
        List<Account> accounts = accSvc.retrieveAllAccount();
        accounts.sort(Comparator.comparing(Account::getAccountName));
        fundTransfer.setFromAccount(accounts);
        fundTransfer.setToAccount(accounts);

        FundTransfer sessionTransfer = (FundTransfer) session.getAttribute("fundTransfer");
        if(null == sessionTransfer) {
            session.setAttribute("fundTransfer", fundTransfer);
        }

        model.addAttribute("fundTransfer", fundTransfer);
        model.addAttribute("error", "");

        return "view0";
    }

    @PostMapping(path={"/transfer"})
    public String transferFund(@RequestBody MultiValueMap<String, String> form, Model model) {

        try {

            TransferPayload payload = accSvc.performTransfer(form);

            // store the transaction record to redis
            auditSvc.storeTransaction(payload);
            model.addAttribute("payload", payload);

            return "view1";

        } catch (Exception ex) {

            String error = ex.getLocalizedMessage();

            FundTransfer fundTransfer = new FundTransfer();
            List<Account> toAccounts = accSvc.retrieveAllAccount();
            List<Account> fromAccounts = accSvc.retrieveAllAccount();
            toAccounts.sort(Comparator.comparing(Account::getAccountName));
            fromAccounts.sort(Comparator.comparing(Account::getAccountName));
            fundTransfer.setFromAccount(fromAccounts);
            fundTransfer.setToAccount(toAccounts);

            // reset the list of accountTo and accountFrom for fundTransfer
            // so that the selected accounts are displayed first
            String accountToId = form.getFirst("accountTo");
            String accountFromId = form.getFirst("accountFrom");

            // get Account to by id, then swap the first position to the selected account
            Account toAccount = accSvc.retrieveAccountById(accountToId); // selected account
            Account toAccountSwapped = fundTransfer.getToAccount().get(0); // original first in the list
            List<String> idToList = fundTransfer.getToAccount().stream().map(a -> a.getAccountId()).toList();
            Integer idxToAccount = idToList.indexOf(accountToId);
            fundTransfer.getToAccount().set(0, toAccount);
            fundTransfer.getToAccount().set(idxToAccount, toAccountSwapped);

            Account fromAccount = accSvc.retrieveAccountById(accountFromId);
            Account fromAccountSwapped = fundTransfer.getFromAccount().get(0);
            List<String> idFromList = fundTransfer.getFromAccount().stream().map(a -> a.getAccountId()).toList();
            Integer idxFromAccount = idFromList.indexOf(accountFromId);
            fundTransfer.getFromAccount().set(0, fromAccount);
            fundTransfer.getFromAccount().set(idxFromAccount, fromAccountSwapped);

            if (null == form.getFirst("amount") || form.getFirst("amount").isEmpty()) {
                fundTransfer.setAmount(null);
                
            } else {
                fundTransfer.setAmount(Float.valueOf(form.getFirst("amount")));
                
            }
            
            model.addAttribute("fundTransfer", fundTransfer);
            model.addAttribute("error", error);

            return "view0";

        }

    }
    
}
