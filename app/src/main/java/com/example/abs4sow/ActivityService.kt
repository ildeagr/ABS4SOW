package com.example.abs4sow

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;

import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class ActivityService {

//VARIABLES COMPARTIR
    public static final String googleac = "";
    public static final String Nwifi = "";
    public static final String ACTION_DATA = ActivityService.
    class.getName();
    public static final String ACTION_WIFI =ActivityService.
    class.getName();

    private SensorManager senSensorManagerAccelerometer;
    private Sensor senAccelerometer;
    private SensorManager senSensorManagerLinearAccelerometer;
    private Sensor senLinearAccelerometer;
    private SensorManager senSensorManagerGyroscope;
    private Sensor senGyroscope;
    private SensorManager senSensorManagerOrientation;
    private Sensor senOrientation;
    private SensorManager senSensorManagerGameRotation;
    private Sensor senGameRotation;
    private SensorManager senSensorManagerGeomagneticRotation;
    private Sensor senGeomagneticRotation;
    private SensorManager senSensorManagerMagnetometer;
    private Sensor senMagnetometer;
    private SensorManager senSensorManagerProximity;
    private Sensor senProximity;
    private SensorManager senSensorManagerLight;
    private Sensor senLight;
    private SensorManager senSensorManagerPressure;
    private Sensor senPressure;
    private SensorManager senSensorManagerStep;
    private Sensor senStep;
    private SensorManager senSensorManagerHumidity;
    private Sensor senHumidity;
    private SensorManager senSensorManagerTemperature;
    private Sensor senTemperature;
    private SensorManager senSensorManagerHeartRate;
    private Sensor senHeartRate;
    private SensorManager senSensorManagerGravity;
    private Sensor senGravity;

    // WIFI VARIABLES
    private WifiManager mainWifiObj;
    WifiScanReceiver wifiReceiver;

    // BLUETOOTH VARIABLES
    private BluetoothManager bluetoothObj;
    BluetoothAdapter adapter;
    BluetoothAdapter adapterLE;
    BluetoothLeScanner adapterLE_new;


    // GPS VARIABLES
    private LocationManager locationManager;
    private LocationListener mlocListener;
    private LocationListener mlocListenerN;

    // CELLS VARIABLES
    private TelephonyManager tm;
    private Handler mHandler;


    // DATA FILES VARIABLES
    FileOutputStream fOutACC;
    FileOutputStream fOutLACC;
    FileOutputStream fOutGYR;
    FileOutputStream fOutMAG;
    FileOutputStream fOutORI;
    FileOutputStream fOutGAR;
    FileOutputStream fOutGER;
    FileOutputStream fOutGRA;
    FileOutputStream fOutLGT;
    FileOutputStream fOutPRX;
    FileOutputStream fOutPRS;
    FileOutputStream fOutSTEP;
    FileOutputStream fOutTMP;
    FileOutputStream fOutHMD;
    FileOutputStream fOutHRT;
    FileOutputStream fOutBLU;
    FileOutputStream fOutWIF;
    FileOutputStream fOutCEL;
    FileOutputStream fOutGPS;
    FileOutputStream fOutGPSN;
    FileOutputStream fOutWAYPOINT;
    FileOutputStream fOutBATTERY;
    FileOutputStream fOutUser;
    FileOutputStream fOutGoogleActi;

    File fileACC;
    File fileLACC;
    File fileGYR;
    File fileMAG;
    File fileORI;
    File fileGAR;
    File fileGER;
    File fileGRA;
    File fileLGT;
    File filePRX;
    File filePRS;
    File fileSTEP;
    File fileTMP;
    File fileHMD;
    File fileHRT;
    File fileBLU;
    File fileWIF;
    File fileCEL;
    File fileGPS;
    File fileGPSN;
    File fileWAYPOINT;
    File fileBATTERY;
    File fileUser;
    File fileGoogleActi;

    String data ;
    String dataACC = "";
    String dataLACC = "";
    String dataGYR = "";
    String dataMAG = "";
    String dataORI = "";
    String dataGAR = "";
    String dataGER = "";
    String dataGRA = "";
    String dataLGT = "";
    String dataPRX = "";
    String dataPRS = "";
    String dataSTEP = "";
    String dataTMP = "";
    String dataHMD = "";
    String dataHRT = "";
    String dataBLU = "";
    String dataWIF = "";
    String dataCEL = "";
    String dataGPS = "";
    String dataGPSN = "";
    String dataWAYPOINT = "";
    String dataBATTERY = "";
    String dataUser = "";
    String dataGoogleActi = "";

    int iACC = 0;
    int iLACC = 0;
    int iGYR = 0;
    int iMAG = 0;
    int iORI = 0;
    int iGAR = 0;
    int iGER = 0;
    int iGRA = 0;
    int iLGT = 0;
    int iPRX = 0;
    int iPRS = 0;
    int iSTEP = 0;
    int iTMP = 0;
    int iHMD = 0;
    int iHRT = 0;
    int iBLU = 0;
    int iWIF = 0;
    int iCEL = 0;
    int iGPS = 0;
    int iGPSN = 0;
    int iBATTERY = 0;

    boolean ACC = true;
    boolean LACC = true;
    boolean GYR = true;
    boolean MAG = true;
    boolean ORI = true;
    boolean GAR = true;
    boolean GER = true;
    boolean GRA = true;
    boolean LGT = true;
    boolean PRX = true;
    boolean PRS = true;
    boolean STEP = true;
    boolean TMP = true;
    boolean HMD = true;
    boolean HRT = true;
    boolean BLU = true;
    boolean WIF = true;
    boolean CEL = true;
    boolean GPS = true;
    boolean GPSN = true;


    // GRAPHIC TEXTVIEW VARIABLES
    private TextView textACCX;
    private TextView textACCY;
    private TextView textACCZ;
    private TextView textLACCX;
    private TextView textLACCY;
    private TextView textLACCZ;
    private TextView textGYRX;
    private TextView textGYRY;
    private TextView textGYRZ;
    private TextView textMAGX;
    private TextView textMAGY;
    private TextView textMAGZ;
    private TextView textROLL;
    private TextView textPITCH;
    private TextView textYAW;
    private TextView textGARROLL;
    private TextView textGARPITCH;
    private TextView textGARYAW;
    private TextView textGERROLL;
    private TextView textGERPITCH;
    private TextView textGERYAW;
    private TextView textLIGHT;
    private TextView textPROXIMITY;
    private TextView textPRESSURE;
    private TextView textSTEP;
    private TextView textTEMPERATURE;
    private TextView textHEARTRATE;
    private TextView textHUMIDITY;
    private TextView textWIFI;
    private TextView textBLUETOOTH;
    private TextView textGRAVITYX;
    private TextView textGRAVITYY;
    private TextView textGRAVITYZ;
    private TextView textCELL;
    private TextView textGPS;

    //VARIABLES
    Integer count = 0;
    private Integer medidas = 0;

    // AUX VARIABLES
    private IntentFilter filter;
    String activity ="";
    String zone ="";
    String place ="";

    Integer contador = 0;
    Integer limite = 10;

    Vibrator v;
    private String archivo = "/mnt/sdcard/mydata";

    //DETECTED ACTIVITY GOOGLE VARIABLES
    BroadcastReceiver myReceiver;
    private String[] Search =
    { "In_Vehicle:", "On_Bicycle:", "On_Foot:", "Running:", "Still:", "Tilting:", "Walking:", "Unknown:" };
    private String Google = "";

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();

        //SERVICE DETECTED ACTIVITY GOOGLE
        myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra(ActivityRecognizedService.LOCAL_BROADCAST_EXTRA);
                String[] parts = message.split(" ");
                Google = "";
                for (int i = 0; i < Search.length; i++) {
                boolean encontrado = false;
                for (int j = 0; j < parts.length; j++) {
                if (Search[i].equals(parts[j])) {
                    Google = Google + "," + parts[j + 1];
                    encontrado = true;
                }
            }
                if (encontrado == false) {
                    Google = Google + "," + "0";
                }
            }
                try {
                    dataGoogleActi = (System.currentTimeMillis() * 1000000 + Google + "\n");
                    fOutGoogleActi.write(dataGoogleActi.getBytes());
                    Intent ActionGoogle = new Intent (ACTION_DATA);
                    ActionGoogle.putExtra(googleac, message);
                    LocalBroadcastManager.getInstance(ActivityService.this).sendBroadcast(ActionGoogle);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.START_ACTION)) {

            // INITIALIZE SENSOR EVENT VARIABLES
            if (ACC) senSensorManagerAccelerometer = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (LACC) senSensorManagerLinearAccelerometer = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (GYR) senSensorManagerGyroscope = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (ORI) senSensorManagerOrientation = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (GAR) senSensorManagerGameRotation = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (GER) senSensorManagerGeomagneticRotation = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (MAG) senSensorManagerMagnetometer = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (LGT) senSensorManagerLight = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (PRS) senSensorManagerPressure = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (PRX) senSensorManagerProximity = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (STEP) senSensorManagerStep = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (TMP) senSensorManagerTemperature = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (HMD) senSensorManagerHumidity = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (HRT) senSensorManagerHeartRate = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (GRA) senSensorManagerGravity = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            if (ACC) senAccelerometer = senSensorManagerAccelerometer.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (LACC) senLinearAccelerometer = senSensorManagerLinearAccelerometer.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            if (GYR) senGyroscope = senSensorManagerGyroscope.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if (ORI) senOrientation = senSensorManagerOrientation.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            if (GAR) senGameRotation = senSensorManagerGameRotation.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
            if (GER) senGeomagneticRotation = senSensorManagerGeomagneticRotation.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
            if (MAG) senMagnetometer = senSensorManagerMagnetometer.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            if (LGT) senLight = senSensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (PRS) senPressure = senSensorManagerPressure.getDefaultSensor(Sensor.TYPE_PRESSURE);
            if (PRX) senProximity = senSensorManagerProximity.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (STEP) senStep = senSensorManagerStep.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if (TMP) senTemperature = senSensorManagerTemperature.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            if (HMD) senHumidity = senSensorManagerHumidity.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            if (HRT) senHeartRate = senSensorManagerHeartRate.getDefaultSensor(Sensor.TYPE_HEART_RATE);
            if (GRA) senGravity = senSensorManagerGravity.getDefaultSensor(Sensor.TYPE_GRAVITY);
            // END INITIALIZE SENSOR EVENT VARIABLES

            /// INITIALIZE GPS
            // GPS INITIALIZATION
            if (GPS || GPSN) locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (GPS) mlocListener = new MyLocationListener();
            if (GPSN) mlocListenerN = new MyLocationListenerN();

            if (ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //       ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                //        ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                // TODO PERMISOS
                //   return;
            }
            if (GPS) locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
            if (GPSN) locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListenerN);

            // CELLS INITIALIZATION
            if (CEL) tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

            // VIBRATOR INITIALIZATION
            v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

            // BLUETOOTH INITIALIZATION
            if (BLU) {
                bluetoothObj = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                //adapterLE = bluetoothObj.getAdapter();
                //adapterLE_new = bluetoothObj.getAdapter();
                adapter = BluetoothAdapter.getDefaultAdapter();
                if (!adapter.isEnabled()) {
                    adapter.enable();
                }
                filter = new IntentFilter();
                filter.addAction(BluetoothDevice.ACTION_FOUND);
                filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                // INITIALIZE BLUETOOTH
                //
                // BLUETOOTH
                registerReceiver(mReceiver, filter);

                // REGISTER ACTIVITY GOOGLE
                //registerReceiver(mActivityReceiver, filter);
                LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, new IntentFilter(ActivityRecognizedService.LOCAL_BROADCAST_NAME));

                if (Build.VERSION.SDK_INT >= 21) {


                    adapterLE_new = adapter.getBluetoothLeScanner();
                    ScanSettings settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).setScanMode(ScanSettings.MATCH_MODE_AGGRESSIVE).build();
                    List<ScanFilter> filters = new ArrayList<ScanFilter>();

                    adapterLE_new.startScan(filters, settings, mLeScanCallback_new);
                } else {
                    adapterLE.startLeScan(mLeScanCallback);
                }
            }
            // WIFI INITIALIZATION
            if (WIF) {
                mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                wifiReceiver = new WifiScanReceiver();

                // INITIALIZE WIFI
                mainWifiObj.startScan();
                registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            }

            // INITIALIZE CELLS
            if (CEL) {
                mHandler = new Handler();
                mHandler.post(runnableCode);
            }

            // BATTERY
            registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

            // OBTAIN NAME FROM EDIT FIELD + CURRENT TIMESTAMP
            archivo = Long.toString(System.currentTimeMillis());
            String archivoname = intent.getStringExtra(MainActivity.filename);
            if (!archivoname.isEmpty()) {
                archivo = (archivo + "_" + archivoname);
            }

            /// OPEN FILES TO STORE DATA
            try {
                String filepath = ("MyFileStorage/" + archivo);
                fileACC = new File(getExternalFilesDir(filepath), "ACC.txt");
                fileLACC = new File(getExternalFilesDir(filepath), "LACC.txt");
                fileGYR = new File(getExternalFilesDir(filepath), "GYR.txt");
                fileMAG = new File(getExternalFilesDir(filepath), "MAG.txt");
                fileORI = new File(getExternalFilesDir(filepath), "ORI.txt");
                fileGAR = new File(getExternalFilesDir(filepath), "GAR.txt");
                fileGER = new File(getExternalFilesDir(filepath), "GER.txt");
                fileGRA = new File(getExternalFilesDir(filepath), "GRA.txt");
                fileLGT = new File(getExternalFilesDir(filepath), "LGT.txt");
                filePRX = new File(getExternalFilesDir(filepath), "PRX.txt");
                filePRS = new File(getExternalFilesDir(filepath), "PRS.txt");
                fileSTEP = new File(getExternalFilesDir(filepath), "STEP.txt");
                fileTMP = new File(getExternalFilesDir(filepath), "TMP.txt");
                fileHMD = new File(getExternalFilesDir(filepath), "HMD.txt");
                fileHRT = new File(getExternalFilesDir(filepath), "HRT.txt");
                fileBLU = new File(getExternalFilesDir(filepath), "BLU.txt");
                fileWIF = new File(getExternalFilesDir(filepath), "WIF.txt");
                fileCEL = new File(getExternalFilesDir(filepath), "CEL.txt");
                fileGPS = new File(getExternalFilesDir(filepath), "GPS.txt");
                fileGPSN = new File(getExternalFilesDir(filepath), "GPSN.txt");
                fileWAYPOINT = new File(getExternalFilesDir(filepath), "WAYPOINT.txt");
                fileBATTERY = new File(getExternalFilesDir(filepath), "BATTERY.txt");
                fileUser = new File(getExternalFilesDir(filepath), "User.txt");
                fileGoogleActi = new File(getExternalFilesDir(filepath),"GoogleActi.txt");

                fOutACC = new FileOutputStream(fileACC);
                fOutLACC = new FileOutputStream(fileLACC);
                fOutGYR = new FileOutputStream(fileGYR);
                fOutMAG = new FileOutputStream(fileMAG);
                fOutORI = new FileOutputStream(fileORI);
                fOutGAR = new FileOutputStream(fileGAR);
                fOutGER = new FileOutputStream(fileGER);
                fOutGRA = new FileOutputStream(fileGRA);
                fOutLGT = new FileOutputStream(fileLGT);
                fOutPRX = new FileOutputStream(filePRX);
                fOutPRS = new FileOutputStream(filePRS);
                fOutSTEP = new FileOutputStream(fileSTEP);
                fOutTMP = new FileOutputStream(fileTMP);
                fOutHMD = new FileOutputStream(fileHMD);
                fOutHRT = new FileOutputStream(fileHRT);
                fOutBLU = new FileOutputStream(fileBLU);
                fOutWIF = new FileOutputStream(fileWIF);
                fOutCEL = new FileOutputStream(fileCEL);
                fOutGPS = new FileOutputStream(fileGPS);
                fOutWAYPOINT = new FileOutputStream(fileWAYPOINT);
                fOutGPSN = new FileOutputStream(fileGPSN);
                fOutBATTERY = new FileOutputStream(fileBATTERY);
                fOutUser = new FileOutputStream(fileUser);
                fOutGoogleActi =new FileOutputStream(fileGoogleActi);

                // WRITE DATA FIELDS ON EACH FILE
                String cabecera = "Timestamp,X,Y,Z\n";
                fOutACC.write(cabecera.getBytes());
                fOutLACC.write(cabecera.getBytes());
                fOutGYR.write(cabecera.getBytes());
                fOutMAG.write(cabecera.getBytes());
                cabecera = "Timestamp,Q1,Q2,Q3,Q4,Yaw_Drift\n";
                fOutORI.write(cabecera.getBytes());
                cabecera = "Timestamp,Q1,Q2,Q3,Q4\n";
                fOutGAR.write(cabecera.getBytes());
                fOutGER.write(cabecera.getBytes());
                cabecera = "Timestamp,X,Y,Z\n";
                fOutGRA.write(cabecera.getBytes());
                cabecera = "Timestamp,Luminance\n";
                fOutLGT.write(cabecera.getBytes());
                cabecera = "Timestamp,Proximity\n";
                fOutPRX.write(cabecera.getBytes());
                cabecera = "Timestamp,Pressure\n";
                fOutPRS.write(cabecera.getBytes());
                cabecera = "Timestamp,Steps\n";
                fOutSTEP.write(cabecera.getBytes());
                cabecera = "Timestamp,Temperature\n";
                fOutTMP.write(cabecera.getBytes());
                cabecera = "Timestamp,Humidity\n";
                fOutHMD.write(cabecera.getBytes());
                cabecera = "Timestamp,HeartRate\n";
                fOutHRT.write(cabecera.getBytes());
                cabecera = "Timestamp,Name,SSID,RSSI,Type\n";
                fOutBLU.write(cabecera.getBytes());
                cabecera = "Timestamp,BSSID,SSID,Frequency,RSSI,TimeStampReceived\n";
                fOutWIF.write(cabecera.getBytes());
                cabecera = "Timestamp,Type,Registered,Latitude/Lac/Lac/Tac,Longitude/Mcc/Mcc/Mcc,NetworkId/Mnc/Mnc/Mnc,SystemId//Psc/Pci,BaseStationId/Cid/Cid/Ci,AsuLevel,Dbm,Level,CdmaDbm///,CdmaEcio///,CdmaLevel///,EvdoDbm///,EvdoEcio///,EvdoLevel///,EvdoSnr///,TimeStampReceived\n";
                fOutCEL.write(cabecera.getBytes());
                cabecera = "Timestamp,Latitude,Longitude,Altitude,Accuracy,Bearing,Provider,Speed,UTMTime\n";
                fOutGPS.write(cabecera.getBytes());
                fOutGPSN.write(cabecera.getBytes());
                cabecera = "Timestamp,Health,Level,Plugged,Present,Scale,Status,Technology,Temperature,Voltage\n";
                fOutBATTERY.write(cabecera.getBytes());
                cabecera = "Timestamp,Activity,Zone,Place\n";
                fOutUser.write(cabecera.getBytes());
                cabecera = "Timestamp ,In_Vehicle,On_Bicycle,On_Foot,Running,Still,Tilting,Walking,Unknown \n";
                fOutGoogleActi.write(cabecera.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // INITIALIZE SENSOR EVENT LISTENERS
            if (senAccelerometer != null && ACC)
                senSensorManagerAccelerometer.registerListener(ActivityService.this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            if (senLinearAccelerometer != null && LACC)
                senSensorManagerLinearAccelerometer.registerListener(ActivityService.this, senLinearAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            if (senGyroscope != null && GYR)
                senSensorManagerGyroscope.registerListener(ActivityService.this, senGyroscope, SensorManager.SENSOR_DELAY_FASTEST);
            if (senMagnetometer != null && MAG)
                senSensorManagerMagnetometer.registerListener(ActivityService.this, senMagnetometer, SensorManager.SENSOR_DELAY_FASTEST);
            if (senOrientation != null && ORI)
                senSensorManagerOrientation.registerListener(ActivityService.this, senOrientation, SensorManager.SENSOR_DELAY_FASTEST);
            if (senGameRotation != null && GAR)
                senSensorManagerGameRotation.registerListener(ActivityService.this, senGameRotation, SensorManager.SENSOR_DELAY_FASTEST);
            if (senGeomagneticRotation != null && GER)
                senSensorManagerGeomagneticRotation.registerListener(ActivityService.this, senGeomagneticRotation, SensorManager.SENSOR_DELAY_FASTEST);
            if (senProximity != null && PRX)
                senSensorManagerProximity.registerListener(ActivityService.this, senProximity, SensorManager.SENSOR_DELAY_FASTEST);
            if (senPressure != null && PRS)
                senSensorManagerPressure.registerListener(ActivityService.this, senPressure, SensorManager.SENSOR_DELAY_FASTEST);
            if (senLight != null && LGT)
                senSensorManagerLight.registerListener(ActivityService.this, senLight, SensorManager.SENSOR_DELAY_FASTEST);
            if (senStep != null && STEP)
                senSensorManagerStep.registerListener(ActivityService.this, senStep, SensorManager.SENSOR_DELAY_FASTEST);
            if (senTemperature != null && TMP)
                senSensorManagerTemperature.registerListener(ActivityService.this, senTemperature, SensorManager.SENSOR_DELAY_FASTEST);
            if (senHumidity != null && HMD) {
                senSensorManagerHumidity.registerListener(ActivityService.this, senHumidity, SensorManager.SENSOR_DELAY_FASTEST);
            }
            if (senHeartRate != null && HRT)
                senSensorManagerHeartRate.registerListener(ActivityService.this, senHeartRate, SensorManager.SENSOR_DELAY_FASTEST);
            if (senGravity != null && GRA)
                senSensorManagerGravity.registerListener(ActivityService.this, senGravity, SensorManager.SENSOR_DELAY_FASTEST);


        } else if (intent.getAction().equals(Constants.ACTION.STOP_ACTION)) {

            // STOP SENSOR EVENTS CALLBACKS
            if (ACC) senSensorManagerAccelerometer.unregisterListener(ActivityService.this);
            if (LACC) senSensorManagerLinearAccelerometer.unregisterListener(ActivityService.this);
            if (GYR) senSensorManagerGyroscope.unregisterListener(ActivityService.this);
            if (MAG) senSensorManagerMagnetometer.unregisterListener(ActivityService.this);
            if (ORI) senSensorManagerOrientation.unregisterListener(ActivityService.this);
            if (GAR) senSensorManagerGameRotation.unregisterListener(ActivityService.this);
            if (GER) senSensorManagerGeomagneticRotation.unregisterListener(ActivityService.this);
            if (PRX) senSensorManagerProximity.unregisterListener(ActivityService.this);
            if (PRS) senSensorManagerPressure.unregisterListener(ActivityService.this);
            if (LGT) senSensorManagerLight.unregisterListener(ActivityService.this);
            if (STEP) senSensorManagerStep.unregisterListener(ActivityService.this);
            if (TMP) senSensorManagerTemperature.unregisterListener(ActivityService.this);
            if (HMD) senSensorManagerHumidity.unregisterListener(ActivityService.this);
            if (HRT) senSensorManagerHeartRate.unregisterListener(ActivityService.this);
            if (GRA) senSensorManagerGravity.unregisterListener(ActivityService.this);

            // STOP GPS CALLBACKS
            if (ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                //return;
            }

            if (GPS) locationManager.removeUpdates(mlocListener);
            if (GPSN) locationManager.removeUpdates(mlocListenerN);

            // STOP WIFI CALLBACK
            if (WIF) unregisterReceiver(wifiReceiver);

            unregisterReceiver(batteryInfoReceiver);

            // STOP BLUETOOTH CALLBACK
            //adapterLE.stopLeScan(mLeScanCallback);
            if (BLU) {
                if (Build.VERSION.SDK_INT >= 21) {
                    adapterLE_new.stopScan(mLeScanCallback_new);
                } else {
                    adapterLE.stopLeScan(mLeScanCallback);
                }
                unregisterReceiver(mReceiver);
            }

            // STOP CELLS CALLBACK
            if (CEL) mHandler.removeCallbacks(runnableCode);

            // WRITE DATA INTO FILES
            try {
                fOutACC.write(dataACC.getBytes());
                dataACC = "";
                iACC = 0;
                fOutLACC.write(dataACC.getBytes());
                dataLACC = "";
                iLACC = 0;
                fOutGYR.write(dataGYR.getBytes());
                dataGYR = "";
                iGYR = 0;
                fOutMAG.write(dataMAG.getBytes());
                dataMAG = "";
                iMAG = 0;
                fOutORI.write(dataORI.getBytes());
                dataORI = "";
                iORI = 0;
                fOutGAR.write(dataGAR.getBytes());
                dataGAR = "";
                iGAR = 0;
                fOutGER.write(dataGER.getBytes());
                dataGER = "";
                iGER = 0;
                fOutPRX.write(dataPRX.getBytes());
                dataPRX = "";
                iPRX = 0;
                fOutPRS.write(dataPRS.getBytes());
                dataPRS = "";
                iPRS = 0;
                fOutLGT.write(dataLGT.getBytes());
                dataLGT = "";
                iLGT = 0;
                fOutSTEP.write(dataSTEP.getBytes());
                dataSTEP = "";
                iSTEP = 0;
                fOutTMP.write(dataTMP.getBytes());
                dataTMP = "";
                iTMP = 0;
                fOutHMD.write(dataHMD.getBytes());
                dataHMD = "";
                iHMD = 0;
                fOutHRT.write(dataHRT.getBytes());
                dataHRT = "";
                iHRT = 0;
                fOutGRA.write(dataGRA.getBytes());
                dataGRA = "";
                iGRA = 0;
                fOutBLU.write(dataBLU.getBytes());
                dataBLU = "";
                iBLU = 0;
                fOutWIF.write(dataWIF.getBytes());
                dataWIF = "";
                iWIF = 0;
                fOutCEL.write(dataCEL.getBytes());
                dataCEL = "";
                iCEL = 0;
                fOutGPS.write(dataGPS.getBytes());
                dataGPS = "";
                iGPS = 0;
                fOutGPSN.write(dataGPSN.getBytes());
                dataGPSN = "";
                iGPSN = 0;
                fOutBATTERY.write(dataBATTERY.getBytes());
                dataBATTERY = "";
                iBATTERY = 0;
                fOutGoogleActi.write(dataGoogleActi.getBytes());
                dataGoogleActi = "";
            } catch (IOException e) {
                e.printStackTrace();
            }

            //COMPRESS FILES
            // DIRETORY OF THE FILES
            String directorioZip = "storage/emulated/0/Android/data/com.example.ilde.abs4sow2_0/files/MyFileStorage/"+archivo;
            //DIRECTORY OF THE FILES TO BE COMPRESSED
            File carpetaComprimir = new File(directorioZip);
            // VALID DIRECTORY
            if (carpetaComprimir.exists()) {
                // LIST OF FILES IN THE DIRECTORY
                File[] ficheros = carpetaComprimir.listFiles();

                try {
                    // CREATE BUFFER FOR ARCHIVES
                    ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(directorioZip + ".zip"));
                    for (int i = 0; i < ficheros.length; i++) {
                        ZipEntry entrada = new ZipEntry(ficheros[i].getName());
                        zous.putNextEntry(entrada);
                        Toast.makeText(ActivityService.this, "Compressing files", Toast.LENGTH_SHORT).show();
                        //COMPRESS THE FILE
                        FileInputStream fis = new FileInputStream(ficheros[i]);
                        int leer;
                        byte[] buffer = new byte[1024];
                        while (0 < (leer = fis.read(buffer))) {
                            zous.write(buffer, 0, leer);
                        }
                        fis.close();
                        zous.closeEntry();
                    }
                    zous.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(ActivityService.this, "Directory not found", Toast.LENGTH_SHORT).show();
            }

            //stopForeground(true);
            stopSelf();
            Toast.makeText(ActivityService.this, "ABS4SOW Stopped", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Constants.ACTION.SAVE_ACTION)) {
            dataUser = (System.currentTimeMillis() * 1000000 + "," + activity + "," + zone + "," + place + "\n");
            try {
                fOutUser.write(dataUser.getBytes());
                Toast.makeText(ActivityService.this, "Saved Data", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (intent.getAction().equals(Constants.ACTIVITY.EATING_ACTION)) {
            activity = "Eating";
        }
        else if (intent.getAction().equals(Constants.ACTIVITY.WALKING_ACTION)) {
            activity = "Walking";
        }
        else if (intent.getAction().equals(Constants.ACTION.SHOPWINDOWS_ACTION)) {
            activity = "Shop Windows";
        }
        else if (intent.getAction().equals(Constants.ACTION.BUYING_ACTION)) {
            activity = "Buying";
        }
        else if (intent.getAction().equals(Constants.ACTION.LEISURE_ACTION)) {
            activity = "Leasure";
        }
        else if (intent.getAction().equals(Constants.ACTION.RESTAURANTS_ACTION)) {
            zone = "Restaurants";
        }
        else if (intent.getAction().equals(Constants.ACTION.OUTDOOR_ACTION)) {
            zone = "Outdoor";
        }
        else if (intent.getAction().equals(Constants.ACTION.SHOPS_ACTION)) {
            zone = "Shops";
        }
        else if (intent.getAction().equals(Constants.ACTION.HYPERMARKET_ACTION)) {
            zone = "Hypermarket";
        }
        else if (intent.getAction().equals(Constants.ACTION.EMPTY_ACTION)) {
            zone = "Empty";
        }
        else if (intent.getAction().equals(Constants.ACTION.MC_DONALDS_ACTION)) {
            place = "Mc Donalds";
        }
        else if (intent.getAction().equals(Constants.ACTION.FLUNCH_ACTION)) {
            place = "Flunch";
        }
        else if (intent.getAction().equals(Constants.ACTION.CAFETITO_ACTION)) {
            place = "Cafetito";
        }
        else if (intent.getAction().equals(Constants.ACTION.POLY_ACTION)) {
            place = "Poly";
        }
        else if (intent.getAction().equals(Constants.ACTION.SPRINGFIELD_ACTION)) {
            place = "Springfield";
        }
        else if (intent.getAction().equals(Constants.ACTION.NO_PLACE_ACTION)) {
            place = "No place";
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        // SENSOR LISTENER EVENTS
        Sensor mySensor = sensorEvent.sensor;

        // ACCELEROMETER
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Log.i(LOG_TAG, "Accelerometer Received");

            iACC = iACC + 1;
            dataACC = dataACC + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "\n";

            try {
                if (iACC == limite) {
                    fOutACC.write(dataACC.getBytes());
                    iACC = 0;
                    dataACC = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //String prueba = String.format("%.3f", sensorEvent.values[0]);

        }

        // ACCELEROMETER WITHOUT GRAVITY
        if (mySensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {


            iLACC = iLACC + 1;
            dataLACC = dataLACC + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "\n";

            try {
                if (iLACC == limite) {
                    fOutLACC.write(dataLACC.getBytes());
                    iLACC = 0;
                    dataLACC = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // GYROSCOPE
        if (mySensor.getType() == Sensor.TYPE_GYROSCOPE) {
            iGYR = iGYR + 1;
            dataGYR = dataGYR + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "\n";
            try {
                if (iGYR == limite) {
                    fOutGYR.write(dataGYR.getBytes());
                    iGYR = 0;
                    dataGYR = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // ORIENTATION WITHOUT MAGNETIC FIELD (FROM GAME_ROTATION_VECTOR)
        if (mySensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            iGAR = iGAR + 1;
            dataGAR = dataGAR + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "," + Float.toString(sensorEvent.values[3]) + "\n";
            try {
                if (iGAR == limite) {
                    fOutGAR.write(dataGAR.getBytes());
                    iGAR = 0;
                    dataGAR = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // ORIENTATION WITH MAGNETIC FIELD (FROM GEOMAGNETIC_ROTATION_VECTOR)
        if (mySensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) {
            iGER = iGER + 1;
            dataGER = dataGER + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "," + Float.toString(sensorEvent.values[3]) + "\n";
            try {
                if (iGER == limite) {
                    fOutGER.write(dataGER.getBytes());
                    iGER = 0;
                    dataGER = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ORIENTATION WITH MAGNETIC FIELD (FROM ROTATION_VECTOR)
        if (mySensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            dataORI = dataORI + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "," + Float.toString(sensorEvent.values[3]) + "," + Float.toString(sensorEvent.values[4]) + "\n";
            iORI = iORI + 1;
            try {
                if (iORI == limite) {
                    fOutORI.write(dataORI.getBytes());
                    iORI = 0;
                    dataORI = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // PODOMETER
        if (mySensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            dataSTEP = dataSTEP + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iSTEP = iSTEP + 1;
            try {
                if (iSTEP == limite) {
                    fOutSTEP.write(dataSTEP.getBytes());
                    iSTEP = 0;
                    dataSTEP = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // MAGNETOMETER
        if (mySensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            dataMAG = dataMAG + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "\n";
            iMAG = iMAG + 1;
            try {
                if (iMAG == limite) {
                    fOutMAG.write(dataMAG.getBytes());
                    iMAG = 0;
                    dataMAG = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // GRAVITY VECTOR
        if (mySensor.getType() == Sensor.TYPE_GRAVITY) {
            dataGRA = dataGRA + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "," + Float.toString(sensorEvent.values[1]) + "," + Float.toString(sensorEvent.values[2]) + "\n";
            iGRA = iGRA + 1;
            try {
                if (iGRA == limite) {
                    fOutGRA.write(dataGRA.getBytes());
                    iGRA = 0;
                    dataGRA = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //        textGRAVITYX.setText("GRAV X: " + String.format("%.2f", sensorEvent.values[0]));
            //        textGRAVITYY.setText("Y: " + String.format("%.2f", sensorEvent.values[1]));
            //        textGRAVITYZ.setText("Z: " + String.format("%.2f", sensorEvent.values[2]));
        }

        // LUMINANCE SENSOR
        if (mySensor.getType() == Sensor.TYPE_LIGHT) {
            dataLGT = dataLGT + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iLGT = iLGT + 1;
            try {
                if (iLGT == limite) {
                    fOutLGT.write(dataLGT.getBytes());
                    iLGT = 0;
                    dataLGT = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //       textLIGHT.setText("Luminance: " + String.format("%.2f", sensorEvent.values[0]));
        }

        // BAROMETER
        if (mySensor.getType() == Sensor.TYPE_PRESSURE) {
            dataPRS = dataPRS + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iPRS = iPRS + 1;
            try {
                if (iPRS == limite) {
                    fOutPRS.write(dataPRS.getBytes());
                    iPRS = 0;
                    dataPRS = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //        textPRESSURE.setText("Pressure: " + String.format("%.2f", sensorEvent.values[0]));
        }

        // PROXIMITY SENSOR
        if (mySensor.getType() == Sensor.TYPE_PROXIMITY) {
            dataPRX = dataPRX + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iPRX = iPRX + 1;
            try {
                if (iPRX == limite) {
                    fOutPRX.write(dataPRX.getBytes());
                    iPRX = 0;
                    dataPRX = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //        textPROXIMITY.setText("Proximinity: " + String.format("%.2f", sensorEvent.values[0]));
        }

        // AMBIENT TEMPERATURE SENSOR
        if (mySensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            dataTMP = dataTMP + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iTMP = iTMP + 1;
            try {
                if (iTMP == limite) {
                    fOutTMP.write(dataTMP.getBytes());
                    iTMP = 0;
                    dataTMP = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
//            textTEMPERATURE.setText("Ambient Temperature: " + String.format("%.2f", sensorEvent.values[0]));
        }

        // HEART RATE SENSOR
        if (mySensor.getType() == Sensor.TYPE_HEART_RATE) {
            dataHRT = dataHRT + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iHRT = iHRT + 1;
            try {
                if (iHRT == limite) {
                    fOutHRT.write(dataHRT.getBytes());
                    iHRT = 0;
                    dataHRT = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //        textHEARTRATE.setText("Heart Rate: " + String.format("%.2f", sensorEvent.values[0]));
        }

        // HUMIDITY SENSOR
        if (mySensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            dataHMD = dataHMD + Long.toString(System.currentTimeMillis() * 1000000 + ((sensorEvent.timestamp - SystemClock.elapsedRealtimeNanos()))) + "," + Float.toString(sensorEvent.values[0]) + "\n";
            iHMD = iHMD + 1;
            try {
                if (iHMD == limite) {
                    fOutHMD.write(dataHMD.getBytes());
                    iHMD = 0;
                    dataHMD = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //  textHUMIDITY.setText("Humidity: " + String.format("%.2f", sensorEvent.values[0]));

        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

            dataBATTERY = dataBATTERY + Long.toString(System.currentTimeMillis() * 1000000) + "," + Integer.toString(health) + "," + Integer.toString(level) + "," + Integer.toString(plugged) + "," + Boolean.toString(present) + "," + Integer.toString(scale) + "," + Integer.toString(status) + "," + technology + "," + Integer.toString(temperature) + "," + Integer.toString(voltage) + "\r\n";
            iBATTERY = iBATTERY + 1;
            try {
                if (iBATTERY == limite) {
                    fOutBATTERY.write(dataBATTERY.getBytes());
                    iBATTERY = 0;
                    dataBATTERY = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            /* batteryInfo.setText(
                     "Health: "+health+"\n"+
                             "Icon Small:"+icon_small+"\n"+
                             "Level: "+level+"\n"+
                             "Plugged: "+plugged+"\n"+
                             "Present: "+present+"\n"+
                             "Scale: "+scale+"\n"+
                             "Status: "+status+"\n"+
                             "Technology: "+technology+"\n"+
                             "Temperature: "+temperature+"\n"+
                             "Voltage: "+voltage+"\n");
             imageBatteryState.setImageResource(icon_small);*/
        }
    };

    public class MyLocationListener implements LocationListener {
        // GPS LISTENER
        @Override

        public void onLocationChanged(Location loc) {
            // EACH TIME LOCATION CHANGES...
            // textGPS.setText("GPS: " + loc.getLatitude() + "," + loc.getLongitude());
            dataGPS = dataGPS + Long.toString(System.currentTimeMillis() * 1000000 + ((loc.getElapsedRealtimeNanos() - SystemClock.elapsedRealtimeNanos()))) + "," + Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude()) + "," + Double.toString(loc.getAltitude()) + "," + Float.toString(loc.getAccuracy()) + "," + Float.toString(loc.getBearing()) + "," + loc.getProvider() + "," + Float.toString(loc.getSpeed()) + "," + Long.toString(loc.getTime()) + "\n";
            iGPS = iGPS + 1;
            // WRITE TO FILE EACH "LIMIT" TIMES
            try {
                if (iGPS == limite) {
                    fOutGPS.write(dataGPS.getBytes());
                    iGPS = 0;
                    dataGPS = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // IF GPS IS DISABLED
            //  textGPS.setText("GPS Disabled");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // IF GPS IS ENABLED
            //   textGPS.setText("GPS Enabled");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // IF GPS CHANGES FROM DISABLED TO ENABLED, START LISTENING FOR UPDATES
            Boolean gps_enabled = false;
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gps_enabled) {
                if (ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    //ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
            }
        }

    }

    public class MyLocationListenerN implements LocationListener {
        // GPS NETWORK LISTENER
        @Override

        public void onLocationChanged(Location loc) {
            // EACH TIME LOCATION CHANGES...
            // textGPS.setText("GPSN: " + loc.getLatitude() + "," + loc.getLongitude());
            dataGPSN = dataGPSN + Long.toString(System.currentTimeMillis() * 1000000 + ((loc.getElapsedRealtimeNanos() - SystemClock.elapsedRealtimeNanos()))) + "," + Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude()) + "," + Double.toString(loc.getAltitude()) + "," + Float.toString(loc.getAccuracy()) + "," + Float.toString(loc.getBearing()) + "," + loc.getProvider() + "," + Float.toString(loc.getSpeed()) + "," + Long.toString(loc.getTime()) + "\n";
            iGPSN = iGPSN + 1;
            // WRITE TO FILE EACH "LIMIT" TIMES
            try {
                if (iGPSN == limite) {
                    fOutGPSN.write(dataGPSN.getBytes());
                    iGPSN = 0;
                    dataGPSN = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override

        public void onProviderDisabled(String provider) {
            // IF GPS NETWORK IS DISABLED
            //   textGPS.setText("GPSN Disabled");
        }

        @Override

        public void onProviderEnabled(String provider) {
            // IF GPS NETWORK IS ENABLED
            // textGPS.setText("GPSN Enabled");
        }

        @Override

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // IF GPS NETWORK CHANGES FROM DISABLED TO ENABLED, START LISTENING FOR UDPATES
            Boolean network_enabled = false;

            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (network_enabled) {
                if (ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    //ActivityCompat.requestPermissions(ForegroundService.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListenerN);
            }
        }
    }

    class WifiScanReceiver extends BroadcastReceiver {
        // WIFI CALLBACK
        public void onReceive(Context c, Intent intent) {
            // EACH TIME NEW DATA ARRIVES...
            List<ScanResult> results = mainWifiObj.getScanResults();
            count = 0;
            Long timeactual = System.currentTimeMillis() * 1000000;
            data = "";

            for (ScanResult result : results) {

            if ((result.timestamp * 1000) <= SystemClock.elapsedRealtimeNanos()) {
                if (count == 0) {
                    contador = contador + 1;
                }
                dataWIF = dataWIF + Long.toString(timeactual) + "," + result.BSSID + "," + result.SSID + "," + Integer.toString(result.frequency) + "," + Integer.toString(result.level) + "," + Long.toString(System.currentTimeMillis() * 1000000 + (((result.timestamp * 1000) - SystemClock.elapsedRealtimeNanos()))) + "\n";

                // WRITE TO FILE EACH "LIMIT" TIMES
                iWIF = iWIF + 1;
                try {
                    // IF NO LIMIT MEASUREMENT MODE...
                    if (medidas == 0) {
                        if (iWIF == limite) {
                            fOutWIF.write(dataWIF.getBytes());
                            iWIF = 0;
                            dataWIF = "";
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                count = count + 1;
            }
        }

            /*Intent wifi = new Intent (ACTION_DATA);
            wifi.putExtra(Nwifi,count);
            LocalBroadcastManager.getInstance(ActivityService.this).sendBroadcast(wifi);

            //   textWIFI.setText("WIFI AP Number: " + Integer.toString(count));*/
            mainWifiObj.startScan();
            /* if (Integer.parseInt(medidas)!= 0) {
                 // IF LIMIT MEASUREMENT MODE AND MEASUREMENT LIMIT REACHED, CHANGE TO "STOP" STATUS
                 if (contador == Integer.parseInt(medidas)) {
                     unregisterReceiver(wifiReceiver);
                     v.vibrate(500);
                 }
             }*/
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        // BLUETOOTH CALLBACK
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
            //   runOnUiThread(new Runnable() {
            // @Override
            //public void run() {
            // EACH TIME BLUETOOTH DATA IS LISTENED...
            // STORE BLUETOOTH INFO
            dataBLU = dataBLU + Long.toString(System.currentTimeMillis() * 1000000) + "," + device.getName() + "," + device.getAddress() + "," + Integer.toString(rssi) + "," + Integer.toString(device.getType()) + "\n";
            //String dispositivo = "Bluetooth: " + device.getName();
            //  textBLUETOOTH.setText(dispositivo);
            iBLU = iBLU + 1;
            try {
                // WRITE TO FILE EACH "LIMIT" TIMES
                if (iBLU == limite) {
                    fOutBLU.write(dataBLU.getBytes());
                    iBLU = 0;
                    dataBLU = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            // }
            //   });
        }
    };


    private android.bluetooth.le.ScanCallback mLeScanCallback_new = new android.bluetooth.le.ScanCallback() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onScanResult(int callbackType, android.bluetooth.le.ScanResult result) {
            Log.i("callbackType", String.valueOf(callbackType));
            Log.i("result", result.toString());
            //Long.toString(System.currentTimeMillis() * 1000000 + (((result.getTimestampNanos() * 1000000000) - SystemClock.elapsedRealtimeNanos())
//                    result.getTimestampNanos();
            dataBLU = dataBLU + Long.toString(System.currentTimeMillis() * 1000000 + (result.getTimestampNanos()) - SystemClock.elapsedRealtimeNanos()) + "," + result.getDevice().getName() + "," + result.getDevice().getAddress() + "," + Integer.toString(result.getRssi()) + "," + Integer.toString(result.getDevice().getType()) + "\n";
            Log.i("data ", dataBLU);
            // String dispositivo = "Bluetooth: " + device.getName();
            //  textBLUETOOTH.setText(dispositivo);
            iBLU = iBLU + 1;
            try {
                // WRITE TO FILE EACH "LIMIT" TIMES
                if (iBLU == limite) {
                    fOutBLU.write(dataBLU.getBytes());
                    iBLU = 0;
                    dataBLU = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e("Scan Failed", "Error Code: " + errorCode);
        }
    };


    private Runnable runnableCode = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void run() {
            // CELLS CALLBACK
            if (ActivityCompat.checkSelfPermission(ActivityService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            List<CellInfo> neighbours = tm.getAllCellInfo();
            // EACH TIME CELLS INFO IS OBTAINED...
            data = "";
            int count = 0;
            Long timeactual = System.currentTimeMillis() * 1000000;

            for (CellInfo neighbour : neighbours) {
            count = count + 1;
            // IF CELL IS CDMA TYPE
            if (neighbour instanceof CellInfoCdma) {

                CellInfoCdma cellaux = (CellInfoCdma) neighbour;
                CellIdentityCdma cellauxidentity = cellaux.getCellIdentity();
                CellSignalStrengthCdma cellauxstrength = cellaux.getCellSignalStrength();
                dataCEL = dataCEL + Long.toString(timeactual) + ",CDMA," + Boolean.toString(neighbour.isRegistered()) + "," + Integer.toString(cellauxidentity.getLatitude()) + "," + Integer.toString(cellauxidentity.getLongitude()) + "," + Integer.toString(cellauxidentity.getNetworkId()) + "," + Integer.toString(cellauxidentity.getSystemId()) + "," + Integer.toString(cellauxidentity.getBasestationId()) + "," + Integer.toString(cellauxstrength.getAsuLevel()) + "," + Integer.toString(cellauxstrength.getDbm()) + "," + Integer.toString(cellauxstrength.getLevel()) + "," + Integer.toString(cellauxstrength.getCdmaDbm()) + "," + Integer.toString(cellauxstrength.getCdmaEcio()) + "," + Integer.toString(cellauxstrength.getCdmaLevel()) + "," + Integer.toString(cellauxstrength.getEvdoDbm()) + "," + Integer.toString(cellauxstrength.getEvdoEcio()) + "," + Integer.toString(cellauxstrength.getEvdoLevel()) + "," + Integer.toString(cellauxstrength.getEvdoSnr()) + "," + Long.toString(System.currentTimeMillis() * 1000000 + (((cellaux.getTimeStamp()) - SystemClock.elapsedRealtimeNanos()))) + "\n";
            }
            // IF CELL IS GSM TYPE
            if (neighbour instanceof CellInfoGsm) {

                CellInfoGsm cellaux = (CellInfoGsm) neighbour;
                CellIdentityGsm cellauxidentity = cellaux.getCellIdentity();
                CellSignalStrengthGsm cellauxstrength = cellaux.getCellSignalStrength();
                dataCEL = dataCEL + Long.toString(timeactual) + ",GSM," + Boolean.toString(neighbour.isRegistered()) + "," + Integer.toString(cellauxidentity.getLac()) + "," + Integer.toString(cellauxidentity.getMcc()) + "," + Integer.toString(cellauxidentity.getMnc()) + ",," + Integer.toString(cellauxidentity.getCid()) + "," + Integer.toString(cellauxstrength.getAsuLevel()) + "," + Integer.toString(cellauxstrength.getDbm()) + "," + Integer.toString(cellauxstrength.getLevel()) + ",,,,,,,," + Long.toString(System.currentTimeMillis() * 1000000 + (((cellaux.getTimeStamp()) - SystemClock.elapsedRealtimeNanos()))) + "\n";
            }
            // IF CELL IS WCDMA TYPE
            if (neighbour instanceof CellInfoWcdma) {
                CellInfoWcdma cellaux = (CellInfoWcdma) neighbour;
                CellIdentityWcdma cellauxidentity = cellaux.getCellIdentity();
                CellSignalStrengthWcdma cellauxstrength = cellaux.getCellSignalStrength();
                dataCEL = dataCEL + Long.toString(timeactual) + ",WCDMA," + Boolean.toString(neighbour.isRegistered()) + "," + Integer.toString(cellauxidentity.getLac()) + "," + Integer.toString(cellauxidentity.getMcc()) + "," + Integer.toString(cellauxidentity.getMnc()) + "," + Integer.toString(cellauxidentity.getPsc()) + "," + Integer.toString(cellauxidentity.getCid()) + "," + Integer.toString(cellauxstrength.getAsuLevel()) + "," + Integer.toString(cellauxstrength.getDbm()) + "," + Integer.toString(cellauxstrength.getLevel()) + ",,,,,,,," + Long.toString(System.currentTimeMillis() * 1000000 + (((cellaux.getTimeStamp()) - SystemClock.elapsedRealtimeNanos()))) + "\n";
            }
            // IF CELL IS LTE TYPE
            if (neighbour instanceof CellInfoLte) {

                CellInfoLte cellaux = (CellInfoLte) neighbour;
                CellIdentityLte cellauxidentity = cellaux.getCellIdentity();
                CellSignalStrengthLte cellauxstrength = cellaux.getCellSignalStrength();
                dataCEL = dataCEL + Long.toString(timeactual) + ",LTE," + Boolean.toString(neighbour.isRegistered()) + "," + Integer.toString(cellauxidentity.getTac()) + "," + Integer.toString(cellauxidentity.getMcc()) + "," + Integer.toString(cellauxidentity.getMnc()) + "," + Integer.toString(cellauxidentity.getPci()) + "," + Integer.toString(cellauxidentity.getCi()) + "," + Integer.toString(cellauxstrength.getAsuLevel()) + "," + Integer.toString(cellauxstrength.getDbm()) + "," + Integer.toString(cellauxstrength.getLevel()) + "," + Integer.toString(cellauxstrength.getTimingAdvance()) + ",,,,,,," + Long.toString(System.currentTimeMillis() * 1000000 + (((cellaux.getTimeStamp()) - SystemClock.elapsedRealtimeNanos()))) + "\n";
            }

            iCEL = iCEL + 1;
            try {
                // WRITE TO FILE EACH "LIMIT" TIMES
                if (iCEL == limite) {
                    fOutCEL.write(dataCEL.getBytes());
                    iCEL = 0;
                    dataCEL = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
            // textCELL.setText("Cell Number: " + Integer.toString(count));
            // EXECUTE THIS CALLBACK EACH SECOND
            mHandler.postDelayed(runnableCode, 1000);
        }
    };



    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void onReceive(Context context, Intent intent) {
            //TextView text = (TextView)findViewById(R.id.textView);

            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
                adapter.startDiscovery();
                // text.setText("Start Discovery Again \n");
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //device.getUuids().toString();
                // ParcelUuid[] uuidbluetooth=device.getUuids();
                //uuidbluetooth.toString();
                // ParcelUuid uuid = uuidbluetooth[0];
                //uuidbluetooth.toString();
                //data=Long.toString(System.currentTimeMillis()) + ",BLU," + device.getName() + "," + device.getAddress() + "," + device.getUuids().toString() + "," + Integer.toString(device.getType()) + "\n";
                dataBLU=dataBLU + Long.toString(System.currentTimeMillis()*1000000) + "," + device.getName() + "," + device.getAddress() + ","  + Integer.toString(rssi) + "," + Integer.toString(device.getType()) + "\n";
//                text.setText("Found device " + device.getName());
                //  textBLUETOOTH.setText("Bluetooth: " + device.getName());
                iBLU=iBLU+1;
                try {
                    //fOut.write(data.getBytes());
                    if (iBLU==1000)
                    {
                        fOutBLU.write(dataBLU.getBytes());
                        iBLU=0;
                        dataBLU="";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // showToast("Found device " + device.getName());
            }
        }
    };

}

