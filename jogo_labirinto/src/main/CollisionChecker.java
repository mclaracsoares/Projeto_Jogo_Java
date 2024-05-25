package main;

import entities.Aventureiro;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Aventureiro aventureiro) {
        int entityLeftWorldX = aventureiro.mapaX + aventureiro.solidArea.x;
        int entityRightWorldX = aventureiro.mapaX + aventureiro.solidArea.x + aventureiro.solidArea.width;
        int entityTopWorldY = aventureiro.mapaY + aventureiro.solidArea.y;
        int entityBottomWorldY = aventureiro.mapaY + aventureiro.solidArea.y + aventureiro.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tamanhoQuadrados;
        int entityRightCol = entityRightWorldX / gp.tamanhoQuadrados;
        int entityTopRow = entityTopWorldY / gp.tamanhoQuadrados;
        int entityBottomRow = entityBottomWorldY / gp.tamanhoQuadrados;

        int tileNum1, tileNum2;

        switch (aventureiro.direcao) {
            case "up":
                entityTopRow = (entityTopWorldY - aventureiro.velocidade) / gp.tamanhoQuadrados;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    aventureiro.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + aventureiro.velocidade) / gp.tamanhoQuadrados;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    aventureiro.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - aventureiro.velocidade) / gp.tamanhoQuadrados;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    aventureiro.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + aventureiro.velocidade) / gp.tamanhoQuadrados;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    aventureiro.collisionOn = true;
                }
                break;
        }
    }
}
