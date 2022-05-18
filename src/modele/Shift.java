package modele;

/**
 * The Shift class is used to shift the position of the pawn in the board
 */
public class Shift {
	private int axeX;
	private int axeY;

	public Shift(int total){
		// The method is used to shift the position of the pawn in the board.
		if(total>=42) {
			total=total-42;
		}	
		if(total>=0 && total <=12) 
		{
			axeX=total;
			axeY=0;
		}

		if(total>=13 && total <=21) 
		{
			axeX=12;
			axeY=-12 + total;
		}
		if(total>=22 && total <=33) 
		{
			axeX=33-total;
			axeY=9;
		}
		if(total>=34 && total <=41) 
		{
			axeX=0;
			axeY=42-total;
		}	
	}

	/**
	 * Returns the x-coordinate of the axe
	 * 
	 * @return The value of the instance variable axeX.
	 */
	public int getAxeX() {
		return axeX;
	}

	/**
	 * Returns the Y-coordinate of the axe
	 * 
	 * @return The value of the instance variable axeY.
	 */
	public int getAxeY() {
		return axeY;
	}
}