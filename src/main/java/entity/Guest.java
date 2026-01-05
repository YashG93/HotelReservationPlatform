package entity;

public class Guest {

    int guestid;
    private  long gIdProof;
    private String name;
    private int members;
    private  long number;

    public Guest() {
    }

    public Guest(long number, String name, int members, long gIdProof) {
        this.number = number;
        this.name = name;
        this.members = members;
        this.gIdProof = gIdProof;
    }
    public Guest(long gIdProof) {
        this.gIdProof = gIdProof;
    }

    public long getgIdProof() {
        return gIdProof;
    }


    public int getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setgIdProof(long gIdProof) {
        this.gIdProof = gIdProof;
    }

    public void setMembers(int members) {
        this.members = members;
    }


    @Override
    public String toString() {
        return "Guest{" +
                " IdProof=" + gIdProof +
                ", Name='" + name + '\'' +
                ", Members=" + members +
                ", Mobile NO=" + number +
                '}';
    }
}
