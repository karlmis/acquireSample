package be.kapture.tdd.acquire;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static be.kapture.tdd.acquire.CompanyId.AMERICA;
import static be.kapture.tdd.acquire.CompanyId.ZETA;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.when;

@Ignore
public class CompanyTest {

    private static final Coordinates COORDINATES_A1 = Coordinates.create(1, 1);
    private static final Coordinates COORDINATES_A2 = Coordinates.create(1, 2);
    private static final Coordinates COORDINATES_A3 = Coordinates.create(1, 3);
    private static final Coordinates COORDINATES_A4 = Coordinates.create(1, 4);
    private static final Coordinates COORDINATES_I12 = Coordinates.create(12, 9);


    @Mock
    private Coordinates coordinatesCentralA0, coordinatesNeighbourA1, coordinatesNeighbourA2, coordinatesFarAway;
    @Mock
    private Coordinates coordinatesCentralB0, coordinatesNeighbourB1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(coordinatesCentralA0.getNeighbours()).thenReturn(asList(coordinatesNeighbourA1, coordinatesNeighbourA2));
        when(coordinatesNeighbourA1.getNeighbours()).thenReturn(singletonList(coordinatesCentralA0));
        when(coordinatesNeighbourA2.getNeighbours()).thenReturn(singletonList(coordinatesCentralA0));

        when(coordinatesCentralB0.getNeighbours()).thenReturn(singletonList(coordinatesNeighbourB1));
        when(coordinatesNeighbourB1.getNeighbours()).thenReturn(singletonList(coordinatesCentralB0));

    }

    @Test
    public void Company_NullSafe() {
        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(null, asList(COORDINATES_A1, COORDINATES_A2)));
        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(AMERICA, null));
    }

    @Test
    public void Company_CoordinatesSize() {
        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(AMERICA, emptyList()));
        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(AMERICA, singletonList(COORDINATES_A1)));
    }

    @Test
    public void coordinates_Neighbours() {
        Company.create(AMERICA, asList(coordinatesCentralA0, coordinatesNeighbourA1, coordinatesNeighbourA2));

        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(AMERICA, asList(coordinatesCentralA0, coordinatesNeighbourA1, coordinatesFarAway)));
        assertThatIllegalArgumentException().isThrownBy(() -> Company.create(AMERICA, asList(coordinatesCentralA0, coordinatesNeighbourA2, coordinatesFarAway)));
    }

    @Test
    public void coordinates_NeighboursDisjoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Company.create(AMERICA, asList(coordinatesCentralA0, coordinatesNeighbourA1, coordinatesNeighbourA2, coordinatesCentralB0, coordinatesNeighbourB1)));

        Company.create(AMERICA, asList(COORDINATES_A1, COORDINATES_A3, COORDINATES_A2));
    }


    @Test
    public void isNeighbour() {
        Company.create(AMERICA, asList(COORDINATES_A1, COORDINATES_A2));
    }

    @Test
    public void getPrice() {
        Company companyAmerica3 = Company.create(AMERICA, asList(COORDINATES_A1, COORDINATES_A2, COORDINATES_A3));
        assertThat(companyAmerica3.getPrice()).isEqualTo(AMERICA.getPriceForOneShare(3));

        Company companyZeta2 = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));
        assertThat(companyZeta2.getPrice()).isEqualTo(ZETA.getPriceForOneShare(2));
    }

    @Test
    public void isNeighbourOf() {
        Company company = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));

        assertThat(company.isNextTo(COORDINATES_A3)).isTrue();
        assertThat(company.isNextTo(COORDINATES_I12)).isFalse();
    }

    @Test
    public void add() {
        Company companyIntialSize2 = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));

        assertThat(companyIntialSize2.getSize()).isEqualTo(2);

        companyIntialSize2.addTile(COORDINATES_A3);
        assertThat(companyIntialSize2.getSize()).isEqualTo(3);

        companyIntialSize2.addTile(COORDINATES_A4);
        assertThat(companyIntialSize2.getSize()).isEqualTo(4);


        assertThatIllegalArgumentException().isThrownBy(() -> companyIntialSize2.addTile(COORDINATES_I12));
    }

    @Test
    public void shares() {
        Company company = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));

        assertThat(company.getRemainingShares()).isEqualTo(25);

        company.removeShares(3);
        assertThat(company.getRemainingShares()).isEqualTo(25 - 3);
        company.removeShares(5);
        assertThat(company.getRemainingShares()).isEqualTo(25 - 3 - 5);

        company.addShares(5);
        assertThat(company.getRemainingShares()).isEqualTo(25 - 3 - 5 + 5);

        company.addShares(2);
        assertThat(company.getRemainingShares()).isEqualTo(25 - 3 - 5 + 5 + 2);
    }

    @Test
    public void shares_AddIllegal() {
        Company company = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));


        assertThatIllegalArgumentException().isThrownBy(() -> company.addShares(2));
        assertThat(company.getRemainingShares()).isEqualTo(25);

        company.removeShares(1);
        assertThatIllegalArgumentException().isThrownBy(() -> company.addShares(2));
    }

    @Test
    public void shares_RemoveIllegal() {
        Company company = Company.create(ZETA, asList(COORDINATES_A1, COORDINATES_A2));

        company.removeShares(25);
        assertThatIllegalArgumentException().isThrownBy(() -> company.removeShares(1));
        assertThat(company.getRemainingShares()).isEqualTo(0);

        company.addShares(1);
        assertThatIllegalArgumentException().isThrownBy(() -> company.removeShares(2));
        assertThat(company.getRemainingShares()).isEqualTo(0);
    }

}