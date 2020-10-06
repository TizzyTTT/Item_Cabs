package com.gm.wj.New_All.dao.cabs;

import com.gm.wj.New_All.entity.Cabinets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinetsDAO extends JpaRepository<Cabinets,Integer> {
     Cabinets findById(int cid);
}
