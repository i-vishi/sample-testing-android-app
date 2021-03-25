package com.vishalgaur.testinguserdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vishalgaur.testinguserdata.databinding.FragmentDetailBinding
import com.vishalgaur.testinguserdata.viewModel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.detailName.text = viewModel.userName.value
        binding.detailEmail.text = viewModel.userEmail.value
        binding.detailMobile.text = viewModel.userPhone.value
        binding.detailBio.text = viewModel.userBio.value

        binding.detailGoBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}