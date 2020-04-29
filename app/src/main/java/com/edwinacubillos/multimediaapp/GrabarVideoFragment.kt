package com.edwinacubillos.multimediaapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import kotlinx.android.synthetic.main.fragment_grabar_video.view.*

/**
 * A simple [Fragment] subclass.
 */
class GrabarVideoFragment : Fragment() {

    private lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_grabar_video, container, false)

        rootView.bt_grabar.setOnClickListener {
            Intent(MediaStore.ACTION_VIDEO_CAPTURE).also{ takeVideoIntent ->
                takeVideoIntent.resolveActivity(requireActivity().packageManager)?.also{
                    startActivityForResult(takeVideoIntent, 1234)
                }
            }
        }
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK){
            val videoUri : Uri? = data?.data
            rootView.vv_video.setVideoURI(videoUri)
            rootView.vv_video.setMediaController(MediaController(activity))
            rootView.vv_video.start()
        }

    }

}
