package ar.edu.design.examples.monedero.ui.jface.simple;


import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.uqbar.ui.jface.AbstractSelectionListener;

public abstract class AbstractPanel<T> extends Composite {
	private T model;

	public AbstractPanel(Composite parent, T model) {
		super(parent, SWT.NONE);
		this.model = model;
		this.createMainTemplate();
		this.pack();
	}

	protected T getModel() {
		return this.model;
	}

	// ********************************************************
	// ** Abstract methods
	// ********************************************************

	protected abstract void agregarBotones(Composite botones);

	protected abstract void armarFormulario(Composite campos);

	// ********************************************************
	// ** Template de la pantalla
	// ********************************************************

	protected void createMainTemplate() {
		this.setLayout(new RowLayout(SWT.VERTICAL));
		this.createFormulario();
		this.createAcciones();
	}

	protected void createFormulario() {
		Composite campos = new Composite(this, SWT.NONE);
		campos.setLayout(new GridLayout(2, false));
		this.armarFormulario(campos);
	}

	protected void createAcciones() {
		Composite botonesStd = new Composite(this, SWT.NONE);
		botonesStd.setLayout(new RowLayout(SWT.HORIZONTAL));

		this.agregarBotones(botonesStd);
	}

	// ********************************************************
	// ** Utilities
	// ********************************************************

	protected void addBoton(Composite parent, String nombre, AbstractSelectionListener listener) {
		Button botonAceptar = new Button(parent, SWT.PUSH);
		botonAceptar.setText(nombre);
		botonAceptar.addSelectionListener(listener);
		this.getShell().setDefaultButton(botonAceptar);
	}

	protected Text addText(Composite campos, String labelText, String propertyName) {
		return this.addText(this.model, campos, labelText, propertyName);
	}

	protected Text addText(Object object, Composite campos, String labelText, String propertyName) {
		Label label = new Label(campos, SWT.LEFT);
		label.setText(labelText);

		Text text = new Text(campos, SWT.LEFT | SWT.BORDER | SWT.SINGLE);
		text.setLayoutData(new GridData(150, SWT.DEFAULT));

		this.bind(object, propertyName, text);
		return text;
	}

	protected void bind(Object object, String propertyName, Control text) {
		new DataBindingContext().bindValue(SWTObservables.observeText(text, SWT.FocusOut), BeansObservables.observeValue(object, propertyName), null, null);
	}
	
	protected void mostrarMensaje(String message) {
		this.mostrarMessageBox(message, SWT.OK | SWT.ICON_INFORMATION);
	}

	protected void mostrarAdvertencia(String message) {
		this.mostrarMessageBox(message, SWT.OK | SWT.ICON_WARNING);
	}

	protected void mostrarError(String message) {
		this.mostrarMessageBox(message, SWT.OK | SWT.ICON_ERROR);
	}

	private void mostrarMessageBox(String message, int style) {
		MessageBox messageBox = new MessageBox(this.getShell(), style);
		messageBox.setMessage(message);
		messageBox.setText(this.getShell().getText());
		messageBox.open();
	}

}