package com.edwinacubillos.multimediaapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_reproductor_audio.view.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class ReproductorAudioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reproductor_audio, container, false)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(activity,R.raw.duhast)

        view.bt_play.setOnClickListener {
            mediaPlayer.start()
        }

        view.bt_pause.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.pause()
            else
                mediaPlayer.start()
        }

        view.bt_stop.setOnClickListener{
            mediaPlayer.stop()
            try{
                mediaPlayer.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return view
    }
}
