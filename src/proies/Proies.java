package proies;

import java.util.ArrayList;
import java.util.List;

import etresVivants.Proie;
import vue.ContexteDeSimulation;


public class Proies {

	private List<Proie> lesProies;
	
	public Proies() {
		this.lesProies = new ArrayList<>();
	}
	
	// ajouter une proie
	public void ajouterProie(Proie p) {
		this.lesProies.add(p);
	}
	
	// la proie est supprimé car mangé par les fourmis
	public void supprimerProie(Proie p) {
		this.lesProies.remove(p);
	}
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		for(Proie proie : lesProies) {
			proie.etapeDeSimulation(contexte);
		}
	}

	
}
