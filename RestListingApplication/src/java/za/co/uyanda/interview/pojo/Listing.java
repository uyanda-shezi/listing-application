package za.co.uyanda.interview.pojo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *
 * @author User
 */
public class Listing {
    @JsonProperty("ListingID")
    private int listingId;
    @JsonProperty("Company")
    private Company company;
    @JsonProperty("Role")
    private Role role;
    @JsonProperty("ClosingDate")
    private String closingDate;

    public Listing() {
    }

    public Listing(int listingId, Company company, Role role, String closingDate) {
        this.listingId = listingId;
        this.company = company;
        this.role = role;
        this.closingDate = closingDate;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    @Override
    public String toString() {
        return "Listing{" + "listingId=" + listingId + ", company=" + company + ", role=" + role + ", closingDate=" + closingDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.listingId;
        hash = 67 * hash + Objects.hashCode(this.company);
        hash = 67 * hash + Objects.hashCode(this.role);
        hash = 67 * hash + Objects.hashCode(this.closingDate);
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
        final Listing other = (Listing) obj;
        if (this.listingId != other.listingId) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return Objects.equals(this.closingDate, other.closingDate);
    }
    
    
    
}
