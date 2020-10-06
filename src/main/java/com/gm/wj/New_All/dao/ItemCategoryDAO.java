package com.gm.wj.New_All.dao.item;

import com.gm.wj.New_All.entity.Itemcategory;
import com.gm.wj.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCategoryDAO extends JpaRepository<Itemcategory, Integer> {

    List<Itemcategory> findAllByOrganization(Organization o);
    Itemcategory findById(int id);
}
