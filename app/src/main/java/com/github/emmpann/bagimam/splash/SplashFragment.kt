package com.github.emmpann.bagimam.splash


//@Suppress("DEPRECATION")
//class SplashFragment : Fragment() {
//    private val splashViewModel: SplashViewModel by viewModels {
//        ViewModelFactory.getInstance(requireContext())
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        Handler().postDelayed({
//
//            when (findNavController().currentDestination?.id) {
//                R.id.homeWorkerActivity -> {
//                    findNavController().navigate(R.id.action_splashFragment_to_homeWorkerActivity)
//                }
//
//                R.id.homeActivity -> {
//                    findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//                }
//
//                else -> {
//                    if (onBoardingFinished()) {
//                        splashViewModel.getSessionData().observe(viewLifecycleOwner) { user ->
//                            if (user.role == "worker") {
//                                findNavController().navigate(R.id.action_splashFragment_to_homeWorkerActivity)
//                            } else {
//                                findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//                            }
//                        }
//                    } else {
//                        findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
//                    }
//                }
//            }
//        }, 3000)
//
//        return inflater.inflate(R.layout.fragment_splash, container, false)
//    }
//
//    private fun onBoardingFinished(): Boolean {
//        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        return sharedPref.getBoolean("Finished", false)
//    }
//
//}