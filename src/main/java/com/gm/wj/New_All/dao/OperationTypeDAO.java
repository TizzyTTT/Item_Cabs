package com.gm.wj.New_All.dao.record;

import com.gm.wj.New_All.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeDAO extends JpaRepository<OperationType,Integer> {
    OperationType findByOptypeId(int id);
}
