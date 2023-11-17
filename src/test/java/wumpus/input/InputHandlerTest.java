package wumpus.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.command.Command;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link InputHandler}.
 */
@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    private static final String USER_INPUT = "testqwer";

    @Mock
    private Command command1;
    @Mock
    private Command command2;

    private InputHandler underTest;

    @BeforeEach
    public void setup() {
        underTest = new InputHandler(List.of(command1, command2));
    }

    @Test
    public void testInputUseCommandUnknownWhenCommandNotExists() {
        // given
        given(command1.validateCommand(USER_INPUT)).willReturn(false);
        // when
        underTest.handleInput(USER_INPUT);
        // then
        verify(command1).validateCommand(USER_INPUT);
        verifyNoMoreInteractions(command1);
        verify(command2).validateCommand(USER_INPUT);
        verifyNoMoreInteractions(command2);
    }

    @Test
    public void testInputHandleTheFirstCommand() {
        //given
        given(command1.validateCommand(USER_INPUT)).willReturn(true);
        // when
        underTest.handleInput(USER_INPUT);
        // then
        verify(command1).validateCommand(USER_INPUT);
        verify(command1).process(USER_INPUT);
        verifyNoInteractions(command2);
    }
}