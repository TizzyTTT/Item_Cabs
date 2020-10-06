package com.gm.wj.New_All.dao.record;

import com.gm.wj.New_All.entity.ChemicallossDetail;
import com.gm.wj.New_All.entity.Chemicals;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChemicallossDetailDAO extends JpaRepository<ChemicallossDetail, Integer> {

    List<ChemicallossDetail> findAllByChemicals(Chemicals c);
}
