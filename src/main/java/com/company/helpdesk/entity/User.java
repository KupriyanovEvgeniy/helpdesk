package com.company.helpdesk.entity;

import io.jmix.core.annotation.Secret;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.security.authentication.JmixUserDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@JmixEntity
@Entity
@Table(name = "SEC_USER", indexes = {
        @Index(name = "IDX_SEC_USER_ORGANIZATION", columnList = "ORGANIZATION_ID")
})
public class User implements JmixUserDetails {

    @Id
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Employee> employees;

    @JoinColumn(name = "ORGANIZATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @Column(name = "CHANGE_PASSWORD_AT_LOGON")
    private Boolean changePasswordAtNextLogon;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE TS")
    @SystemLevel
    private Date createTs;

    @Column(name = "TIME_ZONE_AUTO")
    private Boolean timeZoneAuto;

    @Column(name = "IS_MOBILE")
    private Boolean isMobile;

    @Column(name = "UPDATED_BY", length = 50)
    @SystemLevel
    private String updatedBy;

    @Column(name = "TIME ZONE")
    private String timeZone;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TS")
    @SystemLevel
    private Date updateTs;

    @Column(name = "USE_ACTIVE_DIRECTORY")
    private Boolean useActiveDirectory;

    @Column(name = "NAME")
    private String name;

    @Column(name = "POSITION_")
    private String position;

    @Column(name = "CREATED_BY", length = 50)
    @SystemLevel
    private String createdBy;

    @Column(name = "DELETED_BY", length = 50)
    @SystemLevel
    private String deletedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETE_TS")
    @SystemLevel
    private Date deleteTs;

    @Column(name = "LOGIN_LC", length = 50)
    @SystemLevel(propagateToSubclasses = true, value = true)
    private String loginLowerCase;

    @Column(name = "DEPARTMENT_CODE", length = 20)
    private String departmentCode;

    @Column(name = "IP_MASK", length = 200)
    private String ipMask;

    @Column(name = "LANGUAGE", length = 20)
    private String language;

    @Column(name = "LOGIN", length = 50)
    private String login;

    @Version
    @Column(name = "VERSION", nullable = false)
    @SystemLevel
    private Integer version;

    @Column(name = "PASSWORD_ENCRYPTION", length = 50)
    @SystemLevel(propagateToSubclasses = true, value = true)
    private String passwordEncryption;

    @Column(name = "SYS_TENANT_ID")
    @SystemLevel(propagateToSubclasses = true, value = true)
    private String sysTenantId;

    @Column(name = "GROUP_NAMES")
    @SystemLevel(value = true)
    private String groupNames;

    @Secret
    @SystemLevel(propagateToSubclasses = true, value = true)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "ACTIVE_DIRECTORY_ID")
    private String activeDirectoryID;

    @Email
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    @JmixProperty
    @Transient
    private String nameOrLogin;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getNameOrLogin() {
        return nameOrLogin;
    }

    public String getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String groupNames) {
        this.groupNames = groupNames;
    }

    public String getSysTenantId() {
        return sysTenantId;
    }

    public void setSysTenantId(String sysTenantId) {
        this.sysTenantId = sysTenantId;
    }

    public String getPasswordEncryption() {
        return passwordEncryption;
    }

    public void setPasswordEncryption(String passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUseActiveDirectory() {
        return useActiveDirectory;
    }

    public void setUseActiveDirectory(Boolean useActiveDirectory) {
        this.useActiveDirectory = useActiveDirectory;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    public Boolean getTimeZoneAuto() {
        return timeZoneAuto;
    }

    public void setTimeZoneAuto(Boolean timeZoneAuto) {
        this.timeZoneAuto = timeZoneAuto;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Boolean getChangePasswordAtNextLogon() {
        return changePasswordAtNextLogon;
    }

    public void setChangePasswordAtNextLogon(Boolean changePasswordAtNextLogon) {
        this.changePasswordAtNextLogon = changePasswordAtNextLogon;
    }

    public Date getDeleteTs() {
        return deleteTs;
    }

    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getLoginLowerCase() {
        return loginLowerCase;
    }

    public void setLoginLowerCase(String loginLowerCase) {
        this.loginLowerCase = loginLowerCase;
    }

    public String getActiveDirectoryID() {
        return activeDirectoryID;
    }

    public void setActiveDirectoryID(String activeDirectoryID) {
        this.activeDirectoryID = activeDirectoryID;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptyList();
    }

    @Override
    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(active);
    }

    @InstanceName
    @DependsOnProperties({"firstName", "lastName", "username"})
    public String getDisplayName() {
        return String.format("%s %s [%s]", (firstName != null ? firstName : ""),
                (lastName != null ? lastName : ""), username).trim();
    }

}