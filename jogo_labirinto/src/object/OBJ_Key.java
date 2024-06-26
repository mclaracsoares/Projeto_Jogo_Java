package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
