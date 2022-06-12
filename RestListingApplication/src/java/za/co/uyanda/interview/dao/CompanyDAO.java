package za.co.uyanda.interview.dao;

import java.util.List;
import za.co.uyanda.interview.pojo.Company;

/**
 *
 * @author User
 */
public interface CompanyDAO {
    boolean createCompany(String companyName, String companyAddress);
    List<Company> getCompanies();
    Company getCompany(int companyId);
    boolean updateCompany(int companyId, String companyName, String companyAddress);
    boolean deleteCompany(int companyId);
}
