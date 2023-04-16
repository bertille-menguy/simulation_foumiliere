package vue;

import nicellipse.component.NiEllipse;

/**
 * Classe abstraite qui cree une vue d'elements.
 *
 * @author .
 *
 */
public abstract class VueElement extends NiEllipse {
  private static final long serialVersionUID = 1L;

  public VueElement() {}

  /**
   * Fonction qui sert à mettre à jour les vues.
   */
  public void mettreAJourVue() {
    this.redessine();
  }

  public abstract void redessine();

}
