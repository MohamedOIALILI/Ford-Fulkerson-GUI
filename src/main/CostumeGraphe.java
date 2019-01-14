package main;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CostumeGraphe extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int v;
	public int source;
	public int puits;
	public int graphe[][];
	public int nbrArc;
	public int line;
	public int col;
	public int capacite;
	public ArrayList<Integer> sommets = new ArrayList<Integer>();
	private JPanel contentPane;
	private JTextField sommetNbr;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Object[] row;
	private DefaultTableModel model;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public void printError(String msg, Component pane){
		JOptionPane.showMessageDialog(pane, msg, "Error !", JOptionPane.ERROR_MESSAGE, null);
	}

	/**
	 * Create the frame.
	 */
	
	public CostumeGraphe() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CostumeGraphe.class.getResource("/images/1480793055_code-programming-java-software-develop-command-language.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnTest = new JButton("Calculer le flot");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTest.setFocusable(false);
		btnTest.setForeground(new Color(102, 102, 102));
		btnTest.setBorder(new LineBorder(new Color(0, 255, 102)));
		btnTest.setBackground(new Color(0, 255, 102));
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					v = Integer.parseInt(sommetNbr.getText());
					nbrArc = model.getRowCount();
					graphe = new int[v][v];
					
					//initialiser la matrice valuée
					for(int i = 0;i<nbrArc;i++){
						line = (int)model.getValueAt(i, 0);
						col = (int)model.getValueAt(i, 1);
						capacite = (int)model.getValueAt(i, 2);
						graphe[line-1][col-1] = (int)capacite;
						if(!sommets.contains(line))
							sommets.add(line);
						if(!sommets.contains(col))
							sommets.add(col);
					}
					
					source = Integer.parseInt(textField_3.getText());
					puits = Integer.parseInt(textField_4.getText());
					if(!sommets.contains(source) || !sommets.contains(puits)){
						//si la source ou le puits ne sont pas des sommets.
						printError("La source ou le puits ne sont pas des sommets dans ce graphe.", contentPane);
					}else{
						
						MainAlgo mm = new MainAlgo();
						mm.setSommetNumber(v);
						mm.setGraphe(graphe);
						mm.setPuits(puits-1);
						mm.setSource(source-1);
						JOptionPane.showMessageDialog(contentPane, "Le flot maximal pour le graphe donné est: "+mm.fordFulkerson(), "Flot Maximal", JOptionPane.PLAIN_MESSAGE, null);
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
					printError("SVP vérifier les étapes 1,2,3.\nCliquez sur HELP pour plus d'info.",contentPane);
				}
			}
		});
		btnTest.setBounds(268, 465, 176, 38);
		contentPane.add(btnTest);
		
		JLabel lblLeNombreDe = new JLabel("Le nombre de sommet dans le graphe");
		lblLeNombreDe.setForeground(UIManager.getColor("Button.background"));
		lblLeNombreDe.setBounds(12, 29, 307, 15);
		contentPane.add(lblLeNombreDe);
		sommetNbr = new JTextField();
		sommetNbr.setToolTipText("ex. 10");
		sommetNbr.setBounds(324, 27, 114, 19);
		contentPane.add(sommetNbr);
		sommetNbr.setColumns(10);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHelp.setBorder(new LineBorder(Color.BLUE));
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane,"1) Les noms des sommet doivent être des nombres ex. 1,2,3,... .\n"
						+ "2) Définir une source et un puits ."
						+"\n3) Nommer le premier sommet par 1");
			}
		});
		lblHelp.setFont(new Font("Dialog", Font.BOLD, 10));
		lblHelp.setForeground(new Color(51, 153, 255));
		lblHelp.setBounds(2, 521, 70, 27);
		contentPane.add(lblHelp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(UIManager.getColor("CheckBox.foreground"));
		scrollPane.setBounds(12, 73, 426, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.BOLD, 13));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				textField.setText(model.getValueAt(i, 0).toString());
				textField_1.setText(model.getValueAt(i, 1).toString());
				textField_2.setText(model.getValueAt(i, 2).toString());
			}
		});
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(UIManager.getColor("Button.highlight")));
		table.setAutoscrolls(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		Object[] cols = {"Initiale","Finale","Capacite"};
		model = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		model.setColumnIdentifiers(cols);
		table.setModel(model);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		row = new Object[3];
		JButton btnAjouterArc = new JButton("Ajouter un arc");
		btnAjouterArc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int a1 = Integer.parseInt(textField.getText());
					int a2 = Integer.parseInt(textField_1.getText());
					int a3 = Integer.parseInt(textField_2.getText());
					row[0] = a1;
					row[1] = a2;
					row[2] = a3;
					model.addRow(row);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				}catch (Exception e2) {
					printError("Valeur Invalide!\nCliquez sur HELP pour plus d'informations.", contentPane);
				}
			}
		});
		btnAjouterArc.setForeground(new Color(255, 255, 255));
		btnAjouterArc.setBackground(new Color(204, 0, 0));
		btnAjouterArc.setBorder(new LineBorder(new Color(204, 0, 0), 2));
		btnAjouterArc.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAjouterArc.setBounds(303, 311, 131, 25);
		contentPane.add(btnAjouterArc);
		
		textField = new JTextField();
		textField.setBounds(152, 313, 59, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 357, 59, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(152, 401, 59, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblInitiale = new JLabel("Extremite Initiale");
		lblInitiale.setBounds(8, 315, 142, 15);
		lblInitiale.setForeground(Color.WHITE);
		contentPane.add(lblInitiale);
		
		JLabel lblFinale = new JLabel("Extremite Finale");
		lblFinale.setBounds(8, 359, 126, 15);
		lblFinale.setForeground(Color.WHITE);
		contentPane.add(lblFinale);
		
		JLabel lblCapacit = new JLabel("Capacité");
		lblCapacit.setBounds(8, 403, 70, 15);
		lblCapacit.setForeground(Color.WHITE);
		contentPane.add(lblCapacit);
		
		JButton button = new JButton("Supprimer un arc");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0){
					model.removeRow(i);
				}else{
					if(table.getRowCount() <= 0){
						printError("Il n'y a aucun arc à supprimer..", contentPane);
					}else
						printError("Sélectioner un arc depuis la table pour le supprimer.", contentPane);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.BOLD, 10));
		button.setBorder(new LineBorder(new Color(204, 0, 0), 2));
		button.setBackground(new Color(204, 0, 0));
		button.setBounds(303, 399, 131, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Modifier un arc");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()){
					printError("Selectioner un arc pour le modifier.", contentPane);
				}else{
					int i = table.getSelectedRow();
					model.setValueAt(Integer.parseInt(textField.getText().toString()),i, 0);
					model.setValueAt(Integer.parseInt(textField_1.getText().toString()),i, 1);
					model.setValueAt(Integer.parseInt(textField_2.getText().toString()),i, 2);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 10));
		button_1.setBorder(new LineBorder(new Color(204, 0, 0), 2));
		button_1.setBackground(new Color(204, 0, 0));
		button_1.setBounds(303, 355, 131, 25);
		contentPane.add(button_1);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setForeground(Color.WHITE);
		lblSource.setBounds(8, 469, 70, 15);
		contentPane.add(lblSource);
		
		JLabel lblPuits = new JLabel("Puits");
		lblPuits.setForeground(Color.WHITE);
		lblPuits.setBounds(118, 469, 70, 15);
		contentPane.add(lblPuits);
		
		textField_3 = new JTextField();
		textField_3.setBounds(20, 484, 59, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(129, 484, 59, 19);
		contentPane.add(textField_4);
		
		JLabel label = new JLabel("2.");
		label.setForeground(Color.LIGHT_GRAY);
		label.setBounds(8, 286, 70, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("3.");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setBounds(10, 441, 70, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("4.");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setBounds(267, 444, 70, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("1.");
		label_3.setForeground(Color.LIGHT_GRAY);
		label_3.setBounds(9, 12, 70, 15);
		contentPane.add(label_3);
		
		JLabel lblBoufousOialili = new JLabel("Créée par : BOUFOUS & OIALILI");
		lblBoufousOialili.setFont(new Font("Dialog", Font.BOLD, 10));
		lblBoufousOialili.setForeground(Color.YELLOW);
		lblBoufousOialili.setBounds(280, 532, 120, 15);
		lblBoufousOialili.setSize(200, 50);
		contentPane.add(lblBoufousOialili);
		
		JLabel lblLesArcs = new JLabel("Les Arcs");
		lblLesArcs.setForeground(Color.LIGHT_GRAY);
		lblLesArcs.setBounds(29, 286, 70, 15);
		contentPane.add(lblLesArcs);
		
		JOptionPane.showMessageDialog(contentPane,"1) Les noms des sommet doivent être des nombres ex. 1,2,3,... .\n"
				+ "2) Définir une source et un puits ."
				+"\n3) Nommer le premier sommet par 1");
	}
}
