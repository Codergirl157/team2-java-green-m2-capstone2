package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JDBCReservationDAO implements ReservationDAO {


    private JdbcTemplate jdbcTemplate;

    public JDBCReservationDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    @Override
    public Reservation createReservation(Reservation newReservation) {

        int nextReservationId = retrieveNextReservationId();

        String sql = "INSERT INTO reservation(reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) "
        +  "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, nextReservationId, newReservation.getSpaceId(), newReservation.getNumberOfAttendees(),newReservation.getStartDate(),
        newReservation.getEndDate(), newReservation.getReservedFor());


        return newReservation;
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
