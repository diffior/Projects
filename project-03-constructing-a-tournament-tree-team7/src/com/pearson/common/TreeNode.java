package com.pearson.common;

public class TreeNode<T extends Comparable<T>, Y> extends Node<T, Y> {
    private TreeNode<T, Y> leftChild;
    private TreeNode<T, Y> rightChild;

    public TreeNode(T key, Y data) {
        super(key, data);
    }

    public TreeNode<T, Y> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T, Y> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T, Y> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T, Y> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + key +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
