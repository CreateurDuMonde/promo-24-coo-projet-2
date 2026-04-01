package duckcorp.stock;

import duckcorp.duck.Duck;
import duckcorp.duck.DuckType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Stock générique de canards.
 *
 * TODO (Ex3) :
 *   - Implémentez remove(), count(), countDefective(), countByType()
 *
 * Les méthodes add(), getAll() et total() sont fournies.
 *
 * @param <T> type de canard stocké (doit étendre Duck)
 * @author Roussille Philippe <roussille@3il.fr>
 */
public class Stock<T extends Duck> {

    private final List<T> items = new ArrayList<>();

    // --- Méthodes fournies ---

    /** Ajoute un canard au stock. */
    public void add(T duck) {
        items.add(duck);
    }

    /** Retourne une vue non modifiable de tous les canards en stock. */
    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    /** Retourne le nombre total de canards en stock. */
    public int total() {
        return items.size();
    }

    // --- TODO ---

    /**
     * Retire exactement {@code count} canards du type {@code type} du stock
     * et les retourne dans une liste.
     *
     * @param type  le type de canard à retirer
     * @param count le nombre à retirer
     * @return la liste des canards retirés
     * @throws IllegalStateException si le stock ne contient pas assez de canards du type demandé
     *
     * Conseil : parcourez items en une seule passe.
     * Attention à la signature de retour : elle doit conserver le type générique T.
     */
    public List<T> remove(DuckType type, int count) {
        List<T> removed = new ArrayList<>();

        for (int i = 0; i < items.size() && removed.size() < count; i++) {
            T duck = items.get(i);
            if (duck.getType() == type) {
                removed.add(duck);
            }
        }

        if (removed.size() < count) {
            throw new IllegalStateException(
                    "Pas assez de canards de type " + type + " dans le stock"
            );
        }

        items.removeAll(removed);
        return removed;
    }

    /**
     * Retourne le nombre de canards du type {@code type} présents dans le stock.
     *
     * @param type le type à compter
     */
    public int count(DuckType type) {
        int c = 0;
        for (T duck : items) {
            if (duck.getType() == type) {
                c++;
            }
        }
        return c;
    }

    /**
     * Retourne le nombre de canards défectueux dans le stock.
     * Un canard est défectueux si isDefective() retourne true.
     *
     * Conseil : appelez isDefective() plutôt que de comparer le score manuellement.
     */
    public int countDefective() {
        int c = 0;
        for (T duck : items) {
            if (duck.isDefective()) {
                c++;
            }
        }
        return c;
    }

    /**
     * Retourne une Map associant chaque DuckType au nombre de canards
     * de ce type présents dans le stock.
     *
     * Conseil : construisez la map en une seule passe sur items.
     * Tous les types doivent apparaître dans la map (avec 0 si absent).
     */
    public Map<DuckType, Integer> countByType() {
        Map<DuckType, Integer> map = new java.util.EnumMap<>(DuckType.class);

        // Initialiser tous les types à 0
        for (DuckType type : DuckType.values()) {
            map.put(type, 0);
        }

        // Une seule passe
        for (T duck : items) {
            DuckType type = duck.getType();
            map.put(type, map.get(type) + 1);
        }

        return map;
    }
}
