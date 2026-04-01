package duckcorp.bonus;

import duckcorp.order.Order;
import duckcorp.order.OrderStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Carnet de commandes trié automatiquement par urgence (délai croissant).
 * Les commandes les plus urgentes apparaissent en tête de liste.
 *
 * TODO (Bonus 2) :
 *   - Implémentez addOrder(), getPendingOrders(), getMostUrgent()
 *
 * Utilisez Collections.sort() avec un Comparator approprié.
 * @author Roussille Philippe <roussille@3il.fr>
 */
public class OrderBook {

    private final List<Order> orders = new ArrayList<>();

    /**
     * Ajoute une commande et maintient la liste triée par turnsRemaining croissant.
     * En cas d'égalité de délai, triez par id pour garantir un ordre stable.
     *
     * En cas d'égalité de délai, garantissez un ordre stable et reproductible.
     */
    public void addOrder(Order order) {
        if (order == null) return;
        orders.add(order);
        // Tri par turnsRemaining croissant, puis par id pour un ordre stable
        orders.sort(
                Comparator.comparingInt(Order::getTurnsRemaining)
                        .thenComparingInt(o -> Integer.parseInt(o.getId()))
        );
    }

    /**
     * Retourne la liste des commandes en attente (status == PENDING),
     * dans l'ordre de tri courant.
     *
     */
    public List<Order> getPendingOrders() {
        List<Order> pending = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus() == OrderStatus.PENDING) {
                pending.add(o);
            }
        }
        return pending;
    }

    /**
     * Retourne la commande PENDING la plus urgente (premier élément trié),
     * ou null si aucune commande n'est en attente.
     *
     */
    public Order getMostUrgent() {
        List<Order> pending = getPendingOrders();
        return pending.isEmpty() ? null : pending.get(0);
    }

    /** Retourne toutes les commandes (fourni). */
    public List<Order> getAll() {
        return Collections.unmodifiableList(orders);
    }
}
