package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.ChemicalsDAO;
import com.gm.wj.New_All.dao.RecordDAO;
import com.gm.wj.New_All.dao.RecordDetailDAO;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.Record;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.utils.QueryForRecord;
import com.gm.wj.New_All.utils.Record2;
import com.gm.wj.New_All.utils.RecordDetail2;
import com.gm.wj.dao.UserDAO;
import com.gm.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class RecordService {

    @Autowired
    RecordDAO recordDAO;
    @Autowired
    RecordDetailDAO recordDetailDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ChemicalsDAO chemicalsDAO;

    public List<Record> query(QueryForRecord queryForRecord) {

        int userid = queryForRecord.getOperationUserId();
        String date = queryForRecord.getAfterTime();
        int cid = queryForRecord.getCid();
        Date date1 = new Date();
        User user = userDAO.findById(userid);
        // 从 record 查询到 userid cabid
        return recordDAO.findByCabidAndTimeAfterAndUserOrAduser(cid,date1,user,user);
    }

    @Transactional
    public void convertAndInsertRecord(Record2 record2){
        Record record = new Record();
        record.setCabid(record2.getCabid());
        record.setTime(record2.getTime());
        //OneToOne注释不会创建新实体
        record.setUser(userDAO.findById(record2.getUserid()));
        record.setAduser(userDAO.findById(record2.getAduserid()));
        //保存一条大记录
        Record New_R = recordDAO.saveAndFlush(record);
        System.out.println("New_R.getId() : "+New_R.getId());
        int rid = New_R.getId();
        List<RecordDetail> list = new ArrayList<>();
        for (RecordDetail2 rd : record2.getRecordDetails()){
            RecordDetail recordDetail = new RecordDetail();
            recordDetail.setAccount(rd.getAccount());
            recordDetail.setOptype(rd.getOptype());
            recordDetail.setChemicals(chemicalsDAO.findById(rd.getChemicalid()));
            recordDetail.setRecordid(rid);
            System.out.println(recordDetail.getChemicals().toString());
            list.add(recordDetail);
        }
        recordDetailDAO.saveAll(list);
    }

    @Transactional
    public void deleteRecordCascade(int rid){
        recordDetailDAO.deleteByRecordid(rid);
        recordDAO.deleteById(rid);
    }
}
