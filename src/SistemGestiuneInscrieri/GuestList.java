package SistemGestiuneInscrieri;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GuestList {
    private int numberOfSeats;
    private ArrayList<Guest> guestsList = new ArrayList<>(this.numberOfSeats);
    private ArrayList<Guest> waitingList = new ArrayList<>();
    private Guest theGuest;


    public GuestList(int numberOfSeats){
        this.numberOfSeats = numberOfSeats;
    }


    // cautarea unei persoane dupa atributele clasei Guest
    public boolean isEqual(Guest guest, Guest otherGuest){
        return guest.equals(otherGuest);
    }


    // 1. Adaugarea unei noi persoane (inscrise)
    public int addGuest(Guest guest){
        if (this.guestsList.isEmpty()){
            this.guestsList.add(guest);
            /*System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Felicitari! Locul tau la " +
                    "eveniment este confirmat. Te asteptam!");*/
            return 0;
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            boolean bool = this.isEqual(guest, this.guestsList.get(i));
            if (!bool && (this.guestsList.size() < this.numberOfSeats)){
                this.guestsList.add(guest);
                /*System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Felicitari! Locul tau la " +
                        "eveniment este confirmat. Te asteptam!\n");*/
                return 0;
            }else if (!bool && this.guestsList.size() >= this.numberOfSeats){
                this.waitingList.add(guest);
                /*System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Te-ai inscris cu succes " +
                        "in lista de asteptare si ai primit numarul de ordine" + this.waitingList.indexOf(guest) + ". " +
                        "Te vom notifica daca un loc devine disponibil.\n");*/
                return this.waitingList.indexOf(guest) + 1;
            }
        }

//        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Esti deja inscris!");
        return -1; // daca persoana a fost deja inscrisa la eveniment
    }


    // 2. Determina daca o persoana este inscrisa la eveniment (in oricare lista).
    public boolean findGuest(Guest guest){
        for (int j = 0; j < this.guestsList.size(); j++){
            boolean bool = this.isEqual(guest, this.guestsList.get(j));
            if (bool){
                System.out.println(guest.getLastName() + " " + guest.getFirstName() + " se afla pe Guest List!");
                return true;
            }
        }

        for (int i = 0; i < this.waitingList.size(); i++){
            boolean bool = this.isEqual(guest, this.guestsList.get(i));
            if (bool){
                System.out.println(guest.getLastName() + " " + guest.getFirstName() + " se afla pe Waiting List!");
                return true;
            }
        }

        System.out.println(guest.getLastName() + " " + guest.getFirstName() + " nu se afla pe Guest List sau Waiting List!");
        return false;
    }


    // 3. eliminarea unei persoane (inscrise)
    public boolean removeAddedGuest(Guest guest){
        for (int i = 0; i < this.guestsList.size(); i++){
            boolean bool = this.isEqual(guest, guestsList.get(i));
            if (bool){
                this.guestsList.remove(guest);
                this.guestsList.add(this.waitingList.get(0));
                System.out.println(guest.getLastName() + " " + guest.getFirstName() + " a fost stearsa de pe Guest List!");
                return true;
            }else {
                System.out.println(guest.getLastName() + " " + guest.getFirstName() + " nu era inscrisa pe Guest List!");
                return false;
            }
        }

        return false;
    }


    // 4. Actualizarea detaliilor unei persoane inscrise
    public void updateInfo(Guest guest, String lastName, String firstName, String email, String phoneNumber){
        for (int i = 0; i < this.guestsList.size(); i++){
            boolean bool = this.isEqual(guest, this.guestsList.get(i));
            if (bool){
                guest.setLastName(lastName); ////// ??????? cum fac sa actualizez doar 1 camp
                guest.setFirstName(firstName);
                guest.setEmail(email);
                guest.setPhoneNumber(phoneNumber);
            }
        }
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
