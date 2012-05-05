package ar.com.tadp.examples.presentacion.jface.simple;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.uqbar.ui.jface.SimpleApplication;

public class MonederoApplication extends SimpleApplication {
	public static void main(String[] args) {
		new MonederoApplication().run();
	}

	@Override
	protected Control createContents(Composite parent) {
		MonederoPanel monederoPanel = new MonederoPanel(parent);
		Shell shell = monederoPanel.getShell();
		shell.setText("Monedero Step 2");
		return monederoPanel;
	}
}
