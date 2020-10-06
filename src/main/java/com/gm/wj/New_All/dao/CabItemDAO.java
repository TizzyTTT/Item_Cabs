package com.gm.wj.New_All.dao.cabs;

import com.gm.wj.New_All.entity.CabItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabItemDAO extends JpaRepository<CabItem, Integer> {
    List<CabItem> findAllByCabid(int cabid);
}
