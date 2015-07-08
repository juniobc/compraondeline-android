package br.com.localizacao;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.compraondeline.Tab1Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
 
public class GPSTracker extends Service implements LocationListener, ConnectionCallbacks, OnConnectionFailedListener {
  
    private static Location location;
    private LocationRequest mLocationRequest;
    public static GoogleApiClient mGoogleApiClient;
    private Context contexto;
    private boolean mLocationUpdate = true;
    
    public GPSTracker(Context contexto) {
    	
    	this.contexto = contexto;
    	buildGoogleApiClient();
    	createLocationRequest();
    	
    }
    
    public void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.contexto)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    }
    
    public void createLocationRequest() {
        this.mLocationRequest = new LocationRequest();
        this.mLocationRequest.setInterval(10000);
        this.mLocationRequest.setFastestInterval(5000);
        this.mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    
    public void startLocationUpdates(GoogleApiClient mGoogleApiClient) {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, this.mLocationRequest, this);
    }
    
    public static Location ultimoLocal(){
    	
    	location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    	
    	return location;
    	
    }
 
    @Override
    public void onLocationChanged(Location lt) {
    	
    	location = lt;
    	
    	//Tab1Fragment.atualizaMapa(this.location);
    	
    }

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {

		location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		
		Tab1Fragment.atualizaMapa(location);
		
		/*if(mLocationUpdate){		
			startLocationUpdates(mGoogleApiClient);
		}*/
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Location getLocation() {
		return location;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
