package comp.is.model.project.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.IdClass;

/**
 * The primary key class for the WORKPACKAGE database table.
 * 
 */
public class WorkPackageBudgetPK implements Serializable {
    
    private String wpId;
    private String projId;
    private Integer labourChargeRateId;

    public WorkPackageBudgetPK() {
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkPackageBudgetPK)) {
            return false;
        }
        WorkPackageBudgetPK castOther = (WorkPackageBudgetPK) other;
        return this.wpId.equals(castOther.wpId)
                & this.projId.equals(castOther.projId)
                & labourChargeRateId == castOther.labourChargeRateId;

    }

    @Column(unique = true, nullable = false, length = 16)
    public String getWpId() {
        return wpId;
    }

    @Column(unique = true, nullable = false, length = 16)
    public String getProjId() {
        return this.projId;
    }

    @Column(nullable = false)
    public Integer getLabourChargeRateId() {
        return labourChargeRateId;
    }

    public void setLabourChargeRateId(Integer labourChargeRateId) {
        this.labourChargeRateId = labourChargeRateId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.wpId.hashCode();
        hash = hash * prime + this.projId.hashCode();
        hash = hash * prime + this.labourChargeRateId.hashCode();

        return hash;
    }

    public void setWpId(String wpid) {
        wpId = wpid;
    }

    public void setProjId(String projid) {
        this.projId = projid;
    }
}