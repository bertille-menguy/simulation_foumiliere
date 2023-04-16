package fourmiliere;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Classe qui permet d'établir le bilan des éléments de la fourmilère.
 *
 * @author .
 *
 */
public class Bilan {

  Map<String, Integer> data = new HashMap<String, Integer>();

  public void incr(String k) {
    Integer n = this.data.get(k);
    this.data.put(k, (n == null) ? 1 : n + 1);
  }

  public Integer howMany(String k) {
    Integer n = data.get(k);
    return n == null ? 0 : n;
  }

  public void clear() {
    data.clear();
  }

  /**
   * Fonction qui affiche les éléments de fourmilière.
   *
   * @return une chaine de caractères.
   */
  public String asString() {
    Set<String> s = data.keySet();
    Iterator<String> itor = s.iterator();
    String result = "";
    while (itor.hasNext()) {
      String curr = itor.next();
      result += curr + ":" + data.get(curr) + "; ";
    }
    return result;
  }

}
