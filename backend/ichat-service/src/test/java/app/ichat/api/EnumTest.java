package app.ichat.api;

import app.ichat.api.chat.ChatResponse;
import app.ichat.service.turing.TuringChatResponse;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Steve Zou
 */
public class EnumTest {
    @Test
    public void testChatItemType() {
        Assertions.assertTrue(Arrays.stream(TuringChatResponse.ItemType.values()).allMatch(t -> Arrays.stream(ChatResponse.ItemType.values()).anyMatch(t1 -> Objects.equals(t1.value, t.value))));
        Assertions.assertTrue(Arrays.stream(ChatResponse.ItemType.values()).allMatch(t -> Arrays.stream(TuringChatResponse.ItemType.values()).anyMatch(t1 -> Objects.equals(t1.value, t.value))));
    }
}
