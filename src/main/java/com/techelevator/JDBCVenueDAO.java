package com.techelevator;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenueDAO implements VenueDAO{


    private JdbcTemplate jdbcTemplate;

    public JDBCVenueDAO(DataSource dataSource){

      this.jdbcTemplate = new JdbcTemplate(dataSource);

    }



    @Override
    public List<Venue> retrieveAllVenues() {

        List<Venue> venues = new ArrayList<>();

        String sql = "SELECT venue.*, city.name, state.abbreviation " +
                "FROM venue " +
                "JOIN city on venue.city_id = city.id "+
                "JOIN state on city.state_abbreviation = state.abbreviation ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){

          Venue venue =  mapRowToVenue(results);
          venues.add(venue);

        }

        return venues;
    }

    @Override
    public Venue retrieveVenueById(Long id) {

        Venue venue = null;
        //
        String sql = "SELECT venue.*, city.name, state.abbreviation " +
                "FROM venue " +
                "JOIN city on venue.city_id = city.id "+
                "JOIN state on city.state_abbreviation = state.abbreviation "
                + "WHERE venue.id = ?";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){

          venue = mapRowToVenue(results);


        }

        return venue;
    }

    @Override
    public List<String> retrieveListOfCategories() {

        List<String> categories = new ArrayList<>();

        String sql = "SELECT category.name, venue_id " +
                "FROM category "
                + "JOIN category_venue ON category.id = category_venue.category.id"
                + "WHERE venue_id = ?";

        return categories;
    }

    private Venue mapRowToVenue(SqlRowSet results){

    Venue venue = new Venue();

       venue.setVenueId(results.getLong("id"));
       venue.setVenueName(results.getString("name"));
       venue.setCity(results.getString("name"));
       venue.setState(results.getString("abbreviation"));
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


   }






}
