package be.kapture.tdd.acquire;

import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static be.kapture.tdd.acquire.CompanyId.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Ignore
public class CompanyIdTest {

    @Test
    public void getPriceForOneShare_ExtraCoefficient0() {
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(200));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(300));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(400));

        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(600));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(600));

        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(700));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(700));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(800));

        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(800));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(900));

        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(900));
        asList(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1000));
    }

    @Test
    public void getPriceForOneShare_ExtraCoefficient1() {

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(300));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(400));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(500));

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(700));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(700));

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(800));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(800));

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(900));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(900));

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(1000));
        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(1000));

        asList(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1100));
    }

    @Test
    public void getPriceForOneShare_ExtraCoefficient2() {
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(400));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(500));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(600));

        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(800));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(800));

        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(900));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(900));

        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(1000));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(1000));

        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(1100));
        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(1100));

        asList(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1200));
    }


    @Test
    public void getPriceForOneShare_tooLow() {
        Stream.of(CompanyId.values()).forEach(
                companyId -> assertThatIllegalArgumentException().isThrownBy(() -> companyId.getPriceForOneShare(1)));
    }
}