package iss.paf.pafday05transaction.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import iss.paf.pafday05transaction.model.BankAccount;

@Repository
public class BankAccountRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String CREATE_ACCOUNT_SQL = "insert into accounts (full_name, is_active, acc_type, balance) values (?, ?, ?, ?)";
    private static final String CHECK_BALANCE_SQL = "select balance from accounts where id = ?";
    private static final String GET_ACCOUNT_SQL = "select * from accounts where id = ?";
    private static final String WITHDRAWAL_SQL = "update accounts set (balance = balance - ?) where id = ?";
    private static final String DEPOSIT_SQL = "update accounts set (balance = balance + ?) where id = ?";

    public Boolean checkBalance(Integer id, Float withdrawnAmount) {

        Boolean bWithdrawnBalanceAvailable = false;

        // condition 1: check if account balance is sufficient
        Float balance = jdbcTemplate.queryForObject(CHECK_BALANCE_SQL, Float.class, id);
        if (withdrawnAmount <= balance) {

            bWithdrawnBalanceAvailable = true;
        }

        return bWithdrawnBalanceAvailable;
    }

    public BankAccount retrieveAccountDetails(Integer id) {

        BankAccount acc = jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, BankAccount.class, id);

        return acc;
    }

    public Boolean withdrawAmount(Integer id, Float amount) {

        Integer result = 0;

        result = jdbcTemplate.update(WITHDRAWAL_SQL, amount, id);

        return result > 0 ? true: false;
    }

    public Boolean depositAmount(Integer id, Float amount) {

        Integer result = 0;

        result = jdbcTemplate.update(DEPOSIT_SQL, amount, id);

        return result > 0 ? true : false;
    }

    public Boolean createAccount(BankAccount acc) {

        return jdbcTemplate.execute(CREATE_ACCOUNT_SQL, new PreparedStatementCallback<Boolean>() {
            
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, acc.getFullName());
                ps.setBoolean(2, acc.getIsActive());
                ps.setString(3, acc.getAccType());
                ps.setFloat(4, acc.getBalance());

                Boolean rslt = ps.execute();

                return rslt;
            }
        });
    }
    
}

