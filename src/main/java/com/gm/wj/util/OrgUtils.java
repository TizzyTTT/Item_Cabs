package com.gm.wj.util;

import com.gm.wj.entity.Organization;
import com.gm.wj.entity.User;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;

import java.security.Security;

public class OrgUtils {

    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
