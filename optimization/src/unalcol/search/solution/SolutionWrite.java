package unalcol.search.solution;

import java.io.Writer;

import unalcol.io.Write;
import unalcol.types.tag.TaggedObject;
import unalcol.search.Goal;
import unalcol.services.Service;

public class SolutionWrite<T> implements Write<TaggedObject<T>> {
	protected boolean write_object;
	protected Write<T> tWrite=null;
	
	public SolutionWrite( boolean write_object ) {
		this.write_object = write_object;
	}
	
	public void setTWrite( Write<T> tWrite ){ this.tWrite=tWrite; }
	
	public void twrite( T obj, Writer out ) throws Exception{
		if( tWrite != null ) tWrite.write(obj, out);
		else Service.run(Write.name, obj, out);
	}
	
	@Override
	public void write(TaggedObject<T> sol, Writer out) throws Exception {
		Service.run(Write.name, sol.info(Goal.class.getName()), out);
		if( write_object ){
			out.write(' ');
			twrite(sol.object(), out);
		}
	}
}