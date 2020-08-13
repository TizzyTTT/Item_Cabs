package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.CabinetsDAO;
import com.gm.wj.New_All.entity.CabItem;
import com.gm.wj.New_All.entity.Cabinets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabService {

    @Autowired
    CabinetsDAO cabinetsDAO;

    @Autowired
    CabItemService cabItemService;

    public List<Cabinets> list(){
       return cabinetsDAO.findAll();
    }

    /*
    获取某一个柜子的药品存放信息
     */
    public List<CabItem> getOne(int cid){
        return cabItemService.getAllByCabid(cid);
    }

    public void editOrAdd(Cabinets cabinets){
        cabinetsDAO.save(cabinets);
    }

    public void updateCabItem(List<CabItem> list){
        cabItemService.saveAll(list);
    }


}
