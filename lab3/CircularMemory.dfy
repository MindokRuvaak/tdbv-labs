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
    ensures  isSuccess ==> ((read_position == old(read_position) + 1 || read_position == 0)
                            && content == cells[old(read_position)])
    ensures !isSuccess ==> old(read_position) == read_position && content == 0
    modifies this`read_position, this`isFlipped
  {
    if(isFlipped)
    {
      isSuccess := true;
      content := cells[read_position];
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
        content := cells[read_position];
        read_position := read_position + 1;
      }
    }
  }

  method Write(input : int) returns (isSuccess : bool)
    modifies cells, this`write_position, this`isFlipped
    requires Valid()
    ensures  Valid()
    ensures cells.Length == old(cells.Length)
    ensures  isSuccess ==> (cells[old(write_position)] == input
                            && (write_position == old(write_position) + 1 || write_position == 0))
    ensures !isSuccess ==> (cells == old(cells) && write_position == old(write_position)
                            && isFlipped == old(isFlipped))
  {
    if(isFlipped)
    {
      if (read_position == write_position)
      {
        isSuccess := false;
      }
      else
      {
        isSuccess := true;
        cells[write_position] := input;
        write_position := write_position + 1;
      }
    }
    else // not flipped
    {
      isSuccess := true;
      cells[write_position] := input;
      if (write_position + 1 == cells.Length)
      {
        write_position := 0;
        isFlipped := true;
      }
      else
      {
        write_position := write_position + 1;
      }
    }
  }

  // Question 3 for Lab 3
  method DoubleCapacity()
    modifies this`cells
    requires Valid()
    ensures Valid()
    ensures cells.Length == 2 * old(cells.Length)
    ensures read_position == old(read_position)
    ensures write_position == old(write_position)
    ensures forall j : int :: 0 <= j < old(cells.Length) ==> cells[j] == old(cells[j])
    ensures forall j : int :: old(cells.Length) <= j < cells.Length ==> cells[j] == 0
  {
    // declare new capaity and new array to replace old
    var new_cap := 2*cells.Length;
    var new_cells := new int[new_cap];
    assert new_cells.Length == 2*cells.Length;
    var i := 0;
    while i < new_cap
      invariant new_cap - i >= 0
      invariant forall j : int :: 0 <= j < i ==> new_cells[j] == 0
      invariant new_cap == new_cells.Length == 2*cells.Length
      invariant cells == old(cells)
      decreases new_cap - i
    {// assign 0 to all elements in new array
      new_cells[i] := 0;
      assert new_cells[i] == 0;
      i := i + 1;
      assert new_cells[i-1] == 0;
    }
    assert forall j : int :: 0 <= j < new_cap ==> new_cells[j] == 0;

    assert new_cap == 2*cells.Length;
    var k := 0;
    while k < cells.Length
      invariant cells.Length - k >= 0
      invariant new_cells.Length == 2*cells.Length
      invariant forall j : int :: 0 <= j < k       ==> new_cells[j] == cells[j]
      invariant forall j : int :: k <= j < new_cap ==> new_cells[j] == 0
      invariant cells == old(cells)
      decreases cells.Length - k
    { // overwrite elements in old half of array
      new_cells[k] := cells[k];
      k:= k+1;
    }
    assert new_cells[0] == cells[0];
    assert new_cells[cells.Length-1] == cells[cells.Length-1];
    assert forall j : int :: 0 <= j < cells.Length ==> new_cells[j] == cells[j];
    cells := new_cells;
    assert forall j : int :: 0 <= j < old(cells).Length ==> cells[j] == old(cells)[j];
  }

}