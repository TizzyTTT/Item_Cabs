package com.gm.wj.New_All.dao.cabs;

import com.gm.wj.New_All.entity.CabUser;
import com.gm.wj.New_All.entity.Cabinets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabPrivilegeDAO extends JpaRepository<CabUser,Integer> {

    List<CabUser> findAllByCabinets(Cabinets cabinets);

    CabUser findById(int id);

}
