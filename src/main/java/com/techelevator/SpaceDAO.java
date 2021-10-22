package com.techelevator;

import java.time.LocalDate;
import java.util.List;

public interface SpaceDAO {



    public List<Space> retrieveAllSpacesByVenueId(long venueId);



    public List<Space> retrieveAvailableSpaces(long venueId, LocalDate startDate, LocalDate endDate, int occupancy);





}
