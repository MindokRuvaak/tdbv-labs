// class CircularMemory
// This class implements a circular buffer with 2 int typed pointers
class CircularMemory
{
  var cells : array<int>
  var read_position : int
  var write_position : int
  var isFlipped : bool

  constructor Init(cap : int)
  //missing some pre-conditions here
  requires cap > 0 
  ensures Valid()
  ensures fresh(cells)
  ensures read_position == 0
  ensures write_position == 0
  ensures isFlipped == false
  {
    cells := new int[cap];
    read_position, write_position := 0, 0;
    isFlipped := false;
  }
  
  // Valid is the class invariant we would like to maintain
  // for any CircularMemory at any moment of its lifetime
  predicate Valid()
    reads this, this`cells
  {
  // Think of some constraints on: 
  // 1. cells.Length?
  // 2. write_position?
  // 3. read_position?
    HasElem(cells)
  }




  predicate HasElem(a : array) {
    a.Length > 0
  }
}