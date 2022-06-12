package za.co.uyanda.interview.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import za.co.uyanda.interview.dao.CompanyDAO;
import za.co.uyanda.interview.dao.ListingDAO;
import za.co.uyanda.interview.dao.RoleDAO;
import za.co.uyanda.interview.pojo.Company;
import za.co.uyanda.interview.pojo.Listing;
import za.co.uyanda.interview.pojo.Role;


/**
 *
 * @author User
 */
public class ListingDAOImpl implements ListingDAO{
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private CompanyDAO cDAO = null;
    private RoleDAO rDAO = null;
    private List<Listing> listingList = new ArrayList();
    

    public ListingDAOImpl(Connection con){
        this.con = con;
    }

    public ListingDAOImpl() {
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
    public boolean createListing(int companyId, int roleId, LocalDate closingDate) {
        if(con!=null){
            try {
                ps = con.prepareStatement("insert into listings(companyid,roleid,closingdate) values(?,?,?)");
                ps.setInt(1, companyId);
                ps.setInt(2,roleId);
                ps.setDate(3,Date.valueOf(closingDate));
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to create listing in listings: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Listing> getListings() {
        if(con!=null){
            Listing listing;
            cDAO = new CompanyDAOImpl(con);
            rDAO = new RoleDAOImpl(con);
            try {
                ps = con.prepareStatement("select * from listings");
                rs = ps.executeQuery();
                while(rs.next()){
                    int listingId = rs.getInt("listingid");
                    int companyId = rs.getInt("companyid");
                    Company company = cDAO.getCompany(companyId);
                    int roleId = rs.getInt("roleid");
                    Role role = rDAO.getRole(roleId);
                    String closingDate = rs.getDate("closingdate").toString();
                    listing = new Listing(listingId,company,role,closingDate);
                    listingList.add(listing);
                }
            } catch (SQLException ex) {
                System.out.println("Unable to get from listings: " + ex.getMessage());
            }
        }
        return listingList;
    }

    @Override
    public Listing getListing(int listingId) {
        for(Listing listing : getListings()){
            if(listing.getListingId()==listingId){
                return listing;
            }
        }
        return null;
    }

    @Override
    public boolean updateListing(int listingId, int companyId, int roleId, LocalDate closingDate) {
        if(con!=null){
            try {
                ps = con.prepareStatement("update listings set companyid=?, roleid=?, closingdate=? where listingid = ?");
                ps.setInt(1,companyId);
                ps.setInt(2,roleId);
                ps.setDate(3, Date.valueOf(closingDate));
                ps.setInt(4,listingId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to update listings: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteListing(int listingId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("delete from listings where listingid = ?");
                ps.setInt(1, listingId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to delete from listings: " + ex.getMessage());
            }
        }
        return false;
    }
}
