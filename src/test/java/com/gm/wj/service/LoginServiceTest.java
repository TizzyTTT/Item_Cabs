package com.gm.wj.service;

import com.gm.wj.New_All.dao.cabs.CabinetsDAO;
import com.gm.wj.New_All.dao.chemcials.ChemicalsDAO;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.Chemicalsregister;
import com.gm.wj.New_All.service.ChemicalRegisterService;
import com.gm.wj.dao.UserDAO;
import com.gm.wj.dto.UserDTO;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

//@Log4j2
@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    ChemicalRegisterService chemicalRegisterService;

    @Test
    public void testList() {
        List<Chemicalsregister> list = chemicalRegisterService.ListChemicalsRegister();
        for(int i = 0;i<list.size();++i)
        System.out.println(list.get(i).getChemicals());
    }


}
