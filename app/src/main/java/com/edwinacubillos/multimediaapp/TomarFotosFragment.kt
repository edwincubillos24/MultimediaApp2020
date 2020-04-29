package com.edwinacubillos.multimediaapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tomar_fotos.view.*

/**
 * A simple [Fragment] subclass.
 */
class TomarFotosFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tomar_fotos, container, false)

        rootView.bt_tomar_foto.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{takePictureIntent ->
                takePictureIntent.resolveActivity(requireActivity().packageManager)?.also{
                    startActivityForResult(takePictureIntent, 1234)
                }
            }
        }

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1234 && resultCode==RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            rootView.iv_foto.setImageBitmap(imageBitmap)
        }
    }



}
