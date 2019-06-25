package co.smartreceipts.android.persistence.database.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.smartreceipts.android.model.Receipt;

public interface ReceiptTableEventsListener extends TripForeignKeyTableEventsListener<Receipt> {

    void onMoveSuccess(@NonNull Receipt oldReceipt, @NonNull Receipt newReceipt);

    void onMoveFailure(@NonNull Receipt oldReceipt, @Nullable Throwable e);

    void onCopySuccess(@NonNull Receipt oldReceipt, @NonNull Receipt newReceipt);

    void onCopyFailure(@NonNull Receipt oldReceipt, @Nullable Throwable e);

}
