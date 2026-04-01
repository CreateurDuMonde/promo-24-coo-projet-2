package duckcorp;

import duckcorp.duck.*;
import duckcorp.stock.Stock;

import java.util.List;
import java.util.Map;

/**
 * Point d'entrée de l'application DuckCorp™.
 *
 * @author Roussille Philippe <roussille@3il.fr>
 */
public class Main {
    public static void main(String[] args) {
        Stock<Duck> stock = new Stock<>();

        // ⚠️ Adapte le constructeur si besoin
        stock.add(new StandardDuck(30));
        stock.add(new StandardDuck(60));
        stock.add(new MiniDuck(50));
        stock.add(new MiniDuck(2));
        stock.add(new LuxuryDuck(100));

        // --- TEST total ---
        System.out.println("Total: " + stock.total());

        // --- TEST count ---
        System.out.println("STANDARD: " + stock.count(DuckType.STANDARD));
        System.out.println("MINI: " + stock.count(DuckType.MINI));
        System.out.println("LUXURY: " + stock.count(DuckType.LUXURY));

        // --- TEST countDefective ---
        System.out.println("Défectueux: " + stock.countDefective());

        // --- TEST countByType ---
        Map<DuckType, Integer> map = stock.countByType();
        System.out.println("Par type: " + map);

        // --- TEST remove ---
        try {
            List<Duck> removed = stock.remove(DuckType.MINI, 1);
            System.out.println("Retirés (MINI): " + removed.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // --- Vérification après suppression ---
        System.out.println("Total après remove: " + stock.total());
        System.out.println("MINI après remove: " + stock.count(DuckType.MINI));
    }
}
