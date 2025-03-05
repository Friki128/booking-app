package cat.esliceu.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
class BookingAppController {

    private final ObjectMapper objectMapper;
    private final BookingEngine bookingEngine;



    public BookingAppController(ObjectMapper objectMapper, BookingEngine bookingEngine) {
        this.objectMapper = objectMapper;
        this.bookingEngine = bookingEngine;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }



    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingResponse book(@RequestBody BookingRequest bookingRequest) {
        logBooking(bookingRequest);

        return bookingEngine.book(bookingRequest);
    }



    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CancelResponse cancel(@RequestBody CancelRequest cancelRequest) {
        logCancel(cancelRequest);
        return bookingEngine.cancel(cancelRequest);

    }

    private void logBooking(BookingRequest bookingRequest) {
        try {
            String bookingJson = objectMapper.writeValueAsString(bookingRequest);
            System.out.println(bookingJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private void logCancel(CancelRequest cancelRequest) {
        try {
            String bookingJson = objectMapper.writeValueAsString(cancelRequest);
            System.out.println(bookingJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}


