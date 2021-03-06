package chapter3.sec5

//Tree structures
//tag::init1[]

// Listing 3.11.
// Definition of a binary tree data structure

sealed class Tree<out A>

data class Leaf<A>(val value: A) : Tree<A>()

data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()
//end::init1[]
