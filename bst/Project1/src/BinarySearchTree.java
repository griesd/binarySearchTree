/**
 * BinarySearchTree is a class that implements a Binary Tree storage system with linked Nodes.
 * This class has one parameter, which is the groot. This is the only node in the Class without a parent
 */
public class BinarySearchTree {
    Node groot;
    int size;
    boolean switcher;

    public BinarySearchTree() {
        groot = null;
        size = 0;
        switcher = true;
    }

    /**
     * method that returns the node of a searched name if present.
     * if not present, returns null
     * Prints number of people in family of that name
     *
     * @param value name
     * @return node of name
     */
    public Node search(String value) {
        if (groot == null) {
            return null;
        } else {


            return searchHelper(value, groot);
        }
    }

    /**
     * heper method for search. recursively searches tree for specific node
     *
     * @param value   name searched
     * @param current the node being examined
     * @return the node where the name is found
     */
    private Node searchHelper(String value, Node current) {
        if (current == null) { //checks if current is null
            return null;
        } else if (current.getValue().equals(value)) { //checks if value matches

                return current;
        } else if (current.getValue().compareTo(value) > 0) { //checks if entered value is less than node value
            return (searchHelper(value, current.getLeftChild()));
        } else if (current.getValue().compareTo(value) < 0) { // checks if entered value is more than node value
            return searchHelper(value, current.getRightChild());
        } else {
            System.out.println("0");
            return null;
        }
    }

    /**
     * returns the groot of the tree
     *
     * @return root node
     */
    public Node getGroot() {
        return groot;
    }

    /**
     * Enters name into BST. Sets value as value typed and parent to corresponding node
     * children are set as null
     *
     * @param value name entered
     */
    public void insertName(String value, String firstName) {

        if (groot == null) { //checks if root is null
            Node temp = new Node(value);
            groot = temp;
            size++;
            groot.addFirstName(firstName);
        } else {
            insertHelper(value, firstName, groot);
            size++;
        }
    }

    /**
     * Helper for entering node into BST. Checks tree for correct spot to put node
     *
     * @param value   name to be entered
     * @param current node being analyzed
     */
    private void insertHelper(String value, String firstName, Node current) {
        if (current.getValue().compareTo(value) > 0) {//checks if value of name is less than value of node name
            if (current.getLeftChild() == null) {
                Node temp = new Node(value);
                temp.setParent(current);
                current.setLeftChild(temp);
                temp.addFirstName(firstName);
            } else {
                insertHelper(value, firstName, current.getLeftChild());
            }
        } else if (current.getValue().compareTo(value) < 0) { //checks if value is greater than value of node name
            if (current.getRightChild() == null) {
                Node temp = new Node(value);
                temp.setParent(current);
                current.setRightChild(temp);
                temp.addFirstName(firstName);
            } else {
                insertHelper(value, firstName, current.getRightChild());
            }
        } else if (current.getValue().equals(value)) {
            current.addFirstName(firstName);
        }

    }

    /**
     * returns number of nodes in Tree
     *
     * @return number of names in tree
     */
    public int getSize() {
        return size;
    }

    /**
     * overriding toString method that prints in alphabetical order
     *
     * @return string of names
     */
    public String toString() {
        return traversal(groot);
    }

    /**
     * traverses through the BST and adds node values to String for toString method
     *
     * @param current node being analyzed
     * @return String
     */
    private String traversal(Node current) {
        String printStr = "";

        if (current != null) {
            printStr = printStr + traversal(current.getLeftChild());
            printStr = printStr + current.toString() + "\n" ;
            printStr = printStr + traversal(current.getRightChild());
        }

        return printStr;
    }

    /**
     * deletion of nodes in tree. Has three cases
     * node to be deleted has no children
     * node to be delete has one child
     * node to be deleted has two child
     *
     * @param value name to delete
     */
    public boolean removeName(String value) {
        Node deleteNode = search(value);
        Node tempParent = deleteNode.getParent();
        if (deleteNode != null) { //Checks if the  node searched for is found
            if(deleteNode.children() == 0){ //checks if node has no children
                if(tempParent.getLeftChild() == deleteNode){
                    tempParent.setLeftChild(null);
                }
                else{
                    tempParent.setRightChild(null);
                }
                return true;
            }
            else if(deleteNode.children()== 1){ //checks if node has left child
                Node tempChild = deleteNode.getLeftChild();
                if(tempParent.getLeftChild() == deleteNode){
                    tempParent.setLeftChild(tempChild);
                    tempChild.setParent(tempParent);
                }
                else{
                    tempParent.setRightChild(tempChild);
                    tempChild.setParent(tempParent);
                }
                return true;
            }
            else if(deleteNode.children() == 2){//checks if node has right child
                Node tempChild = deleteNode.getRightChild();
                if(tempParent.getLeftChild() == deleteNode){
                    tempParent.setLeftChild(tempChild);
                    tempChild.setParent(tempParent);
                }
                else{
                    tempParent.setRightChild(tempChild);
                    tempChild.setParent(tempParent);
                }
                return true;
            }
            else if(deleteNode.children() == 3){ //checks if node has two children
                if(switcher == true){ //checks if it should do successor
                    Node replacer = findSuccessor(deleteNode.getRightChild());
                    if(replacer.getRightChild() != null){ //checks if successor has a right child
                        Node tempPar = replacer.getParent();
                        Node tempKid = replacer.getLeftChild();
                        tempKid.setParent(tempPar);
                        tempPar.setLeftChild(tempKid);
                        deleteNode.setValue(replacer.getValue());
                        deleteNode.setFirstNames(replacer.getFirstNames());
                    }
                    else{ //successor has no right child
                        replacer.getParent().setRightChild(null);
                        deleteNode.setValue(replacer.getValue());
                        deleteNode.setFirstNames(replacer.getFirstNames());
                    }

                }
                else{ //if it is predecessor
                    Node replacer = findPredecessor(deleteNode.getLeftChild());
                    if(replacer.getLeftChild() != null){ //checks if predecessor has left child
                        Node tempPar = replacer.getParent();
                        Node tempKid = replacer.getLeftChild();
                        tempKid.setParent(tempPar);
                        tempPar.setRightChild(tempKid);
                        deleteNode.setValue(replacer.getValue());
                        deleteNode.setFirstNames(replacer.getFirstNames());
                    }
                    else{ //if predecessor has no child
                        replacer.getParent().setLeftChild(null);
                        deleteNode.setValue(replacer.getValue());
                        deleteNode.setFirstNames(replacer.getFirstNames());
                    }
                }
                return true;
            }

        } else {
            System.out.println("This name is not in the Search Tree");
            return false;
        }

        return false;
    }

    /**
     * finds the largest node in the current node subtree
     * @param current
     * @return
     */
    private Node findPredecessor(Node current){
        if (current.getRightChild() != null){
            return findPredecessor(current.getRightChild());
        }
        else{
            switcher = false;
            return current;
        }
    }

    /**
     * This is designed to find the smallest node in the current node subtree
     * @param current
     * @return
     */
    private Node findSuccessor(Node current){
        if(current.getLeftChild() != null){
            return findSuccessor(current.getLeftChild());

        }
        else{
            switcher = true;
            return current;
        }
    }

    /**
     * adds one to size of search tree
     */
    public void addToCount(){
        size++;
    }
}
