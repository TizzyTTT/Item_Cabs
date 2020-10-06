package com.gm.wj.New_All.dao.chemcials;

import com.gm.wj.New_All.entity.Chemicalcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalscategoryDAO extends JpaRepository<Chemicalcategory,Integer> {

    Chemicalcategory findById(int cid);

}
