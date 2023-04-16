package etats;


import fourmiliere.Bilan;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe abstraite d'état.
 *
 * @author bertillemenguy
 *
 */
public abstract class Etat {

  
  /**
   *   Fonction qui ajoute un tic.
   *
   * @param contexte Contexte de l'individu.
   */
  public abstract void etapeDeSimulation(ContexteDeSimulation contexte);

  
  /**
   * Fonction qui initilise la vue d'un individu en fonction de son état.
   *
   * @param vue Vue pour afficher l'individu.
   */
  public abstract void initialise(VueIndividu vue);

  
  /**
   * Fonction qui met à jour le bilan des éléments de la fourmilière.
   *
   * @param bilan Bilan de la fourmiliere.
   */
  public abstract void bilan(Bilan bilan);

}
