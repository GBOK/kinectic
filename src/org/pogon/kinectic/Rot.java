package org.pogon.kinectic;

import processing.core.PVector;

public class Rot {

    private final float q0;
    private final float q1;
    private final float q2;
    private final float q3;

    public Rot(PVector axis, float angle) {

        float norm = axis.mag();
        if (norm == 0) {
            q0 = 1;
            q1 = q2 = q3 = 0;
            System.out.println("The axis vector " + axis + " has no magnitude!!");
            return;
        }
        float halfAngle = -0.5f * angle;
        float coeff = (float) Math.sin(halfAngle) / norm;

        q0 = (float) Math.cos (halfAngle);
        q1 = coeff * axis.x;
        q2 = coeff * axis.y;
        q3 = coeff * axis.z;
    }

    public PVector applyTo(PVector u) {

        float x = u.x;
        float y = u.y;
        float z = u.z;

        float s = q1 * x + q2 * y + q3 * z;

        float nx = 2 * (q0 * (x * q0 - (q2 * z - q3 * y)) + s * q1) - x;
        float ny = 2 * (q0 * (y * q0 - (q3 * x - q1 * z)) + s * q2) - y;
        float nz = 2 * (q0 * (z * q0 - (q1 * y - q2 * x)) + s * q3) - z;

        u.x = nx;
        u.y = ny;
        u.z = nz;

        return u;
    }

    public String toString(){
        return "Q["+q0+" "+q1+" "+q2+" "+q3+"] ";
    }
}
