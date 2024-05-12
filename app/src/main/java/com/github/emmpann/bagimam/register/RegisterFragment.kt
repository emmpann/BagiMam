package com.github.emmpann.bagimam.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentRegisterBinding
import com.github.emmpann.core.domain.model.Response
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnSignup.setOnClickListener {
            viewModel.signUpWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun setupObserver() {
        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Success -> {
                    showLoading(false)
                    showSuccessDialog(it.toString())
                    with(binding) {
                        etEmail.text?.clear()
                        etPassword.text?.clear()
                        etConfirmPassword.text?.clear()
                    }
                }

                is Response.Failure -> {
                    showLoading(false)
                    showErrorDialog(it.message)
                }

                is Response.Loading -> {
                    showLoading(true)
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showSuccessDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Berhasil Daftar")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, which ->

            }.show()
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Terjadi Kesalahan!")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, which ->

            }.show()
    }
}