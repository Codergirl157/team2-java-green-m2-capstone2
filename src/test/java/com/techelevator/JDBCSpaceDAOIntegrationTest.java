package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class JDBCSpaceDAOIntegrationTest extends DAOIntegrationTest {



    private SpaceDAO spaceDAO;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    private JDBCSpaceDAO dao = new JDBCSpaceDAO(getDataSource());

    @Before
    public void setUp(){
        spaceDAO = new JDBCSpaceDAO(getDataSource());
    }



    @Test
    public void return_spaces_by_venue_id(){
        long nextId = retrieveNextSpaceId();



        List<Space> listOfAllSpaces = dao.retrieveAllSpacesByVenueId(7);

        assertNotNull(listOfAllSpaces);

            Assert.assertEquals(3, listOfAllSpaces.size());


        listOfAllSpaces.add(attainSpace(nextId, "santas lap", 2, 11, 12, true,BigDecimal.valueOf(500.00)));

        Assert.assertEquals(4, listOfAllSpaces.size());


    }


    @Test
    public void return_available_spaces(){

    List<Space> listOfAvailableSpaces = dao.retrieveAvailableSpaces(5, LocalDate.parse("2021-10-17"), LocalDate.parse("2021-10-25"), 50);


    assertNotNull(listOfAvailableSpaces);
    Assert.assertEquals(1, listOfAvailableSpaces.size());




    }

    private Space mapRowToSpace(SqlRowSet results) {

        Space space = new Space();

        space.setSpaceId(results.getLong("id"));
        space.setSpaceName(results.getString("name"));
        space.setMaxOccupancy(results.getInt("max_occupancy"));
        space.setOpenMonth(results.getInt("open_month"));
        space.setCloseMonth(results.getInt("close_month"));
        space.setAccessible(results.getBoolean("is_accessible"));
        space.setDailyRate(results.getBigDecimal("daily_rate"));


        return space;

    }

    private int retrieveNextSpaceId() {

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('space_id_seq')");

        if (nextIdResult.next()) {


            return nextIdResult.getInt(1);

        } else {

            throw new RuntimeException("Something went wrong when getting new id");


        }


    }

    private Space attainSpace(long spaceId,String spaceName, int maxOccupancy, int openMonth, int closeMonth, boolean isAsccessible, BigDecimal dailyRate ) {

        Space space = new Space();

        space.setSpaceId(spaceId);
        space.setSpaceName(spaceName);
        space.setMaxOccupancy(maxOccupancy);
        space.setOpenMonth(openMonth);
        space.setCloseMonth(closeMonth);
        space.setAccessible(isAsccessible);
        space.setDailyRate(dailyRate);

        return space;
    }

}
