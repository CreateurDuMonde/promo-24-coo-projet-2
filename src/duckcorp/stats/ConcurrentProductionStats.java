package duckcorp.stats;

import duckcorp.duck.Duck;
import duckcorp.duck.DuckType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

/**
 * Version thread-safe de ProductionStats, utilisant ConcurrentHashMap.
 *
 * TODO (Bonus 3) :
 *   - Implémentez le constructeur : remplacez les HashMap héritées
 *     par des ConcurrentHashMap via les champs protected de la classe parente.
 *   - Surchargez recordProduction() pour utiliser ConcurrentHashMap.merge()
 *     de façon atomique.
 *
 * Question ouverte : pourquoi ConcurrentHashMap ne suffit-il pas à garantir
 * la cohérence globale des stats dans un contexte multi-thread ?
 * Que faudrait-il ajouter ?
 * @author Roussille Philippe <roussille@3il.fr>
 */
public class ConcurrentProductionStats extends ProductionStats {

    /**
     * puis initialisez chaque entrée à 0 pour tous les DuckType.
     */
    public ConcurrentProductionStats() {
        super();
        produced = new ConcurrentHashMap<>();
        sold     = new ConcurrentHashMap<>();
        for (DuckType t : DuckType.values()) {
            produced.put(t, 0);
            sold.put(t, 0);
        }
    }

    /**
     * de façon atomique, sans risque de race condition.
     */
    @Override
    public void recordProduction(List<Duck> ducks) {
        if (ducks == null) return;
        for (Duck duck : ducks) {
            produced.merge(duck.getType(), 1, Integer::sum);
        }
    }
}
