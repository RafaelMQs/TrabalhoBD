import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class tabela extends JPanel {
	private JScrollPane scrollTabela;
	private JTable tabela;
	private conexao conexao;
	private PreparedStatement st;
	private ResultSet rs;
	private JLabel lbDataJogo, lbNomeJogo, lbTipoJogo, lbQuantJogo, lbIdJogo;
	private JTextField tfDataJogo, tfNomeJogo, tfTipoJogo, tfQuantJogo, tfIdJogo;
	private JButton btnAvancar, btnRetroceder, btnPrimeiro, btnUltimo, btnMaior, btnMenor;

	private JPanel textFields;

	private String selectSql = "SELECT * FROM joguinhos", pegarMaxSql;

	public tabela() {
		Componentes();
		Eventos();

	}

	public void Componentes() {
		setLayout(null);
		setFont(new Font("Arial", Font.PLAIN, 12));
		scrollTabela = new JScrollPane();
		scrollTabela.setBounds(15, 175, 515, 183);
		add(scrollTabela);

		textFields = new JPanel();
		textFields.setLayout(null);
		textFields.setBounds(15, 5, 510, 100);
		textFields.setBorder(BorderFactory.createTitledBorder(null, "Dados da Tabela", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("Arial", Font.BOLD, 12), Color.black));
		add(textFields);

		// Criando o Label e o Tf do DateGame
		lbDataJogo = new JLabel("DateGame:");
		lbDataJogo.setBounds(5, 25, 65, 25);
		lbDataJogo.setForeground(Color.BLACK);
		textFields.add(lbDataJogo);
		tfDataJogo = new JTextField();
		tfDataJogo.setBounds(75, 25, 125, 25);
		tfDataJogo.setEditable(false);
		tfDataJogo.setBackground(Color.WHITE);
		tfDataJogo.setForeground(Color.BLACK);
		textFields.add(tfDataJogo);

		// Criando o Label e o Tf do NomeJogo
		lbNomeJogo = new JLabel("NameGame:");
		lbNomeJogo.setBounds(5, 65, 70, 25);
		lbNomeJogo.setForeground(Color.BLACK);
		textFields.add(lbNomeJogo);
		tfNomeJogo = new JTextField();
		tfNomeJogo.setBounds(80, 65, 120, 25);
		tfNomeJogo.setEditable(false);
		tfNomeJogo.setBackground(Color.WHITE);
		tfNomeJogo.setForeground(Color.BLACK);
		textFields.add(tfNomeJogo);

		// Criando o Label e o Tf do TipoJogo
		lbTipoJogo = new JLabel("TypeGame:");
		lbTipoJogo.setBounds(210, 25, 70, 25);
		lbTipoJogo.setForeground(Color.BLACK);
		textFields.add(lbTipoJogo);
		tfTipoJogo = new JTextField();
		tfTipoJogo.setBounds(285, 25, 120, 25);
		tfTipoJogo.setEditable(false);
		tfTipoJogo.setBackground(Color.WHITE);
		tfTipoJogo.setForeground(Color.BLACK);
		textFields.add(tfTipoJogo);

		// Criando o Label e o Tf do QuantJogo
		lbQuantJogo = new JLabel("QuantGame:");
		lbQuantJogo.setBounds(210, 65, 70, 25);
		lbQuantJogo.setForeground(Color.BLACK);
		textFields.add(lbQuantJogo);
		tfQuantJogo = new JTextField();
		tfQuantJogo.setBounds(285, 65, 120, 25);
		tfQuantJogo.setEditable(false);
		tfQuantJogo.setBackground(Color.WHITE);
		tfQuantJogo.setForeground(Color.BLACK);
		textFields.add(tfQuantJogo);

		// Criando o Label e o Tf do IdJogo
		lbIdJogo = new JLabel("IdGame:");
		lbIdJogo.setBounds(410, 45, 50, 25);
		lbIdJogo.setForeground(Color.BLACK);
		textFields.add(lbIdJogo);
		tfIdJogo = new JTextField();
		tfIdJogo.setBounds(460, 45, 35, 25);
		tfIdJogo.setEditable(false);
		tfIdJogo.setBackground(Color.WHITE);
		tfIdJogo.setForeground(Color.BLACK);
		textFields.add(tfIdJogo);

		// Criando botão de Retroceder
		btnRetroceder = new JButton("<");
		btnRetroceder.setBounds(100, 125, 50, 25);
		btnRetroceder.setFocusable(false);
		add(btnRetroceder);

		// Criando botão de Avançar
		btnAvancar = new JButton(">");
		btnAvancar.setBounds(390, 125, 50, 25);
		btnAvancar.setFocusable(false);
		add(btnAvancar);

		// Criando botão de ir para o Primeiro
		btnPrimeiro = new JButton("Primeiro");
		btnPrimeiro.setBounds(275, 110, 105, 25);
		btnPrimeiro.setFocusable(false);
		add(btnPrimeiro);

		// Criando botão de ir para o Ultimo
		btnUltimo = new JButton("Ultimo");
		btnUltimo.setBounds(275, 140, 105, 25);
		btnUltimo.setFocusable(false);
		add(btnUltimo);

		// Criando botão de selecionar Maior
		btnMaior = new JButton("Maior");
		btnMaior.setBounds(160, 110, 105, 25);
		btnMaior.setFocusable(false);
		add(btnMaior);
		
		// Criando botão de selecionar Menor
		btnMenor = new JButton("Menos");
		btnMenor.setBounds(160, 140, 105, 25);
		btnMenor.setFocusable(false);
		add(btnMenor);

		conexao = new conexao();
		executarTabela(selectSql);
		atualizarCampos();
	}

	public void Eventos() {
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.next();
					atualizarCampos();
				} catch (SQLException erro) {
				}
			}
		});

		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.previous();
					atualizarCampos();
				} catch (SQLException erro) {
				}
			}
		});

		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					atualizarCampos();
				} catch (SQLException erro) {
				}
			}
		});

		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					atualizarCampos();
				} catch (SQLException erro) {
				}
			}
		});

		btnMaior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String teste = "SELECT MAX(quantpJogo) from joguinhos";
					float Max = 0;
					PreparedStatement stMax = conexao.conn.prepareStatement(teste);
					ResultSet rsMax = stMax.executeQuery();
					if (rsMax != null && rsMax.next()) {
						Max = rsMax.getFloat((rsMax.getMetaData().getColumnName(1)));
					}
					selectSql = "SELECT * FROM joguinhos WHERE quantpJogo = '" + Max + "'";
					st = conexao.conn.prepareStatement(selectSql);
					rs = st.executeQuery();
					atualizarCampos();

					selectSql = "SELECT * FROM joguinhos";
					st = conexao.conn.prepareStatement(selectSql);
					rs = st.executeQuery();
				} catch (SQLException erro) {
					System.out.println(erro);
				}
			}
		});
		
		btnMenor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String teste = "SELECT MIN(quantpJogo) from joguinhos";
					float Min = 0;
					PreparedStatement stMin = conexao.conn.prepareStatement(teste);
					ResultSet rsMin = stMin.executeQuery();
					if (rsMin != null && rsMin.next()) {
						Min = rsMin.getFloat((rsMin.getMetaData().getColumnName(1)));
					}
					selectSql = "SELECT * FROM joguinhos WHERE quantpJogo = '" + Min + "'";
					st = conexao.conn.prepareStatement(selectSql);
					rs = st.executeQuery();
					atualizarCampos();

					selectSql = "SELECT * FROM joguinhos";
					st = conexao.conn.prepareStatement(selectSql);
					rs = st.executeQuery();
				} catch (SQLException erro) {
					System.out.println(erro);
				}
			}
		});

	}

	public void executarTabela(String sql) {
		try {
			if (!conexao.getConnection()) {
				JOptionPane.showMessageDialog(null, "Falha na MATRIX!");
				System.exit(0);
			}
			st = conexao.conn.prepareStatement(sql);
			rs = st.executeQuery();

			DefaultTableModel tableModel = new DefaultTableModel(
					new String[] { "DateGame", "NameGame", "TypeGame", "QuantGame", "IdGame" }, 0) {
				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};

			int qtdeColunas = rs.getMetaData().getColumnCount();
			// for (int indice = 1; indice <= qtdeColunas; indice++) {
			// tableModel.addColumn(rs.getMetaData().getColumnName(indice));
			// }

			tabela = new JTable(tableModel);
			tabela.setForeground(Color.BLACK);
			DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();

			while (rs.next()) {
				try {
					String[] dados = new String[qtdeColunas];
					for (int i = 1; i <= qtdeColunas; i++) {
						dados[i - 1] = rs.getString(i);
					}
					dtm.addRow(dados);
				} catch (SQLException erro) {

				}
				scrollTabela.setViewportView(tabela);
			}
			rs.first();
		} catch (Exception erro) {

		}
	}

	public void atualizarCampos() {
		try {
			if (rs.isAfterLast()) {
				rs.last();
			}
			if (rs.isBeforeFirst()) {
				rs.first();
			}
			tfDataJogo.setText(rs.getString("dataJogo"));
			tfNomeJogo.setText(rs.getString("nomeJogo"));
			tfTipoJogo.setText(rs.getString("tipoJogo"));
			tfQuantJogo.setText(rs.getString("quantpJogo"));
			tfIdJogo.setText(rs.getString("idJogo"));
		} catch (SQLException erro) {

		}

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tabela - BD");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new tabela());
		frame.setSize(560, 410);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
