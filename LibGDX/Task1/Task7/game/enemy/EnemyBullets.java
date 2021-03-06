package com.star.app.game.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.GameController;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class EnemyBullets implements Poolable {
    private GameController gc;
    private Vector2 position;
    private Vector2 velocity;
    private float angle;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void deactivate() {
        active = false;
    }

    public EnemyBullets(GameController gc) {
        this.gc = gc;
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
        if (position.x < 0.0f || position.x > GameController.SPACE_WIDTH || position.y < 0.0f || position.y > GameController.SPACE_HEIGHT) {
            deactivate();
        }
    }
}