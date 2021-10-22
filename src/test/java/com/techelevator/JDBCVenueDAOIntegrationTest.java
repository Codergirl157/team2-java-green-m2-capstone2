package com.techelevator;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.Assert.assertNotNull;

public class JDBCVenueDAOIntegrationTest extends DAOIntegrationTest{

    private static SingleConnectionDataSource dataSource;
    private VenueDAO venueDAO;
    private JdbcTemplate jdbcTemplate;


    @Test
    public void return_all_venues(){
        List <Venue> listOfAllVenues = venueDAO.retrieveAllVenues();


        assertNotNull(listOfAllVenues);
        Assert.assertEquals(15, listOfAllVenues.size());

    }


    @Test
    public void return_venue_by_id(){

     long nextId = retrieveNextVenueId();
     //Venue theVenue =


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

    private int retrieveNextVenueId(){

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT next_val('venue_id_seq')");

        if(nextIdResult.next()){


            return nextIdResult.getInt(1);

        }else{

            throw new RuntimeException("Something went wrong when getting new id");


        }

       // private Venue attainVenue(Long venueId,  )







    }
}
