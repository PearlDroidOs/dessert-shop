package com.pearldroidos.dessertshop

import com.pearldroidos.dessertshop.callback.DessertDetailsCallBack
import com.pearldroidos.dessertshop.presenter.DessertDetailsPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

/**
 * DessertPresenterTest is tested on View
 *
 * Created By Pearl DroidOs
 */
@RunWith(MockitoJUnitRunner::class)
open class DessertPresenterTest{

    @Mock
    private lateinit var callBack: DessertDetailsCallBack

    private lateinit var presenter:DessertDetailsPresenter

    @Before
    @Throws(Exception::class)
    open fun setUp(): Unit{
        presenter = DessertDetailsPresenter(callBack) //Initialize presenter of dessert detail page
    }

    @Test
    open fun checkShowAppBarCustom() {
        presenter.setActionBar()
        verify(callBack, times(1)).setActionBar()
    }
}