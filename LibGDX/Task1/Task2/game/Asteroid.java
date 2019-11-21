package com.star.app.game;

import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Asteroid() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.active = false;
    }

    public void activate(float x, float y, float vx, float vy) {
        position.set(x, y);
        velocity.set(vx, vy);
        active = true;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < -200.0f || position.x > ScreenManager.SCREEN_WIDTH + 200 || position.y < -200.0f || position.y > ScreenManager.SCREEN_HEIGHT + 200) {
            position.x = -200;
            position.y = -200;
        }
    }
}