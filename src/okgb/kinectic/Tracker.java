package okgb.kinectic;

import processing.core.PVector;
import java.lang.Comparable;
import java.util.ArrayList;

public class Tracker implements Comparable<Tracker> {

    public ArrayList<PVector> history;
    private float distance;
    private int threshold;
    private int tick = 0;
    private int color;

    Tracker(Track initial, float distance, int threshold) {
        this.color =
            0xFF000000
            + (int)(Math.random() * 0x100) * 0x10000
            + (int)(Math.random() * 0x100) * 0x100
            + (int)(Math.random() * 0x100);
        this.history = new ArrayList<PVector>(100);
        this.claim(initial);
        this.distance = distance;
        this.threshold = threshold;
    }

    Tracker(Track initial, float distance) {
        this(initial, distance, 2);
    }

    public boolean prune() {
        if (++this.tick > this.history.size() + this.threshold) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        return this.history.size() > this.threshold;
    }

    public PVector getLast() {
        return ! this.history.isEmpty()
            ? this.history.get(this.history.size() - 1)
            : null;
    }

    public boolean claim(Track track) {
        PVector last = ! this.history.isEmpty()
            ? this.history.get(this.history.size() - 1)
            : null;

        KVector tip = track.getTip();
        if (last == null || last.dist(tip) < this.distance) {
            //if (last != null) System.out.println(last.dist(tip) + " " + this.distance);
            this.history.add((PVector)tip);
            track.setTracker(this);
            return true;
        }
        return false;
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public int compareTo(Tracker t) {
        return this.tick > t.tick ? 1 : (this.tick < t.tick ? -1 : 0);
    }
}
