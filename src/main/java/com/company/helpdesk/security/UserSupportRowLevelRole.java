package com.company.helpdesk.security;

import com.company.helpdesk.entity.RepairRequest;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "UserSupportRowLevelRole", code = UserSupportRowLevelRole.CODE)
public interface UserSupportRowLevelRole {
    String CODE = "user-support-row-level-role";

    @JpqlRowLevelPolicy(entityClass = RepairRequest.class, where = "{E}.userSupport.id = :current_user_id")
    void repairRequest();
}