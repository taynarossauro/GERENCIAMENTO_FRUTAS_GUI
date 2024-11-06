package main;

//Importando bibliotecas
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//A classe FrutaManagerGUI é um gerenciador de frutas
public class FrutaManagerGUI {
	//Declarando variáveis e coleções
	private ArrayList<String> frutas;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	//Classe gerenciador e frutas a partir de uma ArrayList, uma lista que contém valores
	public FrutaManagerGUI() {
		frutas = new ArrayList<>();
		listModel = new DefaultListModel<>();

		//Instanciando novo frame para o layout do gerenciador de frutas
		JFrame frame = new JFrame("Gerenciador de Frutas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setLayout(new BorderLayout());
		
		//Criando um container
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);
		
		//Botoes de escolhas do usuário
		//Botão adiciona a fruta
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
		
		//Botão modifica fruta
		JButton modifyButton = new JButton ("Modificar");
		modifyButton.setEnabled(false); //Inicia desabilitado pois não há frutas
		panel.add(modifyButton);
		
		//Botão adiciona fruta
		JButton removeButton = new JButton ("Remover");
		removeButton.setEnabled(false); //Inicia desabilitado pois não há frutas
		panel.add(removeButton);
		
		frame.add(panel, BorderLayout.NORTH);

		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list); //fornece visualização em scroll para o componente
		frame.add(scrollPane, BorderLayout.CENTER);
		
		//Botão lista frutas
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton,BorderLayout.SOUTH);
		
		// Sobrescreve o ActionListener para a situação de quando a fruta for adicionada
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
	
		// Sobrescreve o ActionListener parq a situação de quando a fruta for modificada
		modifyButton.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaSelecionada = listModel.getElementAt (selectedIndex);
					String novaFruta = JOptionPane.showInputDialog(frame, "Modificar: " + frutaSelecionada + " para: ", frutaSelecionada);
				if(novaFruta != null && !novaFruta.isEmpty()) {
					frutas.set(selectedIndex, novaFruta);
					listModel.set(selectedIndex, novaFruta);
					JOptionPane.showMessageDialog(frame, "Fruta" + frutaSelecionada + " foi modificada para: " + novaFruta);
				}
				
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar");
				}	
			}
		});				
	
		// Sobrescreve o ActionListener parq a situação de quando a fruta for removida
		removeButton.addActionListener(new ActionListener() 	{
			@Override	
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaRemovida = frutas.remove(selectedIndex);
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida");
				
				} else{
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover");
				}
				
			}
		
		});
	
		// Sobrescreve o ActionListener para quando for selecionada a opção listar frutas
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
		
		//
		list.addListSelectionListener(e -> {
			boolean selectionExists = !list.isSelectionEmpty();
			removeButton.setEnabled(selectionExists);
			modifyButton.setEnabled(selectionExists);
			
		});
		
		frame.setVisible(true);
		}

		public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrutaManagerGUI();
			}
			
		});
		
		
		}
	}
		
		
		
		
		
		