package co.smartreceipts.android.sync.drive.rx

import com.google.android.gms.tasks.Task

import co.smartreceipts.android.sync.utils.SyncSchedulers
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Provides us with some Rx extensions around the [Task] object that is used for Google Drive
 * integrations
 */
object RxDriveTask {

    /**
     * Converts a [Task] into a [Single]. All operations will be run on our
     * [SyncSchedulers]
     *
     * @param task the [Task] to convert
     * @param <TResult> the result type of this [Task]
     * @return a [Single], which will emit onSuccess/onError based on the results of the task
    </TResult> */
    fun <TResult> toSingle(task: Task<TResult>): Single<TResult> {
        return Single.create<TResult> { emitter ->
            task.addOnSuccessListener { result ->
                if (!emitter.isDisposed) {
                    emitter.onSuccess(result)
                }
            }
            task.addOnFailureListener { throwable ->
                if (!emitter.isDisposed) {
                    emitter.onError(throwable)
                }
            }
            task.addOnCanceledListener {
                if (!emitter.isDisposed) {
                    emitter.onError(InterruptedException("This drive task was cancelled"))
                }
            }
        }.subscribeOn(SyncSchedulers.io())
    }

    /**
     * Converts a [Task] into an [Observable]. All operations will be run on our
     * [SyncSchedulers]
     *
     * @param task the [Task] to convert
     * @param <TResult> the result type of this [Task]
     * @return a [Observable], which will emit onNext/onError based on the results of the task
    </TResult> */
    fun <TResult> toObservable(task: Task<TResult>): Observable<TResult> {
        return Observable.create<TResult> { emitter ->
            task.addOnSuccessListener { result ->
                if (!emitter.isDisposed) {
                    emitter.onNext(result)
                }
            }
            task.addOnCompleteListener { originalTask ->
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }
            task.addOnFailureListener { throwable ->
                if (!emitter.isDisposed) {
                    emitter.onError(throwable)
                }
            }
            task.addOnCanceledListener {
                if (!emitter.isDisposed) {
                    emitter.onError(InterruptedException("This drive task was cancelled"))
                }
            }
        }.subscribeOn(SyncSchedulers.io())
    }

    /**
     * Converts a [Task] into an [Completable]. All operations will be run on our
     * [SyncSchedulers]
     *
     * @param task the [Task] to convert
     * @param <TResult> the result type of this [Task]
     * @return a [Completable], which will emit onComplete/onError based on the results of the task
    </TResult> */
    fun <TResult> toCompletable(task: Task<TResult>): Completable {
        return Completable.create { emitter ->
            task.addOnCompleteListener { originalTask ->
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }
            task.addOnFailureListener { throwable ->
                if (!emitter.isDisposed) {
                    emitter.onError(throwable)
                }
            }
            task.addOnCanceledListener {
                if (!emitter.isDisposed) {
                    emitter.onError(InterruptedException("This drive task was cancelled"))
                }
            }
        }.subscribeOn(SyncSchedulers.io())
    }
}
