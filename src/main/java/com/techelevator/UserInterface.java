package com.techelevator;



import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;


public class UserInterface {
    private JDBCVenueDAO venue;
    private JDBCSpaceDAO space;
    private Scanner scanner = new Scanner(System.in);


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
        System.out.println("Which venue would like to view?");
        String answer = scanner.nextLine();
        int venueAnswer = Integer.parseInt(answer);

        return venueAnswer;
    }

    public String viewVenueDetails(Venue userVenue, List<String>categories) {


        System.out.println("Venue: " + userVenue.getVenueName());
        System.out.println("Location: " + userVenue.getCity() + "," + userVenue.getState());
        System.out.println("Categories: " );
        System.out.println();
        System.out.println();
        System.out.println("Description: " + userVenue.getDescription());
        System.out.println();

        return null;
    }

    public String viewSecondMenu(){

        System.out.println("What would you like to do next?");
        System.out.println("1) View Spaces ");
        System.out.println("2) Search for Reservation");
        System.out.println("R) Return to Previous Screen");

        return scanner.nextLine();
    }

    public String viewAllVenueSpaces(Venue userVenue,List<Space> listOfSpaces) {
        System.out.println(userVenue.getVenueName());
        System.out.println("Name" + "Open" + "Close" + "Daily Rate" + "Max. Occupancy");
        int count = 0;
        for (int i = 0; i < listOfSpaces.size(); i++) {
            count++;
            String number = Integer.toString(count);

            System.out.println("#" + number + " " + listOfSpaces.get(i).getSpaceName() + " " + listOfSpaces.get(i).getOpenMonth()
                    + listOfSpaces.get(i).getCloseMonth() + "$" + listOfSpaces.get(i).getDailyRate() + listOfSpaces.get(i).getMaxOccupancy());
        }
        System.out.println();
        return null;
    }

    public String viewThirdMenu() {

        System.out.println("What would you like to do next?");
        System.out.println("1) Reserve a Space ");
        System.out.println("R) Return to Previous Screen");
        System.out.println();

        return scanner.nextLine();

    }

    public String viewAvailableVenueSpaces(List<Space> listOfAvailableSpaces){
        System.out.println("The following spaces are available based on your needs: ");
        System.out.println();
        for (int i = 0; i < listOfAvailableSpaces.size();i++){
        System.out.println(listOfAvailableSpaces.get(i).getSpaceId() + listOfAvailableSpaces.get(i).getSpaceName() + listOfAvailableSpaces.get(i).getDailyRate()
        + listOfAvailableSpaces.get(i).getMaxOccupancy() + listOfAvailableSpaces.get(i).isAccessible() + "Total Cost ");
        }
        return null;

    }

    public String printConfirmation(){
        return null;
    }

    public LocalDate reserveSpaceDate() {
        System.out.println("When do you need the space?");
        String answer = scanner.nextLine();
        return LocalDate.parse(answer);
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
