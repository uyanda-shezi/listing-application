package za.co.uyanda.interview.dao;

import java.util.List;
import za.co.uyanda.interview.pojo.Role;

/**
 *
 * @author User
 */
public interface RoleDAO {
    boolean createRole(String title, String description);
    List<Role> getRoles();
    Role getRole(int roleId);
    boolean updateRole(int roleId, String title, String description);
    boolean deleteRole(int roleId);
}
