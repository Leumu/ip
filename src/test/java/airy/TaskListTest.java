package airy;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_validTask_success() {
        // Empty ArrayList
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");

        tasks.addTask(todo);

        assertEquals(1, tasks.getSize());
        assertEquals(todo, tasks.getTasks().get(0));
    }

    @Test
    public void markTask_validIndex_taskMarked() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Borrow book");

        tasks.addTask(todo);
        Task taskMark = tasks.markTask(0);

        assertEquals("X", taskMark.getStatus());
    }
}
