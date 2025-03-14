package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "DF_ORGANIZATION", indexes = {
        @Index(name = "IDX_DF_ORGANIZATION_SECRETARY", columnList = "SECRETARY_ID")
})
@Entity
public class Organization {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODE", length = 20)
    private String code;

    @Column(name = "DELETED_BY", length = 50)
    @SystemLevel
    private String deletedBy;

    @Column(name = "LEGAL_ADDRESS", length = 300)
    private String legalAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETE_TS")
    @SystemLevel
    private Date deleteTs;

    @JoinColumn(name = "SECRETARY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User secretary;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TS")
    @SystemLevel
    private Date createTs;

    @Column(name = "FAX", length = 100)
    private String fax;

    @Column(name = "HAS_ATTACHMENTS")
    private Boolean hasAttachments;

    @Column(name = "EMAIL", length = 100)
    @Email
    private String email;

    @Column(name = "OGRN", length = 13)
    private String ogrn;

    @Column(name = "UPDATED_BY", length = 50)
    @SystemLevel
    private String updatedBy;

    @Column(name = "BIN_UIN", length = 12)
    private String binUin;

    @Column(name = "INN", length = 100)
    private String inn;

    @Column(name = "FULL_NAME", length = 200)
    private String fullName;

    @Column(name = "KPP", length = 9)
    private String kpp;

    @Column(name = "VERSION")
    @SystemLevel
    @Version
    private Integer version;

    @Column(name = "OKPO", length = 10)
    private String okpo;

    @Column(name = "KBE", length = 2)
    private String kbe;

    @Column(name = "POSTAL_ADDRESS", length = 300)
    private String postalAddress;

    @Column(name = "CREATED_BY", length = 50)
    @SystemLevel
    private String createdBy;

    @Column(name = "PHONE", length = 100)
    private String phone;

    @Column(name = "IBAN", length = 20)
    private String iban;

    @InstanceName
    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "COMMENT_", length = 1000)
    private String comment;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TS")
    @SystemLevel
    private Date updateTs;

    @Column(name = "BIC", length = 8)
    private String bic;

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getKbe() {
        return kbe;
    }

    public void setKbe(String kbe) {
        this.kbe = kbe;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getBinUin() {
        return binUin;
    }

    public void setBinUin(String binUin) {
        this.binUin = binUin;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(Boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public User getSecretary() {
        return secretary;
    }

    public void setSecretary(User secretary) {
        this.secretary = secretary;
    }

    public Date getDeleteTs() {
        return deleteTs;
    }

    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}