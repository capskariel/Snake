import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Snake extends PApplet {

final int largura = 10;
Cobra snake;
int tempo = 15, timer = 15, pontos = 0;
Comida comida = new Comida(20, 10);
boolean auxMovimento = true;

public void setup() {
  stroke(0);
  
  background(0);
  snake = new Cobra();  
  comida = comida.geraComida(snake);
}

public void draw() {

  fill(0);
  rect(0, 0, 801, 801);  
  fill(255);
  snake.mostraSnake();
  comida.show();
  desenhaLinhas(); 
  jogar();
  
  timer++;
}

public void keyPressed() {

  if (key == CODED) {
    if (keyCode == UP && snake.direcao != Direcao.BAIXO && auxMovimento) {
      snake.direcao = Direcao.CIMA;
      auxMovimento = false;
    }

    if (keyCode == LEFT && snake.direcao != Direcao.DIREITA && auxMovimento) {
      snake.direcao = Direcao.ESQUERDA;
      auxMovimento = false;
    }

    if (keyCode == RIGHT && snake.direcao != Direcao.ESQUERDA && auxMovimento) {
      snake.direcao = Direcao.DIREITA;
      auxMovimento = false;
    }

    if (keyCode == DOWN && snake.direcao != Direcao.CIMA && auxMovimento) {
      snake.direcao = Direcao.BAIXO;
      auxMovimento = false;
    }
  }
}

public void desenhaLinhas(){
  for (int i = 0; i <= width / largura; i++)    
    line(i * largura, width, i * largura, 0);

  for (int i = 0; i <= height / largura; i++)    
    line(height, i * largura, 0, i * largura);
}

public void jogar(){
  if (timer % tempo == 0 && snake.snake.get(snake.snake.size() - 1).posY >= 0 && snake.snake.get(snake.snake.size() - 1).posY < width/largura && snake.snake.get(snake.snake.size() - 1).posX < width/largura && snake.snake.get(snake.snake.size() - 1).posX >= 0) {     

    snake.moveSnake();
    if(bateuEmSiMesmo())
      fimDeJogo();
    System.out.println("Pontos: " + pontos + ", Timer: " + timer + ", Tempo: " + tempo);

    if (snake.confereComida(comida)){
      pontos++;
      comida = comida.geraComida(snake);
      reduzTimer();
    }else
      snake.snake.remove(0);

    if (snake.snake.get(snake.snake.size() - 1).posY == -1 || snake.snake.get(snake.snake.size() - 1).posY == width/largura || snake.snake.get(snake.snake.size() - 1).posX == width/largura || snake.snake.get(snake.snake.size() - 1).posX == -1) {
      fimDeJogo();
    }
    auxMovimento = true;
  }
}

public void reduzTimer(){
   if(pontos % 7 == 0 && tempo > 0)
     tempo--;
}

public void fimDeJogo(){
  rect(0, 0, height - 1, width - 1);
  textAlign(CENTER);
  fill(0);
  textSize(70);
  text("PONTOS: " + pontos, width/2, height/2);
  noLoop();
}

public boolean bateuEmSiMesmo(){
  for(int c = snake.snake.size() - 5; c > 0; c--)
    if(snake.snake.get(snake.snake.size() - 1).posX == snake.snake.get(c).posX && snake.snake.get(snake.snake.size() - 1).posY == snake.snake.get(c).posY)
      return true;
  
  return false; 
}

class Bloco{
  
  int posX;
  int posY;
  
  public Bloco(int x, int y){   
   this.posX = x;
   this.posY = y;
  }  
  
  public int getPosX(){
   return this.posX; 
  }
  public int getPosY(){
   return this.posY; 
  }
     
}
class Cobra{
 
  ArrayList<Bloco> snake = new ArrayList<Bloco>();
  Direcao direcao = Direcao.ESQUERDA;
  
  public Cobra(){
   for(int i = 5; i > 0; i--)
     snake.add(new Bloco(width / largura / 2, height / largura / 2 + i));     
  }
  
  public void mostraSnake(){
    stroke(255);
    for(Bloco bloco : snake)
      rect(bloco.posX * largura, bloco.posY * largura, largura, largura);
    stroke(0);
  }
  
  public void moveSnake(){
           
      if(direcao == Direcao.CIMA)
        moveCima();
      if(direcao == Direcao.DIREITA)
        moveDireita();
      if(direcao == Direcao.ESQUERDA)
        moveEsquerda();
      if(direcao == Direcao.BAIXO)
        moveBaixo();
    
      
      
  }  
  
  public void moveCima(){  
    snake.add(new Bloco(snake.get(snake.size() - 1).posX,snake.get(snake.size() - 1).posY - 1));
  }
  
  public void moveBaixo(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX,snake.get(snake.size() - 1).posY + 1));
  }
  
  public void moveDireita(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX + 1,snake.get(snake.size() - 1).posY));
  }
  
  public void moveEsquerda(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX - 1,snake.get(snake.size() - 1).posY));
  }
  
  public boolean confereComida(Comida comida){
    return(snake.get(snake.size()-1).posX == comida.getPosX() && snake.get(snake.size() -1).posY == comida.getPosY());
  }
}  
  
class Comida extends Bloco{
 
  public Comida(int x, int y){
     super(x,y); 
  } 
  
  public Comida geraComida(Cobra snake){
    int auxX, auxY;
    boolean auxRepete = false;
    Comida comida;
    auxX = PApplet.parseInt(random(width /largura - 1));
    auxY = PApplet.parseInt(random(height / largura - 1));
    for(Bloco atual : snake.snake){
      if(auxX == atual.posX && auxY == atual.posY)
        auxRepete = true;
    }    
    if(auxRepete)
      comida = geraComida(snake);
    else
      comida = new Comida(auxX, auxY);
      
     return comida;
  }
  
  public void show(){
    stroke(255);
    rect(super.getPosX() * largura, super.getPosY() * largura, largura, largura); 
    stroke(0);
  }
}
enum Direcao{
 
  CIMA,
  BAIXO,
  DIREITA,
  ESQUERDA;
  
  private Direcao(){
    
  }
  
}
  public void settings() {  size(801, 801); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Snake" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
