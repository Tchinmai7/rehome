package rehome.in.rehome;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class tabs extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public static ProgressDialog pDialog;
    ViewPager mViewPager;
    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        Intent intent = getIntent();
              email=intent.getStringExtra("email");
        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffffff")));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setImageResource(R.drawable.rehome);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.START | Gravity.LEFT
        );
        layoutParams.rightMargin = 100;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle(Html.fromHtml(""));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public static class HallFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int switches[] = {R.id.switch1, R.id.switch2, R.id.switch3, R.id.switch4, R.id.switch5, R.id.switch6, R.id.switch7, R.id.switch8, R.id.switch9, R.id.switch10};
        int edittexts[] = {R.id.editText, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7, R.id.editText8, R.id.editText9, R.id.editText10 };
        String s[] = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10"};
        String names[];
        EditText edt[]=new EditText[10];
        public HallFragment() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static HallFragment newInstance(int sectionNumber) {
            HallFragment fragment = new HallFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        ArrayList<Integer> al;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_hall, container, false);
            SharedPreferences prefs = getActivity().getSharedPreferences("hall", Context.MODE_PRIVATE);
             al=new ArrayList<>();
           final  RelativeLayout rl=(RelativeLayout)rootView.findViewById(R.id.rr1);

            Button b1=(Button)rootView.findViewById(R.id.button1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout ll1=new LinearLayout(getActivity());

                    Switch s1=new Switch(getActivity());
                    EditText ed1=new EditText(getActivity());
                    RelativeLayout.LayoutParams rp1=new RelativeLayout.LayoutParams(RelativeLayout.BELOW,R.id.ll10);
                    LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    ll1.addView(s1,lp1);
                    ll1.addView(ed1,lp1);
                   rl.addView(ll1,rp1);
                    Log.d("onclick","ss");
                }
            });
            names = new String[11];
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Black.ttf");
            for (int i : edittexts) {
                EditText t = (EditText) rootView.findViewById(i);
                t.setTypeface(font);
            }
            for(int i:switches)
            {
                Switch s=(Switch)rootView.findViewById(i);
                s.setOnCheckedChangeListener(this);
                al.add(i);
            }
            for (int i = 0; i < 10; i++) {
                names[i] = prefs.getString(s[i], "name");
                edt[i]=(EditText)rootView.findViewById(edittexts[i]);
                Log.d("" + i, names[i]);
                edt[i].setText(names[i]);
            }

            return rootView;

        }

