package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCSpaceDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    @Override
    public List<Space> retrieveAllSpacesByVenueId(long venueId) {

        List<Space> listOfAllSpaces = new ArrayList<>();

        Space space = null;

                String sql = "SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, space.daily_rate::money::numeric::float8, space.max_occupancy "
                        + "FROM space "
                        + "WHERE venue_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,venueId);

        while(results.next()){

             space = mapRowToSpace(results);

        listOfAllSpaces.add(space);

        }


        return listOfAllSpaces;
    }

    @Override
    public List<Space> retrieveAvailableSpaces(long venueId, LocalDate startDate, LocalDate endDate,  int occupancy) {
        List<Space> spaces = new ArrayList<>();

        String availableSpaceSql = "SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, space.daily_rate::money::numeric::float8, space.max_occupancy  FROM space " +
                "WHERE space.venue_id = ? " +
                "AND space.id NOT IN (SELECT space.id FROM space " +
                "LEFT JOIN reservation ON space.id = reservation.space_id " +
                "WHERE (? <= reservation.end_date " +
                "AND ? >= reservation.start_date) " +
                "OR EXTRACT(MONTH FROM CAST(? AS DATE)) < space.open_from " +
                "OR EXTRACT(MONTH FROM CAST(? AS DATE)) > space.open_to " +
                "OR space.max_occupancy < ?) " +
                "ORDER BY space.daily_rate DESC " +
                "LIMIT 5 ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(availableSpaceSql,venueId,startDate,endDate, startDate, endDate,  occupancy);

        while(results.next()){

            Space theSpace = mapRowToSpace(results);
            spaces.add(theSpace);
        }


        return spaces;
    }

    //@Override
    //public Space retrieveSpaceBySpaceId(Long id) {
        //Space space = null;

        //String sql = "SELECT space.* " +
               // "FROM space " +
                //"WHERE id = ? ";


        //SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        //while(results.next()){

           //space = mapRowToSpace(results) ;

        //}


        //return space;
    //}


    private Space mapRowToSpace(SqlRowSet results) {

        Space space = new Space();

        space.setSpaceId(results.getLong("id"));
        space.setSpaceName(results.getString("name"));
        space.setMaxOccupancy(results.getInt("max_occupancy"));
        space.setOpenMonth(results.getInt("open_from"));
        space.setCloseMonth(results.getInt("open_to"));
        space.setAccessible(results.getBoolean("is_accessible"));
        space.setDailyRate(results.getBigDecimal("daily_rate"));




        return space;

    }

    private int retrieveNextSpaceId() {

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT next_val('space_id_seq')");

        if (nextIdResult.next()) {


            return nextIdResult.getInt(1);

        } else {

            throw new RuntimeException("Something went wrong when getting new id");


        }

    }
}
