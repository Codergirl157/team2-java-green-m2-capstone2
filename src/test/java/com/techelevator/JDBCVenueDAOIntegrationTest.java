package com.techelevator;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JDBCVenueDAOIntegrationTest extends DAOIntegrationTest{
    private static SingleConnectionDataSource dataSource;
    private VenueDAO venueDAO;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    private JDBCVenueDAO dao;

    @Before
    public void setUp(){
        venueDAO = new JDBCVenueDAO(getDataSource());
    }

    @Test
    public void return_all_venues(){

        List <Venue> listOfAllVenues = venueDAO.retrieveAllVenues();


        assertNotNull(listOfAllVenues);
        Assert.assertEquals(15, listOfAllVenues.size());
        //check size first then
        //add something to list see if it goes up by one, INSERT into table

    }


    @Test
    public void return_venue_by_id(){

     long nextId = retrieveNextVenueId();

     //Venue theVenue = attainVenue( nextId, "SomeVenue", "Jumbo", "OH", "The perfect place to test the size");
     String sqlInsertVenue;
        sqlInsertVenue = "INSERT INTO venue(id, name, city, state, description ) VALUES (?, 'SomeVenue', 'Jumbo', 'OH', 'The perfect place to test the size' ) ";
        jdbcTemplate.update(sqlInsertVenue, nextId);

     Venue results = dao.retrieveVenueById(nextId);

     assertNotNull(results);
    // assertVenuesAreEqual(theVenue, results);

        //when inserting dummy data we can hard code jdbcTemplate and then compare

    }




    private Venue mapRowToVenue(SqlRowSet results){

        Venue venue = new Venue();

        venue.setVenueId(results.getLong("id"));
        venue.setVenueName(results.getString("name"));
        venue.setCity(results.getString("city"));
        venue.setState(results.getString("state"));
        venue.setDescription(results.getString("description"));

        return venue;




    }

    private long retrieveNextVenueId(){

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");

        if(nextIdResult.next()){


            return nextIdResult.getInt(1);

        }else{

            throw new RuntimeException("Something went wrong when getting new id");


        }

    }

    private void assertVenuesAreEqual(Venue expected, Venue actual){
        assertEquals(expected.getVenueId(), actual.getVenueId());
        assertEquals(expected.getVenueName(), actual.getVenueName());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getState(), actual.getState());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
    private Venue attainVenue(long venueId,String venueName, String city, String state, String description ) {

        Venue venue = new Venue();

        venue.setVenueId(venueId);
        venue.setVenueName(venueName);
        venue.setCity(city);
        venue.setState(state);
        venue.setDescription(description);

        return venue;
    }

}

