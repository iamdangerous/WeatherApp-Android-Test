package com.rahullohra.myweatherapp.presentation.ui.activity

import android.Manifest
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.AsyncTask
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.rahullohra.myweatherapp.R
import com.rahullohra.myweatherapp.WeatherApp
import com.rahullohra.myweatherapp.data.LiveDataResult
import com.rahullohra.myweatherapp.data.di.components.DaggerActivityComponent
import com.rahullohra.myweatherapp.data.model.CurrentWeatherData
import com.rahullohra.myweatherapp.data.model.WeatherData
import com.rahullohra.myweatherapp.extras.Util
import com.rahullohra.myweatherapp.idlingResources.IdlingProvider
import com.rahullohra.myweatherapp.idlingResources.WeatherIdlingResource
import com.rahullohra.myweatherapp.presentation.adapter.WeatherAdapter
import com.rahullohra.myweatherapp.presentation.contract.MainActivityContract
import com.rahullohra.myweatherapp.presentation.ui.views.ErrorView
import com.rahullohra.myweatherapp.presentation.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract, ErrorView.ErrorViewCallback {

    val TAG = "MainActivity"
    val PERMISSION_REQUEST_CODE = 1

    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter

    private val list = arrayListOf<WeatherData>()
    private var screenHeight: Float = 0f
    var place = ""

    var idlingResource: WeatherIdlingResource? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationCallback: LocationCallback? = null
    var uiState = MainActivityState(
        FrameworkLocationState.STARTED,
        ActivityDataState.NOTHING
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        injectComponent()
        setListeners()
        requestLocation()
    }

    private fun initUi() {
        rv.layoutManager = LinearLayoutManager(this)
        adapter = WeatherAdapter(list)
        rv.adapter = adapter

        toggleProgressBar(true)
        screenHeight = Util.deviceDimensions(applicationContext).y.toFloat()

        idlingResource = IdlingProvider.provideIdlingResource(TAG)
        idlingResource?.increment()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        error_view.callback = this
    }

    private fun injectComponent() {
        val appComponent = (applicationContext as WeatherApp).appComponent

        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    private fun setListeners() {
        weatherViewModel.locationLiveData.observe(this, Observer {
            when (it.status) {
                LiveDataResult.STATUS.SUCCESS -> {
                    if (it.data != null) {
                        weatherViewModel.getWeather(it.data)
                    } else {
                        onFail()
                    }
                }
                else -> onFail()
            }
        })

        weatherViewModel.weatherLiveData.observe(this, Observer {
            when (it.status) {
                LiveDataResult.STATUS.SUCCESS -> showResult(it.data!!)
                else -> onFail()
            }
        })
    }


    @SuppressLint("CheckResult")
    private fun requestLocation() {
        uiState.frameworkLocationState = FrameworkLocationState.REQUESTING_LOCATION_PERMISSION

        if (checkLocationPermission()) {
            uiState.frameworkLocationState = FrameworkLocationState.LOCATION_PERMISSION_GRANTED
            setLocationListener()
        } else {
            uiState.frameworkLocationState =
                FrameworkLocationState.LOCATION_PERMISSION_NOT_GRANTED
            Toast.makeText(this, "Please provide permission", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setLocationListener() {
        if(true){
            weatherViewModel.getLocation(19.0760, 72.8777)
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                if (TextUtils.isEmpty(place)) {
                    if (it != null) {
                        uiState.frameworkLocationState = FrameworkLocationState.LOCATION_FOUND
                        getLatestAddress(it)
                    } else {
                        uiState.frameworkLocationState =
                            FrameworkLocationState.REQUEST_PERIODIC_LOCATION
                        startLocationUpdates()
                    }
                } else {
                    uiState.frameworkLocationState = FrameworkLocationState.LOCATION_NOT_FOUND
                    Toast.makeText(this, "Cannot find location", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { _ ->
                onFail()
                uiState.frameworkLocationState = FrameworkLocationState.LOCATION_NOT_FOUND
                Toast.makeText(this, "Cannot find location", Toast.LENGTH_LONG).show()
            }
    }

    private fun startLocationUpdates() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)

                val lat = p0?.lastLocation?.latitude
                val lon = p0?.lastLocation?.longitude
                if (lat != null && lon != null) {
                    uiState.frameworkLocationState =
                        FrameworkLocationState.PERIODIC_LOCATION_SUCCESS
                } else {
                    uiState.frameworkLocationState = FrameworkLocationState.PERIODIC_LOCATION_FAIL
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
                if (p0 == null || !p0.isLocationAvailable) {
                    uiState.frameworkLocationState = FrameworkLocationState.PERIODIC_LOCATION_FAIL
                }
            }
        }

        val locationRequest = LocationRequest()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(5 * 1000)

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun getLatestAddress(location: Location) {
        weatherViewModel.getLocation(location.latitude, location.longitude)
    }

    private fun rotateIcon() {
        val duration = 4000L
        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.interpolator = LinearInterpolator()
        rotate.duration = duration
        rotate.repeatMode = Animation.RESTART
        rotate.repeatCount = Animation.INFINITE
        loading_image.animation = rotate
    }

    private fun updateWeather(currentWeatherData: CurrentWeatherData) {
        tv_title.text = currentWeatherData.name
        tv_temp.text = currentWeatherData.temp
    }

    override fun onFail() {
        toggleProgressBar(false)
        error_view.visibility = View.VISIBLE

        idlingResource?.decrement()
    }

    override fun showResult(pair: Pair<CurrentWeatherData, List<WeatherData>>) {
        toggleProgressBar(false)
        ll_container.visibility = View.VISIBLE

        this.list.clear()
        this.list.addAll(pair.second)
        adapter.notifyDataSetChanged()
        translateUp()
        updateWeather(pair.first)

        idlingResource?.decrement()
    }

    override fun toggleProgressBar(value: Boolean) {
        if (value) {
            loading_image.visibility = View.VISIBLE
            ll_container.visibility = View.GONE
            error_view.visibility = View.GONE
            rotateIcon()
        } else {
            loading_image.visibility = View.GONE
            loading_image.clearAnimation()
        }
    }

    override fun onRetry() {
//        idlingResource?.increment()
//        toggleProgressBar(true)
//        weatherViewModel.getWeather(place)
        Toast.makeText(this, "Retry is not supported", Toast.LENGTH_SHORT).show()
    }

    private fun translateUp() {
        val animDuration = 800L
        val valueAnimator = ValueAnimator.ofFloat(screenHeight, 0f)
        valueAnimator.duration = animDuration
        valueAnimator.addUpdateListener {
            val v = it.animatedValue.toString().toFloat()
            view_shadow.translationY = v
            rv.translationY = v
        }
        valueAnimator.start()
    }

    enum class FrameworkLocationState {
        STARTED,
        REQUESTING_LOCATION_PERMISSION,
        LOCATION_PERMISSION_GRANTED,
        LOCATION_PERMISSION_NOT_GRANTED,
        LOCATION_FOUND,
        LOCATION_NOT_FOUND,
        REQUEST_PERIODIC_LOCATION,
        PERIODIC_LOCATION_SUCCESS,
        PERIODIC_LOCATION_FAIL,
    }

    enum class ActivityDataState {
        NOTHING,
        BAD_LOCATION_FOUND,
        WEATHER_API_FAILED
    }

    private fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                    requestLocation()
                }
            }
        }
    }
}

class MainActivityState(
    var frameworkLocationState: MainActivity.FrameworkLocationState,
    var activityDataState: MainActivity.ActivityDataState
)
