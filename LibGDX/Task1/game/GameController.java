package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

import static java.lang.Math.*;

public class GameController {
    public static final int SPACE_WIDTH = 9600;
    public static final int SPACE_HEIGHT = 5400;

    private Music music;
    private int level;
    private Background background;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private ParticleController particleController;
    private PowerUpsController powerUpsController;
    private InfoController infoController;
    private Hero hero;
    private Bot bot;
    private Vector2 tmpVec;
    private Stage stage;

    private StringBuilder tmpStr;
    private float msgTimer;
    private String msg;

    public Bot getBot() {
        return bot;
    }

    public float getMsgTimer() {
        return msgTimer;
    }

    public String getMsg() {
        return msg;
    }

    public Stage getStage() {
        return stage;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public PowerUpsController getPowerUpsController() {
        return powerUpsController;
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public InfoController getInfoController() {
        return infoController;
    }

    public Hero getHero() {
        return hero;
    }

    public int getLevel() {
        return level;
    }

    public GameController(SpriteBatch batch) {
        this.background = new Background(this);
        this.hero = new Hero(this, "PLAYER1");
        this.bot = new Bot(this);
        this.asteroidController = new AsteroidController(this);
        this.bulletController = new BulletController(this);
        this.particleController = new ParticleController();
        this.powerUpsController = new PowerUpsController(this);
        this.infoController = new InfoController();
        this.tmpVec = new Vector2(0.0f, 0.0f);
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.stage.addActor(hero.getShop());
        this.level = 1;
        Gdx.input.setInputProcessor(stage);
        generateTwoBigAsteroids();
        this.msg = "Level 1";
        this.msgTimer = 3.0f;
        this.tmpStr = new StringBuilder();
        this.music = Assets.getInstance().getAssetManager().get("audio/Music.mp3");
        this.music.setLooping(true);
         this.music.play();
    }

    public void generateTwoBigAsteroids() {
        for (int i = 0; i < 100; i++) {
            this.asteroidController.setup(MathUtils.random(0, GameController.SPACE_WIDTH), MathUtils.random(0, GameController.SPACE_HEIGHT),
                    MathUtils.random(-150.0f, 150.0f), MathUtils.random(-150.0f, 150.0f), 1.0f);
        }
    }

    public void update(float dt) {
        msgTimer -= dt;
        background.update(dt);
        hero.update(dt);
        if (bot.isAlive()) {
            bot.update(dt);
        }
        asteroidController.update(dt);
        bulletController.update(dt);
        particleController.update(dt);
        powerUpsController.update(dt);
        infoController.update(dt);
        checkCollisions(dt);

        if (!hero.isAlive()) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAMEOVER, hero);
        }
        if (asteroidController.getActiveList().size() == 0) {
            level++;
            generateTwoBigAsteroids();
        }
        stage.act(dt);
    }

    public boolean checkPhysicHit(Ship s, Asteroid a) {
        if (a.getHitArea().overlaps(s.getHitArea())) {
            float dst = a.getPosition().dst(s.getPosition());
            float halfOverLen = (a.getHitArea().radius + s.getHitArea().radius - dst) / 2.0f;
            tmpVec.set(s.getPosition()).sub(a.getPosition()).nor();
            s.getPosition().mulAdd(tmpVec, halfOverLen);
            a.getPosition().mulAdd(tmpVec, -halfOverLen);

            float v1 = s.getVelocity().len();
            float v2 = a.getVelocity().len();

            float m1 = 0.1f;
            float m2 = a.getScale();

            float th1 = s.getVelocity().angleRad();
            float th2 = a.getVelocity().angleRad();

            float phi1 = tmpVec.set(a.getPosition()).sub(s.getPosition()).angleRad();
            float phi2 = tmpVec.set(s.getPosition()).sub(a.getPosition()).angleRad();

            float v1xN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * cos(phi1) + v1 * sin(th1 - phi1) * cos(phi1 + PI / 2.0f));
            float v1yN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * sin(phi1) + v1 * sin(th1 - phi1) * sin(phi1 + PI / 2.0f));

            float v2xN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * cos(phi2) + v2 * sin(th2 - phi2) * cos(phi2 + PI / 2.0f));
            float v2yN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * sin(phi2) + v2 * sin(th2 - phi2) * sin(phi2 + PI / 2.0f));

            s.getVelocity().set(v1xN, v1yN);
            a.getVelocity().set(v2xN, v2yN);
            return true;
        }
        return false;
    }

    public void checkCollisions(float dt) {
        for (int i = 0; i < asteroidController.getActiveList().size(); i++) {
            Asteroid a = asteroidController.getActiveList().get(i);
            if (checkPhysicHit(hero, a)) {
                hero.takeDamage(2);
                if (a.takeDamage(2)) {
                    hero.addScore(a.getHpMax() * 10);
                    continue;
                }
            }
            if (checkPhysicHit(bot, a)) {
                a.takeDamage(2);
            }
        }

        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid a = asteroidController.getActiveList().get(j);

                if (a.getHitArea().contains(b.getPosition())) {
                    particleController.getEffectBuilder().bulletCollideWithAsteroid(b.getPosition(), b.getVelocity());
                    tmpStr.setLength(0);
                    tmpStr.append(1);
                    b.deactivate();
                    if (a.takeDamage(b.getDamage())) {
                        if (b.getOwner().getOwnerType() == OwnerType.PLAYER) {
                            ((Hero) b.getOwner()).addScore(a.getHpMax() * 100);
                            for (int k = 0; k < 3; k++) {
                                powerUpsController.setup(a.getPosition().x, a.getPosition().y, a.getScale() / 4.0f);
                            }
                        }
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);

            if (b.getOwner().getOwnerType() == OwnerType.PLAYER && bot.isAlive()) {
                if (bot.getHitArea().contains(b.getPosition())) {
                    bot.takeDamage(b.getDamage());
                    b.deactivate();
                }
            }

            if (b.getOwner().getOwnerType() == OwnerType.BOT) {
                if (hero.getHitArea().contains(b.getPosition())) {
                    hero.takeDamage(b.getDamage());
                    b.deactivate();
                }
            }
        }

        for (int i = 0; i < powerUpsController.getActiveList().size(); i++) {
            PowerUp p = powerUpsController.getActiveList().get(i);
            if (p.getPosition().dst(hero.getPosition()) < hero.getObjectCaptureRadius()) {
                tmpVec.set(hero.getPosition()).sub(p.getPosition()).nor().scl(100.0f);
                p.getPosition().mulAdd(tmpVec, dt);
            }
            if (hero.getHitArea().contains(p.getPosition())) {
                hero.consume(p);
                particleController.getEffectBuilder().takePowerUpEffect(p.getPosition().x, p.getPosition().y, p.getType().index);
                p.deactivate();
            }
        }
    }

    public void dispose() {
        background.dispose();
    }
}