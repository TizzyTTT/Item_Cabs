package com.gm.wj.New_All.dao;

import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChemicalsDAO extends JpaRepository<Chemicals,Integer> {

    Chemicals findById(int id);

    Chemicals findByChemicalno(String chemicalno);

    void deleteByChemicalno(String chemicalno);

    Chemicals findByChemicalnoLikeOrChemicalnameLike(String keyword1,String keyword2);

    List<Chemicals> findAllByChemicalnameLikeOrChemicalnoLike(String keyword1,String keyword2);

    List<Chemicals> findAllByChemicalcategoryAndChemicalnameLikeOrChemicalnoLike(Chemicalcategory chemicalcategory,String keyword1, String keyword2);

    List<Chemicals> findAllByChemicalcategory(Chemicalcategory chemicalcategory);

}
