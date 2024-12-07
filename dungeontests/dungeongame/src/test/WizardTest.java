package dungeongame.src.test;

import dungeongame.src.model.Ogre;
import dungeongame.src.model.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Wizard} class.
 * This class tests the behavior of the Wizard class, including its attributes,
 * attack range, speed range, and the effects of its targeted special attack.
 */
public class WizardTest {

    /**
     * The Wizard instance used for testing.
     */
    Wizard myWizard;

    /**
     * The Ogre instance used as a target for the Wizard's special attack.
     */
    Ogre myOgre;

    /**
     * Initializes the Wizard and Ogre objects before each test.
     */
    @BeforeEach
    public void setup() {
        myWizard = new Wizard(75, 20, 40, 4, 10, 7, "Merlin");
        myOgre = new Ogre(50, 5, 10, 3, 8, 2, "Gary");
    }

    /**
     * Tests the basic attributes of the Wizard instance, such as max health, defense,
     * and its string representation.
     */
    @Test
    public void testWizard() {
        assertNotNull(myWizard);
        assertEquals(75, myWizard.getMaxHealth());
        assertEquals(7, myWizard.getDefense());
        assertEquals("Merlin the Wizard", myWizard.toString());
    }

    /**
     * Tests the attack values generated by the Wizard, ensuring they fall within the defined range.
     * Iterates multiple times to ensure randomness stays within bounds.
     */
    @Test
    public void testWizardAttack() {
        for (int i = 0; i <= 1000; i++) {
            int attack = myWizard.getAttack();
            assertTrue(attack >= 20 && attack <= 40);
        }
        System.out.println("Attack is: " + myWizard.getAttack());
    }

    /**
     * Tests the speed values generated by the Wizard, ensuring they fall within the defined range.
     * Iterates multiple times to ensure randomness stays within bounds.
     */
    @Test
    public void testWizardSpeed() {
        for (int i = 0; i <= 1000; i++) {
            int speed = myWizard.getSpeed();
            assertTrue(speed >= 4 && speed <= 10);
        }
        System.out.println("Speed is: " + myWizard.getSpeed());
    }

    /**
     * Tests the behavior of the Wizard's targeted special attack.
     * Verifies that health increases by the bonus amount (50), but does not exceed the maximum health.
     */
    @Test
    void testUseTargetedSpecialAttackHealthIncrease() {
        myWizard.setHealth(15);
        System.out.println("Your health is: " + myWizard.getHealth());

        // Add a listener to verify the property change
        PropertyChangeListener listener = new PropertyChangeListener() {
            boolean eventFired = false;

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                eventFired = true;
                assertEquals("Health Changed", evt.getPropertyName());
                assertEquals(65, evt.getNewValue());
            }
        };
        myWizard.getMyPCS().addPropertyChangeListener(listener);
        myWizard.useTargetedSpecialAttack(myOgre);

        assertEquals(65, myWizard.getHealth());
        System.out.println("Your health is now: " + myWizard.getHealth() + " after using targeted special.");
    }

    /**
     * Tests the behavior of the Wizard's targeted special attack when the health increase
     * would exceed the maximum health. Ensures the health is capped at the maximum value.
     */
    @Test
    void testUseTargetedSpecialHealthIncreaseExceedingMaxHealth() {
        myWizard.setHealth(45);
        System.out.println("Your health is: " + myWizard.getHealth());

        PropertyChangeListener listener = new PropertyChangeListener() {
            boolean eventFired = false;

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                eventFired = true;
                assertEquals("Health Changed", evt.getPropertyName());
                assertEquals(75, evt.getNewValue());
            }
        };
        myWizard.getMyPCS().addPropertyChangeListener(listener);
        myWizard.useTargetedSpecialAttack(myOgre);

        assertEquals(75, myWizard.getHealth());
        System.out.println("Your health is at its max: " + myWizard.getHealth() + " after using targeted special.");
    }

    /**
     * Tests if Wizard is dead when health is 0.
     */
    @Test
    void testIsWizardDead() {
        myWizard.setHealth(0);
        assertTrue(myWizard.isDead());
    }

    /**
     * Tests if wizard is dead when wizard has health.
     */
    @Test
    void testIsWizardDeadWithHealth() {
        myWizard.setHealth(30);
        assertFalse(myWizard.isDead());
    }
}
