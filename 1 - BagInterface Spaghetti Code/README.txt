Currently Added:
BagInterface.java
ArrayBag.java
LinkedBag.java

Methods:
public boolean add(T newEntry); //Adds an entry 
				//NOTE: ArrayBag's add method automatically updates the size

public T remove(); //Removes the most recent entry

public boolean remove(T anEntry); //Removes a desired entry

public T[] toArray(); //Turns the Bag into a array

public boolean isEmpty(); //Checks if its empty

public int getCurrentSize(); //Gets the current size of the bag

public int getFrequencyOf(T anEntry); //Gets the Frequency of an Entry

public void clear(); //Clears the bag

public boolean contains(T anEntry); //Checks if an entry is in the bag

public boolean addEnd(T addEntry); //Adds to the end of the bag
				   //NOTE: ONLY FOR LINKED BAG

Remember, All of my code is spaghetti code, it may be a little messy
and slow. Eventhough the code works, the professor may not like how
I coded each of the methods in the interfaces.

You are free to edit and use my source code all you like.

-Henry