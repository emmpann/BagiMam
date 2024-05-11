package com.github.emmpann.bagimam.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentLoginBinding
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.domain.model.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        setupObserver()
    }

    private fun setOnClickListener() {
        binding.btnSignin.setOnClickListener {
            viewModel.signInWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setupObserver() {
        viewModel.signInResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Success -> {
                    showLoading(true)
                    showSuccessDialog(it.toString())
                    with(binding) {
                        etEmail.text?.clear()
                        etPassword.text?.clear()
                    }
                    viewModel.setSession(User("", "", 0, "", "token-1"))
//                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

                is Response.Failure -> {
                    showLoading(true)
                    showErrorDialog(it.e.message.toString())
                }

                is Response.Loading -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) View.GONE else View.VISIBLE
//        binding.btnSignup.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showSuccessDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Berhasil Login")
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