package za.co.uyanda.interview.pojo;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Company {
    private int companyId;
    private String companyName;
    private String companyAddress;

    public Company() {
        this(0,"no company","no address");
    }

    public Company(int companyId, String companyName, String companyAddress) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "Company{" + "companyId=" + companyId + ", companyName=" + companyName + ", companyAddress=" + companyAddress + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.companyId;
        hash = 83 * hash + Objects.hashCode(this.companyName);
        hash = 83 * hash + Objects.hashCode(this.companyAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (this.companyId != other.companyId) {
            return false;
        }
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        return Objects.equals(this.companyAddress, other.companyAddress);
    }
    
    
}
