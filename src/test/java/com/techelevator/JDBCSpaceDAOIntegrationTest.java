package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCSpaceDAOIntegrationTest extends DAOIntegrationTest {



    private SpaceDAO spaceDAO;
    private JdbcTemplate jdbcTemplate;
    private JDBCSpaceDAO dao;

    @Before
    public void setUp(){
        spaceDAO = new JDBCSpaceDAO(getDataSource());
    }



    @Test
    public void return_spaces_by_venue_id(){






    }


    @Test
    public void return_available_spaces(){





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
