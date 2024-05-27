package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Bau extends SuperObject {
    public OBJ_Bau() {
        name = "bau";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/bau.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
