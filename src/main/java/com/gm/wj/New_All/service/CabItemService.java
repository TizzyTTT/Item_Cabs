package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.cabs.CabItemDAO;
import com.gm.wj.New_All.dao.chemcials.ChemicalsDAO;
import com.gm.wj.New_All.entity.CabItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabItemService {


    @Autowired
    CabItemDAO cabItemDAO;
    @Autowired
    ChemicalsDAO chemicalsDAO;

    public List<CabItem> getAllByCabid(int cabid){
        return cabItemDAO.findAllByCabid(cabid);
    }

    public int saveAll(List<CabItem> list){

        for(CabItem c:list){
            if(chemicalsDAO.findById(c.getId()) == null ){
                //药品id都没找到，错误
                return -1;
            }
        }
        cabItemDAO.saveAll(list);
        return 0;
    }



}
