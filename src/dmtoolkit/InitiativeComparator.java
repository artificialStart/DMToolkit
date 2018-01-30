package dmtoolkit;

import java.util.Comparator;

/*
 * @author InstantRegret
 */

public class InitiativeComparator implements Comparator<Entity>
{
    @Override
    public int compare(Entity comp, Entity to) {
        int compInit;
        int toInit;
        
        if (comp instanceof PlayerChar) {
            compInit = ((PlayerChar) comp).getInit();
        } else if (comp instanceof Monster) {
            compInit = ((Monster) comp).getInit();
        } else{
            compInit = ((NPC) comp).getInit();
        }
        
        if (to instanceof PlayerChar) {
            toInit = ((PlayerChar) to).getInit();
        } else if (to instanceof Monster) {
            toInit = ((Monster) to).getInit();
        } else {
            toInit = ((NPC) to).getInit();
        }
        
        if (compInit < toInit) {
            return 1;
        } else {
            return -1;
        }
    }
}
