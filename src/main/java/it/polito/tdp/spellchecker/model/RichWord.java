package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String word;
	private boolean corretta;
	
	public RichWord(String word, boolean corretta) {
		
		this.word = word;
		this.corretta = corretta;
	}

	public String getWord() {
		return word;
	}

	public boolean isCorretta() {
		return corretta;
	}
	
	
	
	
}
