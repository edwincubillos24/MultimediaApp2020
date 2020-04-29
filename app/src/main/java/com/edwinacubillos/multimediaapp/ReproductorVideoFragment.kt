package com.edwinacubillos.multimediaapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import kotlinx.android.synthetic.main.fragment_reproductor_video.*

/**
 * A simple [Fragment] subclass.
 */
class ReproductorVideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reproductor_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val video : Uri = Uri.parse("https://www.ebookfrenzy.com/android_book/movie.mp4")
        vv_video.setVideoURI(video)
        vv_video.setMediaController(MediaController(activity))
        vv_video.start()
        vv_video.requestFocus()
    }

}
