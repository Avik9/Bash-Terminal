/**
 *
 *
 *  This class creates a DirectoryNode object where each node object represents a node in a tree.
 *
 */

public class DirectoryNode {

    String name; // name of the file
    DirectoryNode left; // left most node
    DirectoryNode middle; // middle node
    DirectoryNode right; // right most node
    DirectoryNode parent; // parent of the node
    Boolean isFile; // boolean that holds if the node is a boolean

    /**
     * Cinstructor for the object
     */
    public DirectoryNode()
    {
        name = ""; // name of the object
        left = middle = right = parent = null; // makes the left, middle, right, and the parent null
        isFile = false;
    }

    /**
     * Another constructor for the object
     *
     * @param assignedName
     *      Name of the object
     *
     * @param isItAFile
     *      Checks if it is a file or not
     *
     * @param currentParent
     *      The parent of the node
     */
    public DirectoryNode(String assignedName, boolean isItAFile, DirectoryNode currentParent)
    {
        name = assignedName;
        isFile = isItAFile;
        parent = currentParent;
        left = middle = right = null;
    }

    /**
     * Returns the name of the node
     *
     * @return
     *      String with the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the node to the given name
     *
     * @param name
     *      New name of the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the left child of the node
     *
     * @return
     *      DirectoryNode with the left child of the node
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * Changes the left node to the given left node
     *
     * @param left
     *      Add the node to the left
     */
    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    /**
     * Returns the middle child of the node
     *
     * @return
     *      DirectoryNode with the left child of the node
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     * Changes the middle node to the given left node
     *
     * @param middle
     *      Add the node to the middle
     */
    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }

    /**
     * Returns the right child of the node
     *
     * @return
     *      DirectoryNode with the left child of the node
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Changes the right node to the given left node
     *
     * @param right
     *      Add the node to the right
     */
    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    /**
     * Gets the parent of the current node.
     *
     * @return
     *      Returns the parent node
     */
    public DirectoryNode getParent()
    {
        return this.parent;
    }

    /**
     * Adds a child to the current node.
     *
     * @param newChild
     *      Adds this child to the current node
     *
     * @throws FullDirectoryException
     *      Throws this exception when there is no more space.
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException
    {
        if(this.getLeft() == null)
        {
            this.setLeft(newChild);
        }

        else if(this.getMiddle() == null)
        {
            this.setMiddle(newChild);
        }

        else if(this.getRight() == null)
        {
            this.setRight(newChild);
        }

        else {
           throw new FullDirectoryException();
        }
    }
}
