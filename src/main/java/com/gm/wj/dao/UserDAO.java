package com.gm.wj.dao;

import com.gm.wj.entity.Organization;
import com.gm.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User findById(int id);

    User getByUsernameAndPassword(String username,String password);

    List<User> findAllByOrganization(Organization o);
}
