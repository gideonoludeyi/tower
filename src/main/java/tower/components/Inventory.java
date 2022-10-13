package tower.components;

import java.util.List;

/**
 * @param ids the id of the entities that are items in the inventory
 */
public record Inventory(List<String> ids) {
}
