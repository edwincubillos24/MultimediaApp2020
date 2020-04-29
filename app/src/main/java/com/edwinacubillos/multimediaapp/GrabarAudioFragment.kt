package com.edwinacubillos.multimediaapp

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_grabar_audio.view.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class GrabarAudioFragment : Fragment() {

    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var fileName = ""
    private var permissionToRecordAccept = false
    private var permission: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccept = if (requestCode == 200) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccept) activity?.finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grabar_audio, container, false)

        ActivityCompat.requestPermissions(requireActivity(), permission,200)

        fileName = "${activity?.externalCacheDir?.absolutePath}/audio.3gp"
        Log.d("path", fileName)

        view.bt_grabar.setOnClickListener {
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(fileName)
                try {
                    prepare()
                } catch (e: IOException) {
                    Log.e("error", "prepare() failed")
                }
                start()
            }
            view.bt_grabar.isEnabled = false
            view.bt_stop.isEnabled = true
            view.bt_play.isEnabled = false
        }

        view.bt_stop.setOnClickListener {
            mediaRecorder.stop()
            mediaRecorder.release()
            view.bt_grabar.isEnabled = true
            view.bt_stop.isEnabled = false
            view.bt_play.isEnabled = true
        }

        view.bt_play.setOnClickListener {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(fileName)
                    prepare()
                    start()
                } catch (e: IOException) {
                    Log.e("Error", "prepare() failed")
                }
            }
            view.bt_grabar.isEnabled = false
            view.bt_stop.isEnabled = false
            view.bt_play.isEnabled = false

            mediaPlayer.setOnCompletionListener {
                view.bt_grabar.isEnabled = true
                view.bt_stop.isEnabled = true
                view.bt_play.isEnabled = true
            }
        }
        return view
    }
}
