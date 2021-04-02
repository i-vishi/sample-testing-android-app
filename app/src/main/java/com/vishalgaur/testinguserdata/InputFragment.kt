package com.vishalgaur.testinguserdata

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vishalgaur.testinguserdata.databinding.FragmentInputBinding
import com.vishalgaur.testinguserdata.viewModel.*

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

        binding.inputSubmitActBtn.setOnClickListener {
            onSubmit(WITH_ACT)
        }

        // remove error text by default
        binding.inputErrorTextView.visibility = View.GONE
    }

    private fun onSubmit(fragOrAct: String? = WITH_FRAG) {
        val name = binding.inputNameEditText.text.toString()
        val email = binding.inputEmailEditText.text.toString()
        val mob = binding.inputMobileEditText.text.toString()
        val bio = binding.inputBioEditText.text.toString()

        if (name.isEmpty() || email.isEmpty() || mob.isEmpty() || bio.isEmpty()) {
            binding.inputErrorTextView.visibility = View.VISIBLE
        } else {
            when (viewModel.submitData(name, email, mob, bio)) {
                "NO_ERROR" -> {
                    setEditTextsError()

                    when (fragOrAct) {
                        WITH_FRAG -> {
                            // launch detail fragment
                            findNavController().navigate(R.id.showDetailScreen)
                        }
                        WITH_ACT -> {
                            //launch detail activity
                            val intent = Intent(activity, DetailActivity::class.java)
                                .putExtra("userName", viewModel.userName.value)
                                .putExtra("userEmail", viewModel.userEmail.value)
                                .putExtra("userMob", viewModel.userPhone.value)
                                .putExtra("userBio", viewModel.userBio.value)
                            startActivity(intent)
                        }
                    }
                }
                "ERROR_EMAIL" -> setEditTextsError(emailError = EMAIL_ERROR)
                "ERROR_PHONE" -> setEditTextsError(mobError = MOB_ERROR)
                "ERROR_EMAIL_PHONE" -> setEditTextsError(EMAIL_ERROR, MOB_ERROR)
            }
        }
    }

    private fun setEditTextsError(emailError: String? = null, mobError: String? = null) {
        binding.inputEmailEditText.error = emailError
        binding.inputMobileEditText.error = mobError
    }
}