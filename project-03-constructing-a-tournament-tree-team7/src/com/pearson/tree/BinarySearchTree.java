package com.pearson.tree;

import com.pearson.common.TreeNode;
import com.pearson.stack.Stack;
import com.pearson.util.InputUtil;

import java.io.IOException;

public class BinarySearchTree<T extends Comparable<T>, Y> {
    private TreeNode<T, Y> root;

    public BinarySearchTree() {
        root = null;
    }

    public TreeNode<T, Y> find(T key) {
        TreeNode<T, Y> current = root;
        while (current.getKey() != key) {
            if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(T key, Y data) {
        TreeNode<T, Y> newTreeNode = new TreeNode<>(key, data);
        if (root == null) {
            root = newTreeNode;
        } else {
            TreeNode<T, Y> current = root;
            TreeNode<T, Y> parent;
            while (true) {
                parent = current;
                if (key.compareTo(current.getKey()) < 0) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newTreeNode);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(newTreeNode);
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(T key) {
        TreeNode<T, Y> current = root;
        TreeNode<T, Y> parent = root;
        boolean isLeftChild = true;

        while (current.getKey().compareTo(key) != 0) {
            parent = current;
            if (key.compareTo(current.getKey()) < 0) {
                isLeftChild = true;
                current = current.getLeftChild();
            } else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            if (current == null) {
                return false;
            }
        }

        if (current.getLeftChild() == null &&
                current.getRightChild() == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        } else if (current.getRightChild() == null) {
            if (current == root) {
                root = current.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else if (current.getLeftChild() == null) {
            if (current == root) {
                root = current.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        } else {
            TreeNode<T, Y> successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }

            successor.setLeftChild(current.getLeftChild());
        }
        return true;
    }

    private TreeNode<T, Y> getSuccessor(TreeNode<T, Y> delNode) {
        TreeNode<T, Y> successorParent = delNode;
        TreeNode<T, Y> successor = delNode;
        TreeNode<T, Y> current = delNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != delNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(delNode.getRightChild());
        }

        return successor;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    private void preOrder(TreeNode<T, Y> localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.getKey() + " ");
            preOrder(localRoot.getLeftChild());
            preOrder(localRoot.getRightChild());
        }
    }

    private void inOrder(TreeNode<T, Y> localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.getLeftChild());
            System.out.print(localRoot.getKey() + " ");
            inOrder(localRoot.getRightChild());
        }
    }

    private void postOrder(TreeNode<T, Y> localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.getLeftChild());
            postOrder(localRoot.getRightChild());
            System.out.print(localRoot.getKey() + " ");
        }
    }

    public void displayTree() {
        Stack<T, TreeNode<T, Y>> globalStack = new Stack<>();
        globalStack.push(root.getKey(), root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (!isRowEmpty) {
            Stack<T, TreeNode<T, Y>> localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) {
                TreeNode<T, Y> temp = globalStack.pop().getData();
                if (temp != null) {
                    System.out.print(temp.getKey());
                    localStack.push(temp.getKey(), temp.getLeftChild());
                    localStack.push(temp.getKey(), temp.getRightChild());

                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null, null);
                    localStack.push(null, null);
                }

                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                TreeNode<T, Y> tTreeNodeLink = localStack.pop().getData();
                globalStack.push(tTreeNodeLink.getKey(), tTreeNodeLink);
            }
        }
        System.out.println("......................................................");
    }

    public static void main(String[] args) throws IOException {
        int value;
        BinarySearchTree<Integer, Double> theTree = new BinarySearchTree<>();

        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);

        while (true) {
            System.out.print("Enter first letter of show, ");
            System.out.print("insert, find, delete, or traverse: ");
            int choice = InputUtil.getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = InputUtil.getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = InputUtil.getInt();
                    TreeNode<Integer, Double> found = theTree.find(value);
                    if (found != null) {
                        System.out.print("Found: " + found);
                        System.out.print("\n");
                    } else {
                        System.out.print("Could not find ");
                    }
                    System.out.print(value + '\n');
                    break;
                case 'd':
                    System.out.print("Enter value to delete: ");
                    value = InputUtil.getInt();
                    boolean didDelete = theTree.delete(value);
                    if (didDelete) {
                        System.out.print("Deleted " + value + '\n');
                    } else {
                        System.out.print("Could not delete ");
                    }
                    System.out.print(value + '\n');
                    break;
                case 't':
                    System.out.print("Enter type 1, 2 or 3: ");
                    value = InputUtil.getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }
}
