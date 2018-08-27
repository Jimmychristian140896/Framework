package framework.jimmy.com.framework.model;


import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserLocationModel {
    private static UserLocationModel instance;
    public static UserLocationModel instanceOf() {
        if (instance == null) {
            instance = new UserLocationModel();
        }
        return instance;
    }
    private PublishSubject<LatLng> subject = PublishSubject.create();

    public void setLocation(LatLng latLng) {
        subject.onNext(latLng);
    }

    public Observable<LatLng> getUserLocation() {
        return subject;
    }
}