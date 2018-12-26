package co.smartreceipts.android.date

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.util.*

/**
 * Contains the required metadata to display a date to the end user.
 */
@Parcelize
data class DisplayableDate(val date: Date,
                           val timeZone: TimeZone) : Parcelable