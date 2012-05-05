package ar.com.tadp.examples.monedero.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Deposito extends Movimiento {

	public Deposito(Date fecha, BigDecimal monto) {
		super(fecha, monto);
	}

	@Override
	protected boolean isDeposito() {
		return true;
	}

	@Override
	protected boolean isExtraccion() {
		return false;
	}

	@Override
	protected void validar(Cuenta cuenta) {
		cuenta.validarCantidadDeDepositosDiaria();
	}

	@Override
	protected BigDecimal calcularValor(Cuenta cuenta) {
		return cuenta.getSaldo().add(this.getMonto());
	}

	

}
