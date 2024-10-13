package org.example.repository.Native.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.Native.CustomerNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class CustomerNativeRepositoryImpl implements CustomerNativeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Integer findCustomerCodeByEmail(String email){
        try {
            String sql = "select customerCode from CustomerTable where email=:email2";
            Map<String, Object> params = new HashMap<>();
            params.put("email2", email);

            return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        }catch (DataAccessException ex){
            return null;
        }
    }
}

//  example for - get (fetch) list of customers
//            String sql = "SELECT * FROM customers WHERE city = :city AND year_of_birth = :yearOfBirth";
//            Map<String, Object> params = new HashMap<>();
//            params.put("city", "New York");
//            params.put("yearOfBirth", 1985);
//            List<Customer> customers = namedParameterJdbcTemplate.query(sql, params, new CustomerRowMapper());

// example for - update a record of the table
//    Map<String, Object> params = new HashMap<>();
//    params.put("firstName", "John");
//    params.put("customerId", 123);
//
//    namedParameterJdbcTemplate.update(sql, params);

// example for - update a record of the table
//    Customer customer = new Customer("John", 123);
//    SqlParameterSource paramSource = new BeanPropertySqlParameterSource(customer);
//
//    namedParameterJdbcTemplate.update(sql, paramSource);
