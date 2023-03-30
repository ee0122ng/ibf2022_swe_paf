package ibf2022.batch2.paf.serverstub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.serverstub.model.Account;
import ibf2022.batch2.paf.serverstub.model.Transaction;
import ibf2022.batch2.paf.serverstub.service.AccountService;
import ibf2022.batch2.paf.serverstub.service.TransactionService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api")
public class FundsTransferController {

	@Autowired
	AccountService accSvc;

	@Autowired
	TransactionService tranSvc;

	@PostMapping(path="/transfer")
	public ResponseEntity<String> postTransfer(@RequestBody MultiValueMap<String, String> transferDetails) {

		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		//
		// Transfer failed return the following JSON object
		// { "message", "Error message" }

		// get the account name
		String nameAccountFrom = transferDetails.getFirst("accountFrom");
		String nameAccountTo = transferDetails.getFirst("accountTo");
		Float amount = Float.valueOf(transferDetails.getFirst("amount"));

		System.out.printf(">>> sample: \n from:%s \n to:%s \n amount:%f".formatted(nameAccountFrom, nameAccountTo, amount));

		try {
			Transaction transaction = accSvc.transferFund(nameAccountFrom, nameAccountTo, amount);

			JsonObject jAccount = Json.createObjectBuilder()
				.add("from", transaction.getUserFrom())
				.add("to", transaction.getUserTo())
				.add("amount", transaction.getAmount())
				.build();

			JsonObject jTransaction = Json.createObjectBuilder()
				.add("transactionId", transaction.getId())
				.add("details", jAccount)
				.build();

			// insert the transaction record to mongodb
			tranSvc.insertTransaction(jTransaction);

			return ResponseEntity.status(HttpStatus.OK).body(jTransaction.toString());

		} catch (Exception ex) {

			String error = ex.getLocalizedMessage();

			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(error);
		}
	}

	@GetMapping(path={"/accounts"})
	public ResponseEntity<List<Account>> getAllAccount() {

		List<Account> accounts = accSvc.retrieveAllAccount();

		return ResponseEntity.status(HttpStatus.OK).body(accounts);
	}
}
