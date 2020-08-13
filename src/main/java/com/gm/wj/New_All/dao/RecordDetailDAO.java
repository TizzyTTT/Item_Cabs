package com.gm.wj.New_All.dao;

import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.RecordDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordDetailDAO extends JpaRepository<RecordDetail,Integer> {
    List<RecordDetail> findAllByRecordid(int id);

    List<RecordDetail> findAllByRecordidAndOptypeAndChemicals(int rid, int optype, Chemicals chemical);

    List<RecordDetail> findAllByRecordidAndChemicals(int rid,  Chemicals chemical);

    void deleteByRecordid(int rid);

}
