package com.gm.wj.New_All.dao.item;

import com.gm.wj.New_All.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<Item, Integer> {

    Item findByItemno(String itemno);
    Item findById(int id);

}
