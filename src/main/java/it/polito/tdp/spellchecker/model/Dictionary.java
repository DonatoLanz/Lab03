package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

	List<String> dizionario = new LinkedList<String>();
	
	public void loadDictionary(String language) {
		
		String l =language+".txt";
		
		try {
			
			FileReader fr = new FileReader("C:\\Users\\lanzi\\Desktop\\TdP\\Lab03\\src\\main\\resources\\"+l);
			BufferedReader br = new BufferedReader(fr);
			
			String word;
			
			while((word = br.readLine()) != null) {
				dizionario.add(word);
				System.out.println(word);
			}
			
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura file");
			e.printStackTrace();
		}
		
	}
	
	
	public List<RichWord> spellCheckerText (List<String> inputTextList){
		
		LinkedList<RichWord> listaRichWord = new LinkedList<RichWord>();
		
		for(String s : inputTextList) {
			if(dizionario.contains(s)==true) {
				RichWord rc = new RichWord(s, true);
				listaRichWord.add(rc);
			}
			else {
				RichWord rc = new RichWord(s, false);
				listaRichWord.add(rc);
			}
		}
		
		return listaRichWord;
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		List<RichWord> listaRCL = new LinkedList<RichWord>();
		
		for(String s : inputTextList) {
			if(this.ilDizionarioContieneQuestaParola(s)==true) {
			   RichWord rc = new RichWord(s, true);
			   listaRCL.add(rc);
			}
			else {
				RichWord rc = new RichWord(s, false);
				listaRCL.add(rc);
			}
		}
				return listaRCL;
		
	}
	
	
public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		List<RichWord> listaRCD = new LinkedList<RichWord>();
		
		
		for(String s : inputTextList) {
			
			boolean trovato=false;
			int ritorno;
			int inizio=0;
			int fine = dizionario.size()-1;
			int mezzo = (inizio+fine)/2;
			
			while(inizio<=fine) {
				
				if((ritorno=dizionario.get(mezzo).compareTo(s))==0) {
					listaRCD.add(new RichWord(s,true));
					trovato=true;
					break;
				}
				
				if(ritorno<0) {
					inizio=mezzo+1;
					mezzo=(fine+inizio)/2;
				}
				else {
					fine=mezzo-1;
					mezzo=(inizio+fine)/2;
				}
			}
			
			if(trovato==false) {
				listaRCD.add(new RichWord(s,false));
			}
		}
		
		
		
		/*for(String s : inputTextList) {
			if(this.ilDizionarioContieneQuestaParolaDico(s)==true) {
			   RichWord rc = new RichWord(s, true);
			   listaRCD.add(rc);
			}
			else {
				RichWord rc = new RichWord(s, false);
				listaRCD.add(rc);
			}
		}*/
				return listaRCD;
		
	}
	
	
	
	public boolean ilDizionarioContieneQuestaParola(String s) {
		
		boolean trovata = false;
		
		for(String ss : dizionario) {
			if(s.compareTo(ss)==0) {
			   trovata = true;
			   break;
			}
		}
		
		return trovata;
		
	}
	
	public boolean ilDizionarioContieneQuestaParolaDico(String s) {
		
		boolean trovata=false;
		int ritorno;
		int inizio=0;
		int fine=dizionario.size()-1;
		int mezzo = (inizio+fine)/2;
		
		
		while(inizio<=fine) {
		
			ritorno = dizionario.get(mezzo).compareTo(s);
					
			if(ritorno==0) {
				trovata=true;
				break;
			}
			else if(ritorno<0) {
				inizio=mezzo+1;
				mezzo=(fine+inizio)/2;
				System.out.println(inizio+" "+fine+" "+mezzo);
			}
			else if(ritorno>0) {
				fine=mezzo-1;
				mezzo=(inizio+fine)/2;
				System.out.println(inizio+" "+fine+" "+mezzo);
			}
		
			
		}
		
		System.out.println("Trovata "+trovata);
		return trovata;
		
	}
}
