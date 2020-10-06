package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.item.ItemDAO;
import com.gm.wj.New_All.entity.Item;
import com.gm.wj.New_All.entity.Itemcategory;
import com.gm.wj.New_All.utils.Option;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    UserService userService;

    public List<Item> list(){
        List<Item> list = itemDAO.findAll();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        list.removeIf(m -> m.getItemcategory().getOrganization().getId() != org.getId());
        return list;
    }

    public int update(Item item){
        try{
            Item New_item = itemDAO.findByItemno(item.getItemno());
            New_item.setItemno(item.getItemno());
            New_item.setItemcategory(item.getItemcategory());
            New_item.setItemname(item.getItemname());
            itemDAO.save(New_item);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    //save 方法（1）有id 修改 （2）没id 添加
    public int add(Item item){
        if(itemDAO.findByItemno(item.getItemno()) != null){
            //存在相同编号
            return -1;
        }
        try {
            itemDAO.save(item);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    public List<Option> getSelect(){
        List<Option> result = new ArrayList<>();
        //所有仪器信息
        List<Item> List = itemDAO.findAll();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        List.removeIf(m -> m.getItemcategory().getOrganization().getId() != org.getId());

        for(Item c :List){
            boolean find = false;
            for(Option o:result){
                if(c.getItemcategory().getId() == o.getValue() ){
                    find = true;
                    //药品类别编号 == 类别id
                    Option op = new Option();
                    op.setLabel(c.getItemno()+" "+c.getItemname());
                    op.setValue(c.getId());

                    List<Option> t = o.getChildren();
                    t.add(op);
                    o.setChildren(t);
                }
                if(find)break;
            }
            if(!find){//没找到
                Option op = new Option();
                op.setLabel(c.getItemcategory().getName());
                op.setValue(c.getItemcategory().getId());

                Option op2 = new Option();
                op2.setLabel(c.getItemno()+" "+c.getItemname());
                op2.setValue(c.getId());

                List<Option> List1 = new ArrayList<>();
                List1.add(op2);
                op.setChildren(List1);
                result.add(op);
                System.out.println(c.getItemcategory().getName());
            }
        }
        //根据类别 转化成 option
        return result;
    }

    public Item findById(int id){
        return itemDAO.findById(id);
    }

    public List<Item> search(Itemcategory itemcategory, String keywords){
        return null;
    }

}
