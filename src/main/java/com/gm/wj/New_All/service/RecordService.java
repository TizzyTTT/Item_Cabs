package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.cabs.CabinetsDAO;
import com.gm.wj.New_All.dao.chemcials.ChemicalsDAO;
import com.gm.wj.New_All.dao.record.OperationTypeDAO;
import com.gm.wj.New_All.dao.record.RecordDAO;
import com.gm.wj.New_All.dao.record.RecordDetailDAO;
import com.gm.wj.New_All.dao.record.RegisterDAO;
import com.gm.wj.New_All.entity.*;
import com.gm.wj.New_All.utils.QueryForRecord;
import com.gm.wj.New_All.utils.Record2;
import com.gm.wj.New_All.utils.RecordDetail2;
import com.gm.wj.dao.UserDAO;
import com.gm.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    RegisterDAO registerDAO;
    @Autowired
    CabinetsDAO cabinetsDAO;
    @Autowired
    OperationTypeDAO operationTypeDAO;

    public List<Record> query(QueryForRecord queryForRecord) {

        int userid = queryForRecord.getOperationUserId();
        String date = queryForRecord.getAfterTime();
        int cid = queryForRecord.getCid();
        Date date1 = new Date();
        User user = userDAO.findById(userid);
        // 从 record 查询到 userid cabid
        return recordDAO.findByCabinetsAndTimeAfterAndUserOrAduser(cabinetsDAO.findById(cid),date1,user,user);
    }

    //用户的出入柜操作
    @Transactional
    public void convertAndInsertRecord(Record2 record2){
        Record record = new Record();
//        record.setCabid(record2.getCabid());
        record.setCabinets(cabinetsDAO.findById(record2.getCabid()));
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
            int optype = rd.getOptype();
            if(optype !=1 && optype !=2){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return;
            }
//            recordDetail.setOptype(optype);
            recordDetail.setOptype(operationTypeDAO.findByOptypeId(optype));
            recordDetail.setChemicals(chemicalsDAO.findById(rd.getChemicalid()));
//            recordDetail.setRecordid(rid);
            recordDetail.setRecord(recordDAO.findById(rid));
            System.out.println(recordDetail.getChemicals().toString());
            list.add(recordDetail);
        }
        recordDetailDAO.saveAll(list);
    }

    //用户从仓库领用药品的操作
    @Transactional
    public void getFromHub(Record2 record2){
        //cab = 0 or -1
        //userid = aduserid
        Record record = new Record();
        record.setCabinets(null);
        record.setTime(record2.getTime());
        //OneToOne注释不会创建新实体
        User u = userDAO.findById(record2.getUserid());
        record.setUser(u);
        record.setAduser(u);
        //保存一条大记录
        Record New_R = recordDAO.saveAndFlush(record);
        System.out.println("New_R.getId() : "+New_R.getId());
        int rid = New_R.getId();
        List<RecordDetail> list = new ArrayList<>();
        for (RecordDetail2 rd : record2.getRecordDetails()){
            RecordDetail recordDetail = new RecordDetail();
            int optype = rd.getOptype();
            if(optype != 3){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return;
            }
            recordDetail.setOptype(operationTypeDAO.findByOptypeId(3));// 全是药品领用类型
            recordDetail.setChemicals(chemicalsDAO.findById(rd.getChemicalid()));
            recordDetail.setAccount(rd.getAccount());
//            recordDetail.setRecordid(rid);
            recordDetail.setRecord(recordDAO.findById(rid));
            //登記 的一条记录
            Register register = registerDAO.findById(rd.getChemicalid());
            double new_weight = register.getTotalweight()-recordDetail.getAccount();
            if( new_weight < 0 ){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return;
            }
            register.setTotalweight(new_weight);
            registerDAO.save(register);

            list.add(recordDetail);
        }
        recordDetailDAO.saveAll(list);
    }

    @Transactional
    public void deleteRecordCascade(int rid){
        recordDetailDAO.deleteByRecord(recordDAO.findById(rid));
        recordDAO.deleteById(rid);
    }



}
