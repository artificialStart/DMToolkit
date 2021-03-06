package dmtoolkit;

import java.io.Serializable;

public abstract class Entity implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	public String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    public abstract void readIn();
    
    public abstract void display();
}
