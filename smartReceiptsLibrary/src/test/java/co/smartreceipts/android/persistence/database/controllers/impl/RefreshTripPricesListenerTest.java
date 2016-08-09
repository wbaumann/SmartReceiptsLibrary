package co.smartreceipts.android.persistence.database.controllers.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;

import java.util.Collections;

import co.smartreceipts.android.model.Trip;
import co.smartreceipts.android.persistence.database.controllers.TableController;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(RobolectricGradleTestRunner.class)
public class RefreshTripPricesListenerTest {
    
    // Class under test
    RefreshTripPricesListener<Object> mRefreshTripPricesListener;
    
    @Mock
    TableController<Trip> mTripTableController;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mRefreshTripPricesListener = new RefreshTripPricesListener<>(mTripTableController);
    }

    @Test
    public void onGetSuccess() {
        mRefreshTripPricesListener.onGetSuccess(Collections.emptyList());
        verifyZeroInteractions(mTripTableController);
    }

    @Test
    public void onGetFailure() {
        mRefreshTripPricesListener.onGetFailure(null);
        verifyZeroInteractions(mTripTableController);
    }

    @Test
    public void onInsertSuccess() {
        mRefreshTripPricesListener.onInsertSuccess(new Object());
        verify(mTripTableController).get();
    }

    @Test
    public void onInsertFailure() {
        mRefreshTripPricesListener.onInsertFailure(new Object(), null);
        verifyZeroInteractions(mTripTableController);
    }

    @Test
    public void onUpdateSuccess() {
        mRefreshTripPricesListener.onUpdateSuccess(new Object(), new Object());
        verify(mTripTableController).get();
    }

    @Test
    public void onUpdateFailure() {
        mRefreshTripPricesListener.onUpdateFailure(new Object(), null);
        verifyZeroInteractions(mTripTableController);
    }

    @Test
    public void onDeleteSuccess() {
        mRefreshTripPricesListener.onDeleteSuccess(new Object());
        verify(mTripTableController).get();
    }

    @Test
    public void onDeleteFailure() {
        mRefreshTripPricesListener.onDeleteFailure(new Object(), null);
        verifyZeroInteractions(mTripTableController);
    }

}