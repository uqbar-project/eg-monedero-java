package ar.com.tadp.examples.monedero.domain;

import java.util.Date;
import org.junit.Before;
import java.math.BigDecimal;
import ar.com.tadp.examples.monedero.exceptions.UserException;

import junit.framework.Assert;
import org.junit.Test;

//TODO la cuenta tiene comportamiento dependiente del dia, por eso este test usa new Date().
//Por lo tanto el test no es exactamente repetible.
public class CuentaTest {

	private Cuenta cuenta;
	
	@Before
	public void setUp() {
		this.cuenta = new Cuenta();
	}
	
	@Test
	public void testDeposito() {
		BigDecimal monto = new BigDecimal("10");
		Assert.assertEquals(new BigDecimal("0"), this.cuenta.getSaldo());
		depositar(monto);
		Assert.assertEquals(monto, this.cuenta.getSaldo());
		monto = new BigDecimal("15");
		depositar(monto);
		Assert.assertEquals(new BigDecimal("25"), this.cuenta.getSaldo());
	}
	
	private void depositar(BigDecimal monto) {
		new Deposito(new Date(), monto).agregateA(this.cuenta);
	}
	private void extraer(BigDecimal monto) {
		new Extraccion(new Date(), monto).agregateA(this.cuenta);
	}
	
	@Test
	public void testExtracion() {
		BigDecimal monto = new BigDecimal("100");
		depositar(monto);
		monto = new BigDecimal("15");
		extraer(monto);
		Assert.assertEquals(new BigDecimal("85"), this.cuenta.getSaldo());
		extraer(monto);
		Assert.assertEquals(new BigDecimal("70"), this.cuenta.getSaldo());
	}
	
	@Test(expected=UserException.class)
	public void testDepositoNegativo() {
		this.depositar(new BigDecimal("-10"));
	}
	
	@Test(expected=UserException.class)
	public void testExtraccionNegativo() {
		this.extraer(new BigDecimal("-10"));
	}

	@Test(expected=UserException.class)
	public void testCuatroDepositos() {
		BigDecimal monto = new BigDecimal("10");
		try {
			this.depositar(monto);
			this.depositar(monto);
			this.depositar(monto);
		}
		catch (UserException ex) {
			throw new RuntimeException("No puede explotar antes de los tres depositos", ex);
		}
		this.depositar(monto);
	}
	
	@Test(expected=UserException.class)
	public void testExtraccionMayorAMil() {
		BigDecimal monto = new BigDecimal("100000");
		try {
			this.depositar(monto);
		}
		catch (UserException ex) {
			throw new RuntimeException("No puede explotar con el deposito", ex);
		}
		this.extraer(new BigDecimal("1001"));
	}
	
	@Test(expected=UserException.class)
	public void testExtraccionMayorAlSaldo() {
		BigDecimal monto = new BigDecimal("100");
		try {
			this.depositar(monto);
		}
		catch (UserException ex) {
			throw new RuntimeException("No puede explotar con el deposito", ex);
		}
		this.extraer(new BigDecimal("101"));
	}
	
	
}
