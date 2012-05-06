package ar.com.tadp.examples.monedero.domain;

import java.math.BigDecimal;

import org.uqbar.commons.model.ObservableObject;

import ar.com.tadp.examples.monedero.exceptions.UserException;

public class Cuenta extends ObservableObject {
	public static final String SALDO = "saldo";

	private BigDecimal saldo;

	// ********************************************************
	// ** Constructors
	// ********************************************************

	public Cuenta() {
		this.saldo = new BigDecimal(0);
	}

	public Cuenta(double montoInicial) {
		this.saldo = new BigDecimal(montoInicial);
	}

	// ********************************************************
	// ** Actions
	// ********************************************************

	public void poner(BigDecimal cuanto) {
		this.validarMonto(cuanto);
		this.sumarMonto(cuanto);
	}

	public void sacar(BigDecimal cuanto) {
		this.validarMonto(cuanto);
		
		if (this.saldo.compareTo(cuanto) < 0) {
			throw new UserException("No puede sacar más de " + this.saldo + " $");
		}
		
		this.sumarMonto(cuanto.negate());
	}

	protected void sumarMonto(BigDecimal cuanto) {
		BigDecimal oldMonto = this.saldo;
		this.saldo = this.saldo.add(cuanto);
		this.firePropertyChange(Cuenta.SALDO, oldMonto, this.saldo);
	}

	// ********************************************************
	// ** Validations
	// ********************************************************
	
	protected void validarMonto(BigDecimal cuanto) {
		if (cuanto.doubleValue() <= 0) {
			throw new UserException(cuanto + ": el monto a ingresar debe ser un valor positivo");
		}
	}

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public BigDecimal getSaldo() {
		return this.saldo;
	}

}
