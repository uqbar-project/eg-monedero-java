package ar.com.tadp.examples.monedero.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Extraccion extends Movimiento {
	public Extraccion(Date fecha, BigDecimal monto) {
		super(fecha, monto);
	}

	@Override
	protected boolean isDeposito() {
		return false;
	}

	@Override
	protected boolean isExtraccion() {
		return true;
	}

	@Override
	protected void validar(Cuenta cuenta) {
		cuenta.validarLimiteExtraccionDiario(this.getMonto());
		cuenta.validarSaldoDisponibleParaExtraer(this.getMonto());
	}
	
	@Override
	protected BigDecimal calcularValor(Cuenta cuenta) {
		return cuenta.getSaldo().subtract(this.getMonto());
	}

}
