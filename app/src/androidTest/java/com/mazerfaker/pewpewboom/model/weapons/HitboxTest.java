package com.mazerfaker.pewpewboom.model.weapons;

import android.support.test.runner.AndroidJUnit4;

import com.mazerfaker.pewpewboom.model.Hitbox;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HitboxTest {

    @Test
    public void updateTest() throws Exception {

        int top = 0;
        int left = 0;
        int width = 100;
        int height = 300;

        Hitbox myHitbox = new Hitbox(left, top, width, height);

        assertEquals(left , myHitbox.getRect().left);
        assertEquals(top , myHitbox.getRect().top);
        assertEquals(width , myHitbox.getRect().width());
        assertEquals(height , myHitbox.getRect().height());
        assertEquals(top + height , myHitbox.getRect().bottom);
        assertEquals(left + width , myHitbox.getRect().right);

        myHitbox.update( 42, 24);
    }
}
