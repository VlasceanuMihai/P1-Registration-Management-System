package SistemGestiuneInscrieri;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {
    private int numberOfSeats;
    private ArrayList<Guest> guestsList = new ArrayList<>(this.numberOfSeats);
    private ArrayList<Guest> waitingList = new ArrayList<>();
    private Guest theGuest;


    GuestList(int numberOfSeats){
        this.numberOfSeats = numberOfSeats;
    }


    // cautarea unei persoane dupa atributele clasei Guest
    private boolean isEqual(Guest currentGuest, Guest addedGuest){
        return currentGuest.equals(addedGuest);
    }


    // 1. Adaugarea unei noi persoane (inscrise)
    int add(Guest guest){
        if (guest == null){
            return 404;
        }

        if (this.guestsList.isEmpty()){
            this.guestsList.add(guest);
            return 0;
        }

        boolean bool = false;
        for (int i = 0; i < this.guestsList.size(); i++){
            bool = this.isEqual(guest, this.guestsList.get(i));
            if (bool){
                return -1; // daca persoana a fost deja inscrisa la eveniment
            }
        }

        if (!bool && this.guestsList.size() < this.numberOfSeats){
            this.guestsList.add(guest);
            return 0;
        }else if(!bool && this.guestsList.size() >= this.numberOfSeats){
            this.waitingList.add(guest);
            return this.waitingList.indexOf(guest) + 1;
        }

        return 404;
    }


    // 2. Determina daca o persoana este inscrisa la eveniment (in oricare lista).
    public boolean check(Guest guest){
        boolean bool = false;
        for (int i = 0; i < this.guestsList.size(); i++){
            bool = this.isEqual(guest, this.guestsList.get(i));
            if (bool){
                System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName() + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(guest) + 1) + "!");
                return true;
            }
        }

        for (int i = 0; i < this.waitingList.size(); i++){
            bool = this.isEqual(guest, this.waitingList.get(i));
            if (bool){
                System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia "+ (this.waitingList.indexOf(guest) + 1) + "!");
                return true;
            }
        }

        System.out.println(guest.getLastName() + " " + guest.getFirstName() + " nu se afla pe Guest List sau Waiting List!");
        return false;
    }


    public boolean check(String lastName, String firstName) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)){
                System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName() + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                return true;
            }
        }

        if (this.waitingList.isEmpty()){
            throw new Exception("Waiting List este goala!");
        }

        for (int i = 0; i < this.waitingList.size(); i++){
            String lastNameFromList = this.waitingList.get(i).getLastName();
            String firstNameFromList = this.waitingList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)){
                System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia "+ (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                return true;
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false;
    }


    public boolean check(String emailOrPhoneNumber) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)){
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)) {
                    System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName() + "] Te afli pe Guest List, pozitia " + (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                    return true;
                }
            }else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)){
                    System.out.println("[" + this.guestsList.get(i).getLastName() + " " + this.guestsList.get(i).getFirstName() + "] Te afli pe Guest List, pozitia "+ (this.guestsList.indexOf(this.guestsList.get(i)) + 1) + "!");
                    return true;
                }
            }
        }

        if (this.waitingList.isEmpty()){
            throw new Exception("Waiting List este goala!");
        }

        for (int i = 0; i < this.waitingList.size(); i++){
            String emailFromList = this.waitingList.get(i).getEmail();
            String phoneNumberFromList = this.waitingList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)){
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)) {
                    System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia " + (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                    return true;
                }
            }else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)){
                    System.out.println("[" + this.waitingList.get(i).getLastName() + " " + this.waitingList.get(i).getFirstName() + "] Te afli pe Waiting List, pozitia "+ (this.waitingList.indexOf(this.waitingList.get(i)) + 1) + "!");
                    return true;
                }
            }
        }

        System.out.println("Nu te aflii pe niciuna din liste!");
        return false;
    }



    // 3. eliminarea unei persoane (inscrise)
    private static boolean isStringOnlyNumeric(String str)
    {
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[0-9]*$")));
    }


    public boolean remove(String lastName, String firstName) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastNameFromList.equalsIgnoreCase(lastName) &&
                    firstNameFromList.equalsIgnoreCase(firstName) &&
                    !this.waitingList.isEmpty()){
                this.guestsList.remove(this.guestsList.get(i));
                this.guestsList.add(this.waitingList.get(0));
                this.waitingList.remove(this.waitingList.get(0));
                return true; // persoana a fost stearsa cu succes
            }else if (lastNameFromList.equalsIgnoreCase(lastName) &&
                    firstNameFromList.equalsIgnoreCase(firstName) &&
                    this.waitingList.isEmpty()){
                this.guestsList.remove(this.guestsList.get(i));
                return true; // persoana a fost stearsa cu succes
            }
        }

        return false; // eroare: persoana nu era inscrisa
    }


    public boolean remove(String emailOrPhoneNumber) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)){
                // isPhoneNumber
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList) && !this.waitingList.isEmpty()){
                    this.guestsList.remove(this.guestsList.get(i));
                    this.guestsList.add(this.waitingList.get(0));
                    this.waitingList.remove(this.waitingList.get(0));
                    return true; // persoana a fost stearsa cu succes
                }else if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList) && this.waitingList.isEmpty()){
                    this.guestsList.remove(this.guestsList.get(i));
                    return true; // persoana a fost stearsa cu succes
                }
            }else {
                // isEmail
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList) && !this.waitingList.isEmpty()){
                    this.guestsList.remove(this.guestsList.get(i));
                    this.guestsList.add(this.waitingList.get(0));
                    this.waitingList.remove(this.waitingList.get(0));
                    return true; // persoana a fost stearsa cu succes
                }else if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList) && this.waitingList.isEmpty()){
                    this.guestsList.remove(this.guestsList.get(i));
                    return true; // persoana a fost stearsa cu succes
                }
            }
        }

        return false; // eroare: persoana nu era inscrisa
    }



    // 4. Actualizarea detaliilor unei persoane inscrise
    public boolean updateInfo(String lastName, String firstName) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            String lastNameFromList = this.guestsList.get(i).getLastName();
            String firstNameFromList = this.guestsList.get(i).getFirstName();

            if (lastName.equalsIgnoreCase(lastNameFromList) &&
                    firstName.equalsIgnoreCase(firstNameFromList)){
                return true;
            }
        }
        return false;
    }


    public boolean updateInfo(String emailOrPhoneNumber) throws Exception {
        if (this.guestsList.isEmpty()){
            throw new Exception("Guest List este goala!");
        }

        for (int i = 0; i < this.guestsList.size(); i++) {
            String emailFromList = this.guestsList.get(i).getEmail();
            String phoneNumberFromList = this.guestsList.get(i).getPhoneNumber();

            if (isStringOnlyNumeric(emailOrPhoneNumber)) {
                if (emailOrPhoneNumber.equalsIgnoreCase(phoneNumberFromList)){
                    return true;
                }
            }else {
                if (emailOrPhoneNumber.equalsIgnoreCase(emailFromList)){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return this.theGuest.toString();
    }


    // 5. Obtinerea listei de persoane care au loc la eveniment (i.e. lista de participare)
    public void guestsList(){
        if (this.guestsList.size() == 0){
            System.out.println("Niciun participant inscris…");
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            System.out.println((i + 1) + ". " + this.guestsList.get(i).toString());
        }
    }


    // 6. Obtinerea listei de persoane din lista de asteptare
    public void waitList(){
        if (this.waitingList.size() == 0){
            System.out.println("Lista de asteptare este goala…");
        }

        for (int i = 0; i < this.waitingList.size(); i++){
            System.out.println((i + 1) + ". " + this.waitingList.get(i).toString());
        }
    }


    // 7. Obtinerea numarului de locuri disponibile in lista de participare
    public int availableSeats(){
        if (this.guestsList.size() == this.numberOfSeats){
            return 0;
        }else {
            return this.numberOfSeats - this.guestsList.size();
        }
    }


    // 8. Obtinerea numarului de persoane participante (i.e. aflate in lista de participare)
    public int guestsNo(){
        return this.guestsList.size();
    }


    // 9. Obtinerea numarului de persoane din lista de asteptare
    public int waitListNo(){
        return this.waitingList.size();
    }


    // 10. Obtinerea numarului total de persoane
    public int subscribeNo(){
        return (this.guestsList.size() + this.waitingList.size());
    }


    // 11. Cautare partiala, dupa un subsir de caractere:
    public ArrayList<String> search(String substring){
        ArrayList<String> contactsList = new ArrayList<String>();

        for (int i = 0; i < this.guestsList.size(); i++){
            String emailField = this.guestsList.get(i).getEmail();
            String phoneNumberField = this.guestsList.get(i).getPhoneNumber();
            String secondNameField = this.guestsList.get(i).getLastName();
            String firstNameField = this.guestsList.get(i).getFirstName();

            if (emailField.contains(substring)){
                contactsList.add(emailField);
            }

            if (phoneNumberField.contains(substring)){
                contactsList.add(phoneNumberField);
            }

            if (secondNameField.contains(substring)){
                contactsList.add(secondNameField);
            }

            if (firstNameField.contains(substring)){
                contactsList.add(firstNameField);
            }


        }

        return contactsList;
    }
}
