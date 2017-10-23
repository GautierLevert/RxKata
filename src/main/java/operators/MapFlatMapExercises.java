package operators;

import io.reactivex.Observable;
import org.javatuples.Triplet;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * As a rule of thumb, you use flatMap() for the following situations:
 * <p>
 * The result of transformation in map() must be an Observable.
 * For example, performing long-running, asynchronous operation on each element of the stream without blocking.
 * <p>
 * You need a one-to-many transformation, a single event is expanded into multiple sub-events.
 * For example, a stream of customers is translated into streams of their orders, for which each customer
 * can have an arbitrary number of orders.
 */

public class MapFlatMapExercises {

	/**
	 * Given a stream t[ [1,2,3],[4,5,6],[7,8,9] ] which has "two levels", flatten it in a "one level" structure :
	 * [ 1,2,3,4,5,6,7,8,9 ]
	 */
	public Observable<Integer> exerciseFlatten() {
		return Observable
				.just(new Triplet<>(1, 2, 3),
						new Triplet<>(4, 5, 6),
						new Triplet<>(7, 8, 9))
				.flatMap(triplet -> Observable.fromIterable(triplet.toList()).cast(Integer.class));
	}

	/**
	 * You have a booking that has an optional user. And the optional user has an optional email.
	 * Write a method getEmail() that gives you the email option of a booking
	 *
	 * @return
	 */

	public Observable<String> exerciseEmail() {
		Booking booking = new Booking(new Booking.User("myemail@gmail.com"));
		return Observable.just(booking.getUser())
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Booking.User::getEmail)
				.filter(Optional::isPresent)
				.map(Optional::get);
	}

	static class Booking {
		private final Optional<User> user;

		public Booking(User user) {
			this.user = Optional.ofNullable(user);
		}

		Optional<User> getUser() {
			return user;
		}

		private static class User {
			Optional<String> getEmail() {
				return email;
			}

			private final Optional<String> email;

			public User(String email) {
				this.email = Optional.ofNullable(email);
			}
		}
	}

	/**
	 * Return an Observable that shows the records as specified in the test using `loadRecordsFor`
	 */

	public Observable<String> loadRecordsExercise() {
		 Observable
				.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);
//				.map(dayOfWeek -> loadRecordsFor(dayOfWeek));
		return null;
	}


	Observable<String> loadRecordsFor(DayOfWeek dow) {
		switch (dow) {
			case SUNDAY:
				return Observable
						.interval(90, MILLISECONDS)
						.take(5)
						.map(i -> "Sun-" + i);
			case MONDAY:
				return Observable
						.interval(65, MILLISECONDS)
						.take(5)
						.map(i -> "Mon-" + i);
		}
		return null;
	}

	/**
	 * We take an arbitrary text in String and split it to words, removing punctuation using a
	 * regular expression. Now, for each word we calculate how much it takes to say that word,
	 * simply by multiplying the word length by millisPerChar.
	 * Then, we would like to spread words over time, so that each word appears in the resulting stream after the delay calculated
	 * @param quote
	 * @param millisPerChar
	 * @return
	 */

	public Observable<String> speak(String quote, long millisPerChar) {
		//FOR THE BRAVE: INSERT CODE HERE

		//FOR THE NON-BRAVE: Use this and just fix the test. Speak.speak(quote, millisPerChar);
		return null;
	}
}
