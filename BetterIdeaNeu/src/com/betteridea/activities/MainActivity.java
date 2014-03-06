package com.betteridea.activities;

import com.example.betteridea.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private String[] navigation;
	private DrawerLayout drawerLayout;
	private ListView navigationList;
	private FragmentManager fragmentManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.drawer_layout);
        
        Fragment fragment = new MainFragment();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
        	.add(R.id.content_frame,fragment)
        	.commit();
        
        navigation = getResources().getStringArray(R.array.navigation_entries);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationList = (ListView) findViewById(R.id.left_drawer);        
        navigationList.setAdapter(new ArrayAdapter<String>(this,R.layout.navigation_list_item,navigation));
//        navigationList.setItemChecked(0,true);
        navigationList.setOnItemClickListener(new NavigationItemClickListener());
    }
    
    private class NavigationItemClickListener implements ListView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
		
		private void selectItem(int position){
			Fragment fragment = new MainFragment();
			
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment)
					.commit();
			
			navigationList.setItemChecked(position, true);
			getActionBar().setTitle(navigation[position]);
			drawerLayout.closeDrawer(navigationList);
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.action_add:
    		return true;
    	case R.id.action_logout:
    		Intent intent = new Intent(this,LoginActivity.class);
    		startActivity(intent);
    		System.out.println("Test");
    		return true;
    	case R.id.action_settings:
    		Intent intent1 = new Intent(this,SettingActivity.class);
    		startActivity(intent1);
    		System.out.println("Test");
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}
