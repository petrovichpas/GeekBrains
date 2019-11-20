package com.star.app.game;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private Hero hero;
    private AsteroidController asteroidController;

    public BulletController getBulletController() {
        return bulletController;
    }


    public Background getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public AsteroidController getAsteroid() {
        return asteroidController;
    }

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.bulletController = new BulletController();
        this.asteroidController = new AsteroidController();
        this.asteroidController.setup(-100, -100, 300, 300);
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        bulletController.update(dt);
        asteroidController.update(dt);
        checkCollisions();
    }

    // Заготовка под столкновение с астероидами (для ДЗ)
    public void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            Asteroid a = asteroidController.getActiveList().get(0);
            if (a.getPosition().dst(b.getPosition()) < 200.0f) {
                b.deactivate();
                a.deactivate();
            }
        }
    }
}