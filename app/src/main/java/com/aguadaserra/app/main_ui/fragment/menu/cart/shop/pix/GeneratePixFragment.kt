package com.aguadaserra.app.main_ui.fragment.menu.cart.shop.pix

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_generate_pix.*
import java.io.File
import java.io.FileOutputStream

/**
 * A simple [Fragment] subclass.
 */
class GeneratePixFragment : BaseFragment() {

    override var hasBackButton: Boolean = false
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override var title: String = "Compra realizada!"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generate_pix, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qrcode: String = requireArguments().getString("qrcode")!!
        val qrcode64: String = requireArguments().getString("qrcode_64")!!

        val data: ByteArray = qrcode64.toByteArray()
        val encodeBase64 = Base64.encodeToString(data, Base64.DEFAULT)
        val decodeBase64 = Base64.decode(data, Base64.DEFAULT)
        var qrcode64Converted = decodeBase64.toString(charset("UTF-8"))

        cod_barras_tv.text = qrcode
        generateQRCode(qrcode)

        savePixImg.setOnClickListener {
            if (!confirmPermissions()) {

                requestPermissions(permissions, 1)
                return@setOnClickListener
            }
            saveImage()
        }

        copycodePix.setOnClickListener {

            setClipboard(requireActivity(), qrcode)
            singleToast.show(requireActivity(), "Pix copiado para área de transferencia.", Toast.LENGTH_SHORT)

        }

        ok_bt.setOnClickListener {
            navigation.popBackStack()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)) {
                //denied

                singleToast.show(requireActivity(),
                    "Por favor, aceite todas as permissões necessárias!", Toast.LENGTH_SHORT)
                useful.startInstalledAppDetailsActivity(requireActivity())
                break
            } else {
                if (ActivityCompat.checkSelfPermission(
                        requireActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                    //never ask again
                    singleToast.show(requireActivity(),
                        "Por favor, aceite todas as permissões necessárias!", Toast.LENGTH_SHORT)
                    useful.startInstalledAppDetailsActivity(requireActivity())
                    break
                }
            }
        }
    }

    private fun setClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copiado!", text)
        clipboard.setPrimaryClip(clip)
    }

    private fun saveImage(){
        //to get the image from the ImageView (say iv)
        //to get the image from the ImageView (say iv)
        val bitmap = qrcodeImg.drawable.toBitmap()

        var outStream: FileOutputStream? = null
        val sdCard: File = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath.toString() + "/DCIM/Bae")
        dir.mkdirs()
        val fileName = String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, fileName)
        outStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
//        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
//        intent.data = Uri.fromFile(outFile)
//        sendBroadcast(intent)
        singleToast.show(requireActivity(), "QRCODE salvo na galeria.", Toast.LENGTH_SHORT)
    }

    private fun generateQRCode(qrcode64: String) {
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(qrcode64, BarcodeFormat.QR_CODE, 1024, 1024)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            qrcodeImg.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    private fun confirmPermissions(): Boolean {
        return !(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)

    }

}