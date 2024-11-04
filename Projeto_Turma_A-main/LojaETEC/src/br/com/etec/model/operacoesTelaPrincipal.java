package br.com.etec.model;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class operacoesTelaPrincipal implements Initializable{
	
	// Botões
	@FXML
	Button btnTipo;
	@FXML
	Button btnMarca;
	@FXML
	Button btnModelo;
	
	// ComboBox
	@FXML
	ComboBox<String> cmbTipo;
	@FXML
	ComboBox<String> cmbMarca;
	@FXML
	ComboBox<String> cmbModelo;
//
	// Inicia os valores das combobox
	public void initialize(URL url, ResourceBundle rb) {
		
		//ObservableList<String> lista = FXCollections.observableArrayList("Tênis", "Chinelo");
		//cmbTipo.setItems(lista);
		
		//ObservableList<String> lista2 = FXCollections.observableArrayList("Nike", "Adidas");
		//cmbMarca.setItems(lista2);
		
		//ObservableList<String> lista3 = FXCollections.observableArrayList("TEN001 (Jordan)", "TEN002 (Shox)", "CHN001 (And 1)", "TEA001 (Swift Run)", "CHA001 (Adinova)");
		//cmbModelo.setItems(lista3);
		carregarComboBox(cmbTipo, "tipo");
		carregarComboBox(cmbMarca, "marca");
		carregarComboBox(cmbModelo, "modelo");
	}

	// Método para puxar do banco
	private void carregarComboBox(ComboBox<String> comboBox, String coluna){
		
		ObservableList<String> lista = FXCollections.observableArrayList();
		
		try (Connection conn = ClasseConexao.conectar()){
			// Faz o select na coluna e joga na variável ResultSet rs
			String sql = "SELECT DISTINCT " + coluna + "FROM minhatabela"; 
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString(coluna));
			}
			// Preenche a combox com os dados puxados do banco
			comboBox.setItems(lista);
			// Exceção caso tenha puxado algo errado.
		} catch(Exception e) {
			e.printStackTrace();
		}
	} //---------------------------------
	
	
	
	
}
