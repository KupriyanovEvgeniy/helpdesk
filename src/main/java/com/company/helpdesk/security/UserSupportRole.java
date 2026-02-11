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

@ResourceRole(name = "UserSupportRole", code = UserSupportRole.CODE, scope = "UI")
public interface UserSupportRole {
    String CODE = "user-support-role";


    @EntityAttributePolicy(entityClass = RepairRequest.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RepairRequest.class, actions = EntityPolicyAction.ALL)
    void repairRequest();

    @SpecificPolicy(resources = "ui.loginToUi")
    void login();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Room.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Room.class, actions = EntityPolicyAction.ALL)
    void room();

    @EntityAttributePolicy(entityClass = Location.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Location.class, actions = EntityPolicyAction.ALL)
    void location();

    @EntityAttributePolicy(entityClass = FaultType.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FaultType.class, actions = EntityPolicyAction.ALL)
    void faultType();

    @EntityAttributePolicy(entityClass = Equipment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Equipment.class, actions = EntityPolicyAction.ALL)
    void equipment();

    @MenuPolicy(menuIds = {"RepairRequest.list", "User.list", "Room.list", "Location.list", "Equipment.list", "FaultType.list"})
    @ViewPolicy(viewIds = {"User.list", "Room.list", "Location.list", "Equipment.list", "FaultType.list", "RepairRequest.list", "MainView", "RepairRequest.detail", "Equipment.detail", "FaultType.detail", "Location.detail", "LoginView", "RegistrationView", "Room.detail", "User.detail"})
    void screens();
}