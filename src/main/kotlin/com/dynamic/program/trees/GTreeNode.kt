package com.dynamic.program.trees

//`val` data holder of type Int
// children - list of pointers to child nodes
data class GTreeNode(val `val`: Int, val children: MutableList<GTreeNode?>)