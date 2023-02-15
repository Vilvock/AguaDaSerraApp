package com.aguadaserra.app.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.aguadaserra.app.global_ui.dialog.DefaultBottomSheetContainerFragDialog
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class Useful (private val context: Context) {

    /**
     * Useful
     *
     * @Requered Androidx, daimajia
     */

    private val builder = AlertDialog.Builder(context)
    val alertDialog = builder.create()

    private var bottomSheetDialog: DefaultBottomSheetContainerFragDialog? = null

//    fun startFragment(frag: Fragment, fragmentManager: FragmentManager) {
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.containerView, frag).commitAllowingStateLoss()
//    }
//
//    fun startFragmentOnBack(frag: Fragment, fragmentManager: FragmentManager) {
//        val transaction = fragmentManager.beginTransaction()
////        transaction.setCustomAnimations(
////            R.anim.enter_from_right,
////            R.anim.exit_to_left,
////            R.anim.enter_from_left,
////            R.anim.exit_to_right
////        )
//        transaction.replace(R.id.containerView, frag).addToBackStack(null).commitAllowingStateLoss()
//    }
//
//    fun openLoading() {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view = inflater.inflate(R.layout.loading, null)
//        alertDialog.setView(view)
//        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(false)
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//    }
//
//    fun closeLoading() {
//        if (alertDialog.isShowing) alertDialog.dismiss()
//    }

    @SuppressLint("SetTextI18n")
    fun showDefaultDialogView(fragmentManager: FragmentManager, tag: String) {
        bottomSheetDialog = DefaultBottomSheetContainerFragDialog()
        fragmentManager.beginTransaction().remove(bottomSheetDialog!!)
        fragmentManager.beginTransaction().add(bottomSheetDialog!!, tag).commitAllowingStateLoss()
    }

    @SuppressLint("SetTextI18n")
    fun dismissDefaultDialogView() {
        bottomSheetDialog?.dismiss()
    }

    fun bitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openWebPage(url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun createLink(targetTextView: TextView, completeString: String,
                   partToClick: String, clickableAction: ClickableSpan?): TextView {
        val spannableString = SpannableString(completeString)

        // make sure the String is exist, if it doesn't exist
        // it will throw IndexOutOfBoundException
        val startPosition = completeString.indexOf(partToClick)
        val endPosition = completeString.lastIndexOf(partToClick) + partToClick.length
        spannableString.setSpan(clickableAction, startPosition, endPosition,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        targetTextView.text = spannableString
        targetTextView.movementMethod = LinkMovementMethod.getInstance()
        return targetTextView
    }


    fun startInstalledAppDetailsActivity(activity: Activity) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }

    fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver,
            inImage, "image", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI(activity: Activity, uri: Uri?): String {
        var path = ""
        if (activity.contentResolver != null) {
            val cursor: Cursor? = activity.contentResolver.query(uri!!,
                null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }


    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(uri!!, projection, null, null, null)
        return if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            val column_index: Int = cursor
                .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } else null
    }

    @Throws(IOException::class)
    fun storeOnCache(context: Context, bitmap: Bitmap): File {
        val cacheDir = context.cacheDir
        val file = File(cacheDir, generateRandomFilename())
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
        return file
    }

    private fun generateRandomFilename(): String {
        return System.currentTimeMillis().toString() +
                (Math.random() * 10000.0).toInt() +
                "." +
                "jpg"
    }


    fun performShareOption(url: String, title: String, subject: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            val shareMessage: String = url
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            context.startActivity(Intent.createChooser(shareIntent, title))
        } catch (e: Exception) {
            //e.toString();
        }
    }
}