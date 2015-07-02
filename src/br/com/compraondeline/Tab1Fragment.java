package br.com.compraondeline;

import java.util.List;

import br.com.entidade.Produto;
import br.com.googleplay.GoogleServicos;
import br.com.localizacao.GPSTracker;
import br.com.maps.CustomMapFragment;
import br.com.model.ProdutoDB;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
  
/**
 * @author Sebastiao Junio
 *
 */
public class Tab1Fragment extends Fragment implements CustomMapFragment.OnMapReadyListener {
	
	private static GoogleMap mMap;
	private GoogleServicos googleServico;
	private static CustomMapFragment mMapFragment;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.tab1, container, false);
    	
    	googleServico = new GoogleServicos(getActivity());
        googleServico.setmLocationUpdate(true);
        googleServico.connect();
    	
    	mMapFragment = CustomMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();
    	        
        return view;
        
    }
    @Override
    public void onMapReady() {
    	
        mMap = mMapFragment.getMap();
        mMap.setMyLocationEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(
        		googleServico.getLocalizador().getLocation().getLongitude(), 
        		googleServico.getLocalizador().getLocation().getLatitude()), 15
        );
        mMap.animateCamera(cameraUpdate);
        
        for (Produto object : buscaProduto()) {
			mMap.addMarker(new MarkerOptions()
	        .position(new LatLng(Double.parseDouble(object.getNrLat().replace(",", ".")), 
	        		Double.parseDouble(object.getNrLong().replace(",", "."))))
	        .title(object.getNome())
	        );
		}
        
    }
    
    public List<Produto> buscaProduto(){
    	
    	List<Produto> produtoList ;
    	
    	ProdutoDB db = new ProdutoDB(getActivity());
    	
    	produtoList = db.getAllProdutos();
    	
    	return produtoList;
		
	}

}
