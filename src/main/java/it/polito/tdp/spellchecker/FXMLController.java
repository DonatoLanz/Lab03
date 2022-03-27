package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLingua;

    @FXML
    private Label labelNumErr;

    @FXML
    private Label labelTempo;

    @FXML
    private TextArea txtMessagio;

    @FXML
    private TextArea txtParoleErrate;

    @FXML
    void doCheck(ActionEvent event) {

    	long start,end;
    	int i=0;
    	
    	start = System.nanoTime();
    	
    	Dictionary d = new Dictionary();
    	d.loadDictionary(cmbLingua.getValue());
    	
    	List<String> listaParole = new LinkedList<String>();
    	
    	String testo = txtMessagio.getText().toLowerCase().replaceAll("\\p{Punct}", "");
    	
    	String array[] = testo.split(" ");
    	
    	for(String s : array) {
    		listaParole.add(s);
    	}
    	
    	
    	List<RichWord> listaRW = d.spellCheckTextDichotomic(listaParole);
    	
    	for(RichWord rw : listaRW) {
    		if(rw.isCorretta()==false) {
    			txtParoleErrate.appendText(rw.getWord()+"\n");
    			i++;
    		}
    	}
    	
    	labelNumErr.setText("Il testo contiene " +i+ " errori");
    	end = System.nanoTime();
    	labelTempo.setText("Il tempo e' "+(end-start));
    	
    }

    @FXML
    void doClear(ActionEvent event) {
    txtMessagio.clear();
    txtParoleErrate.clear();
    }

    @FXML
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelNumErr != null : "fx:id=\"labelNumErr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelTempo != null : "fx:id=\"labelTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessagio != null : "fx:id=\"txtMessagio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleErrate != null : "fx:id=\"txtParoleErrate\" was not injected: check your FXML file 'Scene.fxml'.";

        String e = "English";
        String i = "Italian";
        
        cmbLingua.getItems().clear();
        cmbLingua.getItems().add(e);
        cmbLingua.getItems().add(i);
        
        
        
    }

}
