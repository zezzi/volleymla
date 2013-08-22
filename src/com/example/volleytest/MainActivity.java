package com.example.volleytest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RequestQueue queueOfRequest;
	public final static String URL = "http://mdwplaces.herokuapp.com/places.json";
	private String TAG = this.getClass().getSimpleName();
	private ArrayList<Lugares> places = new ArrayList();
	private ListView placesList;
	private AdapterForList va;
	private ProgressDialog pd;
	private LayoutInflater layouttInf;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		va = new AdapterForList();
		
		placesList = (ListView) findViewById(R.id.listOfPlaces);
		placesList.setAdapter(va);
		this.queueOfRequest = Volley.newRequestQueue(this);
		startrequest();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void startrequest(){
		JsonArrayRequest requestToServer = new JsonArrayRequest(URL,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseJson(response);
                va.notifyDataSetChanged();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.getMessage());
            }
        });
		queueOfRequest.add(requestToServer);

	}
	
	private void parseJson(JSONArray data){
		ArrayList<Lugares> result = new ArrayList<Lugares>();
		if(data != null && data.length() > 0) {
			JSONArray names;
			String current_name;
			Lugares current_place;
			JSONObject current_object;				

			for (int i = 0;i < data.length();i++) {
				try {
					current_object = data.getJSONObject(i);
					names = current_object.names();
					current_place = new Lugares();
					for (int object_index = 0;object_index < current_object.length();object_index++) {
						current_name = names.getString(object_index);
						if (current_name.equals("title")) {
							current_place.setTitle(current_object.getString(current_name));
						} else if (current_name.equals("desc")) {
							current_place.setDesc(current_object.getString(current_name));
						} else if (current_name.equals("code")) {
							current_place.setCode(current_object.getString(current_name));
						} else if (current_name.equals("address")) {
							current_place.setAddress(current_object.getString(current_name));
						} 
					}
					//Log.i(TAG,current_place.toString());
					result.add(current_place);
				} catch (JSONException e) {
					Log.e("ApiRestaurantes","JSON error" + e.toString());
				}
			}
		}
		places=result;
	}
	
	class AdapterForList extends BaseAdapter{

        @Override
        public int getCount() {
            return places.size();
        }

        @Override
        public Object getItem(int i) {
            return places.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
		public View getView(int position, View convertView, ViewGroup parent) {
        	LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	    View rowView = inflater.inflate(R.layout.listview_row_item, parent, false);
        	    TextView textView = (TextView) rowView.findViewById(R.id.titulo);
        	    TextView textView2 = (TextView) rowView.findViewById(R.id.snippet);
        	    textView.setText(places.get(position).getTitle());
        	    textView2.setText(places.get(position).getDesc());        	  
        	    return rowView;
        	
           
        }

    }

}
