package com.example.organization.repository;

import com.example.organization.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByNormalizedName(String normalizedName);

    boolean existsByEmail(String email);

    int deleteByEmail(String email);


}
