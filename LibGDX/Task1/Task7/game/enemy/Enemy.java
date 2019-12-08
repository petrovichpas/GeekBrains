package com.star.app.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.StringBuilder;
import com.star.app.game.GameController;
import com.star.app.screen.utils.Assets;
import com.star.app.screen.utils.OptionsUtils;

public class Enemy {
    private GameController gc;
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 tmp;
    private float angle;
    private float enginePower;
    private Circle hitArea;
    private Vector2 tmpVector;
    private float fireTimer;

    public Enemy(GameController gc) {
        this.gc = gc;
        this.texture = Assets.getInstance().getAtlas().findRegion("ship");
        this.position = new Vector2(140, 160);
        this.velocity = new Vector2(0, 0);
        this.tmp = new Vector2(0, 0);
        this.angle = 0.0f;
        this.enginePower = 200.0f;
        this.hitArea = new Circle(position, 26.0f);
        this.tmpVector = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32, 64, 64, 1, 1, angle);
    }

    public void update(float dt) {
        fireTimer += dt;
            if (fireTimer > 0.5f) {
                fireTimer = 0.0f;
                gc.getEnemyBulletsController().setup(position.x, position.y, (float) Math.cos(Math.toRadians(angle)) * 600 + velocity.x, (float) Math.sin(Math.toRadians(angle)) * 600 + velocity.y);
        }
        angle = tmp.set(gc.getHero().getPosition()).sub(position).nor().angle();
        tmp.set(gc.getHero().getPosition()).sub(position).nor().scl(enginePower);
        position.mulAdd(tmp,dt);

        checkSpaceBorders();
    }

    public void checkSpaceBorders() {
        if (position.x < hitArea.radius) {
            position.x += GameController.SPACE_WIDTH;
        }
        if (position.x > GameController.SPACE_WIDTH - hitArea.radius) {
            position.x -= GameController.SPACE_WIDTH;
        }
        if (position.y < hitArea.radius) {
            position.y = GameController.SPACE_HEIGHT - hitArea.radius - 1;
        }
        if (position.y > GameController.SPACE_HEIGHT - hitArea.radius) {
            position.y = hitArea.radius + 1;
        }
    }
}
