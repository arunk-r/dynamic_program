package com.dynamic.program.design.medium.arrays;

import java.util.Objects;
import java.util.Stack;
/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
    public static Node form(String s) {
        if (Objects.equals(s, "+")) {
            return new AdditionNode();
        } else if (Objects.equals(s, "-")) {
            return new SubstractionNode();
        } else if (Objects.equals(s, "*")) {
            return new MultiplicationNode();
        }else if (Objects.equals(s, "/")) {
            return new DivisionNode();
        } else {
            return new NumberNode(s);
        }
    }
};

abstract class OperationNode extends Node {
    public Node left;
    public Node right;
}

class DivisionNode extends OperationNode {
    public int evaluate() {
        return left.evaluate() / right.evaluate();
    }
}

class MultiplicationNode extends OperationNode {
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}

class SubstractionNode extends OperationNode {
    public int evaluate() {
        return left.evaluate() - right.evaluate();
    }
}

class AdditionNode extends OperationNode {
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }
}

class NumberNode extends Node {
    private int value;
    public NumberNode(String s) {
        value = Integer.parseInt(s);
    }

    public int evaluate() {
        return value;
    }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Stack<Node> stk = new Stack<>();
        for(String s: postfix) {
            Node n = Node.form(s);
            if(n instanceof NumberNode) {
                stk.push(n);
            } else {
                OperationNode op = (OperationNode) n;
                op.right = stk.pop();
                op.left = stk.pop();
                stk.push(op);
            }
        }
        return stk.pop();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
