package okgb.kinectic;

import processing.core.PVector;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.ListIterator;

class Track implements Comparable<Track> {

    public ArrayList<KVector> points;
    public PVector dimesions;
    public float leftBound = 0.0f, rightBound = 0.0f, topBound = 0.0f, bottomBound = 0.0f;
    private float distance;
    public int weight = 0;
    public Tracker tracker;
    private boolean valid = true;

    Track(KVector tip, PVector dimesions){
        this.points = new ArrayList<KVector>(640 * 480);
        this.dimesions = dimesions;
        this.distance = 0.1f;
        this.points.add(tip);
    }

    public boolean claim(KVector v) {

        return false;
    }

    public boolean close(KVector v) {
        return true; //this.tip.dist(v) < this.tip.z;
    }

    public void invalidate() {
        this.valid = false;
    }

    public KVector getTip() {
        return this.points.get(0);
    }

    public boolean isValid() {
        return this.valid && this.weight > 10;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public int compareTo(Track t) {
        return this.weight > t.weight ? 1 : (this.weight < t.weight ? -1 : 0);
    }
}
