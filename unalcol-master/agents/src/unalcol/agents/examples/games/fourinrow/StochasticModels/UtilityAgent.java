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
    public boolean debug = true;

    @Override
    public Action compute(Percept p) {
        /*
        long time = (long)(20 * Math.random());

        //if (debug) System.out.println("Sleep time: "+time);

        try{
            Thread.sleep(time);
        }catch(Exception e){}

        //if (debug) System.out.println("Turn: "+p.getAttribute(FourInRow.TURN));

        */

        if( p.getAttribute(FourInRow.TURN).equals(color) ){
            //Stores in n board size
            int n = Integer.parseInt((String)p.getAttribute(FourInRow.SIZE));

            //if (debug) System.out.println("Size = "+p.getAttribute(FourInRow.SIZE));

            //Chooses random board coordinates.
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
        return new Action(FourInRow.PASS);
    }

    @Override
    public void init() {
    }
}
