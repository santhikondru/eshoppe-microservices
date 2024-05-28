package com.skon.eshoppe.userservice.repository;

import com.skon.eshoppe.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM USERS u where u.user_name=:userName")
    User getUserByUsername(String userName);

}
