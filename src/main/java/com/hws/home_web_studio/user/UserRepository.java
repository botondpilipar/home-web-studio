package com.hws.home_web_studio.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    List<UserModel> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
