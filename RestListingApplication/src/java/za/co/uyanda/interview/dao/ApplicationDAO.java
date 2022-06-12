package za.co.uyanda.interview.dao;

import java.util.List;
import za.co.uyanda.interview.pojo.Application;

/**
 *
 * @author User
 */
public interface ApplicationDAO {
    boolean createApplication(String email, int listingId);
    List<Application> getApplications();
    Application getApplication(int applicationId);
    boolean updateApplication(int applicationId, String email, int listingId);
    boolean deleteApplication(int applicationId);
}
