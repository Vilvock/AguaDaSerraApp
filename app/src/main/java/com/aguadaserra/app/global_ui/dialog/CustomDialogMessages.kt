package com.aguadaserra.app.global_ui.dialog

import android.content.Context
import android.graphics.Bitmap
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.components.SingleToast
import com.aguadaserra.app.util.RecyclerItemListener
import com.github.chrisbanes.photoview.PhotoView
import com.github.chrisbanes.photoview.PhotoViewAttacher
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.io.IOException
import java.text.DecimalFormat


class CustomDialogMessages (private val context: Context) {



//    fun openMultimedia(answerString: AnswerString) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_gallery_camera, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val cameraIb = view.findViewById<ImageButton>(R.id.camera_ib)
//        val galleryIb = view.findViewById<ImageButton>(R.id.gallery_ib)
//        val cancelTv = view.findViewById<TextView>(R.id.cancel_tv)
//
//
//        cameraIb.setOnClickListener {
//            answerString.setOnClickListener("camera")
//            alertDialog.dismiss()
//        }
//
//        galleryIb.setOnClickListener {
//            answerString.setOnClickListener("gallery")
//            alertDialog.dismiss()
//        }
//
//        cancelTv.setOnClickListener {
//
//            alertDialog.dismiss()
//        }
//    }
//
    fun openFormAddress(answer: Answer) {

        val builder = AlertDialog.Builder(context)
        val alertDialog = builder.create()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.dialog_address, null)
        alertDialog.setView(view)
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.setCancelable(true)
        alertDialog.show()

        val finishBt = view.findViewById<Button>(R.id.finish_bt)


        finishBt.setOnClickListener {
            answer.setOnClickListener()
            alertDialog.dismiss()
        }

    }
