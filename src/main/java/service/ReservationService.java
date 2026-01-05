package service;

import entity.Guest;
import entity.Reservation;
import entity.Room;

import java.util.List;

public interface ReservationService {
    public void addGuest(Guest guest);
    List<Guest> list();
    List<Room> getRoomStatus();
    void addReservation(Reservation reservation);
    double totalBill(int reservationid);
    List<Reservation> rlist();

}