package com.star.app.game.bonuses;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.GameController;
import com.star.app.game.helpers.Poolable;

public class Bonus implements Poolable {
    private GameController gc;
    private Vector2 position;
    private int bonusQuantity;
    private int bonusId;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public int getBonusQuantity() {
        return bonusQuantity;
    }

    public int getBonusId() {
        return bonusId;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Bonus(GameController gc) {
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.bonusId = MathUtils.random(0,2);
        this.active = false;
        switch (bonusId){
            case 0: this.bonusQuantity = 10;
            break;
            case 1: this.bonusQuantity = 500;
            break;
            case 2: this.bonusQuantity = 100;
            break;
        }
    }

    public void activate(float x, float y) {
        this.position.set(x, y);
        this.active = true;
    }
}