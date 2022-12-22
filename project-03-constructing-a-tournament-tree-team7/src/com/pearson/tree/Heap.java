package com.pearson.tree;

import com.pearson.util.InputUtil;
import com.pearson.common.Node;

import java.io.IOException;
import java.util.Vector;

public class Heap<T extends Comparable<T>, Y> {
    private Vector<Node<T, Y>> heapArray;
    private int currentSize;

    public Heap() {
        currentSize = 0;
        heapArray = new Vector<>();
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int parentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    public int leftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    public int rightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    public int insert(T key, Y data) {
        Node<T, Y> newNode = new Node<>(key, data);
        heapArray.add(newNode);
        int position = trickleUp(currentSize++);
        return position;
    }

    public Node<T, Y> remove() {
        if (isEmpty()) {
            return null;
        }
        Node<T, Y> root = heapArray.elementAt(0);
        heapArray.set(0, heapArray.elementAt(--currentSize));
        trickleDown(0);
        return root;
    }

    public int trickleUp(int index) {
        int parent = parentIndex(index);
        Node<T, Y> bottom = heapArray.elementAt(index);

        while (index > 0 && heapArray.elementAt(parent).getKey().compareTo(bottom.getKey()) < 0) {
            heapArray.set(index, heapArray.elementAt(parent));
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray.set(index, bottom);
        return index;
    }

    public boolean change(int index, T newKey) {
        if (index < 0 || index >= currentSize)
            return false;

        T oldValue = heapArray.elementAt(index).getKey();
        heapArray.elementAt(index).setKey(newKey);

        if (oldValue.compareTo(newKey) < 0) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    public Node<T, Y> elementAt(int index) {
        if (index < 0 || index >= currentSize) {
            return null;
        }

        return heapArray.elementAt(index);
    }

    // -------------------------------------------------------------
    public int trickleDown(int index) {
        int largerChild;
        Node<T, Y> top = heapArray.elementAt(index);
        while (index < currentSize / 2) {
            int leftChild = leftChildIndex(index);
            int rightChild = leftChild + 1;

            if (rightChild < currentSize &&
                    heapArray.elementAt(leftChild).getKey()
                            .compareTo(heapArray.elementAt(rightChild).getKey()) < 0) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getKey().compareTo(heapArray.elementAt(largerChild).getKey()) >= 0) {
                break;
            }

            heapArray.set(index, heapArray.elementAt(largerChild));
            index = largerChild;
        }
        heapArray.set(index, top);
        return index;
    }

    // -------------------------------------------------------------
    public void displayHeap() {
        System.out.print("heapArray: ");    // array format
        for (int m = 0; m < currentSize; m++) {
            if (heapArray.elementAt(m) != null) {
                System.out.print(heapArray.elementAt(m).getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();

        int nBlanks = 128;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "..............................." +
                "..............................." +
                "..............................." +
                "..............................." +
                "...............................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(' ');
                }
            }

            System.out.print(heapArray.elementAt(j).getKey());

            if (++j == currentSize) {
                break;
            }

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n" + dots + dots);
    }

    public static void main(String[] args) throws IOException {
        int value, value2;
        Heap<Integer, Double> theHeap = new Heap<>();

        theHeap.insert(70, 70.0);
        theHeap.insert(40, 40.0);
        theHeap.insert(50, 50.0);
        theHeap.insert(20, 20.0);
        theHeap.insert(60, 60.0);
        theHeap.insert(100, 100.0);
        theHeap.insert(80, 80.0);
        theHeap.insert(30, 30.0);
        theHeap.insert(10, 10.0);
        theHeap.insert(90, 90.0);

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change: ");
            int choice = InputUtil.getChar();
            switch (choice) {
                case 's':
                    theHeap.displayHeap();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = InputUtil.getInt();
                    int position = theHeap.insert(value, (double) value);
                    System.out.println("Value inserted at position: " + position);
                    break;
                case 'r':
                    if (!theHeap.isEmpty())
                        theHeap.remove();
                    else
                        System.out.println("Can't remove; heap empty");
                    break;
                case 'c':
                    System.out.print("Enter current index of item: ");
                    value = InputUtil.getInt();
                    System.out.print("Enter new key: ");
                    value2 = InputUtil.getInt();
                    if (!theHeap.change(value, value2))
                        System.out.println("Invalid index");
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }
}
