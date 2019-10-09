package util;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class PaginatorTest {

    @org.junit.jupiter.api.Test
    void of() {
        Optional<Paginator> paginator = Paginator.of(10, 2, 4);
        assertNotNull(paginator.get());
        assertEquals(paginator.get().count(), 3);
        assertEquals(paginator.get().current(), 2);
        assertNotNull(paginator.get().prev());
        assertEquals(paginator.flatMap(p -> p.prev()).get(), Long.valueOf(1L));
        assertEquals(paginator.flatMap(p -> p.next()).get(),Long.valueOf(3L));
    }
}