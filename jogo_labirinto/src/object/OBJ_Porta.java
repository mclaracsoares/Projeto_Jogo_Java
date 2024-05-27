package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Porta extends SuperObject {
    public OBJ_Porta() {
        name = "porta";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/porta.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

        // Define a solid area como 16x16
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
