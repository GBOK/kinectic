package okgb.kinectic;

import processing.core.PVector;

public class KVector extends PVector implements Comparable<KVector> {

    public Track track;

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
