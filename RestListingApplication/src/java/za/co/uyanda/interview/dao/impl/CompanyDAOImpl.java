package za.co.uyanda.interview.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.uyanda.interview.dao.CompanyDAO;
import za.co.uyanda.interview.pojo.Company;

/**
 *
 * @author User
 */
public class CompanyDAOImpl implements CompanyDAO{
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private List<Company> companyList = new ArrayList();
    

    public CompanyDAOImpl(Connection con){
        this.con = con;
    }

    public CompanyDAOImpl() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
        }
        try{
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/listingdb", "root","root");
            this.con= myCon;
        }catch(SQLException ex){
            
        }
    }
    
    @Override
    public boolean createCompany(String name, String address) {
        if(con!=null){
            try {
                ps = con.prepareStatement("insert into companies(name,address) values(?,?)");
                ps.setString(1, name);
                ps.setString(2,address);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to create company in companies: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Company> getCompanies() {
        if(con!=null){
            Company company;
            try {
                ps = con.prepareStatement("select * from companies");
                rs = ps.executeQuery();
                while(rs.next()){
                    int companyId = rs.getInt("companyid");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    company = new Company(companyId,name,address);
                    companyList.add(company);
                }
            } catch (SQLException ex) {
                System.out.println("Unable to get from companies: " + ex.getMessage());
            }
        }
        return companyList;
    }

    @Override
    public Company getCompany(int companyId) {
        for(Company company : getCompanies()){
            if(company.getCompanyId()==companyId){
                return company;
            }
        }
        return null;
    }

    @Override
    public boolean updateCompany(int companyId, String name, String address) {
        if(con!=null){
            try {
                ps = con.prepareStatement("update companies set name=?, address=? where companyid = ?");
                ps.setString(1,name);
                ps.setString(2,address);
                ps.setInt(3, companyId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to update companies: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteCompany(int companyId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("delete from companies where companyid = ?");
                ps.setInt(1, companyId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to delete from companies: " + ex.getMessage());
            }
        }
        return false;
    }
    
}