//
//    fun openDescription(answerString: AnswerString) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_description, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(false)
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//        val updateBt = view.findViewById<Button>(R.id.update_bt)
//        val countLengthTv = view.findViewById<TextView>(R.id.countLength_tv)
//        val descriptionEt = view.findViewById<EditText>(R.id.description_et)
//
//        val mTextEditorWatcher: TextWatcher = object : TextWatcher {
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                //This sets a textview to the current length
//                countLengthTv.text = s.length.toString()
//            }
//
//            override fun afterTextChanged(s: Editable) {}
//        }
//
//        descriptionEt.addTextChangedListener(mTextEditorWatcher)
//
//        updateBt.setOnClickListener {
//
//
//            answerString.setOnClickListener(descriptionEt.text.toString())
//
//            alertDialog.dismiss()
//
//        }
//
//        closeIb.setOnClickListener { alertDialog.dismiss() }
//
//    }
//
//    fun openDescriptionRating(answerString: AnswerDoubleString) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_rating, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(false)
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//        val updateBt = view.findViewById<Button>(R.id.update_bt)
//        val countLengthTv = view.findViewById<TextView>(R.id.countLength_tv)
//        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
//        val descriptionEt = view.findViewById<EditText>(R.id.description_et)
//
//        val mTextEditorWatcher: TextWatcher = object : TextWatcher {
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                //This sets a textview to the current length
//                countLengthTv.text = s.length.toString()
//            }
//
//            override fun afterTextChanged(s: Editable) {}
//        }
//
//        descriptionEt.addTextChangedListener(mTextEditorWatcher)
//
//        updateBt.setOnClickListener {
//
//            if (ratingBar.rating == 0F) {
//                return@setOnClickListener
//            }
//
//            answerString.setOnClickListener(descriptionEt.text.toString(),
//                ratingBar.rating.toString()
//            )
//
//            alertDialog.dismiss()
//
//        }
//
//        closeIb.setOnClickListener { alertDialog.dismiss() }
//
//    }
//
//    fun openFilterDialogValue(settedValue1: String?, settedValue2: String?, title: String, minValue: String, maxValue: String, isBetweenValues: Boolean, answerString: AnswerDoubleString) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_filter, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val titleTv = view.findViewById<TextView>(R.id.title_tv)
//        val rangeSlider = view.findViewById<RangeSlider>(R.id.rangeSlider)
//        val updateBt = view.findViewById<Button>(R.id.update_bt)
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//
//        titleTv.text = title
//
//        rangeSlider.valueFrom = minValue.toFloat()
//        rangeSlider.valueTo = maxValue.toFloat()
//
//        var currency = ""
//
//        val valueList = ArrayList<Float>()
//
//        if (isBetweenValues) {
//
//
//            valueList.add(settedValue1!!.toFloat())
//            valueList.add(settedValue2!!.toFloat())
//
//
//        } else {
//            currency = "Km(s)"
//
//            valueList.add(settedValue1!!.toFloat())
//        }
//
//        rangeSlider.values = valueList
//
//        rangeSlider.setLabelFormatter { value: Float ->
//            String.format("%.0f $currency", value)
//        }
//
//        updateBt.setOnClickListener {
//
//            val value1 = DecimalFormat("#").format(rangeSlider.values[0])
//
//            val value2 = if (isBetweenValues) {
//
//                DecimalFormat("#").format(rangeSlider.values[1])
//            } else {
//
//                null
//            }
//
//            answerString.setOnClickListener(
//                answer1 = value1,
//                answer2 = value2)
//
//            alertDialog.dismiss()
//        }
//
//
//        closeIb.setOnClickListener { alertDialog.dismiss() }
//
//    }
//
//    fun openZoom(url: String) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_img_zoom, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val closeIb = view.findViewById<ImageButton>(R.id.close_ib)
//        val photoView = view.findViewById<PhotoView>(R.id.zoom_pv)
//
//        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(photoView)
//
//        val mAttacher = PhotoViewAttacher(photoView)
//        mAttacher.update()
//
//
//        closeIb.setOnClickListener {
//
//            alertDialog.dismiss()
//        }
//
//    }
//
//    fun openGenericItemList(idSelected: String?, list: List<Any>, typeItem: Int, answerItem: AnswerItem) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_default_recycler, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val itemsRv = view.findViewById<RecyclerView>(R.id.genericItems_rv)
//        val okBt = view.findViewById<Button>(R.id.ok_bt)
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//        val titleTv = view.findViewById<TextView>(R.id.title_tv)
//        val searchEt = view.findViewById<EditText>(R.id.search_et)
//        val searchFab = view.findViewById<FloatingActionButton>(R.id.search_fab)
//        val searchLl = view.findViewById<LinearLayout>(R.id.search_ll)
//
//        var title = ""
//        okBt.visibility = View.GONE
//        searchFab.visibility = View.GONE
//
//        when(typeItem) {
//            SIGN -> {
//                title = "Signos"
//
//            }
//            CAREER -> {
//                title = "Profissões"
//                okBt.visibility = View.VISIBLE
//                searchLl.visibility = View.VISIBLE
//
//            }
//            SCHOOLING -> {
//                title = "Escolaridade"
//                okBt.visibility = View.VISIBLE
////                searchLl.visibility = View.VISIBLE
//
//            }
//            CIVIL_STATE -> {
//
//                title = "Estado Civil"
//                okBt.visibility = View.VISIBLE
////                searchLl.visibility = View.VISIBLE
//
//            }
//            ORIENTATION -> {
//
//                title = "Orientação Sexual"
//                okBt.visibility = View.VISIBLE
////                searchLl.visibility = View.VISIBLE
//
//            }
//            PLAN -> {
//
//                title = "Planos"
//
//            }
//            GENDERS -> {
//
//                title = "Gêneros"
//
//            }
//
//            REASONS -> {
//
//                title = "Motivos"
//
//            }
//
//        }
//
//        titleTv.text = title
//
//        val adapter = GenericTypeItemAdapter(idSelected, context, typeItem, list, object :
//            RecyclerItemListener {
//            override fun onClickListenerItem(item: Any?) {
//
//                if (item != null) {
//
//                    when (typeItem) {
//                        SIGN -> {
//
//                            item as Sign
//                        }
//                        CAREER -> {
//
//                            item as Career
//                        }
//                        SCHOOLING -> {
//
//                            item as Schooling
//                        }
//                        CIVIL_STATE -> {
//
//                            item as CivilState
//                        }
//                        PLAN -> {
//
//                            item as DetailPlan
//                        }
//                        GENDERS -> {
//
//                            item as User
//
//                        }
//                        REASONS -> {
//
//                            item as Match
//
//                        }
//                        ORIENTATION -> {
//
//                            item as Orientation
//
//                        }
//
//                    }
//                }
//
//                if (typeItem == CAREER || typeItem == SCHOOLING || typeItem == CIVIL_STATE || typeItem == ORIENTATION) {
//                    okBt.setOnClickListener {
//
//                        if (item == null) return@setOnClickListener
//
//                        answerItem.setOnClickListener(item)
//
//                        alertDialog.dismiss()
//                    }
//                } else {
//
//                    if (item == null) return
//
//                    answerItem.setOnClickListener(item!!)
//
//                    alertDialog.dismiss()
//                }
//
//            }
//        }
//        )
//
//        searchEt.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//                list as List<Career>
//
//                filter(s.toString(), adapter, list)
//            }
//        })
//
////        searchFab.setOnClickListener {
////
////            adapter.filter!!.filter(searchEt.text.toString())
////        }
//
//
//        val layoutManagerRv: RecyclerView.LayoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//
//        itemsRv.layoutManager = layoutManagerRv
//        itemsRv.adapter = adapter
//
//
//
//
//        closeIb.setOnClickListener {
//
//            alertDialog.dismiss()
//        }
//    }
//
//    private fun filter(text: String?, genericTypeItemAdapter: GenericTypeItemAdapter, displayedList: List<Career>) {
//        val temp: MutableList<Career> = ArrayList()
//        for (d in displayedList) {
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if (d.name!!.toLowerCase().contains(text!!)) {
//                temp.add(d)
//            }
//        }
//        //update recyclerview
//        genericTypeItemAdapter.updateList(temp)
//    }
//
//    fun openSearchList(typeItem: Int, viewModel: MeetingPlacesViewModel, owner: LifecycleOwner, answerItem: AnswerItem) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_default_recycler, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val itemsRv = view.findViewById<RecyclerView>(R.id.genericItems_rv)
//        val okBt = view.findViewById<Button>(R.id.ok_bt)
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//        val titleTv = view.findViewById<TextView>(R.id.title_tv)
//        val searchEt = view.findViewById<EditText>(R.id.search_et)
//        val searchFab = view.findViewById<FloatingActionButton>(R.id.search_fab)
//        val searchLl = view.findViewById<LinearLayout>(R.id.search_ll)
//
//        var title = "Pesquisar"
//
//        titleTv.text = title
//
//        searchLl.visibility = View.VISIBLE
//        okBt.visibility = View.GONE
//
//        searchFab.setOnClickListener {
//
//            if (typeItem == CITIES) {
//
//                viewModel.filterCities(searchEt.text.toString())
//            } else {
//
//                viewModel.filterCounties(searchEt.text.toString())
//            }
//        }
//
//        viewModel.viewState.observe(owner, Observer {
//
//            when(it.ONLINE_REQUEST) {
//                "filterCities" -> {
//
//                    if (it.placeList != null) {
//
//                        val adapter = GenericTypeItemAdapter(null, context, typeItem, it.placeList, object :
//                            RecyclerItemListener {
//                            override fun onClickListenerItem(item: Any?) {
//
//                                if (item == null) return
//
//                                answerItem.setOnClickListener(item)
//
//                                alertDialog.dismiss()
//                            }
//                        }
//                        )
//
//                        val layoutManagerRv: RecyclerView.LayoutManager =
//                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//
//                        itemsRv.layoutManager = layoutManagerRv
//                        itemsRv.adapter = adapter
//                    }
//                }
//            }
//        })
//
//
//
//        closeIb.setOnClickListener {
//
//            alertDialog.dismiss()
//        }
//    }
//
//    fun openEdittext(typeMask: String?, info: String, title: String, answerString: AnswerString) {
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_edittext, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val defaultEt = view.findViewById<MaskEditText>(R.id.default_et)
//        val okBt = view.findViewById<Button>(R.id.ok_bt)
//        val closeIb = view.findViewById<ImageButton>(R.id.close_imageButton)
//        val titleTv = view.findViewById<TextView>(R.id.titleType_tv)
//
//        titleTv.text = title
//        defaultEt.setText(info)
//
//        var cpfMask: TextWatcher
//        var cellphoneMask: TextWatcher
//        var birthMask: TextWatcher
//
//        when (typeMask) {
//            "document" -> {
//
//                defaultEt.mask = "###.###.###-##"
//
//                defaultEt.inputType = InputType.TYPE_CLASS_NUMBER
//            }
//            "cellphone" -> {
//
//                defaultEt.mask = "(##) #####-####"
//
//                defaultEt.inputType = InputType.TYPE_CLASS_NUMBER
//            }
//            "birth" -> {
//
//                defaultEt.mask = "##/##/####"
//
//                defaultEt.inputType = InputType.TYPE_CLASS_NUMBER
//            }
//            else -> {
//
//            }
//        }
//
//        okBt.setOnClickListener {
//            if (typeMask != "city") {
//                if (!validateGenericEditText(text = defaultEt.text.toString())) return@setOnClickListener
//            }
//
//            answerString.setOnClickListener(defaultEt.text.toString())
//
//            alertDialog.dismiss()
//        }
//
//        closeIb.setOnClickListener {
//
//            alertDialog.dismiss()
//        }
//    }
//
//    fun editImage(image: Bitmap, answerItem: AnswerItem){
//
//        val builder = AlertDialog.Builder(context)
//        val alertDialog = builder.create()
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View = inflater.inflate(R.layout.dialog_edit_image, null)
//        alertDialog.setView(view)
//        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        alertDialog.setCanceledOnTouchOutside(true)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//
//        val closeIb = view.findViewById<ImageButton>(R.id.close_ib)
//        val saveIb = view.findViewById<ImageButton>(R.id.save_ib)
//        val leftIb = view.findViewById<ImageButton>(R.id.left_ib)
//        val rightIb = view.findViewById<ImageButton>(R.id.right_ib)
//        val civIb = view.findViewById<CropImageView>(R.id.cropImageView)
//
//        civIb.setCropMode(CropImageView.CropMode.SQUARE)
//        civIb.imageBitmap = image
//
//        leftIb.setOnClickListener { civIb.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D) }
//        rightIb.setOnClickListener { civIb.rotateImage(CropImageView.RotateDegrees.ROTATE_90D) }
//
//        saveIb.setOnClickListener {
//            civIb.crop(civIb.sourceUri).execute(object : CropCallback {
//                override fun onSuccess(cropped: Bitmap) {
//                    civIb.save(cropped)
//                    try {
////                        val id: String = Preferences(this@ProfileAct).getUserData()?.id.toString()
////
////                        userControl.updateAvatar(id,
////                            MyUsefulKotlin().storeOnCache(this@ProfileAct, cropped))
//                        answerItem.setOnClickListener(item = cropped)
//
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//
//                override fun onError(e: Throwable) {}
//            })
//            alertDialog.dismiss()
//        }
//
//        closeIb.setOnClickListener { alertDialog.dismiss() }
//    }

    interface AnswerItem {
        fun setOnClickListener(item: Any)
    }

    interface AnswerDoubleString {
        fun setOnClickListener(answer1: String, answer2: String?)
    }

    interface AnswerString {
        fun setOnClickListener(answer: String)
    }

    interface Answer {
        fun setOnClickListener()
    }

    private fun validateGenericEditText(text: String): Boolean {
        return (text.isNotBlank()).also { valid ->
            if (!valid) {
                SingleToast.INSTANCE.show(context, "Este campo é obrigatório!", Toast.LENGTH_LONG)
            }
        }
    }

}