class Comida extends Bloco{
 
  public Comida(int x, int y){
     super(x,y); 
  } 
  
  Comida geraComida(Cobra snake){
    int auxX, auxY;
    boolean auxRepete = false;
    Comida comida;
    auxX = int(random(width /largura - 1));
    auxY = int(random(height / largura - 1));
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
