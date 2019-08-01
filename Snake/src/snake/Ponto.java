package snake;
public class Ponto {

    private int par[] = new int[2];

    public Ponto(int posX, int posY){
        this.par[0] = posX;
        this.par[1] = posY;
    }

    public Ponto(){
    }

    public int[] getPar(){

        return this.par;
    }
    public void setPar(int posX, int posY){

        this.par[0] = posX;
        this.par[1] = posY;
    }
    public int getPosZero(){

        return this.par[0];
    }

    public int getPosUm(){

        return this.par[1];
    }

}
