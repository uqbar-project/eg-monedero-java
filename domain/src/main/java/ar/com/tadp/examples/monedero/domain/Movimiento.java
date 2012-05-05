package ar.com.tadp.examples.monedero.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import ar.com.tadp.examples.monedero.exceptions.UserException;

public abstract class Movimiento {
	private Date fecha;
	private BigDecimal monto;

	public BigDecimal getMonto() {
		return this.monto;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public Movimiento(Date fecha, BigDecimal monto) {
		this.fecha = fecha;
		this.monto = monto;
		this.validarMonto();
	}

	public boolean fueDepositado(Date fecha) {
		return this.isDeposito() && this.esDeLaFecha(fecha);
	}

	public boolean fueExtraido(Date fecha) {
		return this.isExtraccion() && this.esDeLaFecha(fecha);
	}

	/**
	 * Uso un date format para elminar los segundos... no es muy feliz pero no se me ocurre nada mejor.
	 */
	protected boolean esDeLaFecha(Date fecha) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		return dateFormat.format(this.fecha).equals(dateFormat.format(fecha));
	}

	protected abstract boolean isDeposito();

	protected abstract boolean isExtraccion();

	// ********************************************************
	// ** Validaciones
	// ********************************************************

	protected void validarMonto() {
		if (this.getMonto().doubleValue() <= 0) {
			throw new UserException(this.getMonto() + ": el monto a ingresar debe ser un valor positivo");
		}
	}

	protected abstract void validar(Cuenta cuenta);

	public void agregateA(Cuenta cuenta) {
		this.validar(cuenta);
		cuenta.setSaldo(this.calcularValor(cuenta));
		cuenta.agregarMovimiento(this);
	}

	protected abstract BigDecimal calcularValor(Cuenta cuenta);
}
