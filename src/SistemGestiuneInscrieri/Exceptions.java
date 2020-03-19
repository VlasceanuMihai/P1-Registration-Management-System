package SistemGestiuneInscrieri;

/**
 * Project: [P1]SistemGestiuneInscrieri
 * Author: mihai
 * Date: 3/13/2020
 */
public class Exceptions {
    // Guest cannot be added to guestList/waitingList
    public static class GuestCannotBeAddedException extends RuntimeException{
        public GuestCannotBeAddedException(){
            super();
        }

        public GuestCannotBeAddedException(String message){
            super(message);
        }
    }


    // EmptyGustList exception
    public static class EmptyGuestListException extends RuntimeException{
        public EmptyGuestListException(){
            super();
        }

        public EmptyGuestListException(String message){
            super(message);
        }
    }


    // EmptyWaitingList exception
    public static class EmptyWaitingListException extends RuntimeException{
        public EmptyWaitingListException(){
            super();
        }

        public EmptyWaitingListException(String message){
            super(message);
        }
    }
}
