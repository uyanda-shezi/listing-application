package za.co.uyanda.interview.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.uyanda.interview.dao.ApplicantDAO;
import za.co.uyanda.interview.dao.ApplicationDAO;
import za.co.uyanda.interview.dao.ListingDAO;
import za.co.uyanda.interview.pojo.Applicant;
import za.co.uyanda.interview.pojo.Application;
import za.co.uyanda.interview.pojo.Listing;

/**
 *
 * @author User
 */
public class ApplicationDAOImpl implements ApplicationDAO{
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private ListingDAO lDAO;
    private ApplicantDAO aDAO;
    private List<Application> applicationList = new ArrayList();
    

    public ApplicationDAOImpl(Connection con){
        this.con = con;
    }

    public ApplicationDAOImpl() {
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
    public boolean createApplication(String email, int listingId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("insert into applications values(?,?)");
                ps.setString(1, email);
                ps.setInt(2,listingId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to create application in applications: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Application> getApplications() {
        if(con!=null){
            Application application;
            try {
                ps = con.prepareStatement("select * from applications");
                rs = ps.executeQuery();
                lDAO = new ListingDAOImpl(con);
                aDAO = new ApplicantDAOImpl(con);
                while(rs.next()){
                    int applicationId = rs.getInt("applicationid");
                    String email = rs.getString("applicant");
                    int listingId = rs.getInt("listingid");
                    Listing listing = lDAO.getListing(listingId);
                    Applicant applicant = aDAO.getApplicant(email);
                    application = new Application(applicationId,applicant,listing);
                    applicationList.add(application);
                }
            } catch (SQLException ex) {
                System.out.println("Unable to get from applicants: " + ex.getMessage());
            }
        }
        return applicationList;
    }

    @Override
    public Application getApplication(int applicationId) {
        for(Application application : getApplications()){
            if(application.getApplicationId() == applicationId){
                return application;
            }
        }
        return null;
    }

    @Override
    public boolean updateApplication(int applicationId, String applicant, int listingId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("update applications set applicant=?, listingid=? where applicationid = ?");
                ps.setString(1,applicant);
                ps.setInt(2,listingId);
                ps.setInt(3, applicationId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to update applications: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteApplication(int applicationId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("delete from applications where applicationid = ?");
                ps.setInt(1, applicationId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to delete from applications: " + ex.getMessage());
            }
        }
        return false;
    }
}
