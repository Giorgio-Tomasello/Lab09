/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="cmbStati"
    private ComboBox<Country> cmbStati; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno = Integer.parseInt(txtAnno.getText());

    	
    	if(anno>1815 && anno<2017){
    		
    		txtResult.appendText(model.creaGrafo(anno));
    		txtResult.appendText("\n#N Vertici: "+model.nVertici()+"\n");
    		txtResult.appendText("#N Archi: "+model.nArchi());
    		cmbStati.getItems().addAll(model.getGrafo().vertexSet());
    	}
    	
    	else {
    		txtResult.setText("Errore! Anno non valido");
    	}

    }

    @FXML
    void handleRicerca(ActionEvent event) {
    	txtResult.clear();
    	
    	
    	for(Country c:model.getNodi(cmbStati.getValue()))
    		txtResult.appendText(c.getStateName()+"\n");
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }



    
    public void setModel(Model model) {
    	this.model = model;
    }
}



