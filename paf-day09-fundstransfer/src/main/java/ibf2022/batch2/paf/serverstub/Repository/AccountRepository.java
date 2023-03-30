package ibf2022.batch2.paf.serverstub.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.exception.ResourceNotFoundException;
import ibf2022.batch2.paf.serverstub.model.Account;
import ibf2022.batch2.paf.serverstub.model.Transaction;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String FIND_ALL_RECORD = "select * from accounts";

    // to find account based on name
    private final String FIND_ACCOUNT_BY_NAME_SQL = "select * from accounts where account_name = ?";

    // update the account balance accordingly
    private final String UPDATE_FROM_ACCOUNT_SQL = "update accounts set balance=balance-? where id=?";
    private final String UPDATE_TO_ACCOUNT_SQL = "update accounts set balance=balance+? where id=?";    

    // to insert record to transaction
    private final String INSERT_TRANSACTION_SQL = "insert into transactions(account_id, user_from, user_to, amount) values(?, ?, ?, ?)";

    public List<Account> getAllAccount() {

        return jdbcTemplate.query(FIND_ALL_RECORD, BeanPropertyRowMapper.newInstance(Account.class));
        
    }

    public Account getCountByName(String name) {

        try {

            Account account = jdbcTemplate.queryForObject(FIND_ACCOUNT_BY_NAME_SQL, BeanPropertyRowMapper.newInstance(Account.class), name);
            return account;

        } catch (Exception ex) {

             throw new ResourceNotFoundException("Name provided '%s' not found in the system record".formatted(name));
        }
    }

    public Boolean updateFromAccount(Integer id, Float amount) {

        Integer rowAffected = jdbcTemplate.update(UPDATE_FROM_ACCOUNT_SQL, amount, id);

        return rowAffected > 0 ? true: false;
    }

    public Boolean updateToAccount(Integer id, Float amount) {

        Integer rowAffected =jdbcTemplate.update(UPDATE_TO_ACCOUNT_SQL, amount, id);

        return rowAffected > 0 ? true : false;
    }

    public Integer insertTransaction(Transaction tran) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
                
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(INSERT_TRANSACTION_SQL, new String[]{"id"});
                ps.setInt(1, tran.getAccountId());
                ps.setString(2, tran.getUserFrom());
                ps.setString(3, tran.getUserTo());
                ps.setFloat(4, tran.getAmount());

                return ps;
            }
        };

        Integer affectedRow = jdbcTemplate.update(psc, generatedKeyHolder);
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return affectedRow > 0 ? returnedId : null;
    }
}
