package com.aguadaserra.app.main_ui.fragment.menu.cart.shop.ticket

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.oned.Code128Writer
import kotlinx.android.synthetic.main.fragment_generate_ticket.*

/**
 * A simple [Fragment] subclass.
 */
class GenerateTicketFragment : BaseFragment() {

    override var toolbarVisibility: Boolean = true
    override var hasBackButton: Boolean = true

    override var title: String = "Compra realizada!"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generate_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barCode: String = requireArguments().getString("barCodeNumber")!!

        generateBarCode(code = barCode)

        clip_bt.setOnClickListener {
                setClipboard(requireActivity(), barCode)
                singleToast.show(requireActivity(), "Boleto copiado para área de transferência.", Toast.LENGTH_SHORT)

        }
        ok_bt.setOnClickListener {
//            navigation.popBackStack(R.id.profileFragment, false)
        }

    }

    private fun generateBarCode(code: String) {
        val writer = Code128Writer()
        try {
            val bitMatrix = writer.encode(code, BarcodeFormat.CODE_128, 1024, 50)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            code_iv.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    private fun setClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copiado!", text)
        clipboard.setPrimaryClip(clip)
    }


}