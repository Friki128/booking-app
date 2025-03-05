package cat.esliceu.engine;

import java.util.List;

public record BookingRequest(
        String hotelCode,
        String roomCode,
        String boardCode,
        String checkIn,
        String checkOut,
        List<String> guests) {
}
