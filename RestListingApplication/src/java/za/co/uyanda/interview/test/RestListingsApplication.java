package za.co.uyanda.interview.test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import za.co.uyanda.interview.dao.ApplicantDAO;
import za.co.uyanda.interview.dao.ApplicationDAO;
import za.co.uyanda.interview.dao.CompanyDAO;
import za.co.uyanda.interview.dao.ListingDAO;
import za.co.uyanda.interview.dao.RoleDAO;
import za.co.uyanda.interview.dao.impl.ApplicantDAOImpl;
import za.co.uyanda.interview.dao.impl.ApplicationDAOImpl;
import za.co.uyanda.interview.dao.impl.CompanyDAOImpl;
import za.co.uyanda.interview.dao.impl.ListingDAOImpl;
import za.co.uyanda.interview.dao.impl.RoleDAOImpl;
import za.co.uyanda.interview.pojo.Applicant;
import za.co.uyanda.interview.pojo.Application;
import za.co.uyanda.interview.pojo.Company;
import za.co.uyanda.interview.pojo.Listing;
import za.co.uyanda.interview.pojo.Role;

@Path("/restlistingsapplication") 

public class RestListingsApplication {
///////////////////////DAO///////////////////////////////////////////////////////////////
    ApplicantDAO aDAO = new ApplicantDAOImpl();
    ListingDAO lDAO = new ListingDAOImpl();
    CompanyDAO cDAO = new CompanyDAOImpl();
    RoleDAO rDAO = new RoleDAOImpl();
    ApplicationDAO appDAO = new ApplicationDAOImpl();
///////////////////////GLOBAL VARIABLES//////////////////////////////////////////////////////    
    Company company = null;
    Role role = null;
    Listing listing = null;
    Applicant applicant = null;
    Application application = null;
    LocalDate ld;
/////////////////////////////////////////////LISTINGS///////////////////////////////////////    
    @GET 
    @Path("/listings/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Listing getListing(@PathParam("id") int id){ 
      listing = lDAO.getListing(id);
      return listing;
    }
    @GET 
    @Path("/listings") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Listing> getListings(){ 
      List<Listing> listingList = lDAO.getListings();
      for(Listing myListing :listingList){
          cDAO.getCompany(myListing.getCompany().getCompanyId());
          rDAO.getRole(myListing.getRole().getRoleId());
          listing.setCompany(cDAO.getCompany(listing.getCompany().getCompanyId()));
      }
      return listingList;
    }
    
