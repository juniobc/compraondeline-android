package br.com.localizacao;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.location.Location;
import com.google.android.gms.location.LocationListener;
 
public class GPSTracker implements LocationListener {
  
    private Location location;
    private LocationRequest mLocationRequest;
    
    public GPSTracker() {
    }
    
    public void createLocationRequest() {
        this.mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    
    public void startLocationUpdates(GoogleApiClient mGoogleApiClient) {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }
    
    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
 
    @Override
    public void onLocationChanged(Location location) {
    	
    	this.location = location;
        //mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        //updateUI();
    	
    }
 
}
