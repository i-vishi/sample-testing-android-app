package com.vishalgaur.testinguserdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vishalgaur.testinguserdata.databinding.FragmentInputBinding
import com.vishalgaur.testinguserdata.viewModel.DetailViewModel

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
	private val viewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        return binding.root
    }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.inputSubmitBtn.setOnClickListener {
			onSubmit()
		}

		// remove error text by default
		binding.inputErrorTextView.isVisible = false
	}

	private fun onSubmit() {
		val name = binding.inputNameEditText.text.toString()
		val email = binding.inputEmailEditText.text.toString()
		val mob = binding.inputMobileEditText.text.toString()
		val bio = binding.inputBioEditText.text.toString()

		if(name.isEmpty() || email.isEmpty() || mob.isEmpty() || bio.isEmpty()) {
			binding.inputErrorTextView.isVisible = true
		}else {
			viewModel.submitData(name, email, mob, bio)
			findNavController().navigate(R.id.showDetailScreen)
		}
	}
}