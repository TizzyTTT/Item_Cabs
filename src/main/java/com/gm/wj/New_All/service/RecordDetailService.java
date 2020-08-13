package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.ChemicalsDAO;
import com.gm.wj.New_All.dao.RecordDetailDAO;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.utils.QueryForDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordDetailService {

    @Autowired
    RecordDetailDAO recordDetailDAO;

    @Autowired
    ChemicalsDAO chemicalsDAO;

    public List<RecordDetail> listByRecordid(int rid){
        return recordDetailDAO.findAllByRecordid(rid);
    }

    public List<RecordDetail> query(QueryForDetail queryForDetail){

        String chemicalinfo = queryForDetail.getChemicalinfo();
        int operationtype = queryForDetail.getOperationtype();
        int rid = queryForDetail.getRid();
        Chemicals cc = chemicalsDAO.findByChemicalnoLikeOrChemicalnameLike(chemicalinfo,chemicalinfo);

        if(operationtype == -1){
            return recordDetailDAO.findAllByRecordidAndChemicals(rid,cc);
        }else return recordDetailDAO.findAllByRecordidAndOptypeAndChemicals(rid,operationtype,cc);
    
    }
}
