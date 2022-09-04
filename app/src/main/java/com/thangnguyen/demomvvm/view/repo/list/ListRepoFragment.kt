package com.thangnguyen.demomvvm.view.repo.list

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.thangnguyen.demomvvm.R
import com.thangnguyen.demomvvm.data.db.entity.Repo
import com.thangnguyen.demomvvm.databinding.FragmentListRepoBinding
import com.thangnguyen.demomvvm.util.ext.bind
import com.thangnguyen.demomvvm.util.ext.textChanges
import com.thangnguyen.demomvvm.view.base.BaseFragment
import com.thangnguyen.demomvvm.viewmodel.ListRepoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
class ListRepoFragment : BaseFragment<FragmentListRepoBinding>() {

    private val viewModel: ListRepoViewModel by viewModel()

    private lateinit var adapter: RepoAdapter

    override fun layoutRsc() = R.layout.fragment_list_repo

    override fun viewReady() {
        setupMenu()
        adapter = RepoAdapter(this::showDetail)
        binding.rvRepo.adapter = adapter
        binding.rvRepo.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        observe()
    }

    private fun showDetail(repo: Repo) {
        val action = ListRepoFragmentDirections.actionListRepoFragmentToDetailRepoFragment(
            repo.user.login,
            repo.name
        )
        findNavController().navigate(action)
    }

    private fun observe() {
        bind(viewModel.repoFlow) {
            adapter.submitData(it)
        }
    }

    private fun setupMenu() {
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                // reduce requests to server
                searchView.textChanges()
                    .filterNot { it.isBlank() }
                    .debounce(300)
                    .distinctUntilChanged()
                    .onEach {
                        viewModel.setUserName(it)
                    }.launchIn(viewLifecycleOwner.lifecycleScope)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}