package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.RegisterDAO;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.utils.Option;
import com.gm.wj.New_All.entity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    RegisterDAO registerDAO;

    @Autowired
    ChemicalBasicService chemicalBasicService;

    public List<Register> List(){
        System.out.println("register enter List");
        return registerDAO.findAll();
    }

    public int add(Register register){
        Register register1 = registerDAO.findById(register.getId());
        if(register1 != null){
            //存在相同id
            return -1;
        }else {
            try {
                registerDAO.save(register);
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
            return 1;
        }
    }

    public List<Option> getSelect(){
        List<Option> result = new ArrayList<>();
        //所有药品信息
        List<Chemicals> List = chemicalBasicService.listAll();

        for(Chemicals c :List){
            boolean find = false;
            for(Option o:result){
                if(c.getChemicalcategory().getId() == o.getValue() ){
                    find = true;
                    //药品类别编号 == 类别id
                    Option op = new Option();
                    op.setLabel(c.getChemicalno()+" "+c.getChemicalname());
                    op.setValue(c.getId());

                    List<Option> t = o.getChildren();
                    t.add(op);
                    o.setChildren(t);
                    System.out.println("-----------"+c.getChemicalno()+" "+c.getChemicalname());
                }
                if(find)break;
            }
            if(!find){//没找到
                Option op = new Option();
                op.setLabel(c.getChemicalcategory().getCname());
                op.setValue(c.getChemicalcategory().getId());

                Option op2 = new Option();
                op2.setLabel(c.getChemicalno()+" "+c.getChemicalname());
                op2.setValue(c.getId());

                List<Option> List1 = new ArrayList<>();
                List1.add(op2);
                op.setChildren(List1);
                result.add(op);
                System.out.println(c.getChemicalcategory().getCname());
                System.out.println("-----------"+c.getChemicalno()+" "+c.getChemicalname());
            }
        }
        //根据类别 转化成 option
        return result;
    }

    public List<Register> findByChemicalsIn(List<Chemicals> list){
        return registerDAO.findByChemicalsIn(list);
    }

//    public List<Register> search(Chemicalcategory chemicalcategory, String keywords){
//
//        keywords = "%"+keywords+"%";
//
//        if(chemicalcategory == null && keywords.equals("")){
//            return registerDAO.findAll();
//        }else if(chemicalcategory != null && keywords.equals("")){
//
////            return registerDAO.findAllByChemicalcategory(chemicalcategory);
//        }else if(chemicalcategory == null && !keywords.equals("")){
//            return registerDAO.findAllByChemicalnameLikeOrChemicalnoLike(keywords,keywords);
//        }else {
//            return registerDAO.findAllByChemicalcategoryAndChemicalnameLikeOrChemicalnoLike(chemicalcategory,keywords,keywords);
//        }
//
//    }

}
