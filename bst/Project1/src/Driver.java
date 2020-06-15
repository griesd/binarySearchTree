import java.util.Scanner;
import java.io.File;

public class Driver {

    BinarySearchTree bST;
    boolean finished;

    public Driver(){
        bST = new BinarySearchTree();
        finished = false;
    }

    public static void main(String[] args) {
    Driver runProgram = new Driver();
    runProgram.runActions();

    }

    /**
     * Runs the loop that gets the input and keeps program running
     */
    public void runActions(){
        printInstructions();

         while(!finished){
             getInput();
         }
         System.out.println("You have quit the program");
    }

    /**
     * gets the input from user. grabs the words entered and calls command on them.
     */
    public void getInput() {
        System.out.println("Please input a command");
        System.out.println("> ") ;
        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine();
        String word1 = null;
        String word2 = null;
        String word3 = null;
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()){//checks for each word
            word1 = tokenizer.next();
            if(tokenizer.hasNext()){
                word2 = tokenizer.next();
                if(tokenizer.hasNext()){
                    word3 = tokenizer.next();
                }
            }
        }
        command(word1, word2, word3);


    }

    /**
     * uses the words grabbed from the input to run the commands
     * @param w1 command
     * @param w2 name
     * @param w3 name
     */
    public void command(String w1, String w2, String w3){
        if(isCommand(w1)){
            if(w1.equals("add")){ //if the command is add
                bST.insertName(w3, w2);
                System.out.println("You have added " + w2 + ", " + w3);
            }
            else if(w1.equals("remove")){ //if the command is remove
                boolean temp = bST.removeName(w2);
                if (temp) {
                    System.out.println(w2 + " has been removed from the structure.");
                }
                else{
                    System.out.println(w2 + ", " + w3 + "Was not found in the structure");
                }
            }
            else if(w1.equals("search")){ //if the command is search
                System.out.println(bST.search(w2));
            }
            else if(w1.equals("size")){ //command is size
                System.out.println("There are " + bST.getSize() + "Families in the building.");
            }
            else if(w1.equals("print")){//if the command is print
                System.out.println(bST);
            }
            else if(w1.equals("quit")){//if the command is quit
                finished = true;
            }
            else{
                System.out.println("That was not a valid command. Try again.");
            }
        }

    }

    /**
     * checks if the word is command word
     * @param word
     * @return boolean
     */

    public boolean isCommand(String word){
        String commands = "add remove search size print quit";
        if(commands.contains(word)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * prints the instructions for the input
     */
    public void printInstructions(){
        System.out.println("This is a the structure that has all the names of people in the building");
        System.out.println("The following command words are valid:");
        System.out.println("Add (name), remove (name), size, search (name), print, quit");
    }

    private void readInputFileName (BinarySearchTree foo) throws Exception {
     // Read in the contents of the data file to seed the registry
        File file = new File("names.txt");
        Scanner reader, tokenizer;
        String inputLine, lastName, firstName;
        Node temp;
        Boolean survivorAdded;

        reader = new Scanner(file);
        while (reader.hasNextLine()) {
            inputLine = reader.nextLine();

// Parse input expression
            tokenizer = new Scanner(inputLine);
            lastName = tokenizer.next();
            firstName = tokenizer.next();
            bST.insertName(firstName , lastName);
            bST.addToCount();
        }
    }


}
