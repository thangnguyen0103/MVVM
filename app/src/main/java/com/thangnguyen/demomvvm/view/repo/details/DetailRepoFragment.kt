package com.thangnguyen.demomvvm.view.repo.details

import androidx.navigation.fragment.navArgs
import com.thangnguyen.demomvvm.R
import com.thangnguyen.demomvvm.databinding.FragmentDetailRepoBinding
import com.thangnguyen.demomvvm.util.ext.bind
import com.thangnguyen.demomvvm.view.base.BaseFragment
import com.thangnguyen.demomvvm.viewmodel.DetailsRepoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


@ExperimentalCoroutinesApi
class DetailRepoFragment : BaseFragment<FragmentDetailRepoBinding>() {

    private val args: DetailRepoFragmentArgs by navArgs()

    private val viewModel: DetailsRepoViewModel by viewModel {
        parametersOf(args.user, args.repo)
    }


    override fun layoutRsc() = R.layout.fragment_detail_repo

    override fun viewReady() {
        bind(viewModel.isLoading) {
            binding.isLoading = it
        }
        bind(viewModel.isFail) {
            binding.isFail = it
        }
        bind(viewModel.data) {
            binding.repo = it
        }

        binding.error.setOnClickRetryButton {
            viewModel.retry()
        }
    }
}