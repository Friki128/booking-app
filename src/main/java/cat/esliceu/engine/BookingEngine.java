package cat.esliceu.engine;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingEngine {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();


    public BookingResponse book(BookingRequest booking) {
        String status = checkAvailability(booking);
        String reference = generateRef();
        double totalPrice = calculateTotalPrice(booking.guests(), booking.checkIn(), booking.checkOut());


        return new BookingResponse(
                status,
                reference,
                totalPrice
        );
    }

    public CancelResponse cancel(CancelRequest cancelRequest) {
        if(checkBookingExists(cancelRequest.bookingRef())) {
            String status = cancelBooking(cancelRequest);

            return new CancelResponse(status);
        } else {
            throw new BookingNotFound(cancelRequest.bookingRef() + " bookines does not exist in the system");
        }
    }

    private boolean checkBookingExists(String bookingRef) {
        return true;
    }

    private String cancelBooking(CancelRequest cancelRequest) {
        return "CANCELLED";
    }

    private String checkAvailability(BookingRequest booking) {
        //it should check availability
        return "OK";
    }

    private double calculateTotalPrice(List<String> guestList, String checkIn, String checkOut) {
        int guests = guestList.size();

        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);
        long days = checkInDate.until(checkOutDate).getDays();


        return 100.0 * guests * days;
    }


    private String generateRef() {
        StringBuilder ref = new StringBuilder("ref-");
        for (int i = 0; i < 8; i++) {
            ref.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return ref.toString();
    }
}

