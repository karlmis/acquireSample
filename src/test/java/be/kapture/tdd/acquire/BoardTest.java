package be.kapture.tdd.acquire;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Ignore
public class BoardTest {

    private static final Coordinates COORDINATES2 = Coordinates.create(2, 2);
    private static final Coordinates COORDINATES3 = Coordinates.create(3, 3);

    @Test
    public void addTile() {
        Board board = new Board();
        board.addTile(Coordinates.create(1, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> board.addTile(Coordinates.create(1, 1)));

        board.addTile(COORDINATES2);
    }

    @Test
    public void getOccupiedNeighbours() {
        Board board = new Board();
        assertThat(board.getOccupiedNeighbours(Coordinates.create(2, 2))).isEmpty();

        List<Coordinates> neighbours = asList(
                Coordinates.create(2 - 1, 2),
                Coordinates.create(2 + 1, 2),
                Coordinates.create(2, 2 - 1));

        neighbours.forEach(board::addTile);
        assertThat(board.getOccupiedNeighbours(Coordinates.create(2, 2))).isEqualTo(neighbours);
    }
}