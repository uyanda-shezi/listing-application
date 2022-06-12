package za.co.uyanda.interview.pojo;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Applicant {
    private String name;
    private String surname;
    private String cellNumber;
    private String email;

    public Applicant() {
        this("no applicant","no applicant","no applicant","no applicant");
    }

    public Applicant(String name, String surname, String cellNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.cellNumber = cellNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Applicant{" + " name=" + name + ", surname=" + surname + ", cellNumber=" + cellNumber + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.surname);
        hash = 37 * hash + Objects.hashCode(this.cellNumber);
        hash = 37 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Applicant other = (Applicant) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.cellNumber, other.cellNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
