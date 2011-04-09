package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import comp.is.model.project.key.WorkPackageBudgetPK;
import comp.is.model.project.key.WorkpackagePK;


@Entity
@IdClass(WorkPackageBudgetPK.class)
@Table(name="WORKPACKAGEBUDGET")
public class WorkPackageBudgetEntity implements Serializable{
 
    private String wpId;
    private String projId;
    private Integer labourChargeRateId;
    private LabourchargerateEntity labourChargeRate;
    private Double amount;
    private WorkpackageEntity wp;
    
    
    @Id
    @Column(nullable = false)
    public String getWpId() {
        return wpId;
    }
    public void setWpId(String wpId) {
        this.wpId = wpId;
    }
    @Id
    @Column(nullable = false)
    public String getProjId() {
        return projId;
    }
    public void setProjId(String projId) {
        this.projId = projId;
    }
    @Id
    @Column(nullable = false)
    public Integer getLabourChargeRateId() {
        return labourChargeRateId;
    }
    public void setLabourChargeRateId(Integer labourChargeRateId) {
        this.labourChargeRateId = labourChargeRateId;
    }
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LABOURCHARGERATEID", insertable=false, updatable=false)
    public LabourchargerateEntity getLabourChargeRate() {
        return labourChargeRate;
    }
    public void setLabourChargeRate(LabourchargerateEntity labourChargeRate) {
        this.labourChargeRate = labourChargeRate;
    }
    public Double getWpAmount() {
        return amount;
    }
    public void setWpAmount(Double amount) {
        this.amount = amount;
    }
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PROJID", referencedColumnName = "PROJID", insertable = false, updatable = false),
        @JoinColumn(name = "WPID", referencedColumnName = "ID", insertable = false, updatable = false) })
    public WorkpackageEntity getWp() {
        return wp;
    }
    public void setWp(WorkpackageEntity wp) {
        this.wp = wp;
    }
    
}
