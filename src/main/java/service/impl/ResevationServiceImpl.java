package service.impl;

import Dao.ReservationDao;
import Dao.impl.ReservationDaoImpl;
import entity.*;
import service.ReservationService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.sql.Date;
import java.util.List;

public class ResevationServiceImpl extends Reservation  implements ReservationService  {
// ArrayList <Guest> guests=new ArrayList<>();
    ReservationDao dao=new ReservationDaoImpl();

    @Override
    public void addGuest(Guest guest) {
        dao.addGuest(guest);
    }

    @Override
    public List<Guest> list() {
       return  dao.list();
    }

    @Override
    public List<Room> getRoomStatus() {
        return dao.getRoomStatus();
    }

   @Override
    public void addReservation(Reservation reservation){
        dao.addReservation(reservation);;
   }

    @Override
    public double totalBill(int reservationId) {
        ResultSet rs = dao.getBillingData(reservationId);
        try {
            if (rs.next()) {
                Date checkin = rs.getDate("checkin");
                Date checkout = rs.getDate("checkout");
                double charges = rs.getDouble("charges");
                Period period = Period.between(
                        checkin.toLocalDate(),
                        checkout.toLocalDate()
                );
                int days = period.getDays();
                return days * charges;
            }
            throw new RuntimeException("Reservation is not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> rlist() {
       return dao.rlist();
    }
}

