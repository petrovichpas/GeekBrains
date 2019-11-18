package com.star.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Vector2 position;
    private float velocity;

    public Asteroid() {
        this.position = new Vector2(MathUtils.random(0, ScreenManager.SCREEN_WIDTH - 200), MathUtils.random(-200, -150));
        this.velocity = 300;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float dt) {
        position.x += velocity * dt;
        position.y += velocity * dt;

        if (position.y > ScreenManager.SCREEN_HEIGHT + 200) {
            position.x = MathUtils.random(0, ScreenManager.SCREEN_WIDTH -200);
            position.y = -200;
        }
    }
}