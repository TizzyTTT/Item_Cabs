package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.record.ChemicallossDetailDAO;
import com.gm.wj.New_All.entity.ChemicallossDetail;
import com.gm.wj.New_All.utils.MaxUse;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChemicallossDetailService {

    @Autowired
    ChemicallossDetailDAO chemicallossDetailDAO;

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserService userService;

    @Autowired
    ChemicalBasicService chemicalBasicService;

    @Transactional
    public List<MaxUse> getMaxLoss(){
        List<MaxUse> result = new ArrayList<MaxUse>();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        int orgid = org.getId();
        String sql ="select c.chemicalname as chemicalname , sum(cd.account) as sum_loss " +
                "from loss cl join chemicalloss_detail cd join chemicals c " +
                "where c.id =  cd.chemicalid  and cl.id = cd.lossid and cl.orgid= "+orgid +
                " group by cd.chemicalid order by sum(cd.account) desc limit 0,7";
        System.out.println("sql : "+sql);
        Query query = em.createNativeQuery(sql);
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

    public List<ChemicallossDetail> listByChemicalid(int cid){
        List<ChemicallossDetail> list = chemicallossDetailDAO.findAllByChemicals(chemicalBasicService.findById(cid));
        System.out.println("list.size() "+ list.size());
        return chemicallossDetailDAO.findAllByChemicals(chemicalBasicService.findById(cid));
    }

}
