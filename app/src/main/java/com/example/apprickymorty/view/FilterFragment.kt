package com.example.apprickymorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apprickymorty.R
import com.example.apprickymorty.databinding.FragmentFilterBinding
import com.example.apprickymorty.extensions.getTextButtonChecked
import com.example.apprickymorty.extensions.getTextChipChecked
import com.example.apprickymorty.extensions.setButtonChecked
import com.example.apprickymorty.extensions.setChipChecked
import com.example.apprickymorty.repository.Repository
import com.example.apprickymorty.viewmodel.SharedViewModel
import com.example.apprickymorty.viewmodel.SharedViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class filterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(
        Repository()
    ) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFilterBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
        sharedViewModel.filterValue.observe(viewLifecycleOwner, {item ->
            chipgroupStatus.setChipChecked(item[0])
            radioGroupGender.setButtonChecked(item[1])
        })

            clearRadioCheck.setOnClickListener { radioGroupGender.clearCheck() }

        btnApplyFilter.setOnClickListener {

            if (chipgroupStatus.getTextChipChecked().isNotEmpty() &&
                radioGroupGender.getTextButtonChecked().isNotEmpty()){
                sharedViewModel.getByStatusAndGender(chipgroupStatus.getTextChipChecked(),
                    radioGroupGender.getTextButtonChecked(), 1)
            }else{
                if(chipgroupStatus.getTextChipChecked().isNotEmpty()){
                    sharedViewModel.getByStatus(chipgroupStatus.getTextChipChecked(),1)
                }else{
                    sharedViewModel.getByGender(radioGroupGender.getTextButtonChecked(),1)
                }
            }

            sharedViewModel.filterValue.value= arrayOf(chipgroupStatus.checkedChipId,
                radioGroupGender.checkedRadioButtonId)

            findNavController().popBackStack(R.id.listFragment,false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}