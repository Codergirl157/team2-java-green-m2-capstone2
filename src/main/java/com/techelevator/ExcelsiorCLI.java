package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class ExcelsiorCLI {

	private UserInterface userInterface;
	private VenueDAO venueDAO;
	private SpaceDAO spaceDAO;
	private ReservationDAO reservationDAO;


	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsiorVenues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		this.userInterface = new UserInterface();
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);

	}


	public void run() {
		while (true) {
			String userChoice = userInterface.printMenu();
			if (userChoice.equals("1")) {
				List<Venue> listOfVenues = venueDAO.retrieveAllVenues();

				userInterface.viewVenueMenu(listOfVenues);
				int userVenueChoice = userInterface.promptUserForVenue();

				Venue venue = listOfVenues.get(userVenueChoice - 1);
				long venueId = venue.getVenueId();
				Venue userVenue = venueDAO.retrieveVenueById(venueId);
				List<String> categories = venueDAO.retrieveListOfCategories(venueId);
				userInterface.viewVenueDetails(userVenue, categories);
				String secondMenuUserChoice = userInterface.viewNextStepsMenu();

				if(secondMenuUserChoice.equals("1")){
					List<Space> listOfSpaces = spaceDAO.retrieveAllSpacesByVenueId(venueId);
					userInterface.viewAllVenueSpaces(userVenue,listOfSpaces);
					String thirdMenuChoice = userInterface.viewThirdMenu();

					if(thirdMenuChoice.equals("1")){
					LocalDate userDate = userInterface.reserveSpaceDate();
					int userDays = userInterface.reserveSpaceHowLong();
					LocalDate userEndDate = userDate.plusDays(userDays);
					int userAttendees = userInterface.reserveSpaceHowMany();
					List<Space> listOfAvailableSpaces = spaceDAO.retrieveAvailableSpaces(venueId, userDate, userEndDate, userAttendees);
					userInterface.viewAvailableVenueSpaces(listOfAvailableSpaces, userDays);
					Long userSpaceId = userInterface.promptUserForReserveRequest();
					//Space userSpace = spaceDAO.retrieveSpaceBySpaceId(userSpaceId);

						if(userSpaceId == 0){
							userInterface.viewVenueMenu(listOfVenues);
						}else{
							String userReserveFor = userInterface.promptUserForReserveFor();
							//Reservation userReservation = reservationDAO.createReservation(venueId, userSpaceId, userReserveFor, userAttendees, userDate, userEndDate);
							userInterface.printConfirmation(userVenue, userSpaceId, userReserveFor, userAttendees, userDate, userEndDate, userDays);


						}

				} else if(thirdMenuChoice.equals("R")){

						userInterface.viewNextStepsMenu();


					}
				}
				else if(secondMenuUserChoice.equals("2")){
				}
				else if(secondMenuUserChoice.equals("R")){
					userInterface.viewVenueMenu(listOfVenues);

				}

			}
			else{
				break;
			}
		}


		}

		}



