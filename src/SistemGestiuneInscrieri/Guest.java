package SistemGestiuneInscrieri;

public class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;


    public Guest(String lastName, String firstName, String email, String phoneNumber){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (object == null){
            System.out.println("Datele nu au fost completate!");
            return true;
        }

        if (this.getClass() != object.getClass()){
            return true;
        }

        Guest guestObject = (Guest) object;
        if ((this.lastName.equalsIgnoreCase(guestObject.lastName) &&
                this.firstName.equalsIgnoreCase(guestObject.firstName)) ||
                this.email.equalsIgnoreCase(guestObject.email) ||
                this.phoneNumber.equalsIgnoreCase(guestObject.phoneNumber)){
            return true;
        }

        return false;
    }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.phoneNumber == null) ? 0 : this.phoneNumber.hashCode());
        return result;

    }


    @Override
    public String toString(){
        return "Nume: " + this.getLastName() + " " + this.getFirstName() + ", Email: " + this.getEmail() + ", Telefon: " + this.getPhoneNumber();
    }
}
