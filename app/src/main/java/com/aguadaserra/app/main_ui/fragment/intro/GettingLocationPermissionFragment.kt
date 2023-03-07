package com.aguadaserra.app.main_ui.fragment.intro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_getting_location_permission.*

/**
 * A simple [Fragment] subclass.
 */
class GettingLocationPermissionFragment : BaseFragment() {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override var toolbarVisibility: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_getting_location_permission, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permission_bt.setOnClickListener { this.requestPermissions(permissions, 1)}


    }

    override fun onResume() {
        super.onResume()

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            navigation.popBackStack()

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray){
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission!!)) {
                //denied

                singleToast.show(requireActivity(),
                    "Por favor, aceite todas as permissões necessárias!", Toast.LENGTH_SHORT)
                openPermissionsScreen()
                return
            } else {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    //never ask again

                    singleToast.show(requireActivity(),
                        "Por favor, aceite todas as permissões necessárias!", Toast.LENGTH_SHORT)
                    openPermissionsScreen()
                    return
                }
            }
        }

        navigation.popBackStack()

    }

    private fun openPermissionsScreen() {

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}