    @POST
    @Path("/listings")
    public Response addListing(@QueryParam("companyid") int companyId, @QueryParam("roleid") int roleId, @QueryParam("closedate") String closeDate){
        company = cDAO.getCompany(companyId);
        role = rDAO.getRole(roleId);
        if(company==null){
            return Response.status(500).entity("That company is not in the database. Please create it first").build();
        }
        if(role==null){
            return Response.status(500).entity("That role is not in the database. Please create it first").build();
        }
        try{
            ld = LocalDate.parse(closeDate);
        } catch(DateTimeParseException ex){
            return Response.status(500).entity("Date not in correct format. \nExpected: yyyy-mm-dd\nGot: "+ closeDate).build();
        }
        String s ="Query parameters are: \nCompany: "+company+"\nRole: "+role+"\nClosing Date:" + closeDate;
        if(lDAO.createListing(companyId,roleId,ld)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("cannot add listing").build();
    }
    @PUT
    @Path("/listings")
    public Response updateListing(@QueryParam("listingid")int listingId, @QueryParam("companyid") int companyId, @QueryParam("roleid") int roleId, @QueryParam("closedate") String closeDate){
        company = cDAO.getCompany(companyId);
        role = rDAO.getRole(roleId);
        listing = lDAO.getListing(listingId);
        
        if(listing==null){
            return Response.status(500).entity("That listing is not in the database. Please create it first").build();
        }
        if(company==null){
            return Response.status(500).entity("That company is not in the database. Please create it first").build();
        }
        if(role==null){
            return Response.status(500).entity("That role is not in the database. Please create it first").build();
        }
        try{
            ld = LocalDate.parse(closeDate);
        } catch(DateTimeParseException ex){
            return Response.status(500).entity("Date not in correct format. \nExpected: yyyy-mm-dd\nGot: "+ closeDate).build();
        }
        String s ="Query parameters are: \nCompany: "+company+"\nRole: "+role+"\nClosing Date:" + closeDate;
        if(lDAO.updateListing(listingId,companyId,roleId,ld)){
            return Response.status(200).entity(s).build();
        }
        
        return Response.status(500).entity("cannot add listing").build();
    }
////////////////////////ROLES//////////////////////////////////////////////////////////    
    @GET 
    @Path("/roles") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Role> getRoles(){ 
      List<Role> rolesList = rDAO.getRoles();
      return rolesList;
    }
    @GET 
    @Path("/roles/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Role getRole(@PathParam("id") int id){ 
      role = rDAO.getRole(id);
      if(role!=null){
          return role;
      }
      Response.status(500).entity("This roleid "+ id +" does not exist\nPlease create it").build();
      return new Role(0, new Company(0,"no company","no address") , "no title", "no description");
    }
    @POST
    @Path("/roles")
    public Response addRole(@QueryParam("title") String title, @QueryParam("description") String description){
        if(title==null){
            return Response.status(500).entity("The title null. Please enter a title").build();
        }
        String s ="Query parameters are: \nTitle: "+title+"\nRole Description: "+description;
        if(rDAO.createRole(title,description)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to add applicant").build();
    }
    @PUT
    @Path("/roles")
    public Response updateRole(@QueryParam("id") int id, @QueryParam("companyid") int companyid, @QueryParam("title") String title, @QueryParam("description") String description){
        if(rDAO.getRole(id)!=null){
            if(rDAO.getRole(id).getCompany().getCompanyId()==companyid){
                if(rDAO.updateRole(id, title, description)){
                    String s ="Query parameters are: \nId: "+id+"\nTitle: "+title+"\nDescription: "+ description;
                    return Response.status(200).entity(s).build();
                }
                return Response.status(500).entity("That role is not linked to that business. Please create it first").build();
            }
            return Response.status(500).entity("That role is not in the database. Please create it first").build();
        }
        return Response.status(500).entity("Unable to update applicant").build();
    }
    @DELETE
    @Path("/roles")
    public Response removeRole(@QueryParam("id") int id){
        String s ="Query parameters are: \nid: "+id;
        if(rDAO.deleteRole(id)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to remove role").build();
    }
//////////////////////////COMPANIES//////////////////////////////////////////////    
    @GET 
    @Path("/companies") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Company> getCompanies(){
        List<Company> companyList = new ArrayList(); 
        if(cDAO.getCompanies()!=null){
            companyList = cDAO.getCompanies();
        }
        return companyList;
    }
    @GET 
    @Path("/companies/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Company getCompany(@PathParam("id") int id){
        if(cDAO.getCompany(id)!=null){
            company = cDAO.getCompany(id);
        }
        return new Company();
    }
    @POST
    @Path("/companies")
    public Response addCompany(@QueryParam("name") String name, @QueryParam("address") String address){
        if(name==null){
            return Response.status(500).entity("\"name\" parameter not found").build();
        }
        if(address==null){
            return Response.status(500).entity("\"address\" parameter not found").build();
        }
        String s ="Query parameters are: \nCompany name: "+name+"\nCompany address: "+address;
        if(cDAO.createCompany(name,address)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to add applicant").build();
    }
    @PUT
    @Path("/companies")
    public Response updateCompany(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("address") String address){
        if(name==null){
            return Response.status(500).entity("\"name\" parameter not found").build();
        }
        if(address==null){
            return Response.status(500).entity("\"address\" parameter not found").build();
        }
        if(cDAO.getCompany(id)==null){
            return Response.status(500).entity("Company does not exist").build();
        }
        String s ="Query parameters are: \nEmail: "+id+"\nName: "+name+"\nSurname: "+ address;
        if(cDAO.updateCompany(id, name, address)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to update applicant").build();
    }
    @DELETE
    @Path("/companies")
    public Response removeCompany(@QueryParam("id") int id){
        if(cDAO.getCompany(id)==null){
            return Response.status(500).entity("Company does not exist").build();
        }
        String s ="Query parameters are: \nid: "+id;
        if(cDAO.deleteCompany(id)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to remove applicant").build();
    }
/////////////////////////////////////APPLICANTS/////////////////////////////////    
    @GET 
    @Path("/applicants") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Applicant> getApplicants(){ 
        List<Applicant> applicantList = new ArrayList();
        if(aDAO.getApplicants()!=null){
            applicantList = aDAO.getApplicants();
        }
      return applicantList;
    }
    @GET 
    @Path("/applicants/{email}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Applicant getApplicant(@PathParam("email") String email){ 
        if(aDAO.getApplicant(email)!=null){
            applicant = aDAO.getApplicant(email);
            return applicant;
        }
      return new Applicant();
    }
    @POST
    @Path("/applicants")
    public Response addApplicant(@QueryParam("email") String email, @QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("phone") String phone){
        if(email==null){
            return Response.status(500).entity("email can not be blank").build();
        }
        if(name==null){
            return Response.status(500).entity("name can not be blank").build();
        }
        if(surname==null){
            return Response.status(500).entity("surname can not be blank").build();
        }
        if(phone==null){
            phone = "0";
        }
        String s ="Query parameters are: \nEmail: "+email+"\nName: "+name+"\nSurname: "+ surname +"\nPhone: " + phone;
        if(aDAO.createApplicant(email, name, surname, phone)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to add applicant").build();
    }
    @PUT
    @Path("/applicants")
    public Response updateApplicant(@QueryParam("email") String email, @QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("phone") String phone){
        if(email==null){
            return Response.status(500).entity("email can not be blank").build();
        }
        if(name==null){
            name = getApplicant(email).getName();
        }
        if(surname==null){
            surname = getApplicant(email).getSurname();
        }
        if(phone==null){
            phone = getApplicant(email).getCellNumber();
        }
        String s ="Query parameters are: \nEmail: "+email+"\nName: "+name+"\nSurname: "+ surname +"\nPhone: " + phone;
        if(aDAO.updateApplicant(email, name, surname, phone)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to update applicant").build();
    }
    @DELETE
    @Path("/applicants")
    public Response removeApplicant(@QueryParam("email") String email){
        if(email==null){
            return Response.status(500).entity("email is blank").build();
        }
        if(aDAO.getApplicant(email)==null){
            return Response.status(500).entity("Applicant not in database").build();
        }
        String s ="Query parameters are: \nEmail: "+email;
        if(aDAO.deleteApplicant(email)){
            return Response.status(200).entity(s).build();
        }
        return Response.status(500).entity("Unable to remove applicant").build();
    }
    
    @GET 
    @Path("/applications") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Application> getApplications(){ 
      List<Application> applicationList = appDAO.getApplications();
      return applicationList;
    }
    @GET 
    @Path("/applications/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Application getApplication(@PathParam("id") int id){ 
      application = appDAO.getApplication(id);
      return application;
    }
    @POST
    @Path("/applications")
    public Response addApplication(@PathParam("applicant") String email, @PathParam("listingid") int listingid){
        if(lDAO.getListing(listingid)!=null){
            for(Application app:appDAO.getApplications()){
                if(app.getListing().getListingId()==listingid){
                    if(app.getApplicant().getEmail().equals(email)){
                        return Response.status(500).entity("Email + listing combo already exists").build();
                    }
                }
            }
        }
        if(appDAO.createApplication(email, listingid)){
            return Response.status(200).entity("Application created").build();
        }
        return Response.status(500).entity("Cannot create application").build();
    }
    @PUT
    @Path("/applications")
    public Response updateApplication(@PathParam("applicationid") int applicationid, @PathParam("applicant") String email, @PathParam("listingid") int listingid){
        if(appDAO.getApplication(applicationid)!=null){
            addApplication(email, listingid);
        }
        return Response.status(500).entity("Cannot create application").build();
    }
    @DELETE
    @Path("/applications")
    public Response removeApplication(@PathParam("applicationid") int applicationid){
        if(appDAO.getApplication(applicationid)!=null){
            if(appDAO.deleteApplication(applicationid)){
                Response.status(200).entity("Application " + applicationid + " has been removed");
            }
        }
        return Response.status(500).entity("Cannot create application").build();
    }
}
