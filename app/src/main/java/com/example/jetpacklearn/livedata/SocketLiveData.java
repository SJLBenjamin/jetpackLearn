package com.example.jetpacklearn.livedata;

import android.icu.math.BigDecimal;
import androidx.lifecycle.LiveData;

public class SocketLiveData  extends LiveData<BigDecimal> {
    String mSymbol;

    public SocketLiveData(String symbol){
      mSymbol =symbol;
    }

    public SocketLiveData(BigDecimal value) {
        super(value);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

}
