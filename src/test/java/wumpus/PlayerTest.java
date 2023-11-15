package wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    public void testPlayerNameSetter() {
        // given
        String playerName = "Player";
        Player underTest = new Player();
        underTest.setName(playerName);
        // when
        String result = underTest.getName();
        // then
        Assertions.assertEquals(result, playerName);

    }

}