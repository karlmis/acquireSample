package be.kapture.tdd.acquire;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.kapture.tdd.acquire.Coordinates.MAX_X;
import static be.kapture.tdd.acquire.Coordinates.MAX_Y;
import static org.assertj.core.api.Assertions.*;
@Disabled
public class CoordinatesTest {

    @Test
    public void coordinates_NoNegativeNumbers() {
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(0, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(1, 0));
    }

    @Test
    public void coordinates_NumbersNotTooBig() {
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(MAX_X + 1, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(MAX_X + 2, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(1, MAX_Y + 1));
        assertThatIllegalArgumentException().isThrownBy(() -> Coordinates.create(1, MAX_Y + 2));
    }

    @Test
    public void coordinates() {
        assertThat(Coordinates.create(1, 1).toString()).isEqualTo("1A");
        assertThat(Coordinates.create(12, 9).toString()).isEqualTo("12I");
    }

    @Test
    public void getNeighbours() {
        assertThat(Coordinates.create(5, 5).getNeighbours()).containsExactly(
                Coordinates.create(5 - 1, 5),
                Coordinates.create(5 + 1, 5),
                Coordinates.create(5, 5 - 1),
                Coordinates.create(5, 5 + 1)
        );

        assertThat(Coordinates.create(1, 1).getNeighbours()).containsExactly(
                Coordinates.create(1 + 1, 1),
                Coordinates.create(1, 1 + 1)
        );

        assertThat(Coordinates.create(12, 9).getNeighbours()).containsExactly(
                Coordinates.create(12 - 1, 9),
                Coordinates.create(12, 9 - 1)
        );
    }

    @Test
    public void getNeighbours_Immutable() {
        List<Coordinates> neighbours = Coordinates.create(5, 5).getNeighbours();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> neighbours.add(Coordinates.create(1,1)));
    }
}