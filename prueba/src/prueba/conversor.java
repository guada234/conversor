package prueba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class conversor {

	private JFrame frame;
	private JComboBox cmb;
	private JButton btn;
	private JLabel lbl;
	private JTextField txt;
	
	public enum Moneda{
		peso_dolar,
		peso_won,
		peso_euro,
		dolar_peso,
		won_peso,
		euro_peso,
	}
	
	public double dolar = 17.09;
	public double won = 0.012;
	public double euro = 18.27;
	
	public double valorInput = 0.00;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conversor window = new conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(0, 0, 169, 38);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 55, 159, 22);
		frame.getContentPane().add(cmb);
		
		btn = new JButton("convertir");
		btn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(54, 88, 89, 38);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(187, 0, 67, 38);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if (Validar(txt.getText())){ 
		Moneda moneda = (Moneda) cmb.getSelectedItem();
		
		switch (moneda) {
		
		case peso_dolar:
			PesoAMoneda(dolar);
			break;
			
		case peso_won:
			PesoAMoneda(won);
			break;
			
		case peso_euro:
			PesoAMoneda(euro);
			break;
			
		case dolar_peso:
			MonedaAPeso(dolar);
			break;
			
		case won_peso:
			MonedaAPeso(won);
			break;
			
		case euro_peso:
			MonedaAPeso(euro);
			break;
			
		default: 
			throw new IllegalArgumentException("Unexpectd value: " + moneda);
		}
	}
	}
	public void PesoAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPeso(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente números ||");
			return false;
		}
	
	}
}

