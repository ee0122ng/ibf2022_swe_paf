package iss.paf.pafday05transaction.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import iss.paf.pafday05transaction.exception.BookException;
import iss.paf.pafday05transaction.model.Book;
import iss.paf.pafday05transaction.model.Reservation;
import iss.paf.pafday05transaction.model.ReservationDetails;
import iss.paf.pafday05transaction.repository.BookRepo;
import iss.paf.pafday05transaction.repository.ReservationDetailsRepo;
import iss.paf.pafday05transaction.repository.ReservationRepo;

@Service
public class BookReservationService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    ReservationRepo rsvpRepo;

    @Autowired
    ReservationDetailsRepo rsvpDetailsRepo;

    @Transactional(isolation=Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED)
    public Boolean reserveBooks(List<Book> books, String reservePersonName) {

        // Boolean bReservationCompleted = false;
        Boolean bBookExist = false;
        Boolean bBookSufficient = false;
        Boolean bUpdateBook = false;
        Boolean bUpdateReservation = false;
        Boolean bUpdateReservationDetails = false;


        // conditions to meet
        // 1. check is book exists
        // 2. if yes, check if there is available unit to reserve
        // 3. if yes, update the book quantity, create reservation details and create reservation

        // one reservation per person
        Reservation rsvp = new Reservation();

        for (Book book : books) {

            Integer idx = bookRepo.getAllBook().stream().map(b -> b.getTitle()).toList().indexOf(book.getTitle());

            bBookExist = idx >= 0 ? true : false;

            if (bBookExist) {

                Integer availQuantity = bookRepo.getAllBook().get(idx).getQuantity();
                bBookSufficient = availQuantity > 0 ? true : false;

                if (bBookSufficient) {

                    // update the book record
                    bUpdateBook = bookRepo.updateBook(bookRepo.getAllBook().get(idx).getId()) > 0 ? true : false;
                    
                    // update reservation record once per transaction
                    if (null == rsvp.getFullName()) {
                        rsvp.setFullName(reservePersonName);
                        // set reservation to now
                        LocalDate today = LocalDate.now();
                        rsvp.setReservationDate(Date.valueOf(today));
                        // set update status
                        Integer rsvpId = rsvpRepo.insertNewReservation(rsvp);
                        rsvp.setId(rsvpId);
                        bUpdateReservation = rsvpId != null ? true : false;

                    }

                    // create new reservation details
                    ReservationDetails rsvpDetails = new ReservationDetails();
                    rsvpDetails.setBookId(bookRepo.getAllBook().get(idx).getId());
                    rsvpDetails.setReservationId(rsvp.getId());
                    // update reservation details record
                    Integer rsvpDetailsId = rsvpDetailsRepo.insertNewRecord(rsvpDetails);
                    // rsvpDetails.setId(rsvpDetailsId);
                    bUpdateReservationDetails = rsvpDetailsId != null ? true : false;

                } else {

                    throw new BookException("Book is not available");
                }

            } else {

                throw new BookException("Book is not available");
                
            }
        }

        return bBookExist && bBookSufficient && bUpdateBook && bUpdateReservation && bUpdateReservationDetails;
    }
    
}
