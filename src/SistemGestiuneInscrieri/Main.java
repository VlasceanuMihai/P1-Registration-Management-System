package SistemGestiuneInscrieri;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String file = "save.dat";

    private static final long serialVersionUID = 1L;


    public static int inputMismatchInt(Scanner scanner){
        while (true){
            try {
                int number = scanner.nextInt();
                if (number <= 3 && number >= 1){
                    return number;
                }
                System.out.println("Optiunile valabile sunt:\n 1 - Nume si prenume\n 2 - Email\n 3 - Numar de telefon");
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Introdu o valoare intreaga.");
            }
        }
    }


    public static int numberOfSeatsMethod(Scanner scanner){
        while (true){
            try{
                return scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Introdu o valoare intreaga.");
            }
        }
    }


    private static String inputLastName(String lastName, Scanner scanner){
        for (int i = 0; i < lastName.length(); i++){
            if (!Character.isLetter(lastName.charAt(i))){
                System.out.println("*** Numele trebuie sa contina doar litere! ***");
                i = 0;
                lastName = scanner.next();
            }
        }
        return lastName;
    }


    private static String inputFirstName(String firstName, Scanner scanner){
        for (int i = 0; i < firstName.length(); i++){
            if (!Character.isLetter(firstName.charAt(i))){
                System.out.println("*** Prenumele trebuie sa contina doar litere! ***");
                firstName = scanner.next();
            }
        }
        return firstName;
    }


    private static String inputPhoneNumber(String phoneNumber, Scanner scanner){
        for (int i = 0; i < phoneNumber.length(); i++){
            if (!Character.isDigit(phoneNumber.charAt(i))){
                System.out.println("*** Numarul de telefon trebuie sa contina doar cifre! ***");
                phoneNumber = scanner.next();
            }
        }
        return phoneNumber;
    }


    private static void checkRemoveUpdate(Scanner scanner, GuestList guestList, String keyWord) {
        int authenticationNumber = inputMismatchInt(scanner);

        // 'Check' option
        if (keyWord.equalsIgnoreCase("check")){
            boolean isChecked = false;
            if (authenticationNumber == 1){
                System.out.println("Introduceti numele de familie: ");
                String lastName = scanner.next();
                lastName = inputLastName(lastName, scanner);

                System.out.println("Introduceti prenumele: ");
                String firstName = scanner.next();
                firstName = inputFirstName(firstName, scanner);
                isChecked = guestList.check(lastName, firstName);
            }else if(authenticationNumber == 2){
                System.out.println("Introduceti email: ");
                String email = scanner.next();
                isChecked = guestList.check(email);
            }else if (authenticationNumber == 3) {
                System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
                String phoneNumber = scanner.next();
                phoneNumber = inputPhoneNumber(phoneNumber, scanner);
                isChecked = guestList.check(phoneNumber);
            }

            if (!isChecked){
                System.out.println("Eroare: Persoana nu este inscrisa!");
            }

            // 'Remove' option
        }else if (keyWord.equalsIgnoreCase("remove")){
            if (authenticationNumber == 1){
                System.out.println("Introduceti numele de familie: ");
                String lastName = scanner.next();
                lastName = inputLastName(lastName, scanner);

                System.out.println("Introduceti prenumele: ");
                String firstName = scanner.next();
                firstName = inputFirstName(firstName, scanner);
                if (guestList.remove(lastName, firstName)) {
                    System.out.println("Stergerea persoanei s-a realizat cu succes.");
                }else {
                    System.out.println("Eroare: Persoana nu este inscrisa!");
                }
            }else if(authenticationNumber == 2){
                System.out.println("Introduceti email: ");
                String email = scanner.next();
                if (guestList.remove(email)) {
                    System.out.println("Stergerea persoanei s-a realizat cu succes.");
                }else {
                    System.out.println("Eroare: Persoana nu este inscrisa");
                }
            }else if (authenticationNumber == 3){
                System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
                String phoneNumber = scanner.next();
                phoneNumber = inputPhoneNumber(phoneNumber, scanner);
                if (guestList.remove(phoneNumber)) {
                    System.out.println("Stergerea persoanei s-a realizat cu succes.");
                }else {
                    System.out.println("Eroare: Persoana nu este inscrisa!");
                }
            }
        }
    }


    // 'Update' option
    public static Guest signInUpdateMethod(Scanner scanner, GuestList guestList) {
        int authenticationNumber = inputMismatchInt(scanner);

        if (authenticationNumber == 1){
            System.out.println("Introduceti numele de familie: ");
            String lastName = scanner.next();
            lastName = inputLastName(lastName, scanner);

            System.out.println("Introduceti prenumele: ");
            String firstName = scanner.next();
            firstName = inputFirstName(firstName, scanner);

            try {
                return guestList.signInForUpdate(lastName, firstName);
            }catch (NullPointerException e){
                System.out.println("Guest-ul nu a fost gasit in GuestList!");
                return null;
            }catch (Exceptions.EmptyGuestListException e){
                System.out.println(e.getMessage());
                return null;
            }

        }else if(authenticationNumber == 2){
            System.out.println("Introduceti email: ");
            String email = scanner.next();

            try {
                return guestList.signInForUpdate(email);
            }catch (NullPointerException e){
                System.out.println("Guest-ul nu a fost gasit in GuestList!");
                return null;
            }catch (Exceptions.EmptyGuestListException e){
                System.out.println(e.getMessage());
                return null;
            }

        }else if (authenticationNumber == 3){
            System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
            String phoneNumber = scanner.next();
            phoneNumber = inputPhoneNumber(phoneNumber, scanner);

            try {
                return guestList.signInForUpdate(phoneNumber);
            }catch (NullPointerException e){
                System.out.println("Guest-ul nu a fost gasit in GuestList!");
                return null;
            }catch (Exceptions.EmptyGuestListException e){
                System.out.println(e.getMessage());
                return null;
            }
        }

        return null;
    }


    private static void selectFieldToUpdate(Scanner scanner, GuestList guestList, Guest guest) {
        System.out.println("Alege campul de actualizat, tastand:\n" +
                "1 - Nume\n" +
                "2 - Prenume\n" +
                "3 - Email\n" +
                "4 - Numar de telefon (format \"+40733386463\")\n");

        int fieldToUpdate = inputMismatchInt(scanner);
        if (fieldToUpdate == 1){
            System.out.println("Se actualizeaza numele de familie: ");
            String lastNameUpdated = scanner.next();
            lastNameUpdated = inputLastName(lastNameUpdated, scanner);
            guestList.update(guest, fieldToUpdate, lastNameUpdated);

        }else if(fieldToUpdate == 2){
            System.out.println("Se actualizeaza prenumele: ");
            String firstNameUpdated = scanner.next();
            firstNameUpdated = inputFirstName(firstNameUpdated, scanner);
            guestList.update(guest, fieldToUpdate, firstNameUpdated);

        }else if (fieldToUpdate == 3){
            System.out.println("Se actualizeaza email: ");
            String emailUpdated = scanner.next();
            guestList.update(guest, fieldToUpdate, emailUpdated);

        }else if (fieldToUpdate == 4){
            System.out.println("Se actualizeaza numarul de telefon (format „0733386463“): ");
            String phoneNumberUpdated = scanner.next();
            phoneNumberUpdated = inputPhoneNumber(phoneNumberUpdated, scanner);
            guestList.update(guest, fieldToUpdate, phoneNumberUpdated);
        }
    }


    private static void addMethod(Scanner scanner, GuestList guestList) throws Exceptions.GuestCannotBeAddedException {
        System.out.println("Se adauga o noua persoana...");
        System.out.println("Introduceti numele de familie: ");
        String lastName = scanner.next();
        lastName = inputLastName(lastName, scanner);

        System.out.println("Introduceti prenumele: ");
        String firstName = scanner.next();
        firstName = inputFirstName(firstName, scanner);

        System.out.println("Introduceti email: ");
        String email = scanner.next();

        System.out.println("Introduceti numarul de telefon (format „0733386463“): ");
        String phoneNumber = scanner.next();
        inputPhoneNumber(phoneNumber, scanner);

        // Create 'Guest'
        Guest guest = new Guest(lastName, firstName, email, phoneNumber);
        try {
            int addGuestValue = guestList.add(guest);
            if (addGuestValue == 0) {
                System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Felicitari! Locul tau la " +
                        "eveniment este confirmat. Te asteptam!");
            } else if (addGuestValue == -1) {
                System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Esti deja inscris!");
            } else {
                System.out.println("[" + guest.getLastName() + " " + guest.getFirstName() + "] Te-ai inscris cu succes " +
                        "in lista de asteptare si ai primit numarul de ordine " + addGuestValue + ". " +
                        "Te vom notifica daca un loc devine disponibil.");
            }
        }catch (Exceptions.GuestCannotBeAddedException e){
            e.getMessage();
        }

        System.out.println("Guest List: ");
        guestList.guestsList();
        System.out.println("Waiting List: ");
        guestList.waitList();
        System.out.println();
    }


    // Save data into 'save.dat' file
    private static void saveFile(GuestList guestList) throws IOException {
        try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))){
            binaryFileOut.writeObject(guestList);
        }
    }


    // Restore from 'save.dat' file
    private static GuestList restore() throws EOFException, IOException, ClassNotFoundException {
        GuestList restoreGuestList = null;

        try (ObjectInputStream binaryFileIn = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))){
            restoreGuestList = (GuestList) binaryFileIn.readObject();
        }catch (EOFException e){
            System.out.println("Empty file!");
        }finally{
            return restoreGuestList;
        }
    }


    // Reset/Delete data from 'save.dat' file
    private static void reset()throws IOException{
        try (ObjectOutputStream reset = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            reset.reset();
        }
    }



    public static void main(String[] args) throws NullPointerException, EOFException, IOException, ClassNotFoundException  {
        try (Scanner scanner = new Scanner(System.in)) {
            GuestList guestList = restore();

            if (guestList == null){
                System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
                int numberOfSeats = numberOfSeatsMethod(scanner);
                guestList = new GuestList(numberOfSeats);
            }

            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            String keyWord = scanner.next();


            while (!keyWord.equalsIgnoreCase("quit")) {
                switch (keyWord) {
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
                        addMethod(scanner, guestList);
                        break;

                    case "check":
                        System.out.println("Se cauta o persoana in Guest List si Waiting List…");
                        System.out.println("Alege modul de autentificare, tastand:\n" +
                                "1 - Nume si prenume\n" +
                                "2 - Email\n" +
                                "3 - Numar de telefon (format \"+40733386463\")");

                        checkRemoveUpdate(scanner, guestList, keyWord); // check
                        break;

                    case "remove":
                        System.out.println("Se sterge o persoana existenta din lista…");
                        System.out.println("Alege modul de autentificare, tastand:\n" +
                                "1 - Nume si prenume\n" +
                                "2 - Email\n" +
                                "3 - Numar de telefon (format \"+40733386463\")");

                        checkRemoveUpdate(scanner, guestList, keyWord);

                        System.out.println("\nGuest List: ");
                        guestList.guestsList();
                        System.out.println("Waiting List: ");
                        guestList.waitList();
                        System.out.println();
                        break;

                    case "update":
                        System.out.println("Se actualizeaza datele unei persoane…");
                        System.out.println("Alege modul de autentificare, tastand:\n" +
                                "1 - Nume si prenume\n" +
                                "2 - Email\n" +
                                "3 - Numar de telefon (format \"+40733386463\")");

                        Guest guestToUpdate = signInUpdateMethod(scanner, guestList);
                        if (guestToUpdate == null) {
                            System.out.println("Guest-ul nu se afla in GuestList!");
                            break;
                        }
                        System.out.println("Autentificare reusita!");
                        selectFieldToUpdate(scanner, guestList, guestToUpdate);

                        System.out.println("\nGuest List: ");
                        guestList.guestsList();
                        System.out.println("Waiting List: ");
                        guestList.waitList();
                        System.out.println();
                        break;

                    case "guests":
                        guestList.guestsList();
                        break;

                    case "waitlist":
                        guestList.waitList();
                        break;

                    case "available":
                        System.out.println("Numarul de locuri ramase: " + guestList.availableSeats());
                        break;

                    case "guests_no":
                        System.out.println("Numarul de participanti: " + guestList.guestsNo());
                        break;

                    case "waitlist_no":
                        System.out.println("Dimensiunea listei de asteptare: " + guestList.waitListNo());
                        break;

                    case "subscribe_no":
                        System.out.println("Numarul total de persoane: " + guestList.subscribeNo());
                        break;

                    case "search":
                        System.out.println("Introduceti sirul de caractere: ");
                        String string = scanner.next();
                        ArrayList<Guest> contactsList = guestList.search(string);
                        System.out.println("Contacts List: \n" + contactsList);
                        break;

                    case "reset":
                        reset();
                        System.out.println("Reset successful!");
                        break;

                    default:
                        System.out.println("Comanda invalida!\n");
                        break;
                }

                System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                keyWord = scanner.next();
            }

            System.out.println("Quit! Save successful!");
            saveFile(guestList);
        }
    }
}
