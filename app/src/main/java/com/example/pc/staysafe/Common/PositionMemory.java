package com.example.pc.staysafe.Common;

public class PositionMemory {
    private int max;
    private int position = 1;

    public PositionMemory(int max) {
        this.max = max;
    }

    public boolean incrementPosition() {
        if (position < max) {
            position++;
            return true;
        } else {
            return false;
        }
    }
    public boolean decrementPosition() {
        if (position > 1) {
            position--;
            return true;
        } else {
            return false;
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int current) {
        this.position = current;
    }
}