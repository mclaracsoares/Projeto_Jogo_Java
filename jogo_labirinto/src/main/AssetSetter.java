package main;

import object.OBJ_Bau;
import object.OBJ_Key;
import object.OBJ_Porta;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 8 * gp.tamanhoQuadrados;
        gp.obj[0].worldY = 25 * gp.tamanhoQuadrados;

        gp.obj[1] = new OBJ_Porta();
        gp.obj[1].worldX = 34 * gp.tamanhoQuadrados;
        gp.obj[1].worldY = 33 * gp.tamanhoQuadrados;

        gp.obj[2] = new OBJ_Bau();  // Alterado o índice para 2
        gp.obj[2].worldX = 37 * gp.tamanhoQuadrados;
        gp.obj[2].worldY = 35 * gp.tamanhoQuadrados;
    }
}
