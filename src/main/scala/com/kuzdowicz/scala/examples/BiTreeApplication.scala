package com.kuzdowicz.scala.examples

trait Tree[+T] {

  private def addNextRecHandler[B](value: B, last: Tree[B]): Tree[B] = {
    last match {
      case Node(el, l, EmptyNode) => Node(value, last, EmptyNode)
      case Node(el, l, r) => Node(value, l, last)
    }
  }

  def addNext[B](value: B): Tree[Any] = addNextRecHandler(value, this)

}

case object EmptyNode extends Tree[Nothing]

case class Node[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

object BiTreeUtils {

  private def sizeRecHandler[T](n: Tree[T]): Int = n match {
    case EmptyNode => 0
    case Node(el, l, r) => sizeRecHandler(l) + sizeRecHandler(r) + 1
  }

  def size[T](headNode: Tree[T]): Int = sizeRecHandler(headNode)

}

object BiTreeApplication extends App {

  val last = Node[Int](4, EmptyNode, EmptyNode)
  val third = Node[Int](3, last, EmptyNode)
  val sec = Node[Int](2, EmptyNode, EmptyNode)
  val first = Node[Int](1, sec, third)

  val result = BiTreeUtils.size(first)

  println(first)
  println(result)

  val tree2 = Node[Int](8, EmptyNode, EmptyNode)
    .addNext(7).addNext(6)

  println(s"tree2 val = ${tree2.asInstanceOf[Node[Int]].value}")
  println(tree2)


  println(BiTreeUtils.size(tree2))

}
