
method ComputeFact(n : nat) returns (res : nat)
  requires n > 0
  ensures res == fact(n)
 {
  res := 1;
  var i := 2;
  assert n - i >= -1 && res == fact(i-1);
  while (i <= n) 
  decreases n - i + 1
  invariant n - i >= - 1 && res == fact(i-1)
  {
  assert n - i >= -1 && res == fact(i-1);
    res := res * i;
    i := i + 1;
  assert n - i >= -1 && res == fact(i-1);
  }
  assert n - i >= -1 && res == fact(i-1);
 }

ghost function fact(n : nat) : nat
  requires n >= 0
{
  if n == 0 then 1 else n*fact(n-1)
}
