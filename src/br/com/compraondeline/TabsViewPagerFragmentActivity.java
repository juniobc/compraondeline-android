package br.com.compraondeline;


import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import br.com.localizacao.GPSTracker;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;


  

public class TabsViewPagerFragmentActivity extends FragmentActivity implements 
TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
 
	private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabsViewPagerFragmentActivity.TabInfo>();
    private PagerAdapter mPagerAdapter;
    private Fragment currentFragment;
    public static FragmentManager fragmentManager;
    private static GPSTracker localizador;

    private class TabInfo {
         private String tag;
         private Class<?> clss;
         private Bundle args;
         private Fragment fragment;
         TabInfo(String tag, Class<?> clazz, Bundle args) {
             this.tag = tag;
             this.clss = clazz;
             this.args = args;
         }
 
    }

    class TabFactory implements TabContentFactory {
 
        private final Context mContext;

        public TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
 
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabview);
        
        this.intialiseViewPager();

        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
        
        fragmentManager = getSupportFragmentManager();
        
        localizador = new GPSTracker(this);
		
    }
    
    @Override
    protected void onStart(){
    	
    	super.onStart();
    	
    	GPSTracker.mGoogleApiClient.connect();
    	
    }
    
    @Override
    protected void onStop() {
    	GPSTracker.mGoogleApiClient.disconnect();
        super.onStop();
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }

    private void intialiseViewPager() {
 
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Tab1Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this, Tab2Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);

        this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initialiseTabHost(Bundle args) {
    	
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        View tabview = createTabView(mTabHost.getContext(), 0);
        View tabview2 = createTabView(mTabHost.getContext(), 1);
        View tabview3 = createTabView(mTabHost.getContext(), 2);
        
        TabInfo tabInfo = null;
        
        TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, 
        		this.mTabHost.newTabSpec("Tab1").setIndicator(tabview), 
        		( tabInfo = new TabInfo("Tab1", Tab1Fragment.class, args)));        
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        
        TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, 
        		this.mTabHost.newTabSpec("Tab2").setIndicator(tabview2), 
        		( tabInfo = new TabInfo("Tab2", Tab2Fragment.class, args)));        
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        
        TabsViewPagerFragmentActivity.AddTab(this, this.mTabHost, 
        		this.mTabHost.newTabSpec("Tab3").setIndicator(tabview3), 
        		( tabInfo = new TabInfo("Tab3", Tab3Fragment.class, args)));        
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        
        // Default to first tab
        //this.onTabChanged("Tab3");
        
        mTabHost.setOnTabChangedListener(this);
    }

    private static View createTabView(final Context context, final int tab) {
    	
	    View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
	    //ImageButton img_btn = (ImageButton) view.findViewById(R.id.img_tab);
	    TextView txt_aba = (TextView) view.findViewById(R.id.txt_aba);
	    
	    switch(tab){
	    
		    case 0:
		    	//img_btn.setImageResource(R.drawable.consulta);
		    	txt_aba.setText("Mapa");
		    	txt_aba.setTextColor(Color.parseColor("#FFFFFF"));
		    	break;
		    	
		    case 1:
		    	//img_btn.setImageResource(R.drawable.cadastro);
		    	txt_aba.setText("Cadastro");
		    	txt_aba.setTextColor(Color.parseColor("#FFFFFF"));
		    	break;
		    	
		    case 2:
		    	//img_btn.setImageResource(R.drawable.cadastro);
		    	txt_aba.setText("Consulta");
		    	txt_aba.setTextColor(Color.parseColor("#FFFFFF"));
		    	break;
	    
	    }

	    return view;
	}

 
    private static void AddTab(TabsViewPagerFragmentActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {

        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
                
    }


    public void onTabChanged(String tag) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }
 
    /* (non-Javadoc)
     * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        // TODO Auto-generated method stub
 
    }
 
    /* (non-Javadoc)
     * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
     */
    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        this.mTabHost.setCurrentTab(position);
        
        if(position==0){
        	
        	Tab1Fragment.atualizaProdutoMapa(this);
        	
        	/*GPSTracker gps;
        	this.currentFragment = mPagerAdapter.getItem(mViewPager.getCurrentItem());

    		gps = new GPSTracker(TabsViewPagerFragmentActivity.this);

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			Tab2Fragment.updateLocation(latitude, longitude);*/
        	
        }else if(position==2){
        	
        	Tab3Fragment.listaProduto(this);
        	
        }
        
    }
    
    public interface OnRefreshListener {
        public void onRefresh();
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
 
}
