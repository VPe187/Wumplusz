package wumpus.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.input.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private InputReader inputReader;
    @Mock
    private GameState gameState;
    @Mock
    private GameController underTest;

    @Test
    public void testLoopStartedAndRunning() throws IOException {
        // given
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inputReader = new InputReader(bufferedReader);
        gameState = new GameState(null, null, true);
        underTest = new GameController(gameState, inputReader, null);
        // when
        // then


    }
}