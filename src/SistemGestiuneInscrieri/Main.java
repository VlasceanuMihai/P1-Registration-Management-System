package SistemGestiuneInscrieri;

import java.util.Scanner;

public class Main {
    private static void inputLastName(String lastName, Scanner scanner){
        for (int i = 0; i < lastName.length(); i++){
            if (!Character.isLetter(lastName.charAt(i))){
                System.out.println("*** Numele trebuie sa contina doar litere! ***");
                i = 0;
                lastName = scanner.next();
            }
        }
    }


    private static void inputFirstName(String firstName, Scanner scanner){
        for (int i = 0; i < firstName.length(); i++){
            if (!Character.isLetter(firstName.charAt(i))){
                System.out.println("*** Prenumele trebuie sa contina doar litere! ***");
                firstName = scanner.next();
            }
        }
    }


    private static void inputPhoneNumber(String phoneNumber, Scanner scanner){
        for (int i = 0; i < phoneNumber.length(); i++){
            if (!Character.isDigit(phoneNumber.charAt(i))){
                System.out.println("*** Numarul de telefon trebuie sa contina doar cifre! ***");
                phoneNumber = scanner.next();
            }
        }
    }


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
                    inputLastName(lastName, scanner);

                    System.out.println("Introduceti prenumele: ");
                    String firstName = scanner.next();
                    inputFirstName(firstName, scanner);

                    System.out.println("Introduceti email: ");
                    String email = scanner.next();

                    System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
                    String phoneNumber = scanner.next();
                    inputPhoneNumber(phoneNumber, scanner);

                    Guest guest = new Guest(lastName, firstName, email, phoneNumber);
                    int addGuestValue = guestList.add(guest);
                    if (addGuestValue == 0){
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Felicitari! Locul tau la " +
                                "eveniment este confirmat. Te asteptam!");
                    }else if (addGuestValue == -1){
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Esti deja inscris!");
                    }else if (addGuestValue == 404){
                        System.out.println("Datele nu au fost completate!");
                    }else {
                        System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Te-ai inscris cu succes " +
                                "in lista de asteptare si ai primit numarul de ordine " + addGuestValue + ". " +
                                "Te vom notifica daca un loc devine disponibil.");
                    }

                    guestList.guestsList();
                    System.out.println();
                    break;

                case "check":
                    System.out.println("Se cauta o persoana in Guest List si Wait List...");
                    System.out.println("Introduceti numele de familie: ");
                    lastName = scanner.next();
                    inputLastName(lastName, scanner);

                    System.out.println("Introduceti prenumele: ");
                    firstName = scanner.next();
                    inputFirstName(firstName, scanner);

                    System.out.println("Introduceti email: ");
                    email = scanner.next();

                    System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
                    phoneNumber = scanner.next();
                    inputPhoneNumber(phoneNumber, scanner);

                    guest = new Guest(lastName, firstName, email, phoneNumber);
                    guestList.check(guest);
                    break;

                case "remove":
                    System.out.println("Se sterge o persoana existenta din lista…");
                    System.out.println("Alege modul de autentificare, tastand:\n" +
                            "1 - Nume si prenume\n" +
                            "2 - Email\n" +
                            "3 - Numar de telefon (format \"+40733386463\")");

                    int authenticationNumber = scanner.nextInt();
                    if (authenticationNumber == 1){
                        System.out.println("Introduceti numele de familie: ");
                        lastName = scanner.next();
                        inputLastName(lastName, scanner);

                        System.out.println("Introduceti prenumele: ");
                        firstName = scanner.next();
                        inputFirstName(firstName, scanner);
                        guestList.remove(lastName, firstName);
                    }else if(authenticationNumber == 2){
                        System.out.println("Introduceti email: ");
                        email = scanner.next();
                        if (guestList.remove(email)) {
                            System.out.println("Stergerea persoanei s-a realizat cu succes.");
                        }else {
                            System.out.println("Eroare: persoana nu era inscrisa");
                        }
                    }else if (authenticationNumber == 3){
                        System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
                        phoneNumber = scanner.next();
                        inputPhoneNumber(phoneNumber, scanner);
                        guestList.remove(phoneNumber);
                    }
                    guestList.guestsList();
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
