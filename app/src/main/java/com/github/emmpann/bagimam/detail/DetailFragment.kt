package com.github.emmpann.bagimam.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentDetailBinding
import com.github.emmpann.core.domain.model.Donatur
import com.github.emmpann.core.utils.Event
import com.github.emmpann.core.utils.UIHelper.alertBuilder
import com.github.emmpann.core.utils.UIHelper.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvJob.text = DetailFragmentArgs.fromBundle(arguments as Bundle).name

        binding.btnOrder.setOnClickListener {
            val donatur =
                Donatur("1", "tes@dev.com", binding.etJenismakanan.text.toString())

            viewModel.sendDonate(donatur)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            listenStatus(it)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun listenStatus(event: Event<Boolean>) {
        if (!event.hasBeenHandled) {
            if (event.peekContent()) {
                alertBuilder(
                    requireContext(),
                    "Berhasil",
                    "Tim BagiMam akan datang untuk mengambil paket donasinya"
                ).setPositiveButton("Ok") { dialog, which ->

                }.show()

                with(binding) {
                    etMessage.text.clear()
                    etJenismakanan.text.clear()
                    etJumlah.text.clear()
                }

            } else {
                showSnackbar(requireView(), "Terjadi kesalaham, coba lagi")
            }
        }
    }
}