package application;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;



/**
 * 
 * A Cat class representing a cat in the family tree.
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

public class Cat implements Animal <Cat>

{

	protected String name;

	protected LocalDate birthDate;

	protected Gender gender;

	// ************** VG - KOMMENTAR ASSOCIATION ******************
	// Every object of cat can hold a multiple sets of cats
	// these objects can exist by them self if you destroy one
	// Cat lets say a parent, the child will still exist. In Aggregation
	// the contained objects life cycle is tied to the container objects
	// life cycle, this is not the case here and therefore this is a
	// good example of Association.
	//
	// Composition is a special case of aggregation. When the contained object
	// cannot exist without the existence of container object.

	protected Set<Cat> siblings;

	protected Set<Cat> children;

	protected Set<Cat> parents;

	protected Cat mate;

	// ************** VG - KOMMENTAR OVERLOAD & POLYMORPHISM ******************
	// This overloaded constructor isn't used any more, it was used for testing
	// of the family tree logic before i heaved a graphical interface in FX.
	// It is kept for showing what overload can look like and future changes.
	// in my program.
	// In Java it you can have two or more methods within the same
	// class that share the same name, as long as their parameter declarations
	// are different. When this is the case, the methods are said to be
	// OVERLOADED. Method overloading is one of the ways that Java implements
	// Polymorphism.

	/**
	 * The constructor takes the cat's name as a parameter.
	 * 
	 * @param name
	 *            Name of the cat.
	 */

	public Cat(String name) {

	}

	/**
	 * The constructor takes the cat's name as a parameter. and birthDate as a
	 * local date.
	 * 
	 * @param name
	 *            Name of the cat.
	 * 
	 * @param birthDate
	 *            day that the cat was born.
	 */
	
	public Cat(String name, LocalDate birthDate) {

		this.name = name;
		this.birthDate = birthDate;

	}

	public String getName()

	{

		return name;

	}

	public void setName(String name)

	{

		this.name = name;

	}

	public LocalDate getBirthDate()

	{

		return birthDate;

	}

	public void setBirthDate(LocalDate birthDate)

	{

		this.birthDate = birthDate;

	}

	public Gender getGender()

	{

		return gender;

	}

	public void setGender(Gender gender)

	{

		this.gender = gender;

	}

	public Cat getMate()

	{

		return mate;

	}

	public void setMate(Cat mate)

	{

		this.mate = mate;

	}

	public void setParents(Set<Cat> parent) {

		this.parents = parent;

	}

	public Set<Cat> getParents() {

		return parents;

	}

	public Set<Cat> getSiblings() {

		return siblings;

	}

	public Set<Cat> getChildren() {

		return children;

	}

	public Cat getMother() {

		if (parents != null) {

			for (Cat parent : parents) {

				if (parent instanceof FemaleCat) {

					return parent;

				}

			}

		}

		return null;

	}

	public Cat getFather() {

		if (parents != null) {

			for (Cat parent : parents) {

				if (parent instanceof MaleCat) {

					return parent;

				}

			}

		}

		return null;

	}

	/**
	 * 
	 * Add mate and also transfer to the children.
	 * 
	 * 
	 * @param mate
	 *            the mate that should be added.
	 * 
	 */
	
	
	public void addMate(Cat mate)

	{

		this.mate = mate;

		if (mate.getMate() == null) {

			mate.addMate(this);

		}

		Set<Cat> thisChildren = this.children;

		Set<Cat> mateChildren = mate.getChildren();

		// Mate has kids and this doesn't
		if (thisChildren == null && mateChildren != null) {

			copyChildren(mateChildren, this);
			// If this has kids and mate doesn't
		} else if (thisChildren != null && mateChildren == null) {

			copyChildren(thisChildren, mate);

		} else if (thisChildren != null && mateChildren != null) {

			if (thisChildren.size() > mateChildren.size()) {

				copyChildren(thisChildren, mate);

			} else if (thisChildren.size() < mateChildren.size()) {

				copyChildren(mateChildren, this);

			}

		}

	}

	/**
	 * This method is private but is commented for clarity.
	 * Copy the set of children to a cat.
	 * 
	 * @param childrenToCopy
	 *            Children to copy
	 * 
	 * @param toAnimal
	 *            the target cat.
	 * 
	 */

	private void copyChildren(Set<Cat> childrenToCopy, Cat toAnimal) {

		for (Cat child : childrenToCopy) {

			toAnimal.addChild(child);

		}

	}

	/**
	 * 
	 * Set this cat as child of parent. also set the same for this cat's
	 * siblings. also add this child to parent as a child.
	 * 
	 * @param parent
	 *            the parent to add.
	 * 
	 */

	public void addParent(Cat parent) {

		// checks if parents is empty
		if (parents != null) {

			if (!isAlreadyAdded(parents, parent) && parents.size() < 2) {

				parents.add(parent);

			} else {

				return;

			}
			// If not empty reset parents and add new parent.
		} else {

			parents = new HashSet<Cat>(2);

			parents.add(parent);

		}

		Set<Cat> tempSiblings = getSiblings();

		if (tempSiblings != null)

			for (Cat sib : tempSiblings) {

				sib.addParent(parent);

			}

		parent.addChild(this);

		if (parent.getMate() != null) {

			parent.getMate().addChild(this);

		}

	}

	/**
	 * 
	 * Add cat as a sibling also add cat as a child to this cat's parent.
	 * 
	 * @param animal
	 *            Cat that will be the new sibling.
	 * 
	 */

	public void addSibling(Cat animal) {

		if (siblings != null) {

			if (!isAlreadyAdded(siblings, animal)) {

				siblings.add(animal);

				for (Cat sib : siblings) {

					if (!sib.equals(animal))

						sib.addSibling(animal);
				}

			} else {

				return;

			}

		} else {

			siblings = new HashSet<Cat>();

			siblings.add(animal);

		}

		if (this.parents != null) {

			if (null != animal.getParents()) {

				if (this.parents.size() > animal.getParents().size()) {

					animal.setParents(this.parents);

				} else if (this.parents.size() < animal.getParents().size()) {

					this.setParents(animal.parents);

				}

			} else {

				for (Cat parent : this.parents) {

					animal.addParent(parent);

				}

			}

		}

		animal.addSibling(this);

	}

	/**
	 * This method is private but is commented for clarity. 
	 * Check if the cat is already there in the set of relations.
	 * 
	 * @param relations
	 *            a set that need to be checked for a cat
	 * 
	 * @param animal
	 *            the cat that are going to be tested with the set.
	 * 
	 * @return boolean Return true if already in the set.
	 * 
	 */

	private boolean isAlreadyAdded(Set<Cat> relations, Cat animal)

	{

		boolean isAdded = false;

		for (Cat catToCheck : relations) {

			if (catToCheck.equals(animal)) {

				isAdded = true;

				break;

			}

		}

		return isAdded;

	}

	/**
	 * 
	 * Add a cat to parent and parent's mate as a child and and if parent has
	 * child's add the cat to siblings.
	 * 
	 * 
	 * @param animal
	 *            the cat that is a child.
	 * 
	 */

	public void addChild(Cat animal) {

		if (children != null) {

			if (!isAlreadyAdded(children, animal)) {

				Cat[] siblings = this.getChildren().toArray(new Cat[this.getChildren().size()]);

				siblings[0].addSibling(animal);

				children.add(animal);

			} else {

				return;

			}

		} else {

			children = new HashSet<Cat>();

			children.add(animal);

		}

		animal.addParent(this);

		if (this.getMate() != null) {

			this.getMate().addChild(animal);

		}

	}

	// ************ VG - KOMMENTAR, OVERRIDE POLYMORPHISM *******************
	// This method is override because i have made my one method
	// that suites my purpose better. I need to print out the signs for Male and
	// Female
	// and that is not implemented in the superclass's method. Thats why i
	// override the
	// superclass Object's toString() method
	// When the overriding method has the same name, number and
	// type of parameters, and return type as the method that it overrides.
	// Method overriding is one of the ways that Java implements Polymorphism.

	/**
	 * Overrided to toString method prints out the Name of the object and signs
	 * for Male and Female
	 * 
	 * 
	 */

	@Override
	public String toString()

	{

		if (getName() != null) {

			Gender key = getGender();

			switch (key) {
			case FEMALE:

				return getName() + " \u2640";

			case MALE:

				return getName() + " \u2642";

			default:
				break;
			}

		}

		return super.toString();

	}

	

}
