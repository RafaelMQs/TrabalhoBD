import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class tabela extends JPanel {
	private JScrollPane scrollTabela;
	private JTable tabela;
	private conexao conexao;
	private PreparedStatement st;
	private ResultSet rs;

	private String sql = "SELECT * FROM joguinhos";

	public tabela() {
		Componentes();
		Eventos();

	}

	public void Componentes() {
		setLayout(null);
		scrollTabela = new JScrollPane();
		scrollTabela.setBounds(50, 170, 450, 183);
		add(scrollTabela);
		conexao = new conexao();

		try {
			if (!conexao.getConnection()) {
				JOptionPane.showMessageDialog(null, "Falha na MATRIX!");
				System.exit(0);
			}
			st = conexao.conn.prepareStatement(sql);
			rs = st.executeQuery();

			DefaultTableModel tableModel = new DefaultTableModel(new String[] {}, 0) {
				public boolean isCellEditable(int row, int col) {
					return false;
				}
			};

			int qtdeColunas = rs.getMetaData().getColumnCount();
			for (int indice = 1; indice <= qtdeColunas; indice++) {
				tableModel.addColumn(rs.getMetaData().getColumnName(indice));
			}

			tabela = new JTable(tableModel);
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

		} catch (Exception erro) {

		}

	}

	public void Eventos() {

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tabela - BD");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new tabela());
		frame.setSize(560, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
