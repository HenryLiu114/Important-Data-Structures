Currently Added:
SetInterface.java
ArraySet.java
LinkedSet.java

Methods:
public boolean add(T newEntry); //Adds an entry 
				//NOTE: ArraySet's add method automatically updates the size
					Also, you cannot add a item that already exists in the set

public T remove(); //Removes the most recent entry

public boolean remove(T anEntry); //Removes a desired entry

public T[] toArray(); //Turns the Bag into a array

public SetInterface<T> union (SetInterface<T> rhs); //Combines two sets with each other
    
public SetInterface<T> intersection(SetInterface<T> rhs); //Takes what the two sets have in common

Remember, All of my code is spaghetti code, it may be a little messy
and slow. Eventhough the code works, the professor may not like how
I coded each of the methods in the interfaces.

You are free to edit and use my source code all you like.

-Henry