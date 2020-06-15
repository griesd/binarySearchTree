import java.util.ArrayList;
/**
 * This is a class that creates and accesses nodes that store a value, as well as three pointers:
 * parent, left child, and right child. The class has methods to set each pointer, set the value of nodes,
 * and access the pointers and values of the nodes.
 * @author Daniel Gries
 */
public class Node {
    private String value;
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    ArrayList firstNames;
    private boolean active;

    /**
     * Constructor that sets the value to the parameter entered, and all three pointers to null
     *
     * @param value of Node
     */
    public Node(String value) {
        this.value = value;
        parent = null;
        leftChild = null;
        rightChild = null;
        firstNames = new ArrayList<>();
        active = true;
    }

    /**
     * Accessor for value of Node
     *
     * @return E value
     */
    public String getValue() {
        return value;
    }

    /**
     * returns parent node
     *
     * @return Node parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * returns leftChild node
     *
     * @return Node leftChild
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * returns rightChild Node
     *
     * @return Node rightChild
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * mutator that sets the parent node to parameter passed
     *
     * @param parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * mutator that sets the leftChild node to parameter passed
     *
     * @param leftChild
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * mutator that sets the rightChild node to parameter passed
     *
     * @param rightChild
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * mutator that sets a value for a Node
     */
    public void setValue(String s){
        value = s;
    }

    /**
     * returns the arrayList of first names for a Node
     * @return Array List
     */
    public ArrayList getFirstNames(){
        return firstNames;
    }

    /**
     * Sets an arrayList of first names for a Node
     * @param names firstNames list
     */
    public void setFirstNames(ArrayList names){
        firstNames = names;
    }


    /**
     * mutator that adds a first name to the bank of names within a Node
     *
     * @param name first name of member
     */
    public void addFamilyMember(String name) {
        firstNames.add(name);
    }


    /**
     * returns a string of the last and first names of the family of a particular node
     * @return last name followed by all first names
     */
    public String toString(){
        String printStr = "";
        printStr = "[ " + value + ": ";
        for(int i = 0; i<firstNames.size(); i++){
            printStr = printStr + firstNames.get(i) + " ";
        }
        return printStr + "]";
    }

    /**
     * Mutator that removes a member from the tree
     * @param firstName
     * @return
     */
    public void addFirstName(String firstName){
        firstNames.add(firstName);
    }


    /**
     * figures out what children a Node has. Will return 0 if it has no children,
     * 1 if it has a left Child, 2 if it has a right child, and 3 if it has both
     * @return number correlating to children
     */
    public int children(){
        int counter = 0;
        if(getRightChild()!= null){
            counter++;
            counter++;
        }
        if(getLeftChild()!= null){
            counter++;
        }
        return counter;
    }
}
