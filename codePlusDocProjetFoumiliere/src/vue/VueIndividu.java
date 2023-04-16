package vue;

import etresVivants.Individu;

/**
 * Classe qui creer une vue d'un individu.
 *
 * @author .
 *
 */
public class VueIndividu extends VueElement {

  private static final long serialVersionUID = 8010266472160477056L;
  Individu individu;

  public Individu getIndividu() {
    return individu;
  }

  /**
   * Constructeur d'une vue d'un individu.
   *
   * @param individu Individu à afficher dans la vue.
   */
  public VueIndividu(Individu individu) {
    this.individu = individu;
    individu.initialise(this);
    this.setLocation(this.individu.getPos());
  }

  @Override
  public void redessine() {
    individu.initialise(this);
    this.setLocation(this.individu.getPos());
  }
}

