package br.com.googleplay;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import br.com.localizacao.GPSTracker;

public class GoogleServicos implements ConnectionCallbacks, OnConnectionFailedListener{
	
	private GoogleApiClient mGoogleApiClient;
    private final GPSTracker localizador;

	private final Context contexto;
    private boolean mLocationUpdate = false;

	public GoogleServicos(Context contexto){
    	
    	this.contexto = contexto;
    	localizador = new GPSTracker();
    	buildGoogleApiClient();
    	
    }
	
	public void connect(){
		
		mGoogleApiClient.connect();
		
	}
	
	public synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.contexto)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    }
	
	public void setmLocationUpdate(boolean mLocationUpdate) {
		this.mLocationUpdate = mLocationUpdate;
	}
	
	public GPSTracker getLocalizador() {
		return localizador;
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("teste", "passou");
		
	}

	@Override
	public void onConnected(Bundle arg0) {

		this.localizador.setLocation(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
		
		if(mLocationUpdate){		
			this.localizador.startLocationUpdates(mGoogleApiClient);
		}
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