@Override
public void onDestroyView()
{
    super.onDestroyView();
    SharedPreferences prefs = getActivity().getSharedPreferences("hall", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    for(int i=0;i<10;i++)
    {
        names[i]=edt[i].getText().toString();
        editor.putString(s[i],names[i]);
        editor.commit();
        Log.d(s[i],names[i]);
    }
}
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            UserFunctions uf=new UserFunctions();
          int id=buttonView.getId();
            ArrayList<Integer> al=new ArrayList<>(12);
            for(int i=0;i<10;i++)
                al.add(switches[i]);
            int index=al.indexOf(id);
            uf.execute(s[index]);

        }
    }

    public static class BedroomFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int switches[] = {R.id.switch11, R.id.switch12, R.id.switch13, R.id.switch14, R.id.switch15, R.id.switch16, R.id.switch17, R.id.switch18, R.id.switch19, R.id.switch20};
        int edittexts[] = {R.id.editText11, R.id.editText12, R.id.editText13, R.id.editText14, R.id.editText15, R.id.editText16, R.id.editText17, R.id.editText18, R.id.editText19, R.id.editText20};
        String s[] = {"switch11", "switch12", "switch13", "switch14", "switch15", "switch16", "switch17", "switch18", "switch19", "switch20"};
        String names[];
        EditText edt[]=new EditText[10];

        public BedroomFragment() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static BedroomFragment newInstance(int sectionNumber) {
            BedroomFragment fragment = new BedroomFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bedroom, container, false);
            SharedPreferences prefs = getActivity().getSharedPreferences("bedroom", Context.MODE_PRIVATE);
            names = new String[11];
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Black.ttf");
            for (int i : edittexts) {
                EditText t = (EditText) rootView.findViewById(i);
                t.setTypeface(font);
            }
            for(int i:switches)
            {
                Switch s=(Switch)rootView.findViewById(i);
                s.setOnCheckedChangeListener(this);

            }
            for (int i = 0; i < 10; i++) {
                names[i] = prefs.getString(s[i], "name");
                edt[i]=(EditText)rootView.findViewById(edittexts[i]);
                Log.d("" + i, names[i]);
                edt[i].setText(names[i]);
            }

            return rootView;

        }
        @Override
        public void onDestroyView()
        {
            super.onDestroyView();
            SharedPreferences prefs = getActivity().getSharedPreferences("bedroom", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            for(int i=0;i<10;i++)
            {
                names[i]=edt[i].getText().toString();
                editor.putString(s[i],names[i]);
                editor.commit();
            }
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            UserFunctions uf=new UserFunctions();
            int id=buttonView.getId();
            //int index=switches.indexOf(id);
            ArrayList<Integer> al=new ArrayList<>(12);
            for(int i=0;i<10;i++)
                al.add(switches[i]);
            int index=al.indexOf(id);
            uf.execute(s[index]);
        }
    }



    public static class BedroomFragment2 extends Fragment implements CompoundButton.OnCheckedChangeListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public BedroomFragment2() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static BedroomFragment2 newInstance(int sectionNumber) {
            BedroomFragment2 fragment = new BedroomFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        int switches[] = {R.id.switch1, R.id.switch2, R.id.switch2, R.id.switch3, R.id.switch3, R.id.switch4, R.id.switch5, R.id.switch6, R.id.switch7, R.id.switch8, R.id.switch9, R.id.switch10};
        int edittexts[] = {R.id.editText21, R.id.editText22, R.id.editText23, R.id.editText24, R.id.editText25, R.id.editText26, R.id.editText27, R.id.editText28, R.id.editText29, R.id.editText30};
        String s[] = {"switch21", "switch2", "switch3", "switch4", "switch5", "switch6", "switch7", "switch8", "switch9", "switch10"};
        String names[];
        EditText edt[]=new EditText[10];

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_bedroom2, container, false);
            SharedPreferences prefs = getActivity().getSharedPreferences("Bedroom2", Context.MODE_PRIVATE);

            names = new String[11];
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Black.ttf");
            for (int i : edittexts) {
                EditText t = (EditText) rootView.findViewById(i);
                t.setTypeface(font);
            }
            for(int i:switches)
            {
                Switch s=(Switch)rootView.findViewById(i);
                s.setOnCheckedChangeListener(this);

            }
            for (int i = 0; i < 10; i++) {
                names[i] = prefs.getString(s[i], "name");
                edt[i]=(EditText)rootView.findViewById(edittexts[i]);
                Log.d("" + i, names[i]);
                edt[i].setText(names[i]);
            }

            return rootView;

        }
        @Override
        public void onDestroyView()
        {
            super.onDestroyView();
            SharedPreferences prefs = getActivity().getSharedPreferences("Bedroom2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            for(int i=0;i<10;i++)
            {
                names[i]=edt[i].getText().toString();
                editor.putString(s[i],names[i]);
                editor.commit();
            }
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            UserFunctions uf=new UserFunctions();
            int id=buttonView.getId();
            //int index=switches.indexOf(id);
            ArrayList<Integer> al=new ArrayList<>(12);
            for(int i:switches)
                al.add(switches[i]);
            int index=al.indexOf(id);
         //   uf.postUpdate("tchinmai7@gmail.com",s[index]);
        }
    }


    public static class KitchenFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public KitchenFragment() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static KitchenFragment newInstance(int sectionNumber) {
            KitchenFragment fragment = new KitchenFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_kitchen, container, false);
            return rootView;
        }
    }

    public static class BathroomFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public BathroomFragment() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static BathroomFragment newInstance(int sectionNumber) {
            BathroomFragment fragment = new BathroomFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bathroom, container, false);
            return rootView;
        }
    }

    public static class BathroomFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public BathroomFragment2() {

        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static BathroomFragment2 newInstance(int sectionNumber) {
            BathroomFragment2 fragment = new BathroomFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bathroom2, container, false);

            return rootView;
        }
    }
    public static class favoriteFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public favoriteFragment() {
        }

        /**
         * Returns a new instance of this fragment for the g0iven section
         * number.
         */
        public static favoriteFragment newInstance(int sectionNumber) {
           favoriteFragment fragment = new favoriteFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.favorites, container, false);
            LinearLayout ll1=new LinearLayout(getActivity());
            final  RelativeLayout rl=(RelativeLayout)rootView.findViewById(R.id.favrr);
            EditText ed1=new EditText(getActivity());
            Switch s1=new Switch(getActivity());

            //  RelativeLayout.LayoutParams rp1=new RelativeLayout.LayoutParams(RelativeLayout.BELOW,R.id.ll10);
            LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            ll1.addView(s1,lp1);
            ll1.addView(ed1,lp1);
            rl.addView(ll1);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position==0)
                return favoriteFragment.newInstance(position);
            else if (position == 1)
                return HallFragment.newInstance(position);
            else if (position == 2)
                return BedroomFragment.newInstance(position);
            else if (position == 3)
                return BedroomFragment2.newInstance(position);
            else if (position == 4)
                return KitchenFragment.newInstance(position);
            else if (position == 5)
                return BathroomFragment.newInstance(position);
            else
                return BathroomFragment2.newInstance(position);

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Favorites";
                case 1:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 3:
                    return "Bedroom 2";

                case 4:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 5:
                    return "Bathroom 1";
                case 6:
                    return "Bathroom 2";
            }
            return null;
        }
    }
    public static class UserFunctions extends AsyncTask<String,Void,JSONObject>{

        private JSONParser jsonParser;
        private  String loginURL = "http://rehome.in/google/sync.php";
        private  String update_tag = "update";
        public UserFunctions(){
            jsonParser = new JSONParser();
        }
        SharedPreferences sharedPreferences;

        @Override
        protected JSONObject doInBackground(String... switchid) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String j="{\"tag\":\""+update_tag+"\",\"email\":\""+email+"\",\"switchid\":\""+switchid[0]+"\"}";

       //     params.add(new BasicNameValuePair("tag", update_tag));
         //   params.add(new BasicNameValuePair("email", email));
           // params.add(new BasicNameValuePair("switchid", switchid[0]));
            params.add(new BasicNameValuePair("params",j));
            //Log.e("JSON", json.toString());
            return jsonParser.getJSONFromUrl(loginURL, params);
        }


    }



    JSONArray responses = null;
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> responseList = new ArrayList<>();

    String url="http://rehome.in/google/sync.php";
    public String status;
  /*  private class getStatus extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            int i;
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    responses = jsonObj.getJSONArray("switches");
                    for (i = 0; i < responses.length() - 1; i++) {
                        // looping through All Contacts
                        JSONObject c = responses.getJSONObject(i);

                        // tmp hashmap for single contact
                        // adding each child node to HashMap key => value
                        HashMap<String, String> response = new HashMap<>();
                        response.put(TAG_QUESTION, question);
                        response.put(TAG_OPTION1, option1);
                        response.put(TAG_OPTION2, option2);
                        response.put(TAG_OPTION3, option3);
                        response.put(TAG_OPTION4, option4);
                        response.put(TAG_ANSWER, answer);
                        responseList.add(response);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Contacting Servers...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            setContentView(R.layout.ques);
            View v = findViewById(R.id.ques);
            ques = (TextView) findViewById(R.id.ques);
            score = (TextView) findViewById(R.id.score);
            tv[1] = (TextView) findViewById(R.id.card1);
            tv[2] = (TextView) findViewById(R.id.card2);
            tv[3] = (TextView) findViewById(R.id.card3);
            tv[4] = (TextView) findViewById(R.id.card4);
            score.setText("Me:" + mScore);
            printer(qno);
            tv[1].setOnClickListener(listener);
            tv[2].setOnClickListener(listener);
            tv[3].setOnClickListener(listener);
            tv[4].setOnClickListener(listener);
            if (v != null) {
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mSecondsLeft <= 0)
                            return;
                        gameTick();
                        h.postDelayed(this, 1000);
                    }
                }, 1000);
            }
        }*/
}
