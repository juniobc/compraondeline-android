package br.com.compraondeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
  
/**
 * @author mwho
 *
 */
public class Tab1Fragment extends Fragment {
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.tab1, container, false);
        
        return view;
        
    }
    

}
