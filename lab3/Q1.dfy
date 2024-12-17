
/* 
Answer Question 1a here:
Could not ensure post condition, could not prove that a > b
Going through the if-else block:
  if x > y then assigns a:=x and b:=y 
    x > y && a == x && b == y ==> a > b - always ok
  else !(x > y) ==> x <= y then assigns a:=y and b:=x
    x <= y && a == y && b == x ==> a >= b != a > b
    the post condition requires a strictly greater than b but the method may return a equal to b
*/
method M(x : int, y : int) returns (a : int, b : int)
  ensures a > b;
{
  if (x > y)
  {a:= x;
   b := y;}
  else
  {a := y;
   b := x;}
}

/*
Answers to part 3
*/
// postcondition
method M1(x : int, y : int) returns (a : int, b : int)
  ensures a >= b
{
  if (x > y)
  {a:= x;
   b := y;}
  else
  {a := y;
   b := x;}
}

// precondition
method M2(x : int, y : int) returns (a : int, b : int)
  requires x != y
  ensures a > b
{
  if (x > y)
  {a:= x;
   b := y;}
  else
  {a := y;
   b := x;}
}

// method
method M3(x : int, y : int) returns (a : int, b : int)
  ensures a > b
{
  if (x > y)
  { a:= x;
    b := y;
  } else if (x < y)
  { a := y;
    b := x;
  } else
  { assert x == y;
    a:=x;
    b:=y-1;
  }
}
