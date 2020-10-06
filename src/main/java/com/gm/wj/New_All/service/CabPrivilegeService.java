package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.cabs.CabPrivilegeDAO;
import com.gm.wj.New_All.entity.CabUser;
import com.gm.wj.New_All.utils.CabUser2;
import com.gm.wj.New_All.utils.SimpleUser;
import com.gm.wj.entity.User;
import com.gm.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CabPrivilegeService {

    @Autowired
    CabPrivilegeDAO cabPrivilegeDAO;

    @Autowired
    UserService userService;

    @Autowired
    CabService cabService;

    @Transactional
    public void saveCabPrivilege(CabUser2 cabUser2){
        //将cabuser2 转化成 cabuser
        int id = cabUser2.getId();
        CabUser exists = cabPrivilegeDAO.findById(id);
        CabUser cabUser = new CabUser();

        if(exists != null){
            //找到了！！ 修改记录
            cabUser.setId(id);//id 一定要放进去
        }else {
            //未找到id对应的记录，则新增一条记录
        }
        cabUser.setpIn(cabUser2.getpIn());
        cabUser.setpOpen(cabUser2.getpOpen());
        cabUser.setpOut(cabUser2.getpOut());

        User user = userService.findById(cabUser2.getUserid());
        cabUser.setUser(new SimpleUser(user));
        cabUser.setCabinets(cabService.findById(cabUser2.getCabid()));
        cabPrivilegeDAO.save(cabUser);

    }

    public List<CabUser> list(int cid){
        return cabPrivilegeDAO.findAllByCabinets(cabService.findById(cid));
    }

}
