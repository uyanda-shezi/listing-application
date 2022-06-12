package za.co.uyanda.interview.dao;

import java.util.List;
import za.co.uyanda.interview.pojo.Applicant;

/**
 *
 * @author User
 */
public interface ApplicantDAO {
    boolean createApplicant(String email, String name, String surname, String cellNumber);
    List<Applicant> getApplicants();
    Applicant getApplicant(String email);
    boolean updateApplicant(String email, String name, String surname, String cellNumber);
    boolean deleteApplicant(String email);
}
