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
    reads this`cells, this`write_position, this`read_position, this`isFlipped // can still read fields?
  {
    // Think of some constraints on:
    // 1. cells.Length?
    // 2. write_position?
    // 3. read_position?
    cells.Length > 0
    && (0 <= write_position < cells.Length)
    && (0 <= read_position  < cells.Length)
    && ( isFlipped ==> read_position >= write_position) 
    && (!isFlipped ==> read_position <= write_position)
  }


  // A predicate indicating no more Read available
  predicate isEmpty()
    reads this`isFlipped, this`read_position, this`write_position
  {
    read_position == write_position && !isFlipped
  }


  //A predicate indicating no more Write should be allowed
  predicate isFull()
    reads this`isFlipped, this`read_position, this`write_position
  {
    read_position == write_position && isFlipped
  }


  method Read() returns (isSuccess : bool, content : int)
    requires Valid()
    ensures  Valid()
    ensures  isSuccess ==> (old(read_position) != read_position) || cells.Length == 1
    ensures !isSuccess ==> old(read_position) == read_position
    modifies this`cells, this`read_position, this`isFlipped
  {
    content := cells[read_position];
    if(isFlipped) 
    {
      isSuccess := true;
      if (read_position + 1 == cells.Length) 
      {
        read_position := 0;
        isFlipped := false;
      }
      else 
      {
        read_position := read_position + 1;
      }
    }
    else
    {
      if (read_position == write_position) 
      {
        isSuccess := false;
        content := 0;
      }
      else 
      {
        isSuccess:= true;
        read_position := read_position + 1;
      }
    }
  }

  // method Write(input : int) returns (isSuccess : bool)
  //   modifies this
  //   requires Valid()
  //   ensures  Valid()
  //   ensures  isSuccess ==> ...
  //   ensures !isSuccess ==> ...
  // {
  //   if(isFlipped)
  //   {
  //     ...
  //   }
  //   else // not flipped
  //   {
  //     ...
  //   }
  // }


}