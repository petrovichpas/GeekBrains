package com.star.app.game.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.star.app.game.GameController;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.utils.Assets;

public class EnemyBulletsController extends ObjectPool<EnemyBullets> {
    private GameController gc;
    private TextureRegion bulletTexture;

    @Override
    protected EnemyBullets newObject() {
            return new EnemyBullets(gc);
    }

    public EnemyBulletsController(GameController gc) {
            this.gc = gc;
            this.bulletTexture = Assets.getInstance().getAtlas().findRegion("bullet32");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            EnemyBullets b = activeList.get(i);
            batch.draw(bulletTexture, b.getPosition().x - 16, b.getPosition().y - 16);
        }
    }

    public void setup(float x, float y, float vx, float vy) {
        getActiveElement().activate(x, y, vx, vy);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
