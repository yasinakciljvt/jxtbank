package com.jxt.jxtbank.role.service;

import com.jxt.jxtbank.res.Response;
import com.jxt.jxtbank.role.entity.Role;

import java.util.List;

public interface RoleService {

    Response<Role> createRole(Role roleRequest);
    Response<Role> updateRole(Role roleRequest);
    Response<List<Role>> getAllRole(Role roleRequest);
    Response<?> deleteRole(Role roleRequest);

}
