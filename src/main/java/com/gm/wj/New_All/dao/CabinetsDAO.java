package com.gm.wj.New_All.dao;

import com.gm.wj.New_All.entity.Cabinets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetsDAO extends JpaRepository<Cabinets,Integer> {

     Cabinets findById(int cid);
}
