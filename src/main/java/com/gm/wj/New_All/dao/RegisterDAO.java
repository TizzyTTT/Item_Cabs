package com.gm.wj.New_All.dao;

import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.Register;
import com.gm.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterDAO extends JpaRepository<Register,Integer> {

    Register findById(int id);

    List<Register> findByChemicalsIn(List<Chemicals> list);


//    List<Register> findAllByChemicalnameLikeOrChemicals()
//    findAllByChemicalcategoryAndChemicalnameLikeOrChemicalnoLike

}
