package com.dynamic.program.data.structures.tree

class BST {
    data class Node(var v: Int, var parent: Node? = null, var left: Node? = null, var right: Node? = null)

    var root: Node? = null

    fun inorder(): MutableList<Int> {
        val result = mutableListOf<Int>()
        inorderHelper(root, result)
        return result
    }

    fun preorder(): MutableList<Int> {
        val result = mutableListOf<Int>()
        preorderHelper(root, result)
        return result
    }

    fun postorder(): MutableList<Int> {
        val result = mutableListOf<Int>()
        postorderHelper(root, result)
        return result
    }

    fun search(target: Int): Node? {
        return searchHelper(root, target)
    }

    fun insert(target: Int): Boolean {
        insertHelper(root, target)
        return true
    }

    fun delete(node: Node?, x: Int): Node? {
        if(node == null) return node
        if(node.v > x) {
            node.left = delete(node.left, x)
            return node
        } else if(node.v < x) {
            node.right = delete(node.right, x)
            return node
        }
        if (node.left == null) {
            return node.right
        } else if(node.right == null) {
            return node.left
        } else {
            var successorParent = node
            var successor = node.right
            while (successor?.left != null) {
                successorParent = successor
                successor = successor.left
            }
            // removal process for node delete
            if (successorParent != root) {
                successorParent?.left = successor?.right
            } else {
                successorParent?.right = successor?.right
            }
            // swap key of successor
            node.v = successor?.v ?: 0
            // successor is removed- bye bye
            return node
        }
    }
    fun delete(node: Node?): Boolean {
        if (node?.right == null) {
            shiftNodes(node, node?.left)
        } else if (node.left == null) {
            shiftNodes(node, node.right)
        } else {
            val successor = minimum(node.right)
            if (successor != node.right) {
                shiftNodes(successor, successor?.right)
                successor?.right = node.right
                successor?.right?.parent = successor
            }
            shiftNodes(node, successor)
            successor?.left = node.left
            successor?.left?.parent = successor
        }
        return false
    }

    private fun shiftNodes(old: Node?, src: Node?) {
        if (old?.parent == null) {
            root = src
        } else if (old == old.parent?.left) {
            old.parent?.left = src
        } else old.parent?.right = src
        if (src != null) {
            src.parent = old?.parent
        }
    }

    fun maximum(node: Node? = root): Node? {
        return if (node == null) null
        else {
            var n = node
            while (n?.right != null) {
                n = n.right
            }
            n?.left
        }
    }

    fun minimum(node: Node? = root): Node? {
        return if (node == null) null
        else {
            var n = node
            while (n?.left != null) {
                n = n.left
            }
            n
        }
    }

    fun inorderSuccessor(node: Node?): Node? {
        return if (node == null) null
        else if (node.right != null) minimum(node.right)
        else {
            var parent = node.parent
            var n = node
            while (parent != null && n != parent.left) {
                n = parent
                parent = parent.parent
            }
            parent
        }
    }

    fun inorderPredecessor(node: Node?): Node? {
        return if (node == null) null
        else if (node.left != null) maximum(node.left)
        else {
            var parent = node.parent
            var n = node
            while (parent != null && n != parent.right) {
                n = parent
                parent = parent.parent
            }
            parent
        }
    }

    private fun inorderHelper(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            inorderHelper(node.left, result)
            result.add(node.v)
            inorderHelper(node.right, result)
        }
    }

    private fun preorderHelper(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            result.add(node.v)
            inorderHelper(node.left, result)
            inorderHelper(node.right, result)
        }
    }

    private fun postorderHelper(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            inorderHelper(node.left, result)
            inorderHelper(node.right, result)
            result.add(node.v)
        }
    }

    private fun searchHelper(node: Node?, target: Int): Node? {
        if (node != null) {
            if (node.v == target) return node
            else if (node.v < target) searchHelper(node.right, target)
            else searchHelper(node.left, target)
        }
        return null
    }

    private fun insertHelper(node: Node?, target: Int) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                if (node.v < target) {
                    node.right = Node(target, node)
                } else {
                    node.left = Node(target, node)
                }
            } else if (node.v < target) {
                insertHelper(node.right, target)
            } else {
                insertHelper(node.left, target)
            }
        } else {
            if (root == null) {
                root = Node(target)
            }
        }
    }
}
