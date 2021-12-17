package com.techelevator;



import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;


public class UserInterface {
    private JDBCVenueDAO venue;
    private JDBCSpaceDAO space;
    private Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    public String printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1) List Venues");
        System.out.println("Q) Quit");

        return scanner.nextLine();
    }


        public void viewVenueMenu(List<Venue>listOfVenues) {
            int count = 0;
            for (int i = 0; i < listOfVenues.size(); i++) {
                count++;
                String number = Integer.toString(count);
                System.out.println(number + ") " + listOfVenues.get(i).getVenueName());
            }
            System.out.println("R) Return to Previous Screen");

    }
    public void printError (String msg) {
        System.out.println(msg);
    }

    public int promptUserForVenue(){
        System.out.println("\n");
        System.out.println("Which venue would like to view?");
        String answer = scanner.nextLine();
        int venueAnswer = Integer.parseInt(answer);

        return venueAnswer;
    }

    public void viewVenueDetails(Venue userVenue, List<String>categories) {


        System.out.println("Venue: " + userVenue.getVenueName());
        System.out.println("Location: " + userVenue.getCity() + "," + userVenue.getState());
        System.out.println("Categories: " );
        System.out.println();
        System.out.println();
        System.out.println("Description: " + userVenue.getDescription());
        System.out.println();

    }

    public String viewNextStepsMenu(){

        System.out.println("\n");

        System.out.println("What would you like to do next?");
        System.out.println("1) View Spaces ");
        System.out.println("2) Search for Reservation");
        System.out.println("R) Return to Previous Screen");

        return scanner.nextLine();
    }

    public void viewAllVenueSpaces(Venue userVenue,List<Space> listOfSpaces) {
        System.out.println(userVenue.getVenueName());
        System.out.println(String.format("%-35s", "Name") + String.format("%9s","Open") + String.format("%9s", "Close")
                + String.format("%15s", "Daily Rate") + String.format("%20s", "Max. Occupancy"));
        int count = 0;
        for (int i = 0; i < listOfSpaces.size(); i++) {
            count++;
            String number = Integer.toString(count);

            System.out.println(String.format("%-8s","#" + number) + String.format("%-35s", listOfSpaces.get(i).getSpaceName())
                    + String.format("%-6s", listOfSpaces.get(i).getOpenMonth()) + String.format("%-6s", listOfSpaces.get(i).getCloseMonth()) +
                    "$" + String.format("%-15s", listOfSpaces.get(i).getDailyRate()) +
                    String.format("%15s", listOfSpaces.get(i).getMaxOccupancy()));
        }
        System.out.println("\n");
    }

    public String viewThirdMenu() {

        System.out.println("What would you like to do next?");
        System.out.println("1) Reserve a Space ");
        System.out.println("R) Return to Previous Screen");
        System.out.println();

        return scanner.nextLine();

    }

    public void viewAvailableVenueSpaces(List<Space> listOfAvailableSpaces, int userDays){
        System.out.println("The following spaces are available based on your needs: ");
        System.out.println();
        System.out.println(String.format("%-10s", "Space #" )+ String.format("%-35s", "Name") + String.format("%-12s", "Daily Rate") +
                String.format("%-12s", "Max Occup.") + String.format("%-15s","Accessible?") + String.format("%10s", "Total Cost"));
        for (int i = 0; i < listOfAvailableSpaces.size();i++){
        System.out.println(String.format("%-10s", listOfAvailableSpaces.get(i).getSpaceId()) + String.format("%-35s", listOfAvailableSpaces.get(i).getSpaceName()) + "$" + String.format("%-10s",listOfAvailableSpaces.get(i).getDailyRate())
        + String.format("%10s", listOfAvailableSpaces.get(i).getMaxOccupancy()) + String.format("%12s", listOfAvailableSpaces.get(i).isAccessible()) + String.format("%15s", BigDecimal.valueOf(userDays).multiply(listOfAvailableSpaces.get(i).getDailyRate())));
        }

    }

    public long promptUserForReserveRequest(){

        System.out.println("\n");
        System.out.println("Which space would you like to reserve ( enter 0 to cancel)?");
        String answer = scanner.nextLine();
        return Long.parseLong(answer);
    }

    public String promptUserForReserveFor(){

        System.out.println("\n");
        System.out.println("Who is this reservation for?");
        String answer = scanner.nextLine();
        return answer;



    }

    public void printConfirmation(Venue venue, long space, String reserveFor, int attendees, LocalDate arrivalDate,
                                    LocalDate endDate, int userDays){



        System.out.println("\n");
        System.out.println("Thanks for submitting your reservation! The details of your event are listed below:");
        System.out.println("\n");

        System.out.println("Confirmation #: " + Math.random() * 231);
        System.out.println("Venue: " + venue.getVenueName());
        System.out.println("Space: " + space);
        System.out.println("Reserved For: " + reserveFor );
        System.out.println("Attendees: " + attendees);
        System.out.println("Arrival Date: " + formatter.format(arrivalDate) );
        System.out.println("End Date: " + formatter.format(endDate));
        System.out.println("Total Cost: " );//+ BigDecimal.valueOf(userDays).multiply(space.getDailyRate()));






    }

    public LocalDate reserveSpaceDate() {
        System.out.println("When do you need the space?");
        String answer = scanner.nextLine();
        return LocalDate.parse(answer, formatter);
    }

    public int reserveSpaceHowLong(){
        System.out.println("How many days will you need the space?");
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);

    }

    public int reserveSpaceHowMany(){
        System.out.println("How many people will be in attendance?");
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);
    }

}
