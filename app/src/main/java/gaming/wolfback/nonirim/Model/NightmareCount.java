package gaming.wolfback.nonirim.Model;

/**
 * Created by Jarren on 3/12/2016.
 */
public class NightmareCount {
    public int getNightmareCount() {
        return nightmareCount;
    }

    public void incrementNightmareCount() {
        this.nightmareCount++;
    }
    private int nightmareCount = 0;
}
