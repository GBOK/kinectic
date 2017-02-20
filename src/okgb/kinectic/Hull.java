package okgb.kinectic;

import java.util.ArrayList;
import processing.core.PConstants;

public class Hull extends ArrayList<KVector> implements PConstants {

    float tolerance = 0.2f;

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    public boolean claim(KVector v) {
        if (this.size() > 2 && !this.insideHull(v)) return false;
        this.add(v);
        this.recalculate();
        return true;
    }

    private void recalculate() {
        KVector leftmost = null;
        for (KVector p : this) {
            if (
                leftmost == null
                || p.x < leftmost.x
                || p.x == leftmost.x && p.y < leftmost.y
            ) {
                leftmost = p;
            }
        }
        if (leftmost == null) return;
        ArrayList<KVector> list = new ArrayList<KVector>((ArrayList<KVector>)this);
        this.clear();
        this.add(leftmost);
        KVector current = leftmost;
        current.angle = - HALF_PI;
        do {
            current = this.mostCCV(current, list);
            this.add(current);
        } while (current != leftmost);
    }

    private KVector mostCCV(KVector current, ArrayList<KVector> list) {
        if (list.size() == 1) return list.get(0);
        KVector mostCCV = null;
        float angle = TWO_PI;
        for (KVector p : list) {
            if (current == p) continue;
            float x = p.x - current.x;
            float y = p.y - current.y;
            float a = (float)Math.atan2(y, x);
            if (a < current.angle) {
                a += TWO_PI;
            }
            if (
                mostCCV == null
                || a < angle
                || a == angle && current.dist(p) > current.dist(mostCCV)
            ) {
                mostCCV = p;
                angle = a;
            }
        }
        mostCCV.angle = angle;
        //list.remove(mostCCV);
        return mostCCV;
    }

    private boolean insideHull(KVector v) {
        boolean inside = true;
        for (int i = 0, max = this.size() - 1; i < max; i++) {
            inside = inside && this.isRightOf(this.get(i), this.get(i+1), v);
        }
        return inside;
    }

    private boolean isRightOf(KVector a, KVector b, KVector c) {
        KVector cCopy = new KVector(c.x, c.y, c.z);
        KVector d = new KVector(b.x - a.x, b.y - a.y);
        d.rotate(HALF_PI);
        d.setMag(this.tolerance);
        cCopy.add(d);
        return ((b.x - a.x)*(cCopy.y - a.y) - (b.y - a.y)*(cCopy.x - a.x)) >= 0;
    }
}
