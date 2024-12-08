package com.company.helpdesk.security;

import com.company.helpdesk.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "UserRole", code = UserRole.CODE, scope = "UI")
public interface UserRole {
    String CODE = "user-role";

    @EntityAttributePolicy(entityClass = RepairRequest.class, attributes = {"user", "location", "room", "equipmentType", "equipment", "faultType", "description"}, action = EntityAttributePolicyAction.MODIFY)

    @EntityAttributePolicy(entityClass = RepairRequest.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = RepairRequest.class, actions = EntityPolicyAction.ALL)
    void repairRequest();

    @SpecificPolicy(resources = "ui.loginToUi")
    void login();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Room.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Room.class, actions = EntityPolicyAction.READ)
    void room();

    @EntityAttributePolicy(entityClass = Location.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Location.class, actions = EntityPolicyAction.READ)
    void location();

    @EntityAttributePolicy(entityClass = FaultType.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = FaultType.class, actions = EntityPolicyAction.READ)
    void faultType();

    @EntityAttributePolicy(entityClass = Equipment.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Equipment.class, actions = EntityPolicyAction.READ)
    void equipment();

    @MenuPolicy(menuIds = "RepairRequest.list")
    @ViewPolicy(viewIds = {"User.list", "Room.list", "Location.list", "Equipment.list", "FaultType.list", "RepairRequest.list", "MainView", "RepairRequest.detail"})
    void screens();
}