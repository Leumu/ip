package airy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the Parser class.
 * Verifies that outputs match expected results after executing commands on given inputs.
 */
public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void parse_withCommandAndArgs_trimsAndSplitsCorrectly() {
        String[] result = parser.parse("  Deadline  return book /by Sunday  ");
        assertArrayEquals(new String[]{"deadline", "return book /by Sunday"}, result);
    }
    @Test
    void parse_withOnlyCommand_returnsEmptyArgs() {
        String[] result = parser.parse("LIST");
        assertArrayEquals(new String[]{"list", ""}, result);
    }
    @Test
    void parseTaskNum_validNumber_returnsZeroBasedIndex() {
        int index = parser.parseTaskNum("mark", " 3 ", 5);
        assertEquals(2, index);
    }
    @Test
    void parseTaskNum_outOfBounds_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseTaskNum("mark", "10", 3));
        assertThrows(AiryException.class, () -> parser.parseTaskNum("mark", "0", 3));
    }

    // ---------- checkArg() ----------
    @Test
    void checkArg_emptyArgs_throwsException() {
        AiryException ex = assertThrows(AiryException.class, () -> parser.checkArg("todo", ""));
        assertTrue(ex.getMessage().contains("Please enter a task name"));
    }

    // ---------- parseDeadline() ----------
    @Test
    void parseDeadline_validInput_splitsAndTrims() {
        String[] result = parser.parseDeadline("return book   /by  Sunday ");
        assertArrayEquals(new String[]{"return book", "Sunday"}, result);
    }
    @Test
    void parseDeadline_missingBy_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDeadline("return book Sunday"));
    }

    // ---------- parseEvent() ----------
    @Test
    void parseEvent_validInput_splitsAndTrims() {
        String[] result = parser.parseEvent("project meeting /from Mon 2pm /to 4pm");
        assertArrayEquals(new String[]{"project meeting", "Mon 2pm", "4pm"}, result);
    }
    @Test
    void parseEvent_missingFromOrTo_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseEvent("meeting /from Mon 2pm"));
    }
    @Test
    void parseDelete_emptyArgs_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDelete("", 5));
    }
    @Test
    void parseDelete_invalidNumber_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDelete("6", 3));
    }

    // ---------- descDeleteArray() ----------
    @Test
    void descDeleteArray_sortsDescending() {
        int[] result = parser.descDeleteArray(new int[]{2, 0, 5});
        assertArrayEquals(new int[]{5, 2, 0}, result);
    }

    // ---------- trimParts() ----------
    @Test
    void trimParts_removesWhitespace() {
        String[] result = parser.trimParts(new String[]{" hello ", " world "});
        assertArrayEquals(new String[]{"hello", "world"}, result);
    }
}
