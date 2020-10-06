package com.gm.wj.New_All.dao.record;

import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.OperationType;
import com.gm.wj.New_All.entity.Record;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.utils.MaxUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordDetailDAO extends JpaRepository<RecordDetail,Integer> {
    List<RecordDetail> findAllByRecord(Record r);

    List<RecordDetail> findAllByRecordAndOptypeAndChemicals(Record r, OperationType o, Chemicals chemical);

    List<RecordDetail> findAllByRecordAndChemicals(Record r,  Chemicals chemical);

    void deleteByRecord(Record r);

    List<RecordDetail> findByChemicals(Chemicals c);

}
