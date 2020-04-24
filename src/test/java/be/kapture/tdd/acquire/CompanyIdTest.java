package be.kapture.tdd.acquire;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static be.kapture.tdd.acquire.CompanyId.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Disabled
public class CompanyIdTest {

    @Test
    public void getPriceForOneShare_ExtraCoefficient0() {
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(200));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(300));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(400));

        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(600));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(600));

        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(700));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(700));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(800));

        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(800));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(900));

        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(900));
        List.of(SAXON, ZETA).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1000));
    }

    @Test
    public void getPriceForOneShare_ExtraCoefficient1() {

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(300));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(400));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(500));

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(700));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(700));

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(800));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(800));

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(900));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(900));

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(1000));
        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(1000));

        List.of(AMERICA, HYDRA, FUSION).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1100));
    }

    @Test
    public void getPriceForOneShare_ExtraCoefficient2() {
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(2)).isEqualTo(400));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(3)).isEqualTo(500));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(4)).isEqualTo(600));

        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(6)).isEqualTo(800));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(10)).isEqualTo(800));

        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(11)).isEqualTo(900));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(20)).isEqualTo(900));

        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(21)).isEqualTo(1000));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(30)).isEqualTo(1000));

        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(31)).isEqualTo(1100));
        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(40)).isEqualTo(1100));

        List.of(QUANTUM, PHOENIX).forEach(c -> assertThat(c.getPriceForOneShare(41)).isEqualTo(1200));
    }


    @Test
    public void getPriceForOneShare_tooLow() {
        Stream.of(CompanyId.values()).forEach(
                companyId -> assertThatIllegalArgumentException().isThrownBy(() -> companyId.getPriceForOneShare(1)));
    }
}