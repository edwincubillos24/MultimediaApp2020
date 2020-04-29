package com.edwinacubillos.multimediaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.multimediaapp.model.Funcion
import kotlinx.android.synthetic.main.fragment_lista_multimedia.*
import kotlinx.android.synthetic.main.fragment_lista_multimedia.view.*

/**
 * A simple [Fragment] subclass.
 */
class ListaMultimediaFragment : Fragment(), MultimediaRVAdapter.OnItemClickListener {

    private lateinit var funcionList: MutableList<Funcion>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_multimedia, container, false)

        loadList()

        view.rv_multimedia.setHasFixedSize(true)
        view.rv_multimedia.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.rv_multimedia.adapter = MultimediaRVAdapter(funcionList as ArrayList, this)

        return view
    }

    override fun onItemClick(funcion: Funcion) {
        when (funcion.pos){
            1 -> findNavController().navigate(R.id.action_listaMultimediaFragment_to_reproductorVideoFragment)
            2 -> findNavController().navigate(R.id.action_listaMultimediaFragment_to_reproductorAudioFragment)
            3 -> findNavController().navigate(R.id.action_listaMultimediaFragment_to_tomarFotosFragment)
            4 -> findNavController().navigate(R.id.action_listaMultimediaFragment_to_grabarVideoFragment)
            else -> findNavController().navigate(R.id.action_listaMultimediaFragment_to_grabarAudioFragment)
        }
    }

    fun loadList() {
        funcionList = ArrayList()
        funcionList.add(
            Funcion(
                1,
                R.drawable.ic_movie_black_24dp,
                "Reproducir video"
            )
        )
        funcionList.add(
            Funcion(
                2,
                R.drawable.ic_audiotrack_black_24dp,
                "Reproducir audio"
            )
        )
        funcionList.add(
            Funcion(
                3,
                R.drawable.ic_camera_alt_black_24dp,
                "Tomar Fotos"
            )
        )
        funcionList.add(
            Funcion(
                4,
                R.drawable.ic_videocam_black_24dp,
                "Grabar video"
            )
        )
        funcionList.add(
            Funcion(
                5,
                R.drawable.ic_mic_black_24dp,
                "Grabar audio"
            )
        )
    }
}
