package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NiuTest {
    Niu niu = new Niu();
    @Test
    void getMex() {
        assertEquals(4, niu.getMex(new int[]{1, 2, 3, 5, 6, 3, 2}, 7));
    }

    @Test
    void get() {
        assertEquals(8, niu.getMaxSegs("aabbccdd", new String[] {"a", "b", "c", "d"}, 4));
    }
}