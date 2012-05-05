package ar.com.tadp.examples.monedero.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.tadp.examples.monedero.exceptions.UserException;

public class Cuenta {
	public static final String SALDO = "saldo";

	public static int MAX_DEPOSITOS_EN_DIA = 3;
	public static int MAXIMO_EXTRACCION_DIARIO = 1000;

	private BigDecimal saldo;
	private List<Movimiento> movimientos;

	// ********************************************************
	// ** Constructors & initialization
	// ********************************************************

	public Cuenta() {
		this.saldo = new BigDecimal(0);
		this.inicializar();
	}

	public Cuenta(double montoInicial) {
		this.saldo = new BigDecimal(montoInicial);
		this.inicializar();
	}

	protected void inicializar() {
		this.movimientos = new ArrayList<Movimiento>();
	}

	// ********************************************************
	// ** Actions
	// ********************************************************

	public void poner(BigDecimal cuanto) {
		new Deposito(new Date(), cuanto).agregateA(this);
	}

	public void sacar(BigDecimal cuanto) {
		new Extraccion(new Date(), cuanto).agregateA(this);
	}

	// ********************************************************
	// ** Validations
	// ********************************************************

	protected void validarSaldoDisponibleParaExtraer(BigDecimal cuanto) {
		if (this.getSaldo().subtract(cuanto).doubleValue() < 0) {
			throw new UserException("No puede sacar más de " + this.getSaldo() + " $");
		}
	}

	protected void validarLimiteExtraccionDiario(BigDecimal cuanto) {
		BigDecimal montoExtraidoHoy = this.getMontoExtraidoA(new Date());
		BigDecimal limite = new BigDecimal(MAXIMO_EXTRACCION_DIARIO).subtract(montoExtraidoHoy);
		if (cuanto.doubleValue() > limite.doubleValue()) {
			throw new UserException("No puede extraer más de $ " + MAXIMO_EXTRACCION_DIARIO + " diarios, límite: " + limite);
		}
	}

	protected void validarCantidadDeDepositosDiaria() {
		if (this.getDepositos(new Date()).size() >= MAX_DEPOSITOS_EN_DIA) {
			throw new UserException("Ya excedió los " + MAX_DEPOSITOS_EN_DIA + " depósitos diarios");
		}
	}

	// ********************************************************
	// ** Movimientos
	// ********************************************************

	public void agregarMovimiento(Movimiento movimiento) {
		this.movimientos.add(movimiento);
	}

	public List<Movimiento> getDepositos(Date fecha) {
		List<Movimiento> depositos = new ArrayList<Movimiento>();
		for (Movimiento movimiento : this.getMovimientos()) {
			if (movimiento.fueDepositado(fecha)) {
				depositos.add(movimiento);
			}
		}
		return depositos;
	}

	public List<Movimiento> getExtracciones(Date fecha) {
		List<Movimiento> extracciones = new ArrayList<Movimiento>();
		for (Movimiento movimiento : this.getMovimientos()) {
			if (movimiento.fueExtraido(fecha)) {
				extracciones.add(movimiento);
			}
		}
		return extracciones;
	}

	public BigDecimal getMontoExtraidoA(Date fecha) {
		BigDecimal total = new BigDecimal(0);
		for (Movimiento movimiento : this.getExtracciones(fecha)) {
			total = total.add(movimiento.getMonto());
		}
		return total;
	}

	protected List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
