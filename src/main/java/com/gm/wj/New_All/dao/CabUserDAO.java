package com.gm.wj.New_All.dao.cabs;

import com.gm.wj.New_All.entity.CabUser;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.New_All.utils.SimpleUser;
import com.gm.wj.entity.User;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabUserDAO extends JpaRepository<CabUser, Integer> {
    List<CabUser> findCabAllByUser(SimpleUser user);
}
