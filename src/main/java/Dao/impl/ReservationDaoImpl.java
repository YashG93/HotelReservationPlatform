package Dao.impl;

import Dao.ReservationDao;
import entity.*;
import util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

    @Override
    public void addGuest(Guest guest) {
        String sql = "Insert into Guests(IdProof,name,member,Number) values(?,?,?,?)";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, guest.getgIdProof());
            stmt.setString(2, guest.getName());
            stmt.setInt(3, guest.getMembers());
            stmt.setLong(4, guest.getNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Guest> list() {
        ArrayList<Guest> guest = new ArrayList<>();
        String sql = "select * from guests";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                guest.add(new Guest(rs.getLong("number"),
                        rs.getString("name"),
                        rs.getInt("member"),
                        rs.getLong("IdProof")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guest;
    }

    @Override
    public List<Room> getRoomStatus() {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql = "select * from rooms ";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomNo(rs.getInt("roomNo"));
                room.setRoomType(
                        RoomType.fromDb(rs.getString("roomType"))
                );
                room.setCharges(rs.getDouble("charges"));
                room.setRoomstatus(rs.getString("roomstatus"));

                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public void addReservation(Reservation reservation) {
        String checkid="select guestid from guests where idProof=?";
        String sql = "INSERT INTO reservation (guestid, roomno, checkin, checkout, restatus,idproof) VALUES (?, ?, ?, ?, ?,?)";
        String nsql="select roomstatus from rooms where roomno=?";
        String msql = "UPDATE rooms SET roomstatus = 'reserved' WHERE roomno = ? ";
        int reservationId=-1;
        String ksql= "select reservation id from reservation where idproof=?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stml = con.prepareStatement(nsql);
            stml.setInt(1,reservation.getRoomno());
            ResultSet roomsr=stml.executeQuery();
            if(roomsr.next()){
                String status=roomsr.getString("roomstatus");
                if(status.equalsIgnoreCase("unavailable")
                || status.equalsIgnoreCase("reserved")){
                    System.out.println("Check Room status , Apply again");
                    return;
                }
            }
            PreparedStatement stm = con.prepareStatement(checkid);
            stm.setLong(1, reservation.getIdProof());
            ResultSet rs = stm.executeQuery();
            Integer guestid ;
             if (rs.next()) {
             guestid=rs.getInt("guestid");
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, guestid);
                stmt.setInt(2, reservation.getRoomno());
                stmt.setDate(3, reservation.getCheckIn());
                stmt.setDate(4, reservation.getCheckOut());
                stmt.setString(5, reservation.getRestatus().name());
                stmt.setLong(6, reservation.getIdProof());
                stmt.executeUpdate();
                 PreparedStatement upstmt = con.prepareStatement(msql);
                 upstmt.setInt(1, reservation.getRoomno());
                 upstmt.executeUpdate();

            }
            else {
              guestid=null;
                System.out.println("GUEST ID NOT FOUND"); PreparedStatement stme = con.prepareStatement(sql);
                stme.setObject(1, guestid);
                stme.setInt(2, reservation.getRoomno());
                stme.setDate(3, reservation.getCheckIn());
                stme.setDate(4, reservation.getCheckOut());
                stme.setString(5, reservation.getRestatus().name());
                stme.setLong(6, reservation.getIdProof());
                stme.executeUpdate();
                System.out.println("RESERVATION ADDED SUCCESSFULLY");
                 PreparedStatement upstmt = con.prepareStatement(msql);
                 upstmt.setInt(1, reservation.getRoomno());
                 upstmt.executeUpdate();
            }
        }catch (SQLException e) {
            throw  new RuntimeException("Enter ROOM NO =>30");

        }
    }

    @Override
    public ResultSet getBillingData(int reservationId) {
        String sql = "select checkin, checkout ,roomno ,charges from reservation  natural join rooms  where reservationid=? ";
        String nsql= "update rooms set roomstatus='available' where roomno=(select roomno from reservation where reservationid=?)";
        String msql="update reservation set restatus='CHECKOUT'  where reservationid=?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, reservationId);
            PreparedStatement stml=con.prepareStatement(nsql);
            stml.setInt(1,reservationId);
            stml.executeUpdate();
            PreparedStatement stmn= con.prepareStatement(msql);
            stmn.setInt(1,reservationId);
            stmn.executeUpdate();
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Reservation> rlist() {
        ArrayList<Reservation> re = new ArrayList<>();
        String sql = "select * from reservation ";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer guestid;

                int gid = rs.getInt("guestid");
                if (rs.wasNull()) {
                    guestid = 0;
                } else {
                    guestid = gid;
                }
                re.add(new Reservation(
                        rs.getLong("idProof"),
                        rs.getInt("roomno"),
                        rs.getDate("checkIn"),
                        rs.getDate("checkOut"),
                        ReStatus.valueOf(rs.getString("restatus")),
                        rs.getInt("reservationId"),
                        guestid
                ));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return re;
    }}

