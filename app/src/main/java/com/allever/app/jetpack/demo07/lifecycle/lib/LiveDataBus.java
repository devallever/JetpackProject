package com.allever.app.jetpack.demo07.lifecycle.lib;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author allever
 */
public class LiveDataBus {


    private static final String TAG = LiveDataBus.class.getSimpleName();

    private Map<String, MutableLiveData> keyLiveDataMap = new HashMap<>();
    private Map<String, List<OwnerObserver>> keyOwnerObserverListMap = new HashMap<>();

    private Map<String, List<OwnerObserver>> unRegisterKeyOwnerObserverListMap = new HashMap<>();

    private LiveDataBus() {

    }

    private static class Holder{
        private static final LiveDataBus INS = new LiveDataBus();
    }

    public static LiveDataBus getIns() {
        return Holder.INS;
    }


    public void  register(String key, OwnerObserver ownerObserver) {
        MutableLiveData liveData = keyLiveDataMap.get(key);
        List<OwnerObserver> registeredOwnerObserverList  = keyOwnerObserverListMap.get(key);
        if (registeredOwnerObserverList == null) {
            //null， 肯定是未注册
            registeredOwnerObserverList = new ArrayList<>();
            keyOwnerObserverListMap.put(key, registeredOwnerObserverList);
        }

        if (!registeredOwnerObserverList.contains(ownerObserver)) {
            //添加到未注册列表
            List<OwnerObserver> ownerObserverList = unRegisterKeyOwnerObserverListMap.get(key);
            if (ownerObserverList == null) {
                ownerObserverList = new ArrayList<>();
            }
            ownerObserverList.add(ownerObserver);
            unRegisterKeyOwnerObserverListMap.put(key, ownerObserverList);
            return;
        }

        liveData.observe(ownerObserver.getLifecycleOwner(), ownerObserver.getObserver());

    }

    public void unRegister(String key, OwnerObserver targetOwnerObserver) {
        List<OwnerObserver> ownerObserverList = keyOwnerObserverListMap.get(key);
        int removeIndex = -1;
        if (ownerObserverList != null) {
            for (OwnerObserver ownerObserver: ownerObserverList) {
                if (ownerObserver == targetOwnerObserver) {
                    removeIndex = ownerObserverList.indexOf(ownerObserver);
                    MutableLiveData mutableLiveData = keyLiveDataMap.get(key);
                    if (mutableLiveData != null) {
                        mutableLiveData.removeObserver(ownerObserver.getObserver());
                    }
                    break;
                }
            }
            if (removeIndex != -1) {
                ownerObserverList.remove(removeIndex);
            }

            if (ownerObserverList.isEmpty()) {
                keyOwnerObserverListMap.remove(key);
            }
        }
    }

    public <T> void setLiveData(String key, MutableLiveData<T> liveData) {
        keyLiveDataMap.put(key, liveData);
        Log.d(TAG, "setLiveData: keyLiveDataMap.size = " + keyLiveDataMap.size());

        List<OwnerObserver> ownerObserverList = unRegisterKeyOwnerObserverListMap.get(key);
        if (ownerObserverList != null) {
            List<OwnerObserver> registeredList = keyOwnerObserverListMap.get(key);
            if (registeredList != null) {
                registeredList.addAll(ownerObserverList);
            }
            keyOwnerObserverListMap.put(key, registeredList);
            unRegisterKeyOwnerObserverListMap.remove(key);

            for (OwnerObserver ownerObserver : registeredList) {
                liveData.observe(ownerObserver.getLifecycleOwner(), ownerObserver.getObserver());
            }
        }
    }
}
