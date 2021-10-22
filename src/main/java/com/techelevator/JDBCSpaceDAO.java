package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCSpaceDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    @Override
    public List<Space> retrieveAllSpacesByVenueId() {

        List<Space> listOfAllSpaces = new ArrayList<>();







        return null;
    }

    @Override
    public List<Space> retrieveAvailableSpaces() {
        return null;
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

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT next_val('space_id_seq')");

        if (nextIdResult.next()) {


            return nextIdResult.getInt(1);

        } else {

            throw new RuntimeException("Something went wrong when getting new id");


        }

    }
}
