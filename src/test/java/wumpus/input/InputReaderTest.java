package wumpus.input;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputReaderTest {
    private static final String INPUT = "userinput";
    @Mock
    private BufferedReader bufferedReader;
    private InputReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputReader(bufferedReader);
    }

    @Test
    public void testReadInputShouldReturnTheInput() throws IOException {
        // given
        given(bufferedReader.readLine()).willReturn(INPUT);
        // when
        String result = underTest.readInput();
        // then
        assertEquals(INPUT, result);
    }
}