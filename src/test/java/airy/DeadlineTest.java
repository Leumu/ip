package airy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation_validDeadline_success() {
        Deadline d = new Deadline("Return book", "2025-06-02");

        assertEquals("Return book", d.getTaskName());
        assertEquals("2025-06-02", d.getExtraDetailsForStorage());
        assertEquals("[D][ ] Return book (by: Jun 02 2025)", d.toString());
    }

    @Test
    public void markCompleted_deadlineTask_updated() {
        Deadline d = new Deadline("Return book", "2025-06-02");
        d.markCompleted();

        assertEquals("X", d.getStatus());
        assertEquals("[D][X] Return book (by: Jun 02 2025)", d.toString());
    }
}