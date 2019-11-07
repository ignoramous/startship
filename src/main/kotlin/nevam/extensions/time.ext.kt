package nevam.extensions

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import java.time.Duration
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlin.math.roundToLong

val Int.minutes: Duration
  get() = Duration.ofMinutes(toLong())

val Int.seconds: Duration
  get() = Duration.ofSeconds(toLong())

val Int.second: Duration
  get() {
    return Duration.ofSeconds(toLong())
  }

object Observables {
  fun timer(delay: Duration, scheduler: Scheduler = Schedulers.computation()) =
    Observable.timer(delay.toMillis(), MILLISECONDS, scheduler)
        .map { delay }!!

  fun interval(period: Duration, initial: Duration = period, scheduler: Scheduler = Schedulers.computation()) =
    Observable
        .interval(initial.toMillis(), period.toMillis(), MILLISECONDS, scheduler)
        .map { Duration.ofMillis(it * period.toMillis()) }!!
}

fun <T> Observable<T>.delay(period: Duration) =
  delay(period.toMillis(), MILLISECONDS)!!

operator fun Duration.times(times: Double) =
  Duration.ofMillis((toMillis() * times).roundToLong())!!

fun TestScheduler.advanceTimeBy(delayTime: Duration) {
  advanceTimeBy(delayTime.toMillis(), MILLISECONDS)
}
