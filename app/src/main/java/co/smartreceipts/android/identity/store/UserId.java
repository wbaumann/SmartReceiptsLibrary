package co.smartreceipts.android.identity.store;

import androidx.annotation.NonNull;

import co.smartreceipts.android.sync.model.impl.Identifier;

public class UserId extends Identifier {

    public UserId(@NonNull String id) {
        super(id);
    }
}
