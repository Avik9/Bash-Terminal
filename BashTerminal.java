/**
 *
 *
 *  This is a main class where a tree is created from different DirectoryNode objects
 *  by using a Bash Terminal to manage the tree.
 *
 */

import java.util.Scanner;

public class BashTerminal
{
    static boolean keepRunning = true; // boolean used to keep the loop running
    static Scanner sc = new Scanner(System.in); // Scanner to take the input from the user
    static String input; // Scanner takes in and stores in this String
    static DirectoryTree mainTree = new DirectoryTree(); // main tree that holds the entire system

    /**
     *  The main class of the system that connects the bash terminal to the tree data structure.
     */
    public static void main(String[] args)
    {
        System.out.println("Starting Bash Terminal");

        while(keepRunning)
        {
            System.out.print("[aaku@PinkHaven]: $ ");
            input =   sc.nextLine();

            if(input.equals("exit"))
            {
                System.out.println("It was a pleasure working with you today!");

                keepRunning = false;
            }

            if(input.equals("pwd"))
            {
                System.out.println(mainTree.presentWorkingDirectory());
            }


            if(input.equals("ls -R"))
            {
                mainTree.printDirectoryTree();
            }

            else if(input.equals("ls"))
            {
                System.out.println(mainTree.listDirectory());
            }

            if(input.equals("cd .."))
            {
                if(mainTree.cursor == mainTree.root)
                {
                    System.out.println("ERROR: Already at root directory");
                }
                else {
                    mainTree.cursor = mainTree.cursor.getParent();
                }
            }

            else if(input.equals("cd /"))
            {
                mainTree.resetCursor();
            }

            else if(input.contains("cd "))
            {
                try
                {
                    if(input.contains("/"))
                    {
                        String path = input.substring(3);

                        String[] a = path.split("/");

                        mainTree.cursor = mainTree.root;

                        for(int i = 1; i < a.length; i++)
                        {
                            mainTree.changeDirectory(a[i]);
                        }
                    }

                    else

                        mainTree.changeDirectory(input.substring(input.indexOf("cd ") + 3));
                }

                catch(NotADirectoryException n)
                {
                    System.out.println("ERROR: Cannot change directory into a file");
                }

            }

            if(input.contains("mkdir "))
            {
                try
                {
                    mainTree.makeDirectory(input.substring(input.indexOf("mkdir ") + 6));
                }

                catch(FullDirectoryException f)
                {
                    System.out.println("ERROR: Present directory is full.");
                }
            }

            if(input.contains("touch "))
            {
                try
                {
                    mainTree.makeFile(input.substring(input.indexOf("touch ") + 6));
                }

                catch(FullDirectoryException f)
                {
                    System.out.println("ERROR: Present directory is full.");
                }
            }

            if(input.contains("mv"))
            {
                try {


                    String source = input.substring(input.indexOf(" ") + 1, input.lastIndexOf(" "));

                    String[] a = source.split("/");

                    mainTree.cursor = mainTree.root;

                    for (int i = 1; i < a.length; i++) {
                        mainTree.changeDirectory(a[i]);
                    }

                    DirectoryNode tempNode = mainTree.cursor;
                    mainTree.cursor = null;

                    String destination = input.substring(input.lastIndexOf(" ") + 1);

                    a = destination.split("/");

                    mainTree.cursor = mainTree.root;

                    for (int i = 1; i < a.length; i++) {
                        mainTree.changeDirectory(a[i]);
                    }

                    mainTree.cursor.addChild(tempNode);
                }

                catch(FullDirectoryException f)
                {
                    System.out.println("ERROR: Present directory is full.");
                }

                catch(NotADirectoryException n)
                {
                    System.out.println("ERROR: Cannot change directory into a file");
                }
            }
        }
    }
}
