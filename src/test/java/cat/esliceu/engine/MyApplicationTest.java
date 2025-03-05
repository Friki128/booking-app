package cat.esliceu.engine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyApplicationTest {
    @Autowired
    BookingAppController bookingAppController;
    @Test
    void contextLoads() {
        // This ensures the application context loads successfully
        assert bookingAppController != null;
    }
    @Test
    void pingPong(){
        assert bookingAppController.ping().equals("pong");
    }

    @Test
    void cancel(){
        assert bookingAppController.cancel(new CancelRequest("ref-7VY6LSUE")).equals(new CancelResponse("CANCELLED"));
    }

    @Test
    void book(){
        List<String> guest = new ArrayList<>();
        guest.add("Pax1");
        guest.add("Pax2");
        assert bookingAppController.book(new BookingRequest("AMTS00000", "BUNK", "BB", "2025-06-23", "2025-06-30", guest)).status().equals("OK");
    }
}
