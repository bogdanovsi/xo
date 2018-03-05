package com.freekerrr.xo;

/**
 * Created by freekerrr on 28.02.2018.
 */

public class WinLine {

    public int indexStart;
    public int indexEnd;
    public int count;

    public WinLine() {
        indexStart = 4900;
        indexEnd = 0;
        count = 0;
    }

    public WinLine plus(WinLine other) {
        if (indexEnd < other.indexEnd) {
            this.indexEnd = other.indexEnd;
        }
        if (indexStart > other.indexStart) {
            this.indexStart = other.indexStart;
        }

        count += other.count;

        return this;
    }

    @Override
    public String toString() {
        return "IndexStart: " + indexStart + " |indexEnd: " + indexEnd + " count: " + count;
    }
}
