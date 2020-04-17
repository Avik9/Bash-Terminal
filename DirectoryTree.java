/**
 *
 *
 *  This class creates a DirectoryTree object where a tree is made up of DirectoryNode objects.
 *
 */

public class DirectoryTree
{
    DirectoryNode root; // root of the tree
    DirectoryNode cursor; // cursor indicating where you are currently

    /**
     *  Constructor class for the object
     */
    public DirectoryTree()
    {
        root = new DirectoryNode("root", false, null);
        cursor = root;
    }

    /**
     *  Resets the cursor to the root.
     */
    public void resetCursor()
    {
        cursor = root;
    }

    /**
     *
     * Changes the directory to the given name
     *
     * @param newName
     *      Name of the directory to go to
     *
     * @throws NotADirectoryException
     *      Throws this exception when a dictory with the given name does not exist
     */
    public void changeDirectory(String newName) throws NotADirectoryException
    {
        try
        {
            if(cursor.getLeft() != null && cursor.getLeft().getName().equals(newName))
            {
                if(cursor.getLeft().isFile)
                {
                    throw new NotADirectoryException();
                }

                cursor = cursor.getLeft();
            }

            else if(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(newName))
            {
                if(cursor.getMiddle().isFile)
                {
                    throw new NotADirectoryException();
                }

                cursor = cursor.getMiddle();
            }

            else if(cursor.getRight() != null && cursor.getRight().getName().equals(newName))
            {
                if(cursor.getRight().isFile)
                {
                    throw new NotADirectoryException();
                }

                cursor = cursor.getRight();
            }

            else {
                throw new NotADirectoryException();
            }
        }

        catch(NotADirectoryException n)
        {
            System.out.println("ERROR: No such directory named '" + newName + "' exists");
        }
    }

    /**
     * Prints the current working directory.
     *
     * @return
     *      Returns a string with the working directory.
     */
    public String presentWorkingDirectory()
    {
        DirectoryNode traverser = cursor;

        String currentPath = "root";

        while(traverser.getParent() != null)
        {
            currentPath = currentPath.substring(0, 4) + "/" + traverser.getName() + currentPath.substring(currentPath.indexOf("root") + 4);

            traverser = traverser.getParent();
        }

        return currentPath;
    }

    /**
     * Prints the directories in the current node
     *
     * @return
     *      Returns a string with the names of the directories
     */
    public String listDirectory()
    {
        String ans = "";

        if(cursor.getLeft() != null)
        {
            ans += cursor.getLeft().getName() + " ";
        }

        if(cursor.getMiddle() != null)
        {
            ans += cursor.getMiddle().getName() + " ";
        }

        if(cursor.getRight() != null)
        {
            ans += cursor.getRight().getName() + " ";
        }

        return ans;
    }

    /**
     *  Prints the Entire tree by calling the helper method called printTree()
     */
    public void printDirectoryTree()
    {
        this.printTree(cursor, 0);
    }

    /**
     * Called recursively to print the entire tree
     *
     * @param currentNode
     *      The node from which you have to print the tree
     *
     * @param spaces
     *      Amount of spaces to indent
     */
    public void printTree(DirectoryNode currentNode, int spaces)
    {
        if(currentNode == null)
        {
            return;
        }

        if(!currentNode.isFile)
        {
            for(int i = 0; i < spaces; i++)
            {
                System.out.print(" ");
            }
            System.out.print("| - ");
        }

        else if(currentNode.isFile)
        {
            for(int i = 0; i < spaces; i++)
            {
                System.out.print(" ");
            }

            System.out.print("- ");
        }

        System.out.println(currentNode.getName());

        if(currentNode.isFile)
        {
            return;
        }

        if(!currentNode.isFile)
        {
            printTree(currentNode.getLeft(), spaces + 5);

            printTree(currentNode.getMiddle(), spaces + 5);

            printTree(currentNode.getRight(), spaces + 5);
        }
    }

    /**
     * Makes a directory with the given name
     *
     * @param name
     *      Name of the directory
     *
     * @throws IllegalArgumentException
     *      Thrown when the name contains " " (a space) or "/" (forward slash).
     *
     * @throws FullDirectoryException
     *      Thrown when the directory of the current node is full
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException
    {
        if(name.contains(" ") || name.contains("/"))
        {
            throw new IllegalArgumentException();
        }

        DirectoryNode newNode = new DirectoryNode(name, false, cursor);

        cursor.addChild(newNode);
    }

    /**
     * Makes a file with the given name
     *
     * @param name
     *      Name of the file
     *
     * @throws IllegalArgumentException
     *      Thrown when the name contains " " (a space) or "/" (forward slash).
     *
     * @throws FullDirectoryException
     *      Thrown when the directory of the current node is full
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException
    {
        if(name.contains(" ") || name.contains("/"))
        {
            throw new IllegalArgumentException();
        }

        DirectoryNode newFile = new DirectoryNode(name, true, cursor);

        cursor.addChild(newFile);
    }
}
