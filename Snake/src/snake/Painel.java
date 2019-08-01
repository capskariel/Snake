package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Painel extends JPanel implements KeyListener{

    private int altura;
    private int largura;
    private Comida comida;;
    private Snake snake;
    private Direcao direcao;
    
    Timer timer = new Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }		
    });

    public Painel(){
        timer.start();
        this.addKeyListener(this);
        
        comida = new Comida();
        snake = new Snake();
        direcao = Direcao.CIMA;
    }

    public void paintComponent(Graphics g){

        this.altura = 805;
        this.largura = 805;

        g.setColor(Color.BLACK);
        g.fillRect(0,0,altura,largura);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(comida.getPar()[0] * 15, comida.getPar()[1] * 15 ,15 , 15);

        for(Ponto ponto : snake.getSnake())
            g.fillRect(ponto.getPar()[0] * 15, ponto.getPar()[1] * 15 ,15 , 15);
        
        if(direcao == Direcao.DIREITA) {
	        if(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero() < 52)
	            snake.moveSnake(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero() +1, snake.getSnake().get(snake.getSnakeSize() -1).getPosUm());
	    }
        if(direcao == Direcao.CIMA) {
	        if(snake.getSnake().get(snake.getSnakeSize() -1).getPosUm() > 0)
	            snake.moveSnake(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero(), snake.getSnake().get(snake.getSnakeSize() -1).getPosUm() -1);
	    }
        if(direcao == Direcao.ESQUERDA) {
	        if(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero() > 0)
	            snake.moveSnake(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero() - 1, snake.getSnake().get(snake.getSnakeSize() -1).getPosUm());
	    }
        if(direcao == Direcao.BAIXO) {
	        if(snake.getSnake().get(snake.getSnakeSize() -1).getPosUm() < 51)
	            snake.moveSnake(snake.getSnake().get(snake.getSnakeSize() -1).getPosZero(), snake.getSnake().get(snake.getSnakeSize() -1).getPosUm() + 1);
	    }
        
        g.setColor(Color.BLACK);

        for(int i = 0; i <= this.altura; i+=15)
            g.drawLine(i, 0, i, this.altura);

        for(int i = 0; i <= this.largura; i+=15)
            g.drawLine(0, i, this.largura, i);        
        
    }

	@Override
	public void keyPressed(KeyEvent evento) {
		
		System.out.println(evento.getKeyCode());
		
		if(evento.getKeyCode() == KeyEvent.VK_UP) {
			direcao = Direcao.CIMA;
		}
		
		if(evento.getKeyCode() == KeyEvent.VK_LEFT) {
			direcao = Direcao.ESQUERDA;
		}
		
		if(evento.getKeyCode() == KeyEvent.VK_DOWN) {
			direcao = Direcao.BAIXO;
		}
		
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT) {
			direcao = Direcao.DIREITA;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
