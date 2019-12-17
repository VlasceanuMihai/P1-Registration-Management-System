package SistemGestiuneInscrieri;

import java.util.ArrayList;

public class GuestList {
    private int numberOfSeats;
    private ArrayList<Guest> guestsList = new ArrayList<>(3);
    private ArrayList<Guest> waitingList;
    private Guest guest;


    public GuestList(Guest guest){
        this.guest = guest;
    }


    public boolean isEqual(Guest guest, Guest otherGuest){
        return guest.equals(otherGuest);
    }


    public int addGuest(Guest guest){
        if (this.guestsList.isEmpty()){
            System.out.println("GuestsList is empty!");
            this.guestsList.add(guest);
        }

        for (int i = 0; i < this.guestsList.size(); i++){
            boolean bool = guest.equals(this.guestsList.get(i));
            if (!bool && (this.guestsList.size() < 20)){
                System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                this.guestsList.add(guest);
                return 0; // a primit loc la eveniment
            }else if (!bool){
                System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine <X>. Te vom notifica daca un loc devine disponibil.");
                this.waitingList.add(guest);
                return this.waitingList.indexOf(this.waitingList.get(i)); // daca persoana este pe locul "x" in lista de asteptare
            }
        }

        return -1; // daca persoana a fost deja inscrisa la eveniment
    }


    public boolean findGuest(Guest guest){
        for (int j = 0; j < guestsList.size(); j++){
            if (guest.equals(this.guestsList.get(j))){
                System.out.println("Guest " + guest.getLastName() + " " + guest.getFirstName() + " se afla pe lista Guest List!");
                return true;
            }
        }

        for (int i = 0; i < waitingList.size(); i++){
            if (guest.equals(this.waitingList.get(i))){
                System.out.println("Guest " + guest.getLastName() + " " + guest.getFirstName() + " se afla pe Waiting List!");
                return true;
            }
        }

        return false;
    }


    public boolean removeAddedGuest(){

    }
}
