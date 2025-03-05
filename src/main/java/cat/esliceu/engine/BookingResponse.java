package cat.esliceu.engine;

public record BookingResponse(
        String status,
        String bookingRef,
        Double totalPrice) {
}
