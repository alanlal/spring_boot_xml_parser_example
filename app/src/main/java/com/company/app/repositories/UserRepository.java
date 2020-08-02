package com.company.app.repositories;

import com.company.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findByName(String val);

    public List<User> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u from USER_DATA u where u.address like %:address%")
    public List<User> findByAddress(String address);
}
