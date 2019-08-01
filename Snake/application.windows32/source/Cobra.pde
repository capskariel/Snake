class Cobra{
 
  ArrayList<Bloco> snake = new ArrayList<Bloco>();
  Direcao direcao = Direcao.ESQUERDA;
  
  public Cobra(){
   for(int i = 5; i > 0; i--)
     snake.add(new Bloco(width / largura / 2, height / largura / 2 + i));     
  }
  
  void mostraSnake(){
    stroke(255);
    for(Bloco bloco : snake)
      rect(bloco.posX * largura, bloco.posY * largura, largura, largura);
    stroke(0);
  }
  
  void moveSnake(){
           
      if(direcao == Direcao.CIMA)
        moveCima();
      if(direcao == Direcao.DIREITA)
        moveDireita();
      if(direcao == Direcao.ESQUERDA)
        moveEsquerda();
      if(direcao == Direcao.BAIXO)
        moveBaixo();
    
      
      
  }  
  
  void moveCima(){  
    snake.add(new Bloco(snake.get(snake.size() - 1).posX,snake.get(snake.size() - 1).posY - 1));
  }
  
  void moveBaixo(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX,snake.get(snake.size() - 1).posY + 1));
  }
  
  void moveDireita(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX + 1,snake.get(snake.size() - 1).posY));
  }
  
  void moveEsquerda(){
    snake.add(new Bloco(snake.get(snake.size() - 1).posX - 1,snake.get(snake.size() - 1).posY));
  }
  
  boolean confereComida(Comida comida){
    return(snake.get(snake.size()-1).posX == comida.getPosX() && snake.get(snake.size() -1).posY == comida.getPosY());
  }
}  
  
