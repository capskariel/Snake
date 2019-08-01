package snake;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Ponto> snake = new ArrayList<>();

    public Snake(){
        iniciaSnake();
    }

    public void iniciaSnake(){

        int posX = 25, posY = 25;

        for(int contador = 0; contador < 5; contador ++){

            Ponto ponto = new Ponto(posX, posY - contador);
            snake.add(ponto);
        }
    }

    public ArrayList<Ponto> getSnake() {
        return snake;
    }

    public void moveSnake(int posX, int posY){

        this.snake.remove(0);
        Ponto ponto = new Ponto(posX, posY);
        this.snake.add(ponto);
    }

    public int getSnakeSize(){

        return snake.size();
    }
}
