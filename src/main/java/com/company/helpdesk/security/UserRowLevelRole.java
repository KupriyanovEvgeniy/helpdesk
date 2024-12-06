package com.company.helpdesk.security;

import com.company.helpdesk.entity.RepairRequest;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "UserRow-levelRole", code = UserRowLevelRole.CODE)
public interface UserRowLevelRole {
    String CODE = "user-row-level-role";


    @JpqlRowLevelPolicy(entityClass = RepairRequest.class, where = "{E}.user.id = :current_user_id")
    void repairRequest();
}