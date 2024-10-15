package com.example.abs4sow

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.ActivityRecognition


class MainActivity : ComponentActivity() {

    // EDIT TEXT VARIABLES
    private lateinit var mEditFile: EditText
    private lateinit var mEditMeasures: EditText

    // GRAPHIC TEXTVIEW VARIABLES
    private var textWIFI: TextView? = null
    private var textGoogle: TextView? = null
    private var textactivity: TextView? = null
    private var textzone: TextView? = null
    private var textplace: TextView? = null
    private var textsave: TextView? = null

    // BUTTON VARIABLES
    private var buttonEating: Button? = null
    private var buttonWalking: Button? = null
    private var buttonShop_windows: Button? = null
    private var buttonBuying: Button? = null
    private var buttonLeasure: Button? = null
    private var buttonShops: Button? = null
    private var buttonHypermarket: Button? = null
    private var buttonOutdoor: Button? = null
    private var buttonRestaurant: Button? = null
    private var buttonEmpty: Button? = null
    private var buttonFlunch: Button? = null
    private var buttonCafetito: Button? = null
    private var buttonMcDonalds: Button? = null
    private var buttonPoly: Button? = null
    private var buttonSpringfield: Button? = null
    private var buttonNo_Place: Button? = null
    private var buttonSave: Button? = null

    //VARIABLES COMPARTIR
    val filename: String = ""
    val measure: String = ""

    //AUX VARIABLES
    var start: Boolean = false
    var activity: String = ""
    var zone: String = ""
    var place: String = ""

    //DETECTED ACTIVITY GOOGLE VARIABLES
    var apiClient: GoogleApiClient? = null
    var myReceiver: BroadcastReceiver? = null
    var TIMER: Int = 1000 // 1 sec
    private var Mfile: String? = null
    private var Mmeaures: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // INITIALIZE EDIT FIELDS VARIABLES
        mEditFile = findViewById(R.id.editText2);
        mEditMeasures = findViewById(R.id.editText);

        // INITIALIZE TEXTFIELDS
        textWIFI = findViewById(R.id.textView);
        textGoogle = findViewById(R.id.textView45);
        textactivity = findViewById(R.id.tvactivity);
        textzone = findViewById(R.id.tvzone);
        textplace = findViewById(R.id.tvplace);
        textsave = findViewById(R.id.textView44);

