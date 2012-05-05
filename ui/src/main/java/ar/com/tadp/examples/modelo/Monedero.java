package ar.com.tadp.examples.modelo;

import java.math.BigDecimal;

import org.uqbar.commons.model.Observable;

import ar.com.tadp.examples.monedero.domain.Cuenta;
import ar.com.tadp.examples.monedero.exceptions.UserException;

public class Monedero extends Observable {
	public static final String MONTO_A_INGRESAR = "montoAIngresar";

	private Cuenta monedero;
	private String montoAIngresar;

	// ********************************************************
	// ** Constructor & initialization
	// ********************************************************

	public Monedero() {
		this.monedero = new Cuenta(15.0);
		this.limpiar();
	}

	protected void limpiar() {
		this.setMontoAIngresar("");
	}

	// ********************************************************
	// ** Actions
	// ********************************************************

	public void sacar() {
		this.monedero.sacar(this.getMontoAsNumber());
		this.limpiar();
	}

	public void poner() {
		this.monedero.poner(this.getMontoAsNumber());
		this.limpiar();
	}

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public Cuenta getMonedero() {
		return this.monedero;
	}

	public String getMontoAIngresar() {
		return this.montoAIngresar;
	}

	public void setMontoAIngresar(String montoAIngresar) {
		String oldMontoAIngresar = this.montoAIngresar;
		this.montoAIngresar = montoAIngresar;
		this.firePropertyChange(MONTO_A_INGRESAR, oldMontoAIngresar, this.montoAIngresar);
	}

	// ********************************************************
	// ** Internal
	// ********************************************************

	protected BigDecimal getMontoAsNumber() {
		try {
			return new BigDecimal(this.montoAIngresar);
		}
		catch (NumberFormatException exception) {
			throw new UserException("Debe ingresar un monto válido");
		}
	}
}