package org.example.repository.Native.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.Native.CustomerNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class CustomerNativeRepositoryImpl implements CustomerNativeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Integer findCustomerCodeByEmail(String email){
        try {
            Integer customerCodeByEmailFromDb =
                    namedParameterJdbcTemplate.queryForObject(
                            "select customerCode from CustomerTable where email=:email",

                    );

            Integer testing = jdbcTemplate.
            return null;
        }catch (DataAccessException ex){
            return null;
        }
    }


}
