package za.co.uyanda.interview.dao;

import java.time.LocalDate;
import java.util.List;
import za.co.uyanda.interview.pojo.Listing;

/**
 *
 * @author User
 */
public interface ListingDAO {
    boolean createListing(int companyId, int roleId, LocalDate closingDate);
    List<Listing> getListings();
    Listing getListing(int listingId);
    boolean updateListing(int listingId, int companyId, int roleId, LocalDate closingDate);
    boolean deleteListing(int listingId);
}
