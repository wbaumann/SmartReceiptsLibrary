package co.smartreceipts.android.imports

import android.content.Context
import co.smartreceipts.android.model.Trip
import co.smartreceipts.android.settings.UserPreferenceManager
import wb.android.storage.StorageManager
import javax.inject.Inject

class FileImportProcessorFactory @Inject
internal constructor(
    private val context: Context,
    private val preferenceManager: UserPreferenceManager,
    private val storageManager: StorageManager
) {

    operator fun get(requestCode: Int, trip: Trip): FileImportProcessor {
        return when {
            RequestCodes.PHOTO_REQUESTS.contains(requestCode) -> ImageImportProcessor(trip, storageManager, preferenceManager, context)
            RequestCodes.PDF_REQUESTS.contains(requestCode) -> GenericFileImportProcessor(trip, storageManager, context)
            else -> AutoFailImportProcessor()
        }
    }
}
