package com.company.helpdesk.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;


import com.company.helpdesk.entity.User;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.springframework.stereotype.Component;

/**
 * This configuration complements standard security configurations that come from Jmix modules (security-flowui, oidc,
 * authserver).
 * <p>
 * You can configure custom API endpoints security by defining {@link SecurityFilterChain} beans in this class.
 * In most cases, custom SecurityFilterChain must be applied first, so the proper
 * {@link org.springframework.core.annotation.Order} should be defined for the bean. The order value from the
 * {@link io.jmix.core.JmixSecurityFilterChainOrder#CUSTOM} is guaranteed to be smaller than any other filter chain
 * order from Jmix.
 * <p>
 * Example:
 *
 * <pre>
 * &#064;Bean
 * &#064;Order(JmixSecurityFilterChainOrder.CUSTOM)
 * SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
 *     http.securityMatcher("/public/**")
 *             .authorizeHttpRequests(authorize ->
 *                     authorize.anyRequest().permitAll()
 *             );
 *     return http.build();
 * }
 * </pre>
 *
 * @see io.jmix.securityflowui.security.FlowuiVaadinWebSecurity
 */
@Component
public class HelpdeskSecurityConfiguration {

    // Use UnconstrainedDataManager to bypass data access check (https://docs.jmix.io/jmix/security/authorization.html#data-access-checks)
    // when saving entities in anonymous session.
    private final UnconstrainedDataManager unconstrainedDataManager;

    public HelpdeskSecurityConfiguration(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public void register(User user) {
        RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        roleAssignment.setUsername(user.getUsername());
        roleAssignment.setRoleCode(UserRole.CODE);
        roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

        // Назначение Row-Level Role
        RoleAssignmentEntity rowLevelRoleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        rowLevelRoleAssignment.setUsername(user.getUsername());
        rowLevelRoleAssignment.setRoleCode(UserRowLevelRole.CODE); // Указать код Row-Level роли
        rowLevelRoleAssignment.setRoleType(RoleAssignmentRoleType.ROW_LEVEL);

        unconstrainedDataManager.save(user, roleAssignment, rowLevelRoleAssignment);
    }
}