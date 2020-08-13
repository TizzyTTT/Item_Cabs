package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.ChemicalsDAO;
import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ChemicalBasicService {

    @Autowired
    ChemicalsDAO chemicalsDAO;

    public List<Chemicals> listAll() {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return chemicalsDAO.findAll(sort);
    }

    public Chemicals findById(int id){
        return chemicalsDAO.findById(id);
    }

    //save 方法（1）有id 修改 （2）没id 添加
    public int add(Chemicals chemicals){
        System.out.println("enter");
        if(chemicalsDAO.findByChemicalno(chemicals.getChemicalno()) != null){
            //存在相同编号
            System.out.println("same");
            return -1;
        }
        System.out.println("begin");
        try {
            chemicalsDAO.save(chemicals);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    public int update(Chemicals chemicals){
        try{
            Chemicals chemicals_indb = chemicalsDAO.findByChemicalno(chemicals.getChemicalno());
            chemicals_indb.setChemicalno(chemicals.getChemicalno());
            chemicals_indb.setChemicalcategory(chemicals.getChemicalcategory());
            chemicals_indb.setChemicalname(chemicals.getChemicalname());
            chemicalsDAO.save(chemicals_indb);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    @Transactional //不加此条注释会报错
    public int remove(String chemicalno){
        try{
            chemicalsDAO.deleteByChemicalno(chemicalno);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

//    public List<Chemicals> search(String keywords){
//        List<Chemicals> list = chemicalsDAO.findAllByChemicalnameLikeOrChemicalnoLike(keywords,keywords);
//        if(list.size() > 0){
//            return list;
//        }else {
//            return chemicalsDAO.findAll();
//        }
//    }

    public List<Chemicals> search(Chemicalcategory chemicalcategory,String keywords){

        keywords = "%"+keywords+"%";

        if(chemicalcategory == null && keywords.equals("")){
            return chemicalsDAO.findAll();
        }else if(chemicalcategory != null && keywords.equals("")){
            return chemicalsDAO.findAllByChemicalcategory(chemicalcategory);
        }else if(chemicalcategory == null && !keywords.equals("")){
            return chemicalsDAO.findAllByChemicalnameLikeOrChemicalnoLike(keywords,keywords);
        }else {
            return chemicalsDAO.findAllByChemicalcategoryAndChemicalnameLikeOrChemicalnoLike(chemicalcategory,keywords,keywords);
        }

    }

    public List<Chemicals> findByChemicalcategory(Chemicalcategory chemicalcategory){
        return chemicalsDAO.findAllByChemicalcategory(chemicalcategory);
    }

}
