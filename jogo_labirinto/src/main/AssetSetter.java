package main;

import object.OBJ_Key;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void steObejct() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 1 * gp.tamanhoQuadrados;
		gp.obj[0].worldY = 1 * gp.tamanhoQuadrados;
	}
	public void setObject() {
		// TODO Auto-generated method stub
		
	}

}


	