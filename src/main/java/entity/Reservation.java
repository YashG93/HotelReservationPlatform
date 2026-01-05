package entity;

import java.sql.Date;

public class Reservation extends Guest{
    private  int reservationId;
    private  long idProof;
    private  int roomno;
    private Date checkIn;
    private Date checkOut;
    private  ReStatus restatus;

    public Reservation() {
    }

    public Reservation(long idProof, int roomno, Date checkIn, Date checkOut, ReStatus restatus) {
        this.idProof = idProof;
        this.roomno = roomno;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.restatus = restatus;
    };
    public Reservation(long idProof, int roomno, Date checkIn, Date checkOut, ReStatus restatus , int reservationId ,int guestid) {
        this.idProof = idProof;
        this.roomno = roomno;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.restatus = restatus;
        this.reservationId=reservationId;
        this.guestid=guestid;
    }

    public Reservation(int guestid) {
        this.guestid=guestid;
    }

    public Reservation(long idProof, int roomno, Date checkIn, Date checkOut, ReStatus restatus, int reservationId) {
        this.idProof = idProof;
        this.roomno = roomno;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.restatus = restatus;
        this.reservationId=reservationId;
    }


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public long getIdProof() {
        return idProof;
    }

    public void setIdProof(long idProof) {
        this.idProof = idProof;
    }

    public ReStatus getRestatus() {
        return restatus;
    }

    public void setRestatus(ReStatus restatus) {
        this.restatus = restatus;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return " Reservation {"+"Guest_Id="+guestid+" , Reservation_Id="+reservationId+" , IdProof="+idProof+" , Room No="+
                roomno +" , Check_In="+checkIn+" , Check Out="+checkOut+" , Reservation Status="+restatus;
    }
}
