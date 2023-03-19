package seedu.loyaltylift.model.customer;

import static java.util.Objects.requireNonNull;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;

/**
 * Represents a Customer's points in the address book.
 * The minimum points a customer can have is 0.
 */
public class Points {
    public static final Integer MAXIMUM_POINTS = 999999;
    public static final Integer MINIMUM_POINTS = 0;

    public static final Integer MAXIMUM_POINTS_ADD = 999999;
    public static final Integer MAXIMUM_POINTS_SUBTRACT = -999999;

    public static final String MESSAGE_CONSTRAINTS = "Points must be a positive integer "
            + "and can only range from "
            + MINIMUM_POINTS
            + " to "
            + MAXIMUM_POINTS;

    public static final String MESSAGE_CONSTRAINTS_ADDITION = "Points added must be an integer "
            + "and can only range from "
            + MAXIMUM_POINTS_SUBTRACT
            + " to "
            + MAXIMUM_POINTS_ADD;

    public final Integer value;
    public final Integer cumulative;

    /**
     * Constructs an {@code Points}.
     *
     * @param points A valid amount of points.
     */
    public Points(Integer points, Integer maxPoints) {
        requireNonNull(points);
        value = points;
        cumulative = maxPoints;
    }

    /**
     * For adding or subtracting points.
     *
     * @param p Integer points
     * @return a new Points object, value and cumulative depends on whether it is an addition or subtraction.
     * @throws IllegalValueException when points are not valid
     */
    public Points editPoints(Integer p) throws IllegalValueException {
        Points newPoints = p > 0
                ? addPoints(p)
                : subtractPoints(p);
        return newPoints;
    }

    private Points addPoints(Integer p) throws IllegalValueException {
        Integer newPoints = this.value + p;
        Integer newCumulativePoints = this.cumulative + p;
        if (isValidPoints(newPoints) && isValidPoints(newCumulativePoints)) {
            return new Points(newPoints, newCumulativePoints);
        } else {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
    }

    private Points subtractPoints(Integer p) throws IllegalValueException {
        Integer newPoints = this.value + p;
        if (isValidPoints(newPoints)) {
            return new Points(newPoints, this.cumulative);
        } else {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given point is valid.
     */
    public static boolean isValidPoints(Integer test) {
        return (test >= MINIMUM_POINTS && test <= MAXIMUM_POINTS);
    }

    /**
     * Returns true if points to be added or subtracted is within a valid range.
     */
    public static boolean isValidAddition(Integer test) {
        return (test >= MAXIMUM_POINTS_SUBTRACT && test <= MAXIMUM_POINTS_ADD);
    }

    @Override
    public String toString() {
        return value.toString()
                + " (Cumulative: "
                + cumulative.toString()
                + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Points // instanceof handles nulls
                && value.equals(((Points) other).value)) // state check
                && cumulative.equals(((Points) other).cumulative);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
