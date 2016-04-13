package application;



/**
 * 
 * A interface that implements the methods that all types of animals
 * need to have to use the family tree. This is to ensure future
 * add-ons for Dogs and other type of animals.
 * 
 * @author Melker Holmgren
 * 
 * 
 *
 */



public interface Animal<T> {


	
	
	public void addMate(T mate);
	
	public void addParent(T parent);
	
	public void addSibling(T animal);
	
	public void addChild(T animal);

	
	
}
