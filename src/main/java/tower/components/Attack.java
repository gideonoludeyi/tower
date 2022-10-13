package tower.components;

import tower.core.Component;

/**
 * @param targetId the id of the enemy to target for the attack
 */
public record Attack(String targetId) implements Component {
}
