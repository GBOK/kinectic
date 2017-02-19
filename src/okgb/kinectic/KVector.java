package okgb.kinectic;

import processing.core.PVector;

public class KVector extends PVector implements Comparable<KVector> {

    public Track track;
    public float angle;

    KVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    KVector(float x, float y) {
        this(x, y, 0.0f);
    }

    KVector(float x) {
        this(x, 0.0f);
    }

    KVector() {
        this(0.0f);
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public int getColor() {
        return this.track != null && this.track.tracker != null
            ? this.track.tracker.getColor()
            : 0xFF555555;
    }

    @Override
    public int compareTo(KVector v) {
        return this.z > v.z
            ? 1
            : (this.z < v.z ? -1 : 0);
    }
}
