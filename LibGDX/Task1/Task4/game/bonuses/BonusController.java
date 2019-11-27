package com.star.app.game.bonuses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.star.app.game.GameController;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.utils.Assets;

public class BonusController extends ObjectPool<Bonus> {
    private GameController gc;
    private TextureRegion[] bonusTexture;

    @Override
    protected Bonus newObject() {
        return new Bonus(gc);
    }

    public BonusController(GameController gc) {
        this.gc = gc;
        // потом заменить на другие картинки
        this.bonusTexture = new TextureRegion[] {Assets.getInstance().getAtlas().findRegion("ship"),
                Assets.getInstance().getAtlas().findRegion("ship"), Assets.getInstance().getAtlas().findRegion("ship")};
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            batch.draw(bonusTexture[activeList.get(i).getBonusId()], activeList.get(i).getPosition().x, activeList.get(i).getPosition().y);
        }
    }

    public void setup(float x, float y) {
        getActiveElement().activate(x, y);
    }

    public void update(float dt) {
        checkPool();
    }
}
