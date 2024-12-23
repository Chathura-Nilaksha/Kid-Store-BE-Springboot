package org.example.repository.Native.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.CustomerEntity;
import org.example.exeptions.EmailNotExisting;
import org.example.repository.Native.CustomerNativeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomerNativeRepositoryImpl implements CustomerNativeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Integer findCustomerCodeByEmail(String email){
        try {
            String sql = "select customerCode from CustomerEntity where email=:email2";
            Map<String, Object> params = new HashMap<>();
            params.put("email2", email);

            return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        }catch (DataAccessException ex){
            throw new EmailNotExisting("Email address isn't of a registered customer");
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomerAllDetailsByEmail(String email) {
        try {
            String sql = "select * from CustomerEntity where email=:email3";
            // Collections.singletonMap("email3", email); or below Map and params.
            Map<String, Object> params = new HashMap<>();
            params.put("email3", email);
            CustomerEntity customerEntity = namedParameterJdbcTemplate
                                                    .queryForObject(sql, params, CustomerEntity.class);

            return Optional.ofNullable(customerEntity);
        }catch (DataAccessException ex){
            return Optional.empty();
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
