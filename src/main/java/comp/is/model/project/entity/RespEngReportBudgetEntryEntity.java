package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESPENGREPORTBUDGETENTRY")
public class RespEngReportBudgetEntryEntity implements Serializable{
    private Integer reportId;
    private String wpId;
    private String projId;
    private Integer labourChargeRateId;
    private LabourchargerateEntity labourChargeRate;
    private Double amount;
    private WorkpackagestatusreportEntity reports;
    
    @Id
    @Column(nullable = false)
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
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
    @Column(name="LABOURCHARGERATE", nullable = false)
    public Integer getLabourChargeRateId() {
        return labourChargeRateId;
    }
    public void setLabourChargeRateId(Integer labourChargeRateId) {
        this.labourChargeRateId = labourChargeRateId;
    }
    @OneToOne
    @JoinColumn(name="LABOURCHARGERATE", insertable=false, updatable=false)
    public LabourchargerateEntity getLabourChargeRate() {
        return labourChargeRate;
    }
    public void setLabourChargeRate(LabourchargerateEntity labourChargeRate) {
        this.labourChargeRate = labourChargeRate;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @ManyToOne
    @JoinColumn(name="REPORTID", referencedColumnName="ID", nullable = false, insertable = false, updatable = false)
    public WorkpackagestatusreportEntity getReports() {
        return reports;
    }
    public void setReports(WorkpackagestatusreportEntity wps) {
        reports = wps;
    }
    
    
    
}
