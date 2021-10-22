package com.techelevator;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAOIntegrationTest extends DAOIntegrationTest {

    private static SingleConnectionDataSource dataSource;
    private ReservationDAO reservationDAO;
    private JdbcTemplate jdbcTemplate;


    @Test
    public void return_new_reservation(){





    }


    private Reservation mapRowToReservation(SqlRowSet results) {

        Reservation reservation = new Reservation();


        reservation.setReservationId(results.getLong("reservation_id"));
        reservation.setSpaceId(results.getLong("space_id"));
        reservation.setNumberOfAttendees(results.getInt("number_of_attendees"));
        reservation.setStartDate(results.getDate("start_date"));
        reservation.setEndDate(results.getDate("end_date"));
        reservation.setReservedFor(results.getString("reserved_for"));


        return reservation;

    }

    private int retrieveNextReservationId() {

        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT next_val('reservation_id_seq')");

        if (nextIdResult.next()) {


            return nextIdResult.getInt(1);

        } else {

            throw new RuntimeException("Something went wrong when getting new id");


        }


    }
}
