package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jacob Colson (jccolso)


*/
/**
 * This board position class contains all data related to a possible position on the board.
 * BoardPosition contains a Row and Column number.
 */
public class BoardPosition
{
    private int Row;
    private int Column;

    /**
     * Constructs a BoardPosition object that indicates the exact
     * position of a token within the 9x7 range of the board.
     *
     * @param aRow
     * @param aColumn
     *
     * @pre aRow > 0 AND aRow <= 9 AND aColumn > 0 AND aColumn <= 7
     *
     * @post Row = aRow AND Column = aColumn
     */
    public BoardPosition(int aRow, int aColumn)
    {
        //parameterized constructor for BoardPosition
    }

    /**
     * returns the row number for BoardPosition
     *
     * @return The row number as an integer.
     *
     * @pre None
     * 
     * @post getRow = Row. Row = #Row AND Column = #Column
     */
    public int getRow()
    {
        //returns the row
    }

    /**
     * returns the column number for BoardPosition
     *
     * @return The column number as an integer.
     *
     * @pre None
     * 
     * @post getColumn = Column. Row = #Row AND Column = #Column
     */
    public int getColumn()
    {
        //returns the column
    }

    /**
     * Takes in a BoardPosition object and returns true if two boardPositions are equal to each
     * other.
     *
     * @param obj
     *
     * @return boolean, true iff two BoardPosition objects have the same Row and Column values and False iff
     * two BoardPosition objects have different Row and Column values.
     *
     * @pre
     *
     * @post
     */
    @Override
    public boolean equals(Object obj)
    {

    }

    /**
     * Takes the values for Row and Column and converts them into string format "<Row>,<Column>"
     *
     * @return
     *
     * @pre None
     *
     * @post 
     */
    @Override
    public String toString()
    {

    }
}