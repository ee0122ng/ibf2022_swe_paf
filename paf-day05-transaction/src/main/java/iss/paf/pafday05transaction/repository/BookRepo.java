package iss.paf.pafday05transaction.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import iss.paf.pafday05transaction.model.Book;

@Repository
public class BookRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_SQL = "select * from book";
    private static final String INSERT_SQL = "insert into book (title, quantity) values (?, ?)";
    private static final String UPDATE_SQL = "update book set quantity = quantity - 1 where id = ?";
    
    public List<Book> getAllBook() {

        return jdbcTemplate.query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Integer updateBook(Integer id) {

        return jdbcTemplate.update(UPDATE_SQL, id);
    }

    public Integer insertNewBook(Book book) {

        // create GeneratedKeyHolder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator(){

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // the prepared statement will hold sql statement and the column name "id"
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setString(1, book.getTitle());
                ps.setInt(2, book.getQuantity());
                return ps;
            }
        };

        // generatedKeyHolder to hold the auto-generated id
        int rowsAffected = jdbcTemplate.update(psc, generatedKeyHolder);

        // generated auto-incremented Id
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }

}
