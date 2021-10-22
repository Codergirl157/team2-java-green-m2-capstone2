package com.techelevator;

import jdk.jfr.Category;

import java.util.List;

public interface VenueDAO {


    public List<Venue> retrieveAllVenues();


    public Venue retrieveVenueById(Long id);


    public List<String> retrieveListOfCategories();


}
