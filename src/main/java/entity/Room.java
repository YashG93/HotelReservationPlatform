package entity;

public class Room  {

    private int roomNo;
    private RoomType roomType;
    private double charges;
    private  String roomstatus;

    public Room() {
    }

    public Room(double charges, int roomNo, RoomType roomType, String roomstatus) {
        this.charges = charges;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomstatus = roomstatus;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(String roomstatus) {
        this.roomstatus = roomstatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", roomType=" + roomType +
                ", charges=" + charges +
                ", room status='" + roomstatus + '\'' +
                '}';
    }
}

