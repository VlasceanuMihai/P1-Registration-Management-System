package SistemGestiuneInscrieri;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
        int numberOfSeats = scanner.nextInt();
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
        String keyWord = scanner.next();

        GuestList guestList = new GuestList(numberOfSeats);
        while(!keyWord.equalsIgnoreCase("quit")){
            switch (keyWord){
                case "help":
                    System.out.println("help         - Afiseaza aceasta lista de comenzi\n" +
                            "add          - Adauga o noua persoana (inscriere)\n" +
                            "check        - Verifica daca o persoana este inscrisa la eveniment\n" +
                            "remove       - Sterge o persoana existenta din lista\n" +
                            "update       - Actualizeaza detaliile unei persoane\n" +
                            "guests       - Lista de persoane care participa la eveniment\n" +
                            "waitlist     - Persoanele din lista de asteptare\n" +
                            "available    - Numarul de locuri libere\n" +
                            "guests_no    - Numarul de persoane care participa la eveniment\n" +
                            "waitlist_no  - Numarul de persoane din lista de asteptare\n" +
                            "subscribe_no - Numarul total de persoane inscrise\n" +
                            "search       - Cauta toti invitatii conform sirului de caractere introdus\n" +
                            "quit         - Inchide aplicatia\n");
                    break;

                case "add":
                    System.out.println("Se adauga o noua persoana...");
                    System.out.println("Introduceti numele de familie: ");
                    String lastName = scanner.next();
                    System.out.println("Introduceti prenumele: ");
                    String firstName = scanner.next();
                    System.out.println("Introduceti email: ");
                    String email = scanner.next();
                    System.out.println("Introduceti numarul de telefon (format „+40733386463“): ");
                    String phoneNumber = scanner.next();

                    Guest guest = new Guest(lastName, firstName, email, phoneNumber);

                    int addGuestValue = guestList.addGuest(guest);
                    if (addGuestValue == 0){
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Felicitari! Locul tau la " +
                                "eveniment este confirmat. Te asteptam!");
                    }else if (addGuestValue == -1){
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Esti deja inscris!");
                    }else {
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Te-ai inscris cu succes " +
                                "in lista de asteptare si ai primit numarul de ordine " + addGuestValue + ". " +
                                "Te vom notifica daca un loc devine disponibil.");
                    }

                    guestList.guestsList();
                    System.out.println();
                    break;

                case "check":
                    break;

                case "remove":
                    break;

                case "update":
                    break;

                case "guests":
                    break;

                case "quit":
                    scanner.close();
                    break;

                default:
                    System.out.println("Comanda invalida!\n");
                    break;
            }

            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            keyWord = scanner.next();
        }
    }
}
