package com.hws.home_web_studio.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Long>{
    List<UserModel> findByUserName(String userName);
}
