/**
 * 
 */
package ar.com.tadp.examples.presentacion.jface.simple;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.uqbar.ui.jface.AbstractSelectionListener;

public abstract class UserAction<T> extends AbstractSelectionListener {
	private final AbstractPanel<T> container;

	public UserAction(AbstractPanel<T> container) {
		this.container = container;

	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		this.execute(this.container.getModel());
	}

	protected abstract void execute(T t);

	// ********************************************************
	// ** Error messages
	// ********************************************************

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
		MessageBox messageBox = new MessageBox(this.container.getShell(), style);
		messageBox.setMessage(message);
		messageBox.setText(this.container.getShell().getText());
		messageBox.open();
	}
}