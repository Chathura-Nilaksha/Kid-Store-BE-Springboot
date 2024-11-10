package org.example.repository;

import org.example.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    //String existsByEmail(String email);
    //Page<Integer> findCustomerCodeByEmail(String email, PageRequest pageRequest);
    @Query(nativeQuery=true, value ="SELECT * FROM CustomerEntity WHERE email=?1") //this is SQL.
          //When "nativeQuery=true" means it is Native query to DB. it means SQLanguage(SQL).
    //@Query(nativeQuery=false, value ="SELECT c FROM CustomerEntity c WHERE c.email=?1").//this is JPQL.
          //When "nativeQuery=false" means it is not Native query to DB. it means JPQLanguage(JPQL).
                                        // (JPQLanguage comes with JPA)
    //@Query( value ="SELECT c FROM CustomerEntity c WHERE c.email=?1" )
    //above all 3 are ok. as default comes "nativeQuery=false"
    CustomerEntity findCustomerCodeByEmail(String email);
    @Query(nativeQuery=false, value ="SELECT COUNT (c)>0 FROM CustomerEntity c WHERE c.email=?1")
    boolean existsByEmail (String mail);

/*
    @Query("SELECT COUNT(c) > 0 FROM CustomerEntity c WHERE c.email =:email2")
    boolean existsByEmail(@Param("email2") String mail); //(@Param("email2")=Here defined the ":email2".
*/

}
