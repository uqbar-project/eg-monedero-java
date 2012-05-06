package ar.edu.design.examples.monedero.modelo;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.uqbar.commons.model.ObservableObject;

import ar.edu.design.examples.monedero.domain.Monedero;
import ar.edu.design.examples.monedero.exceptions.BusinessException;

public class MonederoModel extends ObservableObject {

	public static final String MONTO_A_INGRESAR = "montoAIngresar";

	private Monedero monedero;
	private String montoAIngresar;

	public MonederoModel() {
		this.monedero = new Monedero(15.0);
		this.inicializar();
	}

	public String getMontoAIngresar() {
		return this.montoAIngresar;
	}

	public void setMontoAIngresar(String montoAIngresar) {
		String oldMontoAIngresar = this.montoAIngresar;
		this.montoAIngresar = montoAIngresar;
		this.firePropertyChange(MONTO_A_INGRESAR, oldMontoAIngresar, this.montoAIngresar);
	}

	public String getMonto() {
		return NumberFormat.getInstance().format(this.monedero.getMonto());
	}

	public void sacar() throws BusinessException {
		try {
			this.monedero.sacar(new BigDecimal(this.montoAIngresar));
			this.inicializar();
		} catch (NumberFormatException e) {
			throw new BusinessException("Debe ingresar un monto v�lido");
		}
	}

	public void poner() throws BusinessException {
		try {
			this.monedero.poner(new BigDecimal(this.montoAIngresar));
			this.inicializar();
		} catch (NumberFormatException e) {
			throw new BusinessException("Debe ingresar un monto v�lido");
		}
	}

	private void inicializar() {
		this.setMontoAIngresar("");
	}

	public Monedero getMonedero() {
		return this.monedero;
	}

}