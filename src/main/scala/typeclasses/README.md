##Why use typeclasses?

You get the following benefits:

*  Type safety and conciseness of an inherited interface
*  Flexibility of a decorator (can enhance types you don't own)
*  Injectible implementations

Look at the programs in the files in this directory.

In the version that uses inheritance, you have a choice. Both options have
some drawbacks.

1. Pass decorators to a function, relying on the decorator to
   provide access to the underlying decorated value

2. Pass values to a function and decorate inside the function.

In the first approach, you almost match the power of the typeclass pattern,
with two major drawbacks.

First, you have to trust the implementation of the decorator to
correctly expose the underlying decorated value, and the compiler
cannot enforce this.

Second, you have to explicitly handle the creation of a decorated value at
the call site. This loses encapsulation and DRYness, and creates more places
that things can go wrong.

In the second approach, you maintain encapsulation and increased type-safety,
but you hard code the decorator implementations, making testing harder and
losing DRYness if you end up needing different versions that use different
decorators.

Looking at the version using the typeclass pattern, these issues don't arise.
You can have code that is written generically to the interface which
directly receives the underlying values, giving you type safe, generic
access to both the interface and the underlying value.
Also, the implementations are still being injected so you maintain
inversion of control.

Arguably the biggest benefit of this is that you allow the Scala compiler
to assemble the interfaces for you (as opposed to constructing a decorator
yourself.) It is generally a correct statement that if you can push work to the
compiler you are reducing the surface area of code that can contain bugs, and
reducing the amount of tests required to prove correctness of your code.
