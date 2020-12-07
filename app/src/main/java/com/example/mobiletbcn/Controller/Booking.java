package com.example.mobiletbcn.Controller;

import java.util.List;

public interface Booking {
    void userBookingBook(int idUserBooking, int idBookBooking);
    List getAllBookOfUserBooking(int idUser_Booking);
    void deleteBookingById(int id);
}
