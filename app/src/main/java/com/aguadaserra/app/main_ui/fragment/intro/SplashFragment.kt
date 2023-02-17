package com.aguadaserra.app.main_ui.fragment.intro

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.aguadaserra.app.util.Preferences
import com.aguadaserra.app.util.Preferences.Companion.ENTERING_FIRST_TIME

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : BaseFragment() {

    private val handler = Handler()
    private var runnable = Runnable { configInit() }
    private val DELAY = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onResume() {
        super.onResume()

        configInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    private fun configInit() {
        handler.postDelayed({

            //pega localizacao
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                navigation.navigate(R.id.action_splashFragment_to_gettingLocationPermissionFragment)


            } else {

                val lm = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)

                //pega verifica se o gps ta ligado
                if (!enabled) {
                    GenericDialogFragment.getInstance(
                        getString(R.string.disable_gps),
                        getString(R.string.disable_gps_description),
                        getString(R.string.enable),
                        getString(R.string.exit)
                    )
                        .setOnRightOptionPressed { startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
                        .setOnLeftOptionPressed { requireActivity().finishAffinity() }
                        .apply { isCancelable = false }
                        .show(parentFragmentManager, "dialog_permission")

                    //intro ou loga direto
                } else {

                    if (preferences.getLogin()){

                        navigation.navigate(R.id.action_splashFragment_to_homeFragment)

                    } else {

                        if (preferences.getInt(
                                ENTERING_FIRST_TIME, 1) == 0) {

                                navigation.navigate(R.id.action_splashFragment_to_authContainerFragment)

                        } else if (preferences.getInt(
                                ENTERING_FIRST_TIME, 1) == 2) {

                                navigation.navigate(R.id.action_splashFragment_to_authContainerFragment)
                                preferences.storeInt(ENTERING_FIRST_TIME, 1)
                        } else {

                                navigation.navigate(R.id.action_splashFragment_to_multipleIntroContainerFragment)
                        }

                    }
                }

            }
        }, DELAY)
    }

}