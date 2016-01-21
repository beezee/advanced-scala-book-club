##Why use monads?

The simplest way to think about this is that monads are for sequencing operations.

More specifically, monads can be used to encapsulate control flow logic that
can then be generically applied to any sequencing operation that would benefit.

Most of us have written code similar to the following at some point:

```
a = thing1()
b = thing2(a)
c = thing3(b)
```

In this case, we need `a` to get `b` to get `c`. This is a very clear and
concrete example of a sequencing operation. No step in this program can
complete if the step before does not behave as expected.

What happens if any of these functions might return null?

Naively:

```
a = thing1()
if (a) {
  b = thing2(a)
  if (b) {
    c = thing3(b)
  }
}
```

It's obvious why this sucks as is. Just imagine a longer sequence.

Now what if you need to know which step fails? Even worse:

```
a = thing1()
if (a) {
  b = thing2(a)
  if (b) {
    c = thing3(b)
    if (c) { c } else { "no c" }
  } else { "no b" }
} else { "no a" }
```

Aside from the obvious, the outer else branches get further and further away
from the condition they apply to as you add more steps in the sequence. This
is textbook spaghetti.

The Example.scala file in this directory walks through a trivial example of
just this kind of sequence, implementing above two samples both with a naive
imperative approach, and using the appropriate Monad (Option and Disjunciton
respectively.)

The ability to abstract control flow in a completely generic manner, defining
essentially what happens _in between_ each line of code you write regardless
of the actually steps you define in your program, is immensely powerful, and
that is exactly what a Monad can give you.

