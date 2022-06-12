package za.co.uyanda.interview.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.uyanda.interview.dao.CompanyDAO;
import za.co.uyanda.interview.dao.RoleDAO;
import za.co.uyanda.interview.pojo.Role;

/**
 *
 * @author User
 */
public class RoleDAOImpl implements RoleDAO{
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private List<Role> roleList = new ArrayList();
    

    public RoleDAOImpl(Connection con){
        this.con = con;
    }

    public RoleDAOImpl() {
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
    public boolean createRole(String title, String description) {
        if(con!=null){
            try {
                ps = con.prepareStatement("insert into roles(title,description) values(?,?)");
                ps.setString(1, title);
                ps.setString(2,description);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to create applicant in applicants: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Role> getRoles() {
        if(con!=null){
            Role role;
            CompanyDAO cDAO = new CompanyDAOImpl(con);
            try {
                ps = con.prepareStatement("select * from roles");
                rs = ps.executeQuery();
                while(rs.next()){
                    int roleId = rs.getInt("roleid");
                    int companyId = rs.getInt("companyid");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    if(cDAO.getCompany(companyId)!=null){
                        role = new Role(roleId,cDAO.getCompany(companyId),title,description);
                        roleList.add(role);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Unable to get from roles: " + ex.getMessage());
            }
        }
        return roleList;
    }

    @Override
    public Role getRole(int roleId) {
        for(Role role : getRoles()){
            if(role.getRoleId()==roleId){
                return role;
            }
        }
        return null;
    }

    @Override
    public boolean updateRole(int roleId, String title, String description) {
        if(con!=null){
            try {
                ps = con.prepareStatement("update roles set title=?, description=? where roleid = ?");
                ps.setString(1,title);
                ps.setString(2,description);
                ps.setInt(3, roleId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to update roles: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteRole(int roleId) {
        if(con!=null){
            try {
                ps = con.prepareStatement("delete from roles where roleid = ?");
                ps.setInt(1, roleId);
                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println("Unable to delete from roles: " + ex.getMessage());
            }
        }
        return false;
    }
}
