package unalcol.agents.examples.games.fourinrow.StochasticModels;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.games.fourinrow.FourInRow;

public class UtilityAgent implements AgentProgram {
    protected String color;
    public UtilityAgent( String color ){
        this.color = color;
    }
    public boolean debug = false;
    public final int ESPACIO = 0;
    public final int ROJA = 1;
    public final int AZUL = -1;

    public int n; //Tamaño del tablero

    @Override
    public Action compute(Percept p) {
        /*
        long time = (long)(20 * Math.random());

        //if (debug) System.out.println("Sleep time: "+time);

        try{
            Thread.sleep(time);
        }catch(Exception e){}

        //if (debug) System.out.println("Turn: "+p.getAttribute(FourInRow.TURN));



        if( p.getAttribute(FourInRow.TURN).equals(color) ){
            //Stores in n board size

            int n = Integer.parseInt((String)p.getAttribute(FourInRow.SIZE));

            //if (debug) System.out.println("Size = "+p.getAttribute(FourInRow.SIZE));

            //Chooses random board coordinates.
            /
            int i = (int)(n*Math.random());
            int j = (int)(n*Math.random());

            if (!(i==n-1) && debug) System.out.println("Status of: " + ((i+1)+":"+j) + ":" + p.getAttribute((i+1)+":"+j));

            boolean flag = (i==n-1) || !p.getAttribute((i+1)+":"+j).equals((String)FourInRow.SPACE);
            while( !flag ){
                i = (int)(n*Math.random());
                j = (int)(n*Math.random());
                flag = (i==n-1) || !p.getAttribute((i+1)+":"+j).equals((String)FourInRow.SPACE);
            }

            if(debug) System.out.println("Movement chosen " + i +":"+ j +":"+color);

            return new Action( i+":"+j+":"+color );

        }
        */
        //n almacena el tamaño del tablero
        if (n == 0)
            n = Integer.parseInt((String)p.getAttribute(FourInRow.SIZE));

        //return new Action(FourInRow.PASS);
        return accion(p,7);
    }

    @Override
    public void init() {
        n = 0;
    }

    /*
    * Recibe la posición horizontal y en que se va a depositar la ficha
    * Por ejemplo, si el tablero es de 8x8 y=0 deposita la ficha al extremo izquierdo
    * y y=7 deposita la ficha al extremo derecho, valores intermedios depositan la
    * ficha en lugares intermedios. La coordenada x se calcula automáticamente de acuerdo
    * a la las fichas que se encuentren allí.
     */
    public Action accion(Percept p, int y) {
        int x = 0;

        /*
        * Esto emula la caida de la ficha, cuando encuentra un obstáculo
        * (casilla != espacio) se detiene.
        */
        while (tablero(p, x + 1, y) == ESPACIO)
        {
            x++;
            System.out.println(x+", "+n);
            if (x >= n-1)
                break;
        }

        /*
        * No se pueden poner fichas en el aire, es necesario ponerlas sobre otra ficha
        * o en el "suelo" del tablero.
         */
        System.out.println("Acción: "+x+":"+y+":"+color);
        return new Action(x+":"+y+":"+color);
    }

    /*
     * Devuelve el estado de una casilla del tablero dadas unas coordenadas
     * Los estados posibles son ESPACIO, ROJA Y AZUL.
     */
    public int tablero(Percept p, int i, int j)
    {
        String casilla = (String)p.getAttribute(i+":"+j);

        System.out.println(casilla);

        if (casilla.equals("space"))
            return ESPACIO;
        else if (casilla.equals("black"))
            return ROJA;
        else
            return AZUL;
    }

    public int[] scanHorizontally()
    {
        return null;
    }


}
