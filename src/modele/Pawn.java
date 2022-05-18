package modele;

import java.util.Objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn {
	private Circle circle;
	private Color color;

	// Creating a new pawn with a color.
	public Pawn(Color color) {
		this.circle = new Circle(20., color);
		circle.setStroke(Color.valueOf("0x333333"));
		this.color = color;
	}

	/**
	 * Returns the circle.
	 * 
	 * @return The circle object.
	 */
	public Circle getCircle() {
		return circle;
	}

	/**
	 * This function returns the color of the object.
	 * 
	 * @return The color of the car.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The hashCode() function returns a hash code value for the object
	 * 
	 * @return The hash code of the color.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color);
	}

	/**
	 * If the object is not null, and the object is of the same class as this object, and the color of the
	 * object is the same as the color of this object, then the objects are equal
	 * 
	 * @param obj The object to be compared.
	 * @return The color of the pawn.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pawn other = (Pawn) obj;
		return Objects.equals(color, other.color);
	}

	/**
	 * The toString() function returns a string representation of the object
	 * 
	 * @return The toString method is being returned.
	 */
	@Override
	public String toString() {
		return "Pawn [circle=" + circle + ", color=" + color + "]";
	}
}