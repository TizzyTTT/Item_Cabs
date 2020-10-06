package com.gm.wj.dao;

import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
    List<AdminRole> findAllByOrganization(Organization o);
}
