package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import logic.Viagem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Simulador extends JFrame {

	private JPanel contentPane;
	private JFrame principal;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador frame = new Simulador();
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
	public Simulador() {
		setResizable(false);
		setTitle("Simulador Efeitos Relativisticos\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setVisible(false);
				Start st = new Start();
				st.setVisible(true);
			}
		});
		btnVoltar.setBounds(0, 0, 84, 25);
		desktopPane.add(btnVoltar);
		
		final JSlider slider = new JSlider();
		slider.setMaximum(99);
		slider.setMinimum(1);
		
		slider.setToolTipText("selecione a fração de c");
		slider.setValue(1);
		slider.setBounds(12, 533, 764, 16);
		desktopPane.add(slider);
		
		JLabel lblSelecineAVelocidade = new JLabel("Selecione a velocidade:");
		lblSelecineAVelocidade.setBounds(14, 513, 172, 15);
		desktopPane.add(lblSelecineAVelocidade);
		
		JList list = new JList();
		list.setBounds(364, 191, -86, -41);
		desktopPane.add(list);
		
		final JLabel lblNewLabel_1 = new JLabel("Planeta Destino");
		//lblNewLabel_1.setIcon(new ImageIcon(Simulador.class.getResource("/figuras/europa.png")));
		lblNewLabel_1.setBounds(543, 95, 200, 200);
		desktopPane.add(lblNewLabel_1);
		
		final JList list_1 = new JList();
		list_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizaDados(table,slider.getValue(),list_1.getSelectedIndex(),lblNewLabel_1);
			}
		});
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				atualizaDados(table,slider.getValue(),list_1.getSelectedIndex(),lblNewLabel_1);
			}
		});
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				atualizaDados(table,slider.getValue(),list_1.getSelectedIndex(),lblNewLabel_1);
			}
		});
		list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list_1.setBackground(Color.LIGHT_GRAY);
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Mercurio", "Vênus", "Lua", "Marte", "Júpiter", "Saturno", "Urano", "Netuno"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(272, 92, 114, 136);
		desktopPane.add(list_1);
		
		JLabel lblEscolhaOPlaneta = new JLabel("Escolha o seu destino:");
		lblEscolhaOPlaneta.setBounds(246, 65, 172, 15);
		desktopPane.add(lblEscolhaOPlaneta);
		
		JLabel lblVocNoPode = new JLabel("Você não pode ultrapassar a velocidade da luz!");
		lblVocNoPode.setForeground(Color.RED);
		lblVocNoPode.setBounds(198, 513, 337, 15);
		desktopPane.add(lblVocNoPode);
		
		JButton btnRelembreOPorqu = new JButton("saiba o porquê");
		btnRelembreOPorqu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//setVisible(false);
				FatorDeLorentz fator = new FatorDeLorentz();
				fator.setVisible(true);
			}
		});
		btnRelembreOPorqu.setBounds(543, 508, 146, 25);
		desktopPane.add(btnRelembreOPorqu);
		
		JLabel lblOrigemTerra = new JLabel("Origem: Terra");
		lblOrigemTerra.setBounds(14, 65, 107, 15);
		desktopPane.add(lblOrigemTerra);
		
		JLabel lblDadosDaViagem = new JLabel("Dados da viagem:");
		lblDadosDaViagem.setBounds(399, 301, 134, 15);
		desktopPane.add(lblDadosDaViagem);
		
		JLabel lblDadosDoVeculo = new JLabel("Dados do Veículo:");
		lblDadosDoVeculo.setBounds(177, 379, 138, 15);
		desktopPane.add(lblDadosDoVeculo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(399, 328, 377, 136);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Velocidade", null, null},
				{"Distancia", null, null},
				{"Tempo na Nave", null, null},
				{"Tempo na Terra", null, null},
				{"Tempo no Destino", null, null},
				{"Comprimento Contraido", null, null},
				{"Massa Relativistica", null, null},
				{"Energia relativistica", null, null},
				{"Energia gasta", null, null},
			},
			new String[] {
				"Campo", "Valor", "Unidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(177, 406, 209, 58);
		desktopPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Massa", "8.6", "10³ kg"},
				{"Comprimento", "3.3", "m"},
			},
			new String[] {
				"Campo", "Valor", "Unidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Simulador.class.getResource("/figuras/terra.png")));
		lblNewLabel.setBounds(14, 95, 200, 203);
		desktopPane.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Simulador.class.getResource("/figuras/orion.png")));
		lblNewLabel_2.setBounds(14, 361, 151, 103);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblOrion = new JLabel("Orion");
		lblOrion.setBounds(14, 334, 134, 15);
		desktopPane.add(lblOrion);
		
		
	}

	//fachada
	public void atualizaDados(JTable table, int velocidade, int indiceCorpo, JLabel icon){

		ArrayList<String> dados = Viagem.getDadosViagem(indiceCorpo, (double)velocidade/100);
		icon.setIcon(new ImageIcon(Simulador.class.getResource(dados.get(0))));
		
		table.setModel(new DefaultTableModel(
		new Object[][] {
			{"Velocidade", dados.get(1), "m/s"},
			{"Distancia", dados.get(2), "10⁹ m"},
			{"Tempo na Nave", dados.get(3), "Dia"},
			{"Tempo na Terra", dados.get(4), "Dia"},
			{"Tempo no Destino", dados.get(5), "Dia"},
			{"Comprimento Contraido", dados.get(6).substring(0, 4), "m"},
			{"Massa Relativistica", dados.get(7).substring(0, 5), "10³ kg"},
			{"Energia relativistica", dados.get(8), "J/s"},
			{"Energia gasta", dados.get(9), "J"},
		},
		new String[] {
			"Campo", "Valor", "Unidade"
		}
	) {
		Class[] columnTypes = new Class[] {
			String.class, String.class, String.class
		};
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
		boolean[] columnEditables = new boolean[] {
			false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
		
	}
}
