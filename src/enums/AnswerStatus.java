package enums;

/**
 * Describes the state of the user's answer to a spelling quiz question, where
 * MASTERED means that the user has gained the correct answer on first attempt,
 * FAULTED means that the user has gained the correct answer on second attempt,
 * INCORRECT means that the user has gained the incorrect answer on first
 * attempt, and FAILED means that the user has gained the incorrect answer on
 * second attempt.
 *
 * @author Jared Daniel Recomendable
 *
 */
public enum AnswerStatus {
	MASTERED,
	FAULTED,
	FAILED,
	INCORRECT
}
