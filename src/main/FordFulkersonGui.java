package main;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class FordFulkersonGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public MainAlgo m ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FordFulkersonGui frame = new FordFulkersonGui();
					frame.setTitle("Ford Fulkerson : Flot maximal");
					Double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth()-1000;
					Double height=Toolkit.getDefaultToolkit().getScreenSize().getHeight()-500;
					frame.setSize(width.intValue(),height.intValue());
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FordFulkersonGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FordFulkersonGui.class.getResource("/images/1480793055_code-programming-java-software-develop-command-language.png")));
		//initialiser le graphe pour un exemple
		this.m = new MainAlgo();
		int[][] GrapheUtilisateur = new int[][] { 
			{0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
          };
      
		m.setSommetNumber(6);
		m.setGraphe(GrapheUtilisateur);
		m.setSource(0);
		m.setPuits(5);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FordFulkersonGui.class.getResource("/images/image.png")));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(12, 30, 400, 220);
		contentPane.add(lblNewLabel);
		
		JButton btnCalculerLeFlot = new JButton("Flot maximal");
		btnCalculerLeFlot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(contentPane, "Le flot maximal de "+m.source+" vers "+m.puits+" est: " + m.fordFulkerson());
			}
		});
		btnCalculerLeFlot.setIcon(null);
		btnCalculerLeFlot.setFont(new Font("Noto Sans CJK JP Light", Font.BOLD, 12));
		btnCalculerLeFlot.setMargin(new Insets(2, 30, 2, 14));
		btnCalculerLeFlot.setFocusable(false);
		btnCalculerLeFlot.setForeground(Color.WHITE);
		btnCalculerLeFlot.setBackground(new Color(255, 0, 51));
		btnCalculerLeFlot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCalculerLeFlot.setBorder(new LineBorder(new Color(204, 204, 204)));
		btnCalculerLeFlot.setBounds(100, 250, 221, 37);
		contentPane.add(btnCalculerLeFlot);
		
		JButton btnNouveauGraphe = new JButton("Nouveau graphe");
		btnNouveauGraphe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CostumeGraphe f2 = new CostumeGraphe();
							Double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth()-1000;
							Double height=Toolkit.getDefaultToolkit().getScreenSize().getHeight()-200;
							f2.setSize(width.intValue(),height.intValue());
							f2.setVisible(true);
							f2.setLocationRelativeTo(contentPane);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnNouveauGraphe.setIconTextGap(1);
		btnNouveauGraphe.setMargin(new Insets(2, 50, 2, 50));
		btnNouveauGraphe.setIcon(null);
		btnNouveauGraphe.setBorder(new LineBorder(new Color(153, 153, 153)));
		btnNouveauGraphe.setForeground(new Color(255, 255, 255));
		btnNouveauGraphe.setFocusable(false);
		btnNouveauGraphe.setBackground(new Color(255, 0, 51));
		btnNouveauGraphe.setBounds(100, 299, 221, 37);
		btnNouveauGraphe.setFocusable(false);
		btnNouveauGraphe.setForeground(Color.white);
		btnNouveauGraphe.setBackground(new Color(255, 0, 51));
		btnNouveauGraphe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouveauGraphe.setBorder(new LineBorder(new Color(204, 204, 204)));
		contentPane.add(btnNouveauGraphe);
		
		JLabel lblBoufousOialili = new JLabel("Créée par : BOUFOUS & OIALILI");
		lblBoufousOialili.setFont(new Font("Dialog", Font.PLAIN, 9));
		lblBoufousOialili.setForeground(Color.WHITE);
		lblBoufousOialili.setBounds(2, 365, 142, 15);
		contentPane.add(lblBoufousOialili);
	}
}
