package MobilePlan.provided;

/**
 * a matcher interface.
 * 
 * implementing classes match objects to some pattern.
 * 
 * @ProgrammingProblem.Hint provided
 * 
 * @param <T>
 *            the type of the object to match
 */
public interface Matcher<T> {

	/**
	 * checks whether some object matches the pattern of this matcher.
	 * 
	 * @param t
	 *            the object to match
	 * @return true if t matches, false otherwise
	 */
	public abstract boolean matches(T t);

}