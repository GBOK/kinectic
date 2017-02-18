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

    public int relation(KVector v) {

        // float dx = Math.abs(v.x - this.tip.x);
        // float dy = Math.abs(v.y - this.tip.y);
        // float dz = Math.abs(v.z - this.tip.z);

        // boolean insidex = dx - dz * 0.5f <= this.dimesions.x;
        // boolean insidey = dy - dz * 0.5f <= this.dimesions.y;

        // ++ this.weight;
        // if (insidex && insidey) return 1;

        // if (dz <= this.dimesions.z) {
        //     float ox = this.dimesions.x + this.dimesions.z * 0.5f;
        //     float oy = this.dimesions.y + this.dimesions.z * 0.5f;
        //     if (dx <= ox && dy <= oy) return 2;
        // }

        return 0;
    }

    public boolean claim(KVector v) {

        boolean add = false;

        KVector tip = this.getTip();

        float tdx = Math.abs(v.x - tip.x);
        float tdy = Math.abs(v.y - tip.y);
        float tdz = Math.abs(v.z - tip.z);

        boolean insideTipX = tdx - tdz * 0.5f <= this.distance;
        boolean insideTipY = tdy - tdz * 0.5f <= this.distance;

        if (insideTipX && insideTipY) add = true;

        if (false) {

            ListIterator<KVector> rli = this.points.listIterator(this.points.size());
            while (rli.hasPrevious()) {
                KVector p = rli.previous();


                float dx = Math.abs(v.x - p.x);
                float dy = Math.abs(v.y - p.y);
                float dz = Math.abs(v.z - p.z);

                boolean insidex = dx - dz * 0.5f <= this.distance;
                boolean insidey = dy - dz * 0.5f <= this.distance;

                if (insidex && insidey) add = true;


                // check if within the plane
//                float dist = Math.max(Math.abs(v.x - p.x), Math.abs(v.y - p.y));
                // float dist = PVector.dist(new PVector(v.x, v.y), new PVector(p.x, p.y));

  //              add = dist < this.distance;
                if (add || v.z > p.z + this.distance) {
                    break;
                }
            }
        }



        if (!add) return false;

        this.points.add(v);
        v.setTrack(this);
        ++ this.weight;
        return true;
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
