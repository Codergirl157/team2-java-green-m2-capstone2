package com.techelevator;

import java.util.List;

public interface SpaceDAO {



    public List<Space> retrieveAllSpacesByVenueId(long venueId);



    public List<Space> retrieveAvailableSpaces();





}
