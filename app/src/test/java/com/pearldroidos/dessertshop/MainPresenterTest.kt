package com.pearldroidos.dessertshop

import android.content.Context
import com.pearldroidos.dessertshop.callback.MainCallback
import com.pearldroidos.dessertshop.mock.ServiceDataMock
import com.pearldroidos.dessertshop.presenter.MainPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner


/**
 * MainPresenterTest is tested on main view
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 * See [Another testing document](https://developer.android.com/training/testing/unit-testing/local-unit-tests).
 *
 * Created by Pearl DroidOs
 */
@RunWith(MockitoJUnitRunner::class)
open class MainPresenterTest {


    @Mock
    private lateinit var callBack: MainCallback

    @Mock
    private lateinit var mockContext: Context


    private lateinit var presenter: MainPresenter

    @Before
    @Throws(Exception::class)
    open fun setUp(): Unit {
        presenter = MainPresenter(callBack, mockContext) //Initialize presenter of main page
    }

    @Test
    open fun checkShowAppBarCustom() {
        presenter.setActionBar()
        verify(callBack, times(1)).setActionBar()
    }

    @Test
    open fun checkShowProgressBar() {
        presenter.setView()
        verify(callBack, times(1)).showProgressBar()
        verify(callBack, times(1)).hideRefreshView()
    }


    @Test
    open fun checkConvertToImageUrlList() {
        Assert.assertEquals(
            "Convert list to string",
            ServiceDataMock.URL_LIST,
            presenter.convertToImageUrlList(ServiceDataMock.INFORMATION_LIST)
        )
    }

    @Test
    open fun checkConvertToImageUrlListInEmptyCase() {
        Assert.assertEquals(
            "Convert list to string Empty case",
            ServiceDataMock.EMPTY_URL_LIST,
            presenter.convertToImageUrlList(ServiceDataMock.NO_URL_INFORMATION_LIST)
        )
    }



}
