package app;

import Dao.ReservationDao;
import Dao.impl.ReservationDaoImpl;
import entity.Guest;
import entity.ReStatus;
import entity.Reservation;
import entity.Room;
import service.ReservationService;
import service.impl.ResevationServiceImpl;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ResevationServiceImpl service=new ResevationServiceImpl();
        Scanner sc=new Scanner(System.in );

        InputMismatchException m=new InputMismatchException();

        while (true){
            System.out.println("------------Welcome to Hotel Reservation Platform--------------");
            System.out.println("1)Add Guest");
            System.out.println("2)Current Status");
            System.out.println("3)Reservation");
            System.out.println("4)Exit");
            try {
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        sc.nextLine();
                        System.out.println("Add Name");
                        String name = sc.nextLine();
                        System.out.println("Add IdProof");
                        long gIdProof = sc.nextLong();
                        System.out.println("Add members");
                        int members = sc.nextInt();
                        System.out.println("Add Mobile number");
                        long number = sc.nextLong();

                        service.addGuest(new Guest(number, name, members, gIdProof));
                        System.out.println("Guest Added successfully");
                        break;

                    case 2:
                        System.out.println("1.List all guest");
                        System.out.println("2.Charges");
                        System.out.println("3.Room Status");
                        int subchoice = sc.nextInt();
                        switch (subchoice) {
                            case 1:
                                System.out.println("List All guest");
                                List<Guest> guest = service.list();
                            {
                                for (Guest g : guest) {
                                    System.out.println(g);
                                }
                                break;
                            }
                            case 2:
                                System.out.println("Charges ");
                                System.out.println("Standard Rooms 2000");
                                System.out.println("Delux Rooms 3000");
                                System.out.println("Suite Rooms 5000");
                                break;

                            case 3:
                                System.out.println("Room Status");
                                List<Room> r = service.getRoomStatus();
                                r.forEach(System.out::println);
                                break;
                            default:
                                System.out.println("Choose correct option");
                                break;
                        }
                        break;

                    case 3:
                        System.out.println("1.Add Reservation");
                        System.out.println("2.List Reservation");
                        System.out.println("3.Billing");
                        subchoice = sc.nextInt();
                        switch (subchoice) {
                            case 1:
                                System.out.println("Reservation Details");

                                try{System.out.println("Enter IdProof");
                                Long idProof = sc.nextLong();
                                System.out.println("Enter Room No");
                                int roomno = sc.nextInt();
                                System.out.println("Check In Time yyyy-mm-dd");
                                String checkInstr = sc.next();
                                System.out.println("Check Out Time yyyy-mm-dd");
                                String checkOutstr = sc.next();
                                System.out.println("Reservation Status");
                                System.out.println("BOOKED");
                                String status = sc.next();
                                Date checkIn = Date.valueOf(checkInstr);
                                Date checkOut = Date.valueOf(checkOutstr);

                                ReStatus restatus = ReStatus.valueOf(status);
                                service.addReservation(new Reservation(idProof, roomno, checkIn, checkOut, restatus));

                               }catch (IllegalArgumentException e){
                                   System.out.println("Enter in correct Format");

                               }catch (RuntimeException e){
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                List<Reservation> reservation = service.rlist();
                            {
                                for (Reservation re : reservation) {
                                    System.out.println(re);
                                }
                            }
                            break;
                            case 3:
                                System.out.println(" Reservation Id ?");
                                int reservationid = sc.nextInt();
                                try{service.totalBill(reservationid);
                                double bill = service.totalBill(reservationid);
                                System.out.println(bill);}
                                catch (RuntimeException r){
                                    System.out.println(r.getMessage());
                                }
                                System.out.println("See You Soon ....");
                                break;
                            default:
                                System.out.println("choose correct option");
                        }
                        break;
                    case 4:
                        System.out.println("Exit System");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choose correct option");
                }
            } catch (InputMismatchException e){
                System.out.println("Enter Correct input");
                sc.nextLine();
            }
        }}}