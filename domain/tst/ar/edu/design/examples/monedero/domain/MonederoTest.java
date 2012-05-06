package ar.edu.design.examples.monedero.domain;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ar.edu.design.examples.monedero.domain.Monedero;
import ar.edu.design.examples.monedero.exceptions.BusinessException;

public class MonederoTest {

	private Monedero monedero;

	@Before
	public void setUp() throws Exception {
		monedero = new Monedero(10);
	}

	@Test(expected = BusinessException.class)
	public void sacarMontoMasGrandeQueMonedero() {
		monedero.sacar(new BigDecimal(20));
	}

}
