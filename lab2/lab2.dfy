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
    ensures read_position == 0
    ensures write_position == 0
    ensures isFlipped == false
    ensures fresh(cells)
    ensures isEmpty()
    ensures Valid()
  {
    cells := new int[cap];
    read_position, write_position := 0, 0;
    isFlipped := false;
  }

  // Valid is the class invariant we would like to maintain
  // for any CircularMemory at any moment of its lifetime
  predicate Valid()
    reads this//, this`cells, this`write_position, this`read_position // can still read fields?
  {
    // Think of some constraints on:
    // 1. cells.Length?
    // 2. write_position?
    // 3. read_position?
    HasElem(cells) && WithinLimits(write_position) && WithinLimits(read_position)
  }



  // A predicate indicating no more Read available
  predicate isEmpty()
    reads this
  {
    EqPointers() && !isFlipped
  }


  //A predicate indicating no more Write should be allowed
  predicate isFull()
    reads this
  {
    EqPointers() && isFlipped

  }



  predicate EqPointers() 
  reads this`read_position, this`write_position
  {
    read_position == write_position
  }

  predicate HasElem(a : array)
  {
    a.Length > 0
  }

  predicate WithinLimits(index : int)
    reads this`cells
  {
    0 <= index && index < cells.Length
  }
}