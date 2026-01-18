package pocitadlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Počítadlo prejdených otázok. Pôvodne vytvorené počas prokrastinovania na skúšku z Úvodu do počítačových sítí.
 * <p>
 * Coy Ondřej Jánoš, január 2026.
 */

public class Pocitadlo extends Window implements ActionListener {
	
	/**
	 * 
	 */
	private String pathName = "D:\\telefon\\coding\\pocitadlo\\Pocitadlo\\files\\count.txt";
	private int pocetOtazok = 162;
	
	private static final long serialVersionUID = 703823023777029896L;
	private int pocetPrejdenych;
	private JTextField pocetPrejdenychOtazok;
	private JButton plusJedna;
	private JLabel percenta;
	private JLabel status;

	public Pocitadlo() throws IOException {
		// TODO Auto-generated constructor stub
		super();
		
		pocetPrejdenych = 0;
		
		try {
			BufferedReader vstup = new BufferedReader(new FileReader(pathName));
			pocetPrejdenych = Integer.parseInt(vstup.readLine());
			vstup.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Font pocetOtazokFont = new Font("Bahnschrift", Font.PLAIN, super.getHeight() / 12);
		Font percentaFont = new Font("Bahnschrift", Font.PLAIN, super.getHeight() / 6);
		
		JLabel pocetLabel = new JLabel("Počet prejdených otázok: ");
		pocetLabel.setFont(pocetOtazokFont);
		int pocetLabelHeight = pocetLabel.getPreferredSize().height;
		
		pocetPrejdenychOtazok = new JTextField(Integer.toString(pocetPrejdenych));
		pocetPrejdenychOtazok.setBackground(Color.decode("#EEEEEE"));
		pocetPrejdenychOtazok.addActionListener(this);
		pocetPrejdenychOtazok.setFont(pocetOtazokFont);
		pocetPrejdenychOtazok.setPreferredSize(new Dimension((int) (pocetLabel.getPreferredSize().width * 0.2), pocetLabelHeight));
		
		percenta = new JLabel(String.format("%.2f%%", (double) pocetPrejdenych / pocetOtazok * 100.0));
		percenta.setFont(percentaFont);
		
		plusJedna = new JButton("+1");
		plusJedna.setFont(pocetOtazokFont);
		plusJedna.setPreferredSize(new Dimension((int) (pocetLabel.getPreferredSize().width * 1.2) + 10, pocetLabelHeight));
		plusJedna.setBackground(Color.decode("#EEEEEE"));
		plusJedna.addActionListener(this);
		
		status = new JLabel(Integer.toString(pocetPrejdenych) + "/" + Integer.toString(pocetOtazok));
		status.setFont(pocetOtazokFont);
		
		super.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(5, 5, 5, 5);
		g.gridx = 0;
		g.gridy = 0;
		
		add(pocetLabel, g);
		g.gridx++;
		add(pocetPrejdenychOtazok, g);
		g.gridx = 0;
		g.gridy++;
		g.gridwidth = 2;
		add(plusJedna, g);
		g.gridy++;
		g.insets = new Insets(pocetLabelHeight, 0, 0, 0);
		add(percenta, g);
		g.gridy++;
		g.insets = new Insets(0, 0, 0, 0);
		add(status, g);
		
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pocetPrejdenych = Integer.parseInt(pocetPrejdenychOtazok.getText());
		
		if (e.getSource() == plusJedna && pocetPrejdenych < pocetOtazok) {
			pocetPrejdenych++;
		}
		
		if (pocetPrejdenych > pocetOtazok) {
			pocetPrejdenych = pocetOtazok;
		}
		
		pocetPrejdenychOtazok.setText(Integer.toString(pocetPrejdenych));
		percenta.setText(String.format("%.2f%%", (double) pocetPrejdenych / pocetOtazok * 100.0));
		status.setText(Integer.toString(pocetPrejdenych) + "/" + Integer.toString(pocetOtazok));
		
		try {
			BufferedWriter vystup = new BufferedWriter(new FileWriter(pathName));
			vystup.write(Integer.toString(pocetPrejdenych));
			vystup.close();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
}
