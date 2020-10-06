package com.gm.wj.New_All.dao.record;

import com.gm.wj.New_All.entity.ChemicallossDetail;
import com.gm.wj.New_All.entity.Loss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LossDAO extends JpaRepository<Loss, Integer> {

}
