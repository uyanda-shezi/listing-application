Listing Application
/////////////////////////////////////////////////////////////////////
Description

This is a simple appliction that lets you access all
job listings, roles, companies and applicants associated with an
application.
/////////////////////////////////////////////////////////////////////
Requirements

To run the project please download the zip from this github repo
	-https://github.com/uyanda-shezi/listing-application.git

The SQL script to load up a mock database is also included in the repo
under the file name listingdb.sql*

*You will have to go into the the project web.xml and change the username,
 password, uri and driver to the ones pertaining to your database.

Once downloaded please open on NetBeans IDE 12.0 and a Apache 
Tomcat 7 server.
Using JDK 1.8
From the github repo there are libraries required to run the project
Load up these libraries into the project
	> From the REST directory load up all of the files
	> From the jackson directory load up all the files
	> Load up the relevant database connector(I included the one
	  using for MySQL on my machine)
/////////////////////////////////////////////////////////////////////
How To Use The Application

Once the application is running on your local server and the database is up
you can use the url to access functions
	-http://127.0.0.1:8080/restlistingsapplication/resources/restlistingsapplication
//////////////////////////////////////////////////////////////////////////////////////////////////////
		- GET Methods(all results are in JSON)
			(all {id}'s are integer params, {email} is a string)
			-/listings
				- return all listings on the database
			-/applicants
				- return all applicants on the database
			-/roles
				- return all roles on the database
			-/applications
				- return all applications on the database
**************************************************************************************************
			-/listings/{id}
				- return a specific listing
			-/listings/{id}/company
				- return the company associated with a listing
			-/listings/{id}/role
				- return the role associated with a listing
			-/roles/{id}
				- return a specific role
			-/companies/{id}
				- return a specific company
			-/applicant/{email}
				- return a specific applicant
			-/applications/{id}
				- return a specific application
			-/applications/{id}/listing
				- return a listing associated with an application
			-/applications/{id}/listing/company
				- return a company associated with an application
			-/applications/{id}/listing/role
				- return a role associated with an application
/////////////////////////////////////////////////////////////////////////////////////////////////////

		- Delete Methods(all {id}'s are integer params, {email} is a string)
			-/listings/{id}
				- remove a specific listing
			-/roles/{id}
				- remove a specific role
			-/companies/{id}
				- remove a specific company
			-/applicant/{email}
				- remove a specific applicant
			-/applications/{id}
				- remove a specific application
//////////////////////////////////////////////////////////////////////////////////////////////////////
		- Put Methods(all {id}'s are integer params, {email} is a string)
			-/listings/{id}
				- update a specific listing. id not required if it is specified as a query param
				- other query params that are required
					- companyid
					- roleid
					- closedate(yyyy-mm-dd)
			-/roles/{id}
				- update a specific role. id not required if it is specified as a query param
				- other query params that are required
					- title(String)
					- description(String)
			-/companies/{id}
				- update a specific company. id not required if it is specified as a query param
				- other query params that are required
					- name(String)
					- address(String)
			-/applicant/{email}
				- update a specific applicant. email not required if it is specified as a query param
				- other query params that are required
					- name(String)
					- surname(String)
					- phone(string)
//////////////////////////////////////////////////////////////////////////////////////////////////////
		- Post Methods
			-/listings
				- create a listing
				- query params that are required
					- companyid
					- roleid
					- closedate(yyyy-mm-dd)
			-/applicants
				- create an applicant
				- query params that are required
					- name(String)
					- surname(String)
					- email(String)
				- optional params
					-phone(String)
			-/roles
				- create a role
				- query params that are required
					- title(String)
					- description(String)
			-/companies
				- create a company
				- query params that are required
					- name(String)
					- address(String)
			-/applications
				- create an application
				- query params that are required
					- listingid(integer)
					- email(String)