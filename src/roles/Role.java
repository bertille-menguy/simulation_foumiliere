package roles;


import fourmiliere.Bilan;
import vue.ContexteDeSimulation;

/**
 * Classe Abstraite qui détermine le role d'une foumi.
 *
 * @author .
 *
 */
public abstract class Role {
  public abstract void etapeDeSimulation(ContexteDeSimulation contexte);

  public abstract void bilan(Bilan bilan);
}
