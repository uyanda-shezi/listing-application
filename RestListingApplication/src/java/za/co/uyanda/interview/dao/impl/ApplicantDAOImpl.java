package za.co.uyanda.interview.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.uyanda.interview.dao.ApplicantDAO;
import za.co.uyanda.interview.pojo.Applicant;

/**
 *
 * @author User
 */
public class ApplicantDAOImpl implements ApplicantDAO{
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private List<Applicant> applicantList = new ArrayList();
    

    public ApplicantDAOImpl(Connection con){
        this.con = con;
    }

    public ApplicantDAOImpl() {
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
    public boolean createApplicant(String email, String name, String surname, String cellNumber) {
        if(con!=null){
            try {
                ps = con.prepareStatement("insert into applicants values(?,?,?,?)");
                ps.setString(1, email);
                ps.setString(2,name);
                ps.setString(3,surname);
                ps.setString(4,cellNumber);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to create applicant in applicants: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Applicant> getApplicants() {
        if(con!=null){
            Applicant applicant;
            try {
                ps = con.prepareStatement("select * from applicants");
                rs = ps.executeQuery();
                while(rs.next()){
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String cellNumber = rs.getString("phone");
                    applicant = new Applicant(name,surname,cellNumber,email);
                    applicantList.add(applicant);
                }
            } catch (SQLException ex) {
                System.out.println("Unable to get from applicants: " + ex.getMessage());
            }
        }
        return applicantList;
    }

    @Override
    public Applicant getApplicant(String email) {
        for(Applicant applicant : getApplicants()){
            if(applicant.getEmail().equals(email)){
                return applicant;
            }
        }
        return null;
    }

    @Override
    public boolean updateApplicant(String email, String name, String surname, String cellNumber) {
        if(con!=null){
            try {
                ps = con.prepareStatement("update applicants set name=?, surname=?, phone=? where email = ?");
                ps.setString(1,name);
                ps.setString(2,surname);
                ps.setString(3, cellNumber);
                ps.setString(4, email);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to update " + email + " in applicants: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteApplicant(String email) {
        if(con!=null){
            try {
                ps = con.prepareStatement("delete from applicants where email = ?");
                ps.setString(1, email);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to delete from applicants: " + ex.getMessage());
            }
        }
        return false;
    }
    
}