        // INITIALIZE BUTTONS
        var startButton = findViewById(R.id.start);
        var stopButton = findViewById(R.id.stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        buttonEating = findViewById(R.id.eating);
        buttonWalking = findViewById(R.id.walking);
        buttonShop_windows =  findViewById(R.id.shopwindow);
        buttonBuying = findViewById(R.id.buying);
        buttonLeasure = findViewById(R.id.leisure);
        buttonShops = findViewById(R.id.shops);
        buttonHypermarket = findViewById(R.id.hypermarket);
        buttonOutdoor = findViewById(R.id.outdoor);
        buttonRestaurant = findViewById(R.id.restaurants);
        buttonEmpty = findViewById(R.id.empty);
        buttonFlunch = findViewById(R.id.flunch);
        buttonCafetito = findViewById(R.id.cafetito);
        buttonPoly = findViewById(R.id.poly);
        buttonSpringfield =  findViewById(R.id.sprinfield);
        buttonNo_Place =  findViewById(R.id.noplace);
        buttonSave = findViewById(R.id.save);
        buttonMcDonalds = findViewById(R.id.mcDonals);

        //SERVICE DETECTED ACTIVITY GOOGLE
        apiClient = GoogleApiClient.Builder(this)
            .addApi(ActivityRecognition.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build();

        apiClient.connect();

        //  VERIFY PLAY SERVICES IS ACTIVE AND UP-TO-DATE
        checkGooglePlayServicesAvailable(MainActivity.this);

        //ACCESS LOCALITATION
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String GoogleAc = intent.getStringExtra(ActivityService.googleac);
                    //String WifiAP = intent.getStringExtra(ActivityService.Nwifi);
                    textGoogle.setText("Google:" + GoogleAc);
                    //textWIFI.setText("WIFI AP Number: " + WifiAP);
                }
            },new IntentFilter(ActivityService.ACTION_DATA)
        );
    }

    @Override
    protected fun void onPause() {
        super.onPause();
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putBoolean("estado", start);
        editor.commit();
    }

    @Override
    protected fun void onResume() {
        super.onResume();
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        start = preferencias.getBoolean("estado",false);
    }

    @Override
    protected fun void onStop() {super.onStop();}

    @Override
    protected fun void onRestart() {
        super.onRestart();
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        start = preferencias.getBoolean("estado",false);
    }

    @Override
    protected fun void onStart() {super.onStart();}

    fun onClick(view: View) {
        when(view) {
            R.id.start {
                Mfile = mEditFile.text.toString();
                Mmeaures = mEditMeasures.text.toString();

                //INITIALIZE MAIN SERVICE
                Intent startIntent = new Intent(MainActivity.this, ActivityService.class);
                startIntent.setAction(Constants.ACTION.START_ACTION);
                startIntent.putExtra(filename, Mfile);
                //startIntent.putExtra(measure,Mmeaures);
                startService(startIntent);

                start = true;
                Toast.makeText(MainActivity.this, "ABS4SOW Started", Toast.LENGTH_SHORT).show();

                //GOOGLE ACTIVITY START
                Intent intent2 = new Intent(MainActivity.this, ActivityRecognizedService.class);
                PendingIntent pendingIntent = PendingIntent . getService (MainActivity.this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                    apiClient,
                    TIMER,
                    pendingIntent
                );
            }

            case R.id.stop:
            if(start) {
                //STOP MAIN SERVICE
                Intent stopIntent = new Intent(MainActivity.this, ActivityService.class);
                stopIntent.setAction(Constants.ACTION.STOP_ACTION);
                startService(stopIntent);

                //DELETED SERVICE GOOGLE ACTIVITY
                Intent intent = new Intent(MainActivity.this, ActivityRecognizedService.class);
                pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(apiClient, pendingIntent);
                LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);

                start = false;

                //DELETED DATA
                textactivity.setText("Activity:");
                textzone.setText("Zone:");
                textplace.setText("Place:");
                textGoogle.setText("Google:");
                textWIFI.setText("WIFI AP Number:");
            }
            break;

            case R.id.button19:
            if(start) {
                //SAVE MAIN SERVICE
                Intent saveIntent = new Intent(MainActivity.this, ActivityService.class);
                saveIntent.setAction(Constants.ACTION.SAVE_ACTION);
                startService(saveIntent);

                textsave.setText(activity + " ; " + zone + " ; " + place);
            }
            break;

            //Button Activities
            case R.id.button4:

            Intent EatingIntent = new Intent(MainActivity.this, ActivityService.class);
            EatingIntent.setAction(Constants.ACTIVITY.EATING_ACTION);
            startService(EatingIntent);
            activity = "Eating";
            textactivity.setText("Activity: " + activity);

            break;

            case R.id.button5:

            Intent WalkingIntent = new Intent(MainActivity.this, ActivityService.class);
            WalkingIntent.setAction(Constants.ACTIVITY.WALKING_ACTION);
            startService(WalkingIntent);
            activity = "Walking";
            textactivity.setText("Activity: " + activity);

            break;

            case R.id.button6:

            Intent WindowsIntent = new Intent(MainActivity.this, ActivityService.class);
            WindowsIntent.setAction(Constants.ACTION.SHOPWINDOWS_ACTION);
            startService(WindowsIntent);
            activity = "Shop Windows";
            textactivity.setText("Activity: " + activity);

            break;

            case R.id.button7:

            Intent BuyingIntent = new Intent(MainActivity.this, ActivityService.class);
            BuyingIntent.setAction(Constants.ACTION.BUYING_ACTION);
            startService(BuyingIntent);
            activity = "Buying";
            textactivity.setText("Activity: " + activity);

            break;

            case R.id.button8:

            Intent LeisureIntent = new Intent(MainActivity.this, ActivityService.class);
            LeisureIntent.setAction(Constants.ACTION.LEISURE_ACTION);
            startService(LeisureIntent);
            activity = "Leisure";
            textactivity.setText("Activity: " + activity);

            break;

            //Buttons zone
            case R.id.button12:

            Intent RestaurantsIntent = new Intent(MainActivity.this, ActivityService.class);
            RestaurantsIntent.setAction(Constants.ACTION.RESTAURANTS_ACTION);
            startService(RestaurantsIntent);
            zone = "Restaurants";
            textzone.setText("Zone: " + zone);

            break;

            case R.id.button10:

            Intent HypermarketIntent = new Intent(MainActivity.this, ActivityService.class);
            HypermarketIntent.setAction(Constants.ACTION.HYPERMARKET_ACTION);
            startService(HypermarketIntent);
            zone = "Hypermarket";
            textzone.setText("Zone: " + zone);

            break;

            case R.id.button11:

            Intent OutdoorIntent = new Intent(MainActivity.this, ActivityService.class);
            OutdoorIntent.setAction(Constants.ACTION.OUTDOOR_ACTION);
            startService(OutdoorIntent);
            zone = "Outdoor";
            textzone.setText("Zone: " + zone);

            break;

            case R.id.button9:

            Intent ShopIntent = new Intent(MainActivity.this, ActivityService.class);
            ShopIntent.setAction(Constants.ACTION.SHOPS_ACTION);
            startService(ShopIntent);
            zone = "Shops";
            textzone.setText("Zone: " + zone);

            break;

            case R.id.button13:

            Intent EmptyIntent = new Intent(MainActivity.this, ActivityService.class);
            EmptyIntent.setAction(Constants.ACTION.EMPTY_ACTION);
            startService(EmptyIntent);
            zone = "Empty";
            textzone.setText("Zone: " + zone);

            break;

            //Buttons place
            case R.id.button20:

            Intent McDonaldsIntent = new Intent(MainActivity.this, ActivityService.class);
            McDonaldsIntent.setAction(Constants.ACTION.MC_DONALDS_ACTION);
            startService(McDonaldsIntent);
            place = "Mc Donalds";
            textplace.setText("Place: " + place);

            break;

            case R.id.button14:

            Intent FlunchIntent = new Intent(MainActivity.this, ActivityService.class);
            FlunchIntent.setAction(Constants.ACTION.FLUNCH_ACTION);
            startService(FlunchIntent);
            place = "Flunch";
            textplace.setText("Place: " + place);

            break;

            case R.id.button15:

            Intent CafetitoIntent = new Intent(MainActivity.this, ActivityService.class);
            CafetitoIntent.setAction(Constants.ACTION.CAFETITO_ACTION);
            startService(CafetitoIntent);
            place = "Cafetito";
            textplace.setText("Place: " + place);

            break;

            case R.id.button16:

            Intent PolyIntent = new Intent(MainActivity.this, ActivityService.class);
            PolyIntent.setAction(Constants.ACTION.POLY_ACTION);
            startService(PolyIntent);
            place = "Poly";
            textplace.setText("Place: " + place);

            break;

            case R.id.button17:
            Intent SpringfieldIntent = new Intent(MainActivity.this, ActivityService.class);
            SpringfieldIntent.setAction(Constants.ACTION.SPRINGFIELD_ACTION);
            startService(SpringfieldIntent);
            place = "Springfield";
            textplace.setText("Place: " + place);

            break;

            case R.id.button18:
            Intent NoplaceIntent = new Intent(MainActivity.this, ActivityService.class);
            NoplaceIntent.setAction(Constants.ACTION.NO_PLACE_ACTION);
            startService(NoplaceIntent);
            place = "No place";
            textplace.setText("Place: " + place);

            break;

            default:
            break;
        }
    }

    //DETECTED ACTIVITY GOOGLE
    public void checkGooglePlayServicesAvailable(MainActivity activity) {
        int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result))
                googleAPI.getErrorDialog(activity, result, PLAY_SERVICES_RESOLUTION_REQUEST).show();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    //END DETECTED ACTIVITY GOOGLE

}