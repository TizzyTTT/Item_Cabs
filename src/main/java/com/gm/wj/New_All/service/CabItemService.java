package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.CabItemDAO;
import com.gm.wj.New_All.entity.CabItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabItemService {


    @Autowired
    CabItemDAO cabItemDAO;

    public List<CabItem> getAllByCabid(int cabid){
        return cabItemDAO.findAllByCabid(cabid);
    }

    public void saveAll(List<CabItem> list){
        cabItemDAO.saveAll(list);
    }

}
