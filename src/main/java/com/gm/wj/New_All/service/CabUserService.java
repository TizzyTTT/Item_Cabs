package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.cabs.CabUserDAO;
import com.gm.wj.New_All.entity.CabUser;
import com.gm.wj.New_All.utils.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabUserService {

    @Autowired
    CabUserDAO cabUserDAO;

    //who can use the cab;
    public List<CabUser> findCabByUser(SimpleUser user){
        return cabUserDAO.findCabAllByUser(user);
    }




}
