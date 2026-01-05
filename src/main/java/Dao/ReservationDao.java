package Dao;
import entity.Guest;
import entity.Reservation;
import entity.Room;

import java.sql.ResultSet;
import java.util.List;

public interface ReservationDao {
    public  void addGuest(Guest guest);
    public List<Guest> list();
    List<Room> getRoomStatus();

    void  addReservation(Reservation reservation);
    ResultSet getBillingData(int ReservationId);
    List<Reservation> rlist();

}
