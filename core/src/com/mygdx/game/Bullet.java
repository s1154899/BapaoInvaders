package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Bullet{
    private float posX;
    private float posY;
    private Texture laser = new Texture(Gdx.files.internal("laser.png"));
    private boolean friendly;
    private float bulletSpeed;
    private int damage;
    boolean exists = true;
    static ArrayList<Bullet> allBullets = new ArrayList<Bullet>();

    public Bullet (int x, int y, boolean friendly, Player player){
        this.posX = x + player.getPosX();
        this.posY = y + player.getPosY();
        this.friendly = friendly;
        this.damage = 50;
    }

    public Bullet (int x, int y, boolean friendly, Enemy enemy){
        this.posX = x + enemy.getPosX();
        this.posY = y + enemy.getPosY();
        this.friendly = friendly;
        switch (enemy.getEnemyClass()) {
            case 1:{
                this.damage = 25;
                this.bulletSpeed = 1;
            }
            case 2:{
                this.damage = 10;
                this.bulletSpeed = 1.5f;
            }
            case 3:{
                this.damage = 34;
                this.bulletSpeed = 0.85f;
            }
            case 4:{
                this.damage = 50;
                this.bulletSpeed = 0.5f;
            }
            default:{
                this.damage = 25;
                this.bulletSpeed = 1;
            }
        }
    }

    public void setPosX(int posX, Player player, int width) {
        if (this.posX+posX > width || this.posX+posX < 0){
            player.bulletRemove(this);
        }
        else{
            this.posX += posX * bulletSpeed;
        }
    }

    public void setPosY(float posY, int height) {
        if (this.posY+posY > height || this.posY+posY < 0){
            this.exists = false;
        }
        else{
            this.posY += posY * ((this.friendly)?1:-1);
        }
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public Texture getLaser() {
        return laser;
    }

    public boolean getFriendly(){
        return friendly;
    }
}
