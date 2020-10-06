package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.chemcials.ChemicalsDAO;
import com.gm.wj.New_All.dao.record.OperationTypeDAO;
import com.gm.wj.New_All.dao.record.RecordDAO;
import com.gm.wj.New_All.dao.record.RecordDetailDAO;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.utils.MaxUse;
import com.gm.wj.New_All.utils.QueryForDetail;
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
public class RecordDetailService {

    @Autowired
    RecordDAO recordDAO;

    @Autowired
    RecordDetailDAO recordDetailDAO;

    @Autowired
    OperationTypeDAO operationTypeDAO;

    @Autowired
    ChemicalsDAO chemicalsDAO;

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserService userService;

    public List<RecordDetail> listByRecordid(int rid){
        return recordDetailDAO.findAllByRecord(recordDAO.findById(rid));
    }

    public List<RecordDetail> query(QueryForDetail queryForDetail){

        String chemicalinfo = queryForDetail.getChemicalinfo();
        int operationtype = queryForDetail.getOperationtype();
        int rid = queryForDetail.getRid();
        Chemicals cc = chemicalsDAO.findByChemicalnoLikeOrChemicalnameLike(chemicalinfo,chemicalinfo);

        if(operationtype == -1){
            return recordDetailDAO.findAllByRecordAndChemicals(recordDAO.findById(rid),cc);
        }else return recordDetailDAO.findAllByRecordAndOptypeAndChemicals(recordDAO.findById(rid),operationTypeDAO.findByOptypeId(operationtype),cc);

    }
    public List<RecordDetail> listByChemicalid(int cid){
        Chemicals chemicals = chemicalsDAO.findById(cid);
        if (chemicals == null)return null;
        return recordDetailDAO.findByChemicals(chemicals);
    }

    @Transactional
    public List<MaxUse> getMaxUse(){
        List<MaxUse> result = new ArrayList<MaxUse>();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        int orgid = org.getId();
        String sql ="select chemicals.chemicalname as name, sum(account) as sum_use " +
                "from record rd1 join record_detail rd2  join chemicals " +
                "where rd1.id = rd2.recordid and rd2.optype=1 and chemicals.id = rd2.chemicalid and rd1.orgid = "+orgid+
                " group by rd2.chemicalid order by sum(account) desc limit 0,7";
        System.out.println("SQL: "+sql);
        Query query = em.createNativeQuery(sql);
        List<Object[]> vlist = query.getResultList();
        for(Object[] v : vlist){
            MaxUse entity = new MaxUse();
            entity.setKey((String)v[0]);
            entity.setValue((Double)v[1]);
            result.add(entity);
        }
        return result;
    }


}
