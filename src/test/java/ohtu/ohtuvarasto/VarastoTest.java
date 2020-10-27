package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoaLiikaa() {
        varasto.lisaaVarastoon(12);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaa() {
        varasto.lisaaVarastoon(4);

        double saatuMaara = varasto.otaVarastosta(6);

        assertEquals(4, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaaKatsotaanSaldo() {
        varasto.lisaaVarastoon(4);
        varasto.otaVarastosta(6);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaaPaljonkoMahtuu() {
        varasto.lisaaVarastoon(4);
        varasto.otaVarastosta(6);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otetaanLiianVähän() {
        varasto.lisaaVarastoon(4);

        assertEquals(0, varasto.otaVarastosta(-6), vertailuTarkkuus);
    }


    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisäysVarastoonHuonosti() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kirjoitetaan() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals("saldo = 6.0, vielä tilaa 4.0", varasto.toString());
    }

    @Test
    public void kokeillaanUusiVarasto() {
        varasto = new Varasto(10, 8);

        assertEquals(8.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kokeillaanUusiKelvotonVarasto() {
        varasto = new Varasto(0, 12);

        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kokeillaanUusiMiinusSaldoinenVarasto() {
        varasto = new Varasto(10, -12);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kokeillaanUusiVarastoJossaVaintilavuus() {
        varasto = new Varasto(-10);

        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
}