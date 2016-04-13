package application;

import java.time.LocalDate;


/**
 * 
 * A FemaleCat class representing a cat in the family tree.
 *
 * 
 * 
 * @author Melker Holmgren
 * 
 * 
 *
 * 
 * 
 */


public class MaleCat extends Cat 

{
	/**
	 * The constructor takes the cat's name as a parameter. 
	 * 
	 * @param name
	 *            Name of the cat.
	 * 
	 */
		
	public MaleCat(String name) {

		super(name);

	}
	
	/**
	 * The constructor takes the cat's name as a parameter. and birthDate as a
	 * local date. this constructor also sets the Cats gender as male.
	 * 
	 * @param name
	 *            Name of the cat.
	 * 
	 * @param birthDate
	 *            day that the cat was born.
	 */

	public MaleCat(String name, LocalDate birthDate) {

		super(name, birthDate);
		super.setGender(Gender.MALE);
		
	}
	
	

}
