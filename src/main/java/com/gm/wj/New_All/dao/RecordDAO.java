package com.gm.wj.New_All.dao;

import com.gm.wj.New_All.entity.Record;
import com.gm.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RecordDAO extends JpaRepository<Record,Integer> {

    List<Record> findByCabidAndTimeAfterAndUserOrAduser(int cid,  Date time, User user1,User user2);

}
