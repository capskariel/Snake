final int largura = 10;
Cobra snake;
int tempo = 14, timer = 15, pontos = 0;
Comida comida = new Comida(20, 10);
boolean auxMovimento = true;

void setup() {
  stroke(0);
  size(801, 801);
  background(0);
  snake = new Cobra();  
  comida = comida.geraComida(snake);
}

void draw() {

  fill(0);
  rect(0, 0, 801, 801);  
  fill(255);
  snake.mostraSnake();
  comida.show();
  desenhaLinhas(); 
  jogar();
  
  timer++;
}

void keyPressed() {

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

void desenhaLinhas(){
  for (int i = 0; i <= width / largura; i++)    
    line(i * largura, width, i * largura, 0);

  for (int i = 0; i <= height / largura; i++)    
    line(height, i * largura, 0, i * largura);
}

void jogar(){
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

void reduzTimer(){
   if(pontos % 5 == 0 && tempo > 0)
     tempo--;
}

void fimDeJogo(){
  rect(0, 0, height - 1, width - 1);
  textAlign(CENTER);
  fill(0);
  textSize(70);
  text("PONTOS: " + pontos, width/2, height/2);
  noLoop();
}

boolean bateuEmSiMesmo(){
  for(int c = snake.snake.size() - 5; c > 0; c--)
    if(snake.snake.get(snake.snake.size() - 1).posX == snake.snake.get(c).posX && snake.snake.get(snake.snake.size() - 1).posY == snake.snake.get(c).posY)
      return true;
  
  return false; 
}
