import greenfoot.*; 
import java.util.List; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FlappyBird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlappyBird extends Actor {
    private int velocity = 0;
    private int score = 0;
    private boolean scored = false;

    public FlappyBird() {
        setImage(new GreenfootImage("turtle2.png"));
    }

    public void act() {
        handleKeyPress();
        fall();
        checkCollision();
        checkScore();
    }

    private void handleKeyPress() {
        if (Greenfoot.isKeyDown("space")) {
            velocity = -10;
        }
    }

    private void fall() {
        setLocation(getX(), getY() + velocity);
        velocity += 1;
    }

    private void checkCollision() {
        if (isTouching(Pipe.class)) {
            Greenfoot.stop(); // Hentikan permainan jika bersentuhan dengan pipa
        }
    }

    private void checkScore() {
        FlappyWorld world = (FlappyWorld) getWorld();
        List<Pipe> pipes = world.getObjects(Pipe.class);

        for (Pipe pipe : pipes) {
            if (getX() > pipe.getX() && !scored) {
                score++;
                world.showText("Score: " + score, 60, 30);
                scored = true;
                world.removeObject(pipe);
            }
        }
    }
}
