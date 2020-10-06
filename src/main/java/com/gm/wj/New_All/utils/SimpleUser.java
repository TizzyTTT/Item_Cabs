package com.gm.wj.New_All.utils;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * User entity.
 *
 * @author Evan
 * @date 2019/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class SimpleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;

    private String phone;

    @Email(message = "请输入正确的邮箱")
    private String email;


    public SimpleUser(User user){
        this.email = user.getEmail();
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
    }

}

