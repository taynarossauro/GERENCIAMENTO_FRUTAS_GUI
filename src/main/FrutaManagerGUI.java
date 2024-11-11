package main;

//Importando bibliotecas necessárias
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Essa classe: FrutaManagerGUI lida com a gestão de frutas na interface gráfica
public class FrutaManagerGUI {
	// Declaração das variáveis
	private ArrayList<String> frutas;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	//Construtor que inicializa a lista de frutas com uma ArrayList
	public FrutaManagerGUI() {
		frutas = new ArrayList<>();
		listModel = new DefaultListModel<>();

		//Configuração do JFrame para a interface principal
		JFrame frame = new JFrame("Gerenciador de Frutas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setLayout(new BorderLayout());
		
		// Criando o painel de entrada de dados
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);
		
		//Botões de ação para o usuário interagir com a lista de frutas
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
		
		// Botão para modificar a fruta selecionada
		JButton modifyButton = new JButton("Modificar");
		modifyButton.setEnabled(false); // Desabilitado inicialmente sem seleção
		panel.add(modifyButton);
		
		//Botão para remover uma fruta selecionada
		JButton removeButton = new JButton("Remover");
		removeButton.setEnabled(false); // Desabilitado sem seleção
		panel.add(removeButton);
		
		frame.add(panel, BorderLayout.NORTH);

		// Adicionando lista de frutas com capacidade de rolagem
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list); // Proporciona rolagem
		frame.add(scrollPane, BorderLayout.CENTER);
		
		//Botão que lista todas as frutas no console
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton,BorderLayout.SOUTH);
		
		// Evento para adicionar nova fruta à lista
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				String novaFruta = frutaField.getText();
				if(!novaFruta.isEmpty()) {
					frutas.add(novaFruta);
					listModel.addElement(novaFruta);
					frutaField.setText("");
					JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");
				}
			}
		});
	
		//evento para modificar uma fruta já existente
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaSelecionada = listModel.getElementAt(selectedIndex);
					String novaFruta = JOptionPane.showInputDialog(frame, "Modificar: " + frutaSelecionada + " para: ", frutaSelecionada);
					if(novaFruta != null && !novaFruta.isEmpty()) {
						frutas.set(selectedIndex, novaFruta);
						listModel.set(selectedIndex, novaFruta);
						JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para: " + novaFruta);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar");
				}	
			}
		});				
	
		// evento para remover a fruta selecionada da lista
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaRemovida = frutas.remove(selectedIndex);
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida");
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover");
				}
			}
		});
	
		// Evento para exibir a lista completa de frutas em um diálogo
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frutas.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista");
				} else {
					JOptionPane.showMessageDialog(frame, "Frutas: " + frutas);
				}
			}
		});
		
		//Habilita e desabilita os botões de ação dependendo da seleção na lista
		list.addListSelectionListener(e -> {
			boolean selectionExists = !list.isSelectionEmpty();
			removeButton.setEnabled(selectionExists);
			modifyButton.setEnabled(selectionExists);
		});
		
		// Define a visibilidade do frame
		frame.setVisible(true);
	}

	// Método principal para inicializar a aplicação
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrutaManagerGUI();
			}
		});
	}
}
