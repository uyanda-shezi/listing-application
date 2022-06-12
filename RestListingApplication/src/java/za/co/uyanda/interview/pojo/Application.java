package za.co.uyanda.interview.pojo;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Application {
    private int applicationId;
    private Listing listing;
    private Applicant applicant;

    public Application() {
    }

    public Application(int applicationId, Applicant applicant, Listing listing) {
        this.applicationId = applicationId;
        this.listing = listing;
        this.applicant = applicant;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString() {
        return "Application{" + "applicationId=" + applicationId + ", listing=" + listing + ", applicant=" + applicant + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.applicationId;
        hash = 89 * hash + Objects.hashCode(this.listing);
        hash = 89 * hash + Objects.hashCode(this.applicant);
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
        final Application other = (Application) obj;
        if (this.applicationId != other.applicationId) {
            return false;
        }
        if (!Objects.equals(this.listing, other.listing)) {
            return false;
        }
        return Objects.equals(this.applicant, other.applicant);
    }
    
    
}
