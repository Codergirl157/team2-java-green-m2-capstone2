package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JDBCReservationDAOIntegrationTest extends DAOIntegrationTest {


    private ReservationDAO reservationDAO;
    private JdbcTemplate jdbcTemplate;
    private JDBCReservationDAO dao;

    @Before
    public void setUp(){
        reservationDAO = new JDBCReservationDAO(getDataSource());
    }


    @Test
    public void return_new_reservation(){

        long nextId = retrieveNextReservationId();

        Reservation newReservation = attainReservation(nextId, 2, 50,LocalDate.parse("2021-06-15"), LocalDate.parse("2021-06-20"), "The Watkins Family");

        Reservation result = dao.createReservation(newReservation);

        assertNotNull(result);
        assertEquals(newReservation.getReservationId(), result.getReservationId());











    }


    private Reservation mapRowToReservation(SqlRowSet results) {

        Reservation reservation = new Reservation();


        reservation.setReservationId(results.getLong("reservation_id"));
        reservation.setSpaceId(results.getLong("space_id"));
        reservation.setNumberOfAttendees(results.getInt("number_of_attendees"));
        reservation.setStartDate(results.getDate("start_date").toLocalDate());
        reservation.setEndDate(results.getDate("end_date").toLocalDate());
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
    private Reservation attainReservation(long reservationId, long spaceId, int numberOfAttendees, LocalDate startDate, LocalDate endDate, String reservedFor) {



        Reservation reservation = new Reservation();

        reservation.setReservationId(reservationId);
        reservation.setSpaceId(spaceId);
        reservation.setNumberOfAttendees(numberOfAttendees);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setReservedFor(reservedFor);

        return reservation;
    }

}
