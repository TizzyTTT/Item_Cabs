package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.cabs.CabUserDAO;
import com.gm.wj.New_All.dao.cabs.CabinetsDAO;
import com.gm.wj.New_All.entity.CabItem;
import com.gm.wj.New_All.entity.CabUser;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.New_All.utils.MaxUse;
import com.gm.wj.New_All.utils.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CabService {

    @Autowired
    CabinetsDAO cabinetsDAO;

    @Autowired
    CabItemService cabItemService;

    @Autowired
    CabUserDAO cabUserDAO;

    @Autowired
    ChemicalBasicService chemicalBasicService;

    @PersistenceContext
    EntityManager em;

    public List<Cabinets> list(){
       return cabinetsDAO.findAll();
    }

    public List<Cabinets> listforuser(SimpleUser user){
        List<CabUser> list = cabUserDAO.findCabAllByUser(user);

        List<Cabinets> rl = list.stream().map(CabUser::getCabinets).collect(Collectors.toList());;
        return rl;
    }

    /*
    获取某一个柜子的药品存放信息
     */
    public List<CabItem> getOne(int cid){
        return cabItemService.getAllByCabid(cid);
    }

    public Cabinets findById(int cid){
        return cabinetsDAO.findById(cid);
    }

    public int editOrAdd(Cabinets cabinets){
        int cid = cabinets.getId();
        if(cid == -1){
            //是默认值，新增柜子
            cabinetsDAO.save(cabinets);
            return 0;
        }

        Cabinets c = cabinetsDAO.findById(cid);
        if(c == null){
            //有id，但没找到，说明是假的柜子，发送者乱写的id
            return -1;
        }
        cabinetsDAO.save(cabinets);
        return 0;
    }

    public int updateCabItem(List<CabItem> list){
        return cabItemService.saveAll(list);
    }

    public List<MaxUse> getChemicalDistribute(int chemicalid){
        List<MaxUse> result = new ArrayList<MaxUse>();
        String sql ="select c.cabno , sum(amount) as sum from cab_item ct join cabinets  c where  ct.cabid = c.id and  ct.chemicalid = ? group by cabid;";
        Query query = em.createNativeQuery(sql).setParameter(1,chemicalid);
        List<Object[]> vlist = query.getResultList();
        for(Object[] v : vlist){
            MaxUse entity = new MaxUse();
            entity.setKey((String)v[0]);
            entity.setValue((Double)v[1]);
            System.out.println((String)v[0]+" "+(Double)v[1]);
            result.add(entity);
        }
        return result;
    }

}
