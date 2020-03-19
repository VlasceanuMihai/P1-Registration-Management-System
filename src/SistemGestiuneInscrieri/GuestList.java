package SistemGestiuneInscrieri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuestList implements Serializable {
    private int numberOfSeats;
    private List<Guest> guestsList = new ArrayList<>(this.numberOfSeats);
    private List<Guest> waitingList = new ArrayList<>();
    private Guest theGuest;


    GuestList(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    public List<Guest> getGuestsList() {
        return guestsList;
    }

    public void setGuestsList(List<Guest> guestsList) {
        this.guestsList = guestsList;
    }

    public List<Guest> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<Guest> waitingList) {
        this.waitingList = waitingList;
    }


    // cautarea unei persoane dupa atributele clasei Guest
    private boolean isEqual(Guest currentGuest, Guest addedGuest) {
        return currentGuest.equals(addedGuest);
    }


    // 1. Adaugarea unei noi persoane (inscrise)
    int add(Guest guest) throws NullPointerException, Exceptions.GuestCannotBeAddedException {
        if (guest == null) {
            throw new NullPointerException("The Guest instance is null!");
        }

        if (this.guestsList.isEmpty()) {
            this.guestsList.add(guest);
            return 0; // Persoana a fost adaugata in guestList
        }

        boolean hasSeatInGuestList = false;
        for (int i = 0; i < this.guestsList.size(); i++) {
            hasSeatInGuestList = this.isEqual(guest, this.guestsList.get(i));
            if (hasSeatInGuestList) {
                return -1; // daca persoana a fost deja inscrisa la eveniment
            }
        }

        if (!hasSeatInGuestList && (this.guestsList.size() < this.numberOfSeats)) {
            this.guestsList.add(guest);
            return 0; // Persoana a fost adaugata in guestList
        } else if (!hasSeatInGuestList && (this.guestsList.size() >= this.numberOfSeats)) {
            this.waitingList.add(guest); // Persoana a fost adaugata in waitingList
            return this.waitingList.indexOf(guest) + 1;
        }

        throw new Exceptions.GuestCannotBeAddedException("Error! Guest cannot be added in any list!");
    }


    // 2. Determina daca o persoana este inscrisa la eveniment (in oricare lista).
    public boolean check(Guest guest) throws NullPointerException{
        if (guest == null) {
            throw new NullPointerException("The Guest instance is null!");
        }

        boolean bool = false;
        for (int i = 0; i < this.guestsList.size(); i++) {
            bool = this.isEqual(guest, this.guestsList.get(i));
            if (bool) {
                System.out.println("[" + this.guestsList.get(i).getLastName() + " " +
                        this.guestsList.get(i).getFirstName() + "] Te afli pe Guest List, pozitia " +
                        (this.guestsList.indexOf(guest) + 1) + "!");
                return true;
            }
        }

        for (int i = 0; i < this.waitingList.size(); i++) {
            bool = this.isEqual(guest, this.waitingList.get(i));
            if (bool) {
                System.out.println("[" + this.waitingList.get(i).getLastName() + " " +
                        this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia " +
                        (this.waitingList.indexOf(guest) + 1) + "!");
                return true;
            }
        }

        System.out.println(guest.getLastName() + " " + guest.getFirstName() + " nu se afla pe Guest List sau Waiting List!");
        return false;
    }


    public boolean check(String lastName, String firstName) throws NullPointerException {
        if (lastName == null || firstName == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            System.out.println("Guest List este goala!");
            return false;
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)) {
                System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName()
                        + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                return true;
            }
        }

        if (this.waitingList.isEmpty()) {
            System.out.println("Waiting List este goala!");
            return false;
        }

        for (int i = 0; i < this.waitingList.size(); i++) {
            String lastNameFromList = this.waitingList.get(i).getLastName();
            String firstNameFromList = this.waitingList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)) {
                System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName()
                        + "] Te afli pe Waiting List, pozitia " + (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                return true;
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false;
    }


    public boolean check(String emailOrPhoneNumber) throws NullPointerException {
        if (emailOrPhoneNumber == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            System.out.println("Guest List este goala!");
            return false;
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)) {
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)) {
                    System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName()
                            + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                    return true;
                }
            } else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)) {
                    System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName()
                            + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                    return true;
                }
            }
        }

        if (this.waitingList.isEmpty()) {
            System.out.println("Waiting List este goala!");
            return false;
        }

        for (int i = 0; i < this.waitingList.size(); i++) {
            String emailFromList = this.waitingList.get(i).getEmail();
            String phoneNumberFromList = this.waitingList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)) {
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)) {
                    System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() +
                            "] Te afli pe Waiting List, pozitia " + (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                    return true;
                }
            } else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)) {
                    System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia " + (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                    return true;
                }
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false;
    }


    // 3. eliminarea unei persoane (inscrise)
    private static boolean isStringOnlyNumeric(String str) {
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[0-9]*$")));
    }


    public boolean remove(String lastName, String firstName) throws NullPointerException {
        if (lastName == null || firstName == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            System.out.println("Guest List este goala!");
            return false;
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastNameFromList.equalsIgnoreCase(lastName) &&
                    firstNameFromList.equalsIgnoreCase(firstName) &&
                    !this.waitingList.isEmpty()) {
                this.guestsList.remove(this.guestsList.get(i));
                this.guestsList.add(this.waitingList.remove(0));
                return true; // persoana a fost stearsa cu succes din GuestList si prima persoana din WaitingList
                             // a fost adaugata in GuestList pe ultima pozitie

            } else if (lastNameFromList.equalsIgnoreCase(lastName) &&
                    firstNameFromList.equalsIgnoreCase(firstName) &&
                    this.waitingList.isEmpty()) {
                this.guestsList.remove(this.guestsList.get(i));
                return true; // persoana a fost stearsa cu succes din GuestList
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false; // eroare: persoana nu este inscrisa
    }


    public boolean remove(String emailOrPhoneNumber) throws NullPointerException {
        if (emailOrPhoneNumber == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            System.out.println("Guest List este goala!");
            return false;
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)) {
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList) && !this.waitingList.isEmpty()) {
                    this.guestsList.remove(this.guestsList.get(i));
                    this.guestsList.add(this.waitingList.remove(0));
                    return true; // persoana a fost stearsa cu succes din GuestList si prima persoana din WaitingList
                                 // a fost adaugata in GuestList pe ultima pozitie

                } else if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList) && this.waitingList.isEmpty()) {
                    this.guestsList.remove(this.guestsList.get(i));
                    return true; // persoana a fost stearsa cu succes din GuestList
                }
            } else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList) && !this.waitingList.isEmpty()) {
                    this.guestsList.remove(this.guestsList.get(i));
                    this.guestsList.add(this.waitingList.remove(0));
                    return true; // persoana a fost stearsa cu succes din GuestList si prima persoana din WaitingList
                                 // a fost adaugata in GuestList pe ultima pozitie

                } else if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList) && this.waitingList.isEmpty()) {
                    this.guestsList.remove(this.guestsList.get(i));
                    return true; // persoana a fost stearsa cu succes
                }
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false; // eroare: persoana nu era inscrisa
    }


    // 4. Actualizarea detaliilor unei persoane inscrise
    public Guest signInForUpdate(String lastName, String firstName) throws NullPointerException, Exceptions.EmptyGuestListException {
        if (lastName == null || firstName == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            throw new Exceptions.EmptyGuestListException("Guest List este goala. Persoana nu se poate afla pe GuestList!");
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)) {
                return this.guestsList.get(i);
            }
        }

        return null;
    }


    public Guest signInForUpdate(String emailOrPhoneNumber) throws Exceptions.EmptyGuestListException {
        if (emailOrPhoneNumber == null){
            throw new NullPointerException();
        }

        if (this.guestsList.isEmpty()) {
            throw new Exceptions.EmptyGuestListException("GuestList este goala. Persoana nu se poate afla pe GuestList!");
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)) {
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)) {
                    return this.guestsList.get(i);
                }
            } else {
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)) {
                    return this.guestsList.get(i);
                }
            }
        }

        return null;
    }


    public boolean update(Guest guest, int fieldToUpdate, String field) {
        if (guest == null){
            throw new NullPointerException();
        }

        if (field == null || field.equalsIgnoreCase("")){
            throw new NullPointerException();
        }

        if (fieldToUpdate == 1) {
            guest.setLastName(field);
            System.out.println("Datele au fost actualizate cu succes!");
            return true;
        } else if (fieldToUpdate == 2) {
            guest.setFirstName(field);
            System.out.println("Datele au fost actualizate cu succes!");
            return true;
        } else if (fieldToUpdate == 3) {
            guest.setEmail(field);
            System.out.println("Datele au fost actualizate cu succes!");
            return true;
        } else if (fieldToUpdate == 4) {
            guest.setPhoneNumber(field);
            System.out.println("Datele au fost actualizate cu succes!");
            return true;
        }

        System.out.println("Datele nu au fost actualizate!");
        return false;
    }


    @Override
    public String toString() {
        return this.theGuest.toString();
    }


    // 5. Obtinerea listei de persoane care au loc la eveniment (i.e. lista de participare)
    public void guestsList() {
        if (this.guestsList.size() == 0) {
            System.out.println("Niciun participant inscris…");
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            System.out.println((i + 1) + ". " + this.guestsList.get(i).toString());
        }
    }


    // 6. Obtinerea listei de persoane din lista de asteptare
    public void waitList() {
        if (this.waitingList.size() == 0) {
            System.out.println("Lista de asteptare este goala…");
        }

        for (int i = 0; i < this.waitingList.size(); i++) {
            System.out.println((i + 1) + ". " + this.waitingList.get(i).toString());
        }
    }


    // 7. Obtinerea numarului de locuri disponibile in lista de participare
    public int availableSeats() {
        if (this.guestsList.isEmpty()) {
            System.out.println("Guest List este goala…");
        }

        return this.numberOfSeats - this.guestsList.size();
    }


    // 8. Obtinerea numarului de persoane participante (i.e. aflate in lista de participare)
    public int guestsNo() {
        return this.guestsList.size();
    }


    // 9. Obtinerea numarului de persoane din lista de asteptare
    public int waitListNo() {
        return this.waitingList.size();
    }


    // 10. Obtinerea numarului total de persoane
    public int subscribeNo() {
        return (this.guestsList.size() + this.waitingList.size());
    }


    // 11. Cautare partiala, dupa un subsir de caractere:
    public ArrayList<Guest> search(String substring) throws NullPointerException{
        ArrayList<Guest> contactsList = new ArrayList<Guest>();

        for (int i = 0; i < this.guestsList.size(); i++) {
            String lastNameField = this.guestsList.get(i).getLastName();
            String firstNameField = this.guestsList.get(i).getFirstName();
            String emailField = this.guestsList.get(i).getEmail();
            String phoneNumberField = this.guestsList.get(i).getPhoneNumber();

            if (lastNameField.contains(substring)) {
                contactsList.add(this.guestsList.get(i));
            } else if (firstNameField.contains(substring)) {
                contactsList.add(this.guestsList.get(i));
            } else if (emailField.contains(substring)) {
                contactsList.add(this.guestsList.get(i));
            } else if (phoneNumberField.contains(substring)) {
                contactsList.add(this.guestsList.get(i));
            }
        }

        return contactsList;
    }
}
