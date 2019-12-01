package com.leocesario.monad

trait Maybe[+A] {

  def get: A

  def map[B](f: A => B): Maybe[B]

  def flatMap[B](f: A => Maybe[B]): Maybe[B]

  def getOrEls[B >: A](v: => B): B
}

object Maybe {
  def apply[A](someValue: A): Maybe[A] = {
    if (someValue == null) Not
    else Just(someValue)
  }
}

case class Just[A](get: A) extends Maybe[A] {

  override def map[B](f: A => B): Maybe[B] = Maybe(f(get))

  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(get)

  override def getOrEls[B >: A](v: => B): B = get

}

trait Na extends Maybe[Nothing] {

  override def map[B](f: Nothing => B): Maybe[B] = this

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = this

  override def get: Nothing = throw new NoSuchElementException("Na.get")

  override def getOrEls[B >: Nothing](v: => B): B = v

}

case object Not extends Na