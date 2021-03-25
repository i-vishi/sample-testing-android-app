package com.vishalgaur.testinguserdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
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

        if (name.isEmpty() || email.isEmpty() || mob.isEmpty() || bio.isEmpty()) {
            binding.inputErrorTextView.isVisible = true
        } else {
            when (viewModel.submitData(name, email, mob, bio)) {
				"ERROR" -> {
					binding.inputEmailEditText.error = null
					binding.inputMobileEditText.error = null
					findNavController().navigate(R.id.showDetailScreen)
				}
				"ERROR_PHONE" -> {
					binding.inputEmailEditText.error = null
					binding.inputMobileEditText.error = "Enter valid mobile number!"
				}
				"ERROR_EMAIL" -> {
					binding.inputEmailEditText.error = "Enter valid email address!"
					binding.inputMobileEditText.error = null
				}
				"ERROR_EMAIL_PHONE" -> {
					binding.inputMobileEditText.error = "Enter valid mobile number!"
					binding.inputEmailEditText.error = "Enter valid email address!"
				}
            }
        }
    }
}