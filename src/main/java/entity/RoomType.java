package entity;

public enum RoomType {
    standard (2000,2),
    delux (3000,3),
    suit (5000,5);

    private  final double pricePerNight;
    private  final int maxGuests;

    RoomType(double pricePerNight, int maxGuests) {
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
    }

    public static RoomType fromDb(String value) {
        return RoomType.valueOf(value.trim().toLowerCase());
    }

    public int getMaxGuests() {
        return maxGuests;
    }
}
