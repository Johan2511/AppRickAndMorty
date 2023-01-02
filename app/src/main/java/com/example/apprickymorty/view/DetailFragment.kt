package com.example.apprickymorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.apprickymorty.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private  val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.character

        binding.apply {
            txtId.text = character.id.toString()
            txtStatus.text = character.status
            Picasso.get().load(character.image).into(imgCharacterDetail)
            txtName.text = character.name
            txtSpecie.text = character.species
            txtGender.text = character.gender
            txtMEpisodes.text = character.episode.size.toString()
            txtOrigin.text = character.origin.name
            txtLocation.text = character.location.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}