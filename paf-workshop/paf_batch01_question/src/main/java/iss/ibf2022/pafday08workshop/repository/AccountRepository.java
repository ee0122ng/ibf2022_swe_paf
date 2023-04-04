package iss.ibf2022.pafday08workshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafday08workshop.model.Account;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_ACCOUNT_SQL = "select * from accounts";
    private final String SELECT_ACCOUNT_BY_ID_SQL = "select * from accounts where account_id = ?";
    private final String UPDATE_ACCOUNT_FROM_SQL = "update accounts set balance=balance-? where account_id = ?";
    private final String UPDATE_ACCOUNT_TO_SQL = "update accounts set balance=balance+? where account_id = ?";

    public List<Account> getAllAccount() {

        return jdbcTemplate.query(SELECT_ALL_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(Account.class));
        
    }
    public Account getAccountById(String id) {

        try {
            Account account = jdbcTemplate.queryForObject(SELECT_ACCOUNT_BY_ID_SQL, BeanPropertyRowMapper.newInstance(Account.class), id);

            return account;

        } catch (Exception ex) {

            // throw new AccountResourceException("Account id='%s' provided not found".formatted(id));
            throw ex;
        }
        
    }

    public Boolean updateAccountFrom(String id, Float amount) {

        Integer updated = jdbcTemplate.update(UPDATE_ACCOUNT_FROM_SQL, amount, id);

        return updated > 0 ? true : false;
    }

    public Boolean updateAccountTo(String id, Float amount) {

        Integer updated = jdbcTemplate.update(UPDATE_ACCOUNT_TO_SQL, amount, id);

        return updated > 0 ? true : false;
    }

    
    
}
