package snake;

import java.util.Random;

public class Comida extends Ponto {

    public Comida(){

        super();
        setRandom();
    }

    public void setRandom(){

        Random random = new Random();
        int posX = random.nextInt(52);
        int posY = random.nextInt(51);

        this.setPar(posX, posY);
    }
}